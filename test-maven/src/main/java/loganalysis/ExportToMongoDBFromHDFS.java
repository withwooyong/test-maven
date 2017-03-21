package loganalysis;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.hadoop.MongoOutputFormat;
import com.mongodb.hadoop.util.MongoConfigUtil;

public class ExportToMongoDBFromHDFS {

	private static Logger log = LoggerFactory.getLogger(ExportToMongoDBFromHDFS.class);

	public static void main(String[] args) throws Exception {
		
		Configuration conf = new Configuration();
		
		try {			
			MongoConfigUtil.setOutputURI(conf,
					"mongodb://logs2:logs2013@119.149.188.229:25017,119.149.188.229:25018,119.149.188.230:25017,119.149.188.230:25018/im_logs.apilog");

			// MongoConfigUtil.setCreateInputSplits(conf, false);
			log.debug("Configuration: {}", conf);

			Job job = new Job(conf, "Export to Mongo");

			Path in = new Path(args[0]);
			FileInputFormat.setInputPaths(job, in);

			job.setJarByClass(ExportToMongoDBFromHDFS.class);
			job.setReducerClass(IntSumReducer.class);

			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);

			job.setInputFormatClass(TextInputFormat.class);
			job.setOutputFormatClass(MongoOutputFormat.class);

			System.exit(job.waitForCompletion(true) ? 0 : 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
