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
public class phone_price {
    //创建hdfs访问接口对象
    IHDFSDao ihdfsDao = new HDFSDaoImpl();

    @RequestMapping("/phone_price")
    public String toPhone_price(Model model){
        try {
            //读取分析计算出的数据
            Map<String, Object> result = ihdfsDao.readFile("/data/phone_price/output/part-r-00000");
            System.out.println(result);
            //封装模型数据
            model.addAttribute("p1000_2000",result.get("1000-2000"));

            model.addAttribute("p2000_3000",result.get("2000-3000"));

            model.addAttribute("p3000_4000",result.get("3000-4000"));

            model.addAttribute("p4000_5000",result.get("4000-5000"));
            model.addAttribute("p5000",result.get("5000+"));
            //返回展示页面的路径
            return "phone_price.html";
        }catch (Exception e){
            //返回错误页面的路径
            return "error-404";
        }

    }
}
