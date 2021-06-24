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
public class communication {
    //创建hdfs访问接口对象
    IHDFSDao ihdfsDao = new HDFSDaoImpl();

    @RequestMapping("/communication")
    public String toCommunication(Model model)  {
        try {
            //读取分析计算出的数据

            Map<String, Object> result2018 = ihdfsDao.readFile("/data/communication2018/output/part-r-00000");

            Map<String, Object> result2019 = ihdfsDao.readFile("/data/communication2019/output/part-r-00000");

            System.out.println(result2018);
            System.out.println(result2019);
            //封装模型数据
            model.addAttribute("QQ2018",result2018.get("QQ"));
            model.addAttribute("wechat2018",result2018.get("微信"));
            model.addAttribute("weibo2018",result2018.get("微博"));
            model.addAttribute("weboguoji2018",result2018.get("微博国际版"));
            model.addAttribute("baidutieba2018",result2018.get("百度贴吧"));


            model.addAttribute("QQ2019",result2019.get("QQ"));
            model.addAttribute("wechat2019",result2019.get("微信"));
            model.addAttribute("weibo2019",result2019.get("微博"));
            model.addAttribute("weboguoji2019",result2019.get("微博国际版"));
            model.addAttribute("baidutieba2019",result2019.get("百度贴吧"));

            //返回展示页面的路径
            return "communication.html";
        }catch (Exception e){
            //返回错误页面的路径
            return "error-404";
        }

    }
}
