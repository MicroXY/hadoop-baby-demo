package com.micro.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * HDFS数据访问接口
 * @version 1.0.0
 * @AUTOR microlit
 * @DATA 2021/3/16
 **/
public interface IHDFSDao {
    // 服务器地址
    public static final String url = "hdfs://hadoop101:9000";
    // 用户名
    public static final String user = "micro";
    //配置文件
    public static final Configuration configuration = new Configuration();

    /**
     *
     * 文件上传
     * @param sourceUris 上传文件路径
     * @param targetUri  文件数据存放路径
     * @throw Exception
     * */
    public void uploadFile(String sourceUri, String targetUri, MultipartFile file) throws Exception;


    /***
     *
     * 读取文件
     * @param sourceUris 读取文件路径
     * @return
     * @throw Exception
     */
    public Map<String,Object> readFile(String sourceUri) throws Exception;


    /***
     *
     * 下载文件
     * @param sourceUris 欲上传文件路径
     * @param targetUri  目的文件路径
     * @throw Exception
     */
    public void DownloadFile(String sourceUris, String targetUri) throws Exception;

    /***
     * 删除文件
     * @param delsourceUris 删除文件路径
     * @return
     * @throw Exception
     */
    public void DeleteFile(String delsourceUris) throws  Exception;

}
