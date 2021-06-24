package com.micro.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
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
public class LifeDriver {
    /**
     *Maper类
     *单词分割映射类
     */
    public static class LifeMapper extends Mapper<LongWritable, Text,Text, FloatWritable> {

        @Override
        public void map(LongWritable key, Text value,
                        Context context) throws IOException, InterruptedException {
            //            分别统计旅游app与生活服务app人均使用时长
            String line=  value.toString();
            //分割字符串
            String[] filed = line.split("\t+");

            if ( filed != null && filed.length>0){

                if (filed[8].equals("旅游") || filed[8].equals("生活服务") )
                {
                    String app_name =filed[6];
                    String  time=filed[7];
                    //输出数据
                    context.write(new Text(app_name),new FloatWritable(Float.parseFloat(time)));
                }

            }


        }
    }

    /**
     * reduce处理类
     * 汇总数据
     */
    public static class LifeReduce extends Reducer<Text,FloatWritable,Text, FloatWritable> {

        @Override
        public  void reduce(Text key, Iterable<FloatWritable> values,
                            Context context) throws IOException, InterruptedException {
            //            分别统计旅游app与生活服务app人均使用时长
            float count_base=0.0F;
            float count_temp=0.0F;
            for (FloatWritable i:values){
                count_base+=1.0F;
                count_temp+=i.get();
            }
            context.write(key,new FloatWritable(count_temp/count_base));


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
        job.setJarByClass(LifeDriver.class);
        //指定Mapper,Reducer业务处理类
        job.setMapperClass(LifeMapper.class);
        job.setReducerClass(LifeReduce.class);
        //指定Mapper输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FloatWritable.class);
        //最终输出类型（Reducer）
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FloatWritable.class);
        //指定输入输入文件目录
        FileInputFormat.setInputPaths(job,new Path("hdfs://hadoop101:9000/data/userimgs/input/123.txt"));
        FileOutputFormat.setOutputPath(job,new Path("hdfs://hadoop101:9000/data/life/output"));

        //等待作业执行完成
        boolean result = job.waitForCompletion(true);
        System.out.println("Finish...");
    }
}
