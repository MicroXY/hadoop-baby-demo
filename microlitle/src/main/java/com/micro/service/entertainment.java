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
public class entertainment {


    //创建hdfs访问接口对象
    IHDFSDao ihdfsDao = new HDFSDaoImpl();

    @RequestMapping("/entertainment")
    public String toEntertainment(Model model){
        try {
            //读取分析计算出的数据
            Map<String, Object> result = ihdfsDao.readFile("/data/entertainment/output/part-r-00000");
            System.out.println(result);
            //封装模型数据

            model.addAttribute("kugou",result.get("酷狗"));
            model.addAttribute("quanmingk",result.get("全民K歌"));
            model.addAttribute("douyin",result.get("抖音"));
            model.addAttribute("kuaishou",result.get("快手"));
            model.addAttribute("wangzherongyao",result.get("王者荣耀"));
            model.addAttribute("hepinjy",result.get("和平精英"));

            //返回展示页面的路径
            return "entertainment.html";
        }catch (Exception e){
            //返回错误页面的路径
            return "error-404";
        }

    }
}
