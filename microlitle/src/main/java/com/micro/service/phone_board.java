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
public class phone_board {
    //创建hdfs访问接口对象
    IHDFSDao ihdfsDao = new HDFSDaoImpl();


    @RequestMapping("/phone_board")
    public String toPhone_board(Model model){
        try {
            //读取分析计算出的数据
            Map<String, Object> result = ihdfsDao.readFile("/data/phone_board/output/part-r-00000");
            System.out.println(result);
            //封装模型数据
            model.addAttribute("huawei",result.get("华为"));

            model.addAttribute("vivo",result.get("VIVO"));

            model.addAttribute("oppo",result.get("OPPO"));

            model.addAttribute("apple",result.get("APPLE"));

            model.addAttribute("xiaomi",result.get("小米"));
            //返回展示页面的路径
            return "phone_board.html";
        }catch (Exception e){
            //返回错误页面的路径
            return "error-404";
        }

    }
}
