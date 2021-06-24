package com.micro.service;

import com.micro.hdfs.IHDFSDao;
import com.micro.hdfs.impl.HDFSDaoImpl;
import com.micro.mr.RunMapReduce;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @version 1.0.0
 * @AUTOR microlit
 * @DATA 2021/3/19
 **/
@Controller
public class uploads {
    //创建hdfs访问接口对象
    IHDFSDao ihdfsDao = new HDFSDaoImpl();

    @RequestMapping("/uploads")
    public String toUP(@RequestParam("file") MultipartFile files)throws  Exception {
    System.out.println("sahndasohdaishfw");
//    try{
//        ihdfsDao.DeleteFile("/data/userimgs/input/123.txt");
//    }
//    catch (Exception e){
//        e.printStackTrace();
//    }

        ihdfsDao.uploadFile("/","/data/userimgs/input/",files);
        System.out.println("fdsffdsifdsoifdsfdsoi");
        RunMapReduce.run();
        return "form-upload.html";

    }
}
