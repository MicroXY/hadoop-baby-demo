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
public class learn {

    //创建hdfs访问接口对象
    IHDFSDao ihdfsDao = new HDFSDaoImpl();

    @RequestMapping("/learn")
    public String toLearn(Model model){
        try {
            //读取分析计算出的数据
            Map<String, Object> result2018 = ihdfsDao.readFile("/data/learn2018/output/part-r-00000");

            Map<String, Object> result2019 = ihdfsDao.readFile("/data/learn2019/output/part-r-00000");

            System.out.println(result2018);
            System.out.println(result2019);
            //封装模型数据
            model.addAttribute("chaojikcb2018",result2018.get("超级课程表"));
            model.addAttribute("baicizhan2018",result2018.get("百词斩"));
            model.addAttribute("zhidao2018",result2018.get("知到"));
            model.addAttribute("wangyiyoudao2018",result2018.get("网易有道词典"));

            model.addAttribute("chaojikcb2019",result2019.get("超级课程表"));
            model.addAttribute("baicizhan2019",result2019.get("百词斩"));
            model.addAttribute("zhidao2019",result2019.get("知到"));
            model.addAttribute("wangyiyoudao2019",result2019.get("网易有道词典"));
            //返回展示页面的路径
            return "learn.html";
        }catch (Exception e){
            //返回错误页面的路径
            return "error-404";
        }

    }
}
