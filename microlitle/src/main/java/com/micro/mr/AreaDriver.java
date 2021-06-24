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
public class AreaDriver {
    /**
     *Maper类
     *单词分割映射类
     */
    public static class AreaMapper extends Mapper<LongWritable, Text,Text, IntWritable> {

        @Override
        public void map(LongWritable key, Text value,
                        Context context) throws IOException, InterruptedException {
//            设计算法统计不同城市大学生数量
            Text text = new Text();
            IntWritable intWritable = new IntWritable();
            String line = value.toString();
            String[] filed = line.split("\t+");
            //1：将一行的文本数据进行拆分
//            String[] split=value.toString().split("/t");

//            //2；遍历数组，组装K2和V2
//            for (String area :split) {
//                //3:将K2,V2写入上下文中
                text.set(filed[3]);
                intWritable.set(1);
                context.write(text,intWritable);

//            }
        }
    }

    /**
     * reduce处理类
     * 汇总数据
     */
    public static class AreaReduce extends Reducer<Text,IntWritable,Text, IntWritable> {

        @Override
        public  void reduce(Text key, Iterable<IntWritable> values,
                            Context context) throws IOException, InterruptedException {
//            设计算法统计不同城市大学生数量
            int count = 0;//统计value里数字相加的和
            //reduce:将新的K2V2转为K3V3并写入上下文中
            //1:遍历集合，数字相加，得到V3
            for (IntWritable value : values) {
                count += value.get();//循环一次，count+1，最终得到V3
            }
            //2.将V3写入上下文中
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
        job.setJarByClass(AreaDriver.class);
        //指定Mapper,Reducer业务处理类
        job.setMapperClass(AreaMapper.class);
        job.setReducerClass(AreaReduce.class);
        //指定Mapper输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //最终输出类型（Reducer）
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //指定输入输入文件目录
        FileInputFormat.setInputPaths(job,new Path("hdfs://hadoop101:9000/data/userimgs/input/123.txt"));

        FileOutputFormat.setOutputPath(job,new Path("hdfs://hadoop101:9000/data/area/output"));
        //等待作业执行完成
        boolean result = job.waitForCompletion(true);
        System.out.println("Finish...");
    }
}
