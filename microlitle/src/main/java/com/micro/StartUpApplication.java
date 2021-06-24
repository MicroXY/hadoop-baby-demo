package com.micro;

import com.micro.mr.RunMapReduce;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @version 1.0.0
 * @AUTOR microlit
 * @DATA 2021/3/18
 **/
@SpringBootApplication
public class StartUpApplication {
    public static void main(String[] args) throws Exception{

        RunMapReduce.run();
        SpringApplication.run(StartUpApplication.class,args);
    }
}
