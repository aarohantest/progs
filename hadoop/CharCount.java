import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CharCount {



public static class CharMap extends Mapper<LongWritable, Text, Text, IntWritable> {
		public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
			String line = value.toString();
			char[] carr = line.toCharArray();
			for (char c : carr) {
				System.out.println(c);
				context.write(new Text(String.valueOf(c)), new IntWritable(1));
			}
		}
}

public static class CharReduce extends Reducer<Text, IntWritable, Text, IntWritable> {
	public void reduce(Text key,Iterable<IntWritable> values,Context
		context)throws IOException,InterruptedException{
		int count = 0;
		IntWritable result = new IntWritable();
		for (IntWritable val : values) {
			count +=val.get();
			result.set(count);
		}
		context.write(key, result);
	}
}


public static void main(String[] args) throws Exception {
	Configuration conf = new Configuration();
	@SuppressWarnings("deprecation")
	Job job = new Job(conf, "Charcount");
	job.setJarByClass(CharCnt.class);
	
	job.setMapperClass(CharMap.class);
	job.setReducerClass(CharReduce.class);

	job.setInputFormatClass(TextInputFormat.class);
	job.setOutputFormatClass(TextOutputFormat.class);

	job.setMapOutputKeyClass(Text.class);
	job.setMapOutputValueClass(IntWritable.class);

	job.setOutputKeyClass(Text.class);
	job.setOutputValueClass(IntWritable.class);

	FileInputFormat.addInputPath(job, new Path(args[0]));
	FileOutputFormat.setOutputPath(job, new Path(args[1]));

	System.exit(job.waitForCompletion(true) ?0 : 1);
	}
}
