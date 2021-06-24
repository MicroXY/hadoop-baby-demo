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
public class consumption {

    //创建hdfs访问接口对象
    IHDFSDao ihdfsDao = new HDFSDaoImpl();

    @RequestMapping("/consumption")
    public String toConsumption(Model model){
        try {
            //读取分析计算出的数据
            Map<String, Object> result2018 = ihdfsDao.readFile("/data/consumption2018/output/part-r-00000");

            Map<String, Object> result2019 = ihdfsDao.readFile("/data/consumption2019/output/part-r-00000");

            System.out.println(result2018);
            System.out.println(result2019);
            //封装模型数据
            model.addAttribute("taobao2018",result2018.get("手机淘宝"));
            model.addAttribute("jd2018",result2018.get("京东"));
            model.addAttribute("pdd2018",result2018.get("拼多多"));
            model.addAttribute("xianyu2018",result2018.get("闲鱼"));
            model.addAttribute("zhuanzhuan2018",result2018.get("转转"));

            model.addAttribute("taobao2019",result2019.get("手机淘宝"));
            model.addAttribute("jd2019",result2019.get("京东"));
            model.addAttribute("pdd2019",result2019.get("拼多多"));
            model.addAttribute("xianyu2019",result2019.get("闲鱼"));
            model.addAttribute("zhuanzhuan2019",result2019.get("转转"));


            //返回展示页面的路径
            return "consumption.html";
        }catch (Exception e){
            //返回错误页面的路径
            return "error-404";
        }

    }
}
