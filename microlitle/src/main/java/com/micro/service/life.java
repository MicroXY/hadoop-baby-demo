package com.micro.service;

import com.micro.hdfs.IHDFSDao;
import com.micro.hdfs.impl.HDFSDaoImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @version 1.0.0
 * @AUTOR microlit
 * @DATA 2021/3/18
 **/
@Controller
public class life {


    //创建hdfs访问接口对象
    IHDFSDao ihdfsDao = new HDFSDaoImpl();

    @RequestMapping("/life")
    public String toLife(Model model){
        try {
            //读取分析计算出的数据
            Map<String, Object> result = ihdfsDao.readFile("/data/life/output/part-r-00000");
            System.out.println(result);
            //封装模型数据
            model.addAttribute("aibiy",result.get("爱彼迎"));

            model.addAttribute("tujia",result.get("途家"));

            model.addAttribute("meituan",result.get("美团"));

            model.addAttribute("eleme",result.get("饿了么"));

            //返回展示页面的路径
            return "life.html";
        }catch (Exception e){
            //返回错误页面的路径
            return "error-404";
        }

    }
}
