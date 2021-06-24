package com.micro.service;

import com.micro.hdfs.IHDFSDao;
import com.micro.hdfs.impl.HDFSDaoImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 转到地区
 * @version 1.0.0
 * @AUTOR microlit
 * @DATA 2021/3/18
 **/
@Controller
public class area {

    //创建hdfs访问接口对象
    IHDFSDao ihdfsDao = new HDFSDaoImpl();

    @RequestMapping("/area")
    public String toArea(Model model){
        try {
            //读取分析计算出的数据
            Map<String, Object> result = ihdfsDao.readFile("/data/area/output/part-r-00000");
            System.out.println(result);
            //封装模型数据
            model.addAttribute("yixian",result.get("一线"));
            model.addAttribute("erxian",result.get("二线"));
            model.addAttribute("sanxian",result.get("三线"));
            model.addAttribute("sixian",result.get("四线"));
            model.addAttribute("xinyixian",result.get("新一线"));
            model.addAttribute("wuxianyixia",result.get("五线及以下"));

            //返回展示页面的路径
            return "area.html";
        }catch (Exception e){
            //返回错误页面的路径
            return "error-404";
        }

    }
}
