package com.micro.mr;

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

import java.io.IOException;

/**
 * @version 1.0.0
 * @AUTOR microlit
 * @DATA 2021/3/18
 **/
public class IndexDriver {
    /**
     *Maper类
     *单词分割映射类
     */
    public static class IndexMapper extends Mapper<LongWritable, Text,Text, IntWritable> {

        @Override
        public void map(LongWritable key, Text value,
                        Context context) throws IOException, InterruptedException {
//            设计算法统计男女比例
            //单行数据
            String line = value.toString();
            String[] filed = line.split("\t+");

            if ( filed != null && filed.length>0 ) {

                String sex = filed[2];
                System.out.println(sex);
                context.write(new Text(sex), new IntWritable(1));
            }
        }
    }

    /**
     * reduce处理类
     * 汇总数据
     */
    public static class IndexReduce extends Reducer<Text,IntWritable,Text, IntWritable> {

        @Override
        public  void reduce(Text key, Iterable<IntWritable> values,
                            Context context) throws IOException, InterruptedException {
//            设计算法统计男女比例
            int count=0;
            for (IntWritable i:values){
                count++;
            }
            context.write(key,new IntWritable(count));
        }
    }

    /**
     * 执行作业接口
     */
    public void run() throws Exception {
        //创建配置信息对象
        Configuration configuration=new Configuration();
        //创建作业对象
        Job job = Job.getInstance(configuration);
        //指定作业类
        job.setJarByClass(IndexDriver.class);
        //指定Mapper,Reducer业务处理类
        job.setMapperClass(IndexMapper.class);
        job.setReducerClass(IndexReduce.class);
        //指定Mapper输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //最终输出类型（Reducer）
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //指定输入输入文件目录
        FileInputFormat.setInputPaths(job,new Path("hdfs://hadoop101:9000/data/userimgs/input/123.txt"));
        FileOutputFormat.setOutputPath(job,new Path("hdfs://hadoop101:9000/data/index/output"));

        //等待作业执行完成
        boolean result = job.waitForCompletion(true);
        System.out.println("Finish...");
    }
}
