package com.micro.service;

import com.micro.hdfs.IHDFSDao;
import com.micro.hdfs.impl.HDFSDaoImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 访问主页
 * @version 1.0.0
 * @AUTOR microlit
 * @DATA 2021/3/18
 **/
@Controller
public class index {
    //创建hdfs访问接口对象
    IHDFSDao ihdfsDao = new HDFSDaoImpl();

    @RequestMapping("/")
    public String toIndex(Model model)throws  Exception{
        try {
//            读取分析计算出的数据
            Map<String, Object> result = ihdfsDao.readFile("/data/index/output/part-r-00000");
            System.out.println(result);
            //封装模型数据
            model.addAttribute("man",result.get("男"));
            model.addAttribute("female",result.get("女"));
            //返回展示页面的路径
            return "index.html";
        }catch (Exception e){
//            //返回错误页面的路径
            return "error-404";
        }
    }
}
