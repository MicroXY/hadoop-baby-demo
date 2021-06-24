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
public class Phone_priceDriver {
    /**
     *Maper类
     *单词分割映射类
     */
    public static class Phone_priceMapper extends Mapper<LongWritable, Text,Text, IntWritable> {

        @Override
        public void map(LongWritable key, Text value,
                        Context context) throws IOException, InterruptedException {
//            设计算法统计个价格区间手机数量
            String line = value.toString();
            String[] filed = line.split("\t+");

            if ( filed != null && filed.length>0 ) {


                if (Integer.parseInt(filed[5])>=1000 && Integer.parseInt(filed[5])<=2000){
                    String key1 = "1000-2000";
                    context.write(new Text(key1), new IntWritable(1));
                }
                if (Integer.parseInt(filed[5])>2000 && Integer.parseInt(filed[5])<=3000){
                    String key2 = "2000-3000";
                    context.write(new Text(key2), new IntWritable(1));
                }
                if (Integer.parseInt(filed[5])>3000 && Integer.parseInt(filed[5])<=4000){
                    String key3 = "3000-4000";
                    context.write(new Text(key3), new IntWritable(1));
                }
                if (Integer.parseInt(filed[5])>4000 && Integer.parseInt(filed[5])<=5000){
                    String key4 = "4000-5000";
                    context.write(new Text(key4), new IntWritable(1));
                }
                if (Integer.parseInt(filed[5])>5000){
                    String key5 = "5000+";
                    context.write(new Text(key5), new IntWritable(1));
                }

            }
        }
    }

    /**
     * reduce处理类
     * 汇总数据
     */
    public static class Phone_priceReduce extends Reducer<Text,IntWritable,Text, IntWritable> {

        @Override
        public  void reduce(Text key, Iterable<IntWritable> values,
                            Context context) throws IOException, InterruptedException {
//            设计算法统计个价格区间手机数量
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
        job.setJarByClass(Phone_priceDriver.class);
        //指定Mapper,Reducer业务处理类
        job.setMapperClass(Phone_priceMapper.class);
        job.setReducerClass(Phone_priceReduce.class);
        //指定Mapper输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //最终输出类型（Reducer）
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //指定输入输入文件目录
        FileInputFormat.setInputPaths(job,new Path("hdfs://hadoop101:9000/data/userimgs/input/123.txt"));
        FileOutputFormat.setOutputPath(job,new Path("hdfs://hadoop101:9000/data/phone_price/output/"));

        //等待作业执行完成
        boolean result = job.waitForCompletion(true);
        System.out.println("Finish...");
    }
}
