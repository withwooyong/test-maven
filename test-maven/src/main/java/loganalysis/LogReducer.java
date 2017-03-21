package loganalysis;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class LogReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
	
	private LongWritable sumWritable = new LongWritable();

	public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException,
			InterruptedException {
		long sum = 0;
		for (LongWritable val : values) {
			sum += val.get();
		}
		sumWritable.set(sum);
		context.write(key, sumWritable);
	}
}
