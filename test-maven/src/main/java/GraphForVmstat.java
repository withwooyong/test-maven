import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http://jo.centis1504.net/?p=1715
 * http://linoxide.com/tools/vmstat-graphical-mode/
 * @author user
 *
 */
public class GraphForVmstat {
	
	private static Logger log = LoggerFactory.getLogger(GraphForVmstat.class);

	static String workDir;
	static List<Journal> alls = new ArrayList<Journal>();

	static int interval = 10; // second
	static SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/ddHH:mm:ss");

	static class Journal implements Serializable {

		private static final long serialVersionUID = -3906379676663036836L;

		private String timestampString;
		private Date timestamp;
		private int r;
		private int b;
		private int swpd;
		private int free;
		private int buff;
		private int cache;
		private int si;
		private int so;
		private int bi;
		private int bo;
		private int in;
		private int cs;
		private int us;
		private int sy;
		private int id;
		private int wa;
		private int st;

		public Journal(String timestamp) {
			try {
				this.timestamp = sdf.parse(timestamp);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			this.timestampString = timestamp;
		}

		public Journal(Date timestamp) {
			this.timestamp = timestamp;
			this.timestampString = sdf.format(timestamp);
		}
	}

	public static List<Journal> readFile(String file) throws Exception {
		long started = System.currentTimeMillis();
		log.debug("Open " + file);
		List<Journal> result = new ArrayList<Journal>();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String[] tokens = null;
		String line = null;
		Journal j = null;
		int row = 0;
		int journalCount = 0;
		long lastTimed = System.currentTimeMillis();
		Calendar timestamp = Calendar.getInstance();
		while ((line = br.readLine()) != null) {

			row++;
			if (row % 100000 == 0) {
				log.debug(row + " line(s) buffered. (memory " + (Runtime.getRuntime().freeMemory() * 100 / Runtime.getRuntime().maxMemory()) + "% used) " + (System.currentTimeMillis() - lastTimed) + "ms");
				lastTimed = System.currentTimeMillis();
			}
			if (line.trim().length() == 0)
				continue;

			line = line.trim();
			log.debug("line={}", line);
			if (line.contains("procs") || line.contains("swpd")) { // 1, 2 header line 라인 제거
				// header line
				continue;
			} else {
				if (line.length() < 3) {
					continue;
				}
				// valid line
				if (line.charAt(2) == '/') {
					// timestamp
					timestamp.setTime(sdf.parse(line.substring(0, 17)));
					line = line.substring(17).trim();
				}

				// data field
				List<String> buffers = new ArrayList<String>();
				StringBuffer outB = new StringBuffer();
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) != ' ') {
						outB.append(line.charAt(i));
					} else {
						if (outB.length() > 0) {
							buffers.add(outB.toString());
							outB = new StringBuffer();
						}
					}
				}
				if (outB.length() > 0) {
					buffers.add(outB.toString());
				}
				tokens = buffers.toArray(new String[] {});
				j = new Journal(timestamp.getTime());
				timestamp.add(Calendar.SECOND, interval);
				try {
					j.r = toInt(tokens[0]);
					j.b = toInt(tokens[1]);
					j.swpd = toInt(tokens[2]);
					j.free = toInt(tokens[3]);
					j.buff = toInt(tokens[4]);
					j.cache = toInt(tokens[5]);
					j.si = toInt(tokens[6]);
					j.so = toInt(tokens[7]);
					j.bi = toInt(tokens[8]);
					j.bo = toInt(tokens[9]);
					j.in = toInt(tokens[10]);
					j.cs = toInt(tokens[11]);
					j.us = toInt(tokens[12]);
					j.sy = toInt(tokens[13]);
					j.id = toInt(tokens[14]);
					j.wa = toInt(tokens[15]);
					j.st = toInt(tokens[16]);
					
					result.add(j);
					journalCount++;

				} catch (Exception e) {
					log.error("line " + row + "(tokens=" + tokens.length + ") : " + line);
					for (int i = 0; i < tokens.length; i++) {
						log.error("token[" + i + "]=[" + tokens[i] + "]");
					}
					e.printStackTrace();
					System.exit(1);
				}

			}
		}

		br.close();
		log.debug("completed. (" + (System.currentTimeMillis() - started) + "ms, " + row + "rows)");
		return result;
	}

	public static double toDouble(String v) {
		return Double.parseDouble(v);
	}
	
	public static int toInt(String v) {
		return Integer.parseInt(v);
	}

	public static List<String> getFiles(String path) {
		List<String> files = new ArrayList<String>();
		File f = new File(path);
		File f2 = null;
		for (String name : f.list()) {
			f2 = new File(path + "\\" + name);
			if (f2.isDirectory()) {
				files.addAll(getFiles(f2.getAbsolutePath()));
			} else {
				files.add(f2.getAbsolutePath());
			}
		}
		return files;
	}	

	static void makeGraph() throws Exception {

		CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new DateAxis("Time"));
		plot.setBackgroundPaint(Color.white);
		plot.setDomainGridlinePaint(Color.black);
		plot.setRangeGridlinePaint(Color.black);
		plot.setForegroundAlpha(0.5f);
		plot.setBackgroundAlpha(0.5f);

		TimeSeriesCollection dataset1 = new TimeSeriesCollection();
		dataset1.addSeries(new TimeSeries("Requested", Millisecond.class));
		dataset1.addSeries(new TimeSeries("Blocked", Millisecond.class));

		TimeSeriesCollection dataset2 = new TimeSeriesCollection();
		dataset2.addSeries(new TimeSeries("spwd", Millisecond.class));
		dataset2.addSeries(new TimeSeries("free", Millisecond.class));
		dataset2.addSeries(new TimeSeries("buff", Millisecond.class));
		dataset2.addSeries(new TimeSeries("cache", Millisecond.class));

		TimeSeriesCollection dataset3 = new TimeSeriesCollection();
		dataset3.addSeries(new TimeSeries("si", Millisecond.class));
		dataset3.addSeries(new TimeSeries("so", Millisecond.class));

		TimeSeriesCollection dataset4 = new TimeSeriesCollection();
		dataset4.addSeries(new TimeSeries("bi", Millisecond.class));
		dataset4.addSeries(new TimeSeries("bo", Millisecond.class));

		TimeSeriesCollection dataset5 = new TimeSeriesCollection();
		dataset5.addSeries(new TimeSeries("in", Millisecond.class));
		dataset5.addSeries(new TimeSeries("cs", Millisecond.class));

		TimeSeriesCollection dataset6 = new TimeSeriesCollection();
		dataset6.addSeries(new TimeSeries("us", Millisecond.class));
		dataset6.addSeries(new TimeSeries("sy", Millisecond.class));
		dataset6.addSeries(new TimeSeries("wa", Millisecond.class));
		dataset6.addSeries(new TimeSeries("st", Millisecond.class));

		NumberAxis axisY1 = new NumberAxis("Count");
		NumberAxis axisY2 = new NumberAxis("byte");
		NumberAxis axisY3 = new NumberAxis("Count");
		NumberAxis axisY4 = new NumberAxis("Count");
		NumberAxis axisY5 = new NumberAxis("Count");
		NumberAxis axisY6 = new NumberAxis("%");
		axisY6.setAutoRangeIncludesZero(false);
		axisY6.setRange(0, 100);

		XYPlot subplot1 = new XYPlot(dataset1, null, axisY1, new StandardXYItemRenderer());
		subplot1.setBackgroundPaint(Color.white);
		subplot1.setDomainGridlinePaint(Color.DARK_GRAY);
		subplot1.setRangeGridlinePaint(Color.DARK_GRAY);
		plot.add(subplot1);
		subplot1.getRenderer().setSeriesStroke(0, new java.awt.BasicStroke(2));
		subplot1.getRenderer().setSeriesStroke(1, new java.awt.BasicStroke(2));

		XYPlot subplot2 = new XYPlot(dataset2, null, axisY2, new StandardXYItemRenderer());
		subplot2.setBackgroundPaint(Color.white);
		subplot2.setDomainGridlinePaint(Color.DARK_GRAY);
		subplot2.setRangeGridlinePaint(Color.DARK_GRAY);
		plot.add(subplot2);
		subplot2.getRenderer().setSeriesStroke(0, new java.awt.BasicStroke(2));
		subplot2.getRenderer().setSeriesStroke(1, new java.awt.BasicStroke(2));
		subplot2.getRenderer().setSeriesStroke(2, new java.awt.BasicStroke(2));
		subplot2.getRenderer().setSeriesStroke(3, new java.awt.BasicStroke(2));

		XYPlot subplot3 = new XYPlot(dataset3, null, axisY3, new StandardXYItemRenderer());
		subplot3.setBackgroundPaint(Color.white);
		subplot3.setDomainGridlinePaint(Color.DARK_GRAY);
		subplot3.setRangeGridlinePaint(Color.DARK_GRAY);
		plot.add(subplot3);
		subplot3.getRenderer().setSeriesStroke(0, new java.awt.BasicStroke(2));
		subplot3.getRenderer().setSeriesStroke(1, new java.awt.BasicStroke(2));

		XYPlot subplot4 = new XYPlot(dataset4, null, axisY4, new StandardXYItemRenderer());
		subplot4.setBackgroundPaint(Color.white);
		subplot4.setDomainGridlinePaint(Color.DARK_GRAY);
		subplot4.setRangeGridlinePaint(Color.DARK_GRAY);
		plot.add(subplot4);
		subplot4.getRenderer().setSeriesStroke(0, new java.awt.BasicStroke(2));
		subplot4.getRenderer().setSeriesStroke(1, new java.awt.BasicStroke(2));

		XYPlot subplot5 = new XYPlot(dataset5, null, axisY5, new StandardXYItemRenderer());
		subplot5.setBackgroundPaint(Color.white);
		subplot5.setDomainGridlinePaint(Color.DARK_GRAY);
		subplot5.setRangeGridlinePaint(Color.DARK_GRAY);
		plot.add(subplot5);
		subplot5.getRenderer().setSeriesStroke(0, new java.awt.BasicStroke(2));
		subplot5.getRenderer().setSeriesStroke(1, new java.awt.BasicStroke(2));

		XYPlot subplot6 = new XYPlot(dataset6, null, axisY6, new StandardXYItemRenderer());
		subplot6.setBackgroundPaint(Color.white);
		subplot6.setDomainGridlinePaint(Color.DARK_GRAY);
		subplot6.setRangeGridlinePaint(Color.DARK_GRAY);
		plot.add(subplot6);
		subplot6.getRenderer().setSeriesStroke(0, new java.awt.BasicStroke(2));
		subplot6.getRenderer().setSeriesStroke(1, new java.awt.BasicStroke(2));
		subplot6.getRenderer().setSeriesStroke(2, new java.awt.BasicStroke(2));
		subplot6.getRenderer().setSeriesStroke(3, new java.awt.BasicStroke(2));

		for (Journal j : alls) {
			try {
				dataset1.getSeries(0).add(new Millisecond(j.timestamp), j.r);
				dataset1.getSeries(1).add(new Millisecond(j.timestamp), j.b);
			} catch (org.jfree.data.general.SeriesException duplicated) {
			}
			try {
				dataset2.getSeries(0).add(new Millisecond(j.timestamp), j.swpd);
				dataset2.getSeries(1).add(new Millisecond(j.timestamp), j.free);
				dataset2.getSeries(2).add(new Millisecond(j.timestamp), j.buff);
				dataset2.getSeries(3).add(new Millisecond(j.timestamp), j.cache);
			} catch (org.jfree.data.general.SeriesException duplicated) {
			}
			try {
				dataset3.getSeries(0).add(new Millisecond(j.timestamp), j.si);
				dataset3.getSeries(1).add(new Millisecond(j.timestamp), j.so);
			} catch (org.jfree.data.general.SeriesException duplicated) {
			}
			try {
				dataset4.getSeries(0).add(new Millisecond(j.timestamp), j.bi);
				dataset4.getSeries(1).add(new Millisecond(j.timestamp), j.bo);
			} catch (org.jfree.data.general.SeriesException duplicated) {
			}
			try {
				dataset5.getSeries(0).add(new Millisecond(j.timestamp), j.in);
				dataset5.getSeries(1).add(new Millisecond(j.timestamp), j.cs);
			} catch (org.jfree.data.general.SeriesException duplicated) {
			}
			try {
				dataset6.getSeries(0).add(new Millisecond(j.timestamp), j.us);
				dataset6.getSeries(1).add(new Millisecond(j.timestamp), j.sy);
				dataset6.getSeries(2).add(new Millisecond(j.timestamp), j.wa);
				dataset6.getSeries(3).add(new Millisecond(j.timestamp), j.st);
			} catch (org.jfree.data.general.SeriesException duplicated) {
			}
		}

		XYPlot[] subplots = new XYPlot[6];
		subplots[0] = subplot1;
		subplots[1] = subplot2;
		subplots[2] = subplot3;
		subplots[3] = subplot4;
		subplots[4] = subplot5;
		subplots[5] = subplot6;

		String[] titles = new String[] { "Requested/Blocked", "OS Memory", "Swap I/O", "Block I/O", "Interrupt/Context Switches", "CPU Usage" };
		String[] filenames = new String[] { "vmstat_rb", "vmstat_memory", "vmstat_swap", "vmstat_block", "vmstat_cs", "vmstat_cpu" };

		for (int i = 0; i < subplots.length; i++) {

			plot = new CombinedDomainXYPlot(new DateAxis("Time"));
			plot.setBackgroundPaint(Color.white);
			plot.setDomainGridlinePaint(Color.black);
			plot.setRangeGridlinePaint(Color.black);
			plot.setForegroundAlpha(0.5f);
			plot.setBackgroundAlpha(0.5f);
			plot.add(subplots[i]);

			// SAVE GRAPH
			JFreeChart chart = new JFreeChart(titles[i], plot);
			chart.setBorderPaint(Color.black);
			chart.setBorderVisible(true);
			chart.setBackgroundPaint(Color.white);
			chart.getTitle().setFont(new Font("Meiryo UI", 1, 22));

			// 凡例
			LegendTitle legend = chart.getLegend();
			legend.setBackgroundPaint(Color.white);
			legend.setMargin(10d, 10d, 10d, 10d);
			legend.setItemFont(new Font("Meiryo UI", 1, 12));

			// save
			File pngFile = new File(workDir + "\\" + filenames[i] + ".png");
			try {
				ChartUtilities.saveChartAsPNG(pngFile, chart, 600, 400);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {

		File file = new File("D:\\vmstat.log");
		workDir = file.getParentFile().getAbsolutePath() + "\\" + file.getName() + ".output";
		File outputDir = new File(workDir);
		if (!outputDir.exists()) {
			if (!outputDir.mkdirs()) {
				System.err.println(workDir + " could not created.");
				System.exit(1);
			}
		}
		List<String> files = new ArrayList<String>();
		if (file.isDirectory()) {
			files.addAll(getFiles(file.getAbsolutePath()));
		} else {
			files.add(file.getAbsolutePath());
		}

		for (String filename : files) {
			alls.addAll(readFile(filename));
		}

		// ////////////////////////////////////////////////////////////////////
		// Graph
		// ////////////////////////////////////////////////////////////////////
		ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
		makeGraph();
	}

}