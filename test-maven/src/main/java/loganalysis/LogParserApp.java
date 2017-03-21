package loganalysis;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogParserApp {

	private final static Logger log = LoggerFactory.getLogger(LogParserApp.class);

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
//		MongoConfigUtil.setOutputURI(conf,
//						"mongodb://logs2:logs2013@119.149.188.229:25017,119.149.188.229:25018,119.149.188.230:25017,119.149.188.230:25018/im_logs.apilog");

		Job job = new Job(conf, "LogParserApp");

		job.setJarByClass(LogParserApp.class);
		job.setMapperClass(LogMapper.class);
		job.setReducerClass(LogReducer.class);

		// if mapper outputs are different, call setMapOutputKeyClass and setMapOutputValueClass
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);

		// An InputFormat for plain text files. Files are broken into lines. Either linefeed or carriage-return are used
		// to signal end of line.
		// Keys are the position in the file, and values are the line of text..
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.waitForCompletion(true);
	}
}
