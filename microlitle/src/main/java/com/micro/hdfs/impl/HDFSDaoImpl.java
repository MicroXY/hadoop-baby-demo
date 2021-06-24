package com.micro.hdfs.impl;

import com.micro.hdfs.IHDFSDao;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * HDFS文件数据访问类
 * @version 1.0.0
 * @AUTOR microlit
 * @DATA 2021/3/16
 **/
public class HDFSDaoImpl implements IHDFSDao {

    /**
     *
     * 文件上传
     * @param sourceUri 上传文件路径
     * @param targetUri  文件数据存放路径
     * @throw Exception
     * */
    @Override
    public void uploadFile(String sourceUri, String targetUri, MultipartFile file) throws Exception{
        FileSystem fs = FileSystem.get(new URI(url),configuration,user);//获取文件系统实例
//        Path sourcePath = new Path(sourceUri);//源文件路径
//        Path targetPath = new Path(targetUri);//目的文件路径
//        fs.copyFromLocalFile(sourcePath,targetPath);//执行上传操作
//        file.getInputStream();
        DeleteFile(targetUri+"123.txt");
        String filename=file.getName();
        InputStream in =file.getInputStream();
        FSDataOutputStream out =fs.create(new Path(targetUri+"123.txt"));
        IOUtils.copyBytes(in,out,configuration);
    }
    /***
     * 读取文件
     * 读取mapreduce计算分析的结果
     * @param sourceUri 读取文件路径
     * @return
     * @throw Exception
     */
    @Override
    public Map<String,Object> readFile(String sourceUri) throws Exception{
        FileSystem fs = FileSystem.get(new URI(url),configuration,user);//获得文件系统实例
        Path sourcePath = new Path(sourceUri);//获取源
        BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(sourcePath)));
        //创建封装数据的图集合
        Map<String,Object> result = new HashMap<>();
        //循环提取封装数据
        String line;
        while((line = br.readLine()) != null){
            String[] kv = line.split("\t");
            result.put(kv[0],kv[1]);
        }

        fs.close();
        return  result;
    }
    /***
     *
     * 下载文件
     * @param sourceUris 欲上传文件路径
     * @param targetUri  目的文件路径
     * @throw Exception
     */
    @Override
    public void DownloadFile(String sourceUris,String targetUri) throws Exception {
        // 获得文件系统对象
        FileSystem fs =FileSystem.get(new URI(url),configuration,user);
        // 复制文件内容
        fs.copyToLocalFile(new Path(sourceUris),new Path(targetUri));
        fs.close();
    }

    /***
     * 删除文件
     * @param delsourceUris 删除文件路径
     * @return
     * @throw Exception
     */
    @Override
    public void DeleteFile(String delsourceUris) throws  Exception{
        FileSystem fs=FileSystem.get(new URI(url),configuration,user);
        Path path = new Path(delsourceUris);
        //递归删除文件
        fs.delete(path,true);
        fs.close();
    }

}
