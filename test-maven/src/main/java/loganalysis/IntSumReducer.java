package loganalysis;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	
	public void reduce(Text key, IntWritable values, Context context) throws IOException, InterruptedException {
		context.write(key, values);
	}
}
