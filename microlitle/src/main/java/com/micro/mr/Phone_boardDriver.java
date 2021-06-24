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
public class Phone_boardDriver {
    /**
     *Maper类
     *单词分割映射类
     */
    public static class Phone_boardMapper extends Mapper<LongWritable, Text,Text, IntWritable> {

        @Override
        public void map(LongWritable key, Text value,
                        Context context) throws IOException, InterruptedException {
//            设计算法统计各品牌手机大学生的持有量
            //获取单行数据
            String line = value.toString();
            //分割字符串
            String[] fileds = line.split("\t+");
            //判断是否存在字段
            if (fileds != null && fileds.length > 0) {
                //获得字符方式
                String gradeType = fileds[fileds.length - 5];
                //输出数据
                context.write(new Text(gradeType), new IntWritable(1));

            }
        }
    }

    /**
     * reduce处理类
     * 汇总数据
     */
    public static class Phone_boardReduce extends Reducer<Text,IntWritable,Text, IntWritable> {

        @Override
        public  void reduce(Text key, Iterable<IntWritable> values,
                            Context context) throws IOException, InterruptedException {
//            设计算法统计各品牌手机大学生的持有量
            //初始化，声明变量
            int count = 0;
            //迭代
            for (IntWritable value : values) {
                count += value.get();
            }
            //输出数据
            context.write(key, new IntWritable(count));
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
        job.setJarByClass(Phone_boardDriver.class);
        //指定Mapper,Reducer业务处理类
        job.setMapperClass(Phone_boardMapper.class);
        job.setReducerClass(Phone_boardReduce.class);
        //指定Mapper输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //最终输出类型（Reducer）
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //指定输入输入文件目录
        FileInputFormat.setInputPaths(job,new Path("hdfs://hadoop101:9000/data/userimgs/input/123.txt"));
        FileOutputFormat.setOutputPath(job,new Path("hdfs://hadoop101:9000/data/phone_board/output/"));

        //等待作业执行完成
        boolean result = job.waitForCompletion(true);
        System.out.println("Finish...");
    }
}
