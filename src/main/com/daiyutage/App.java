package com.daiyutage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/7/23.
 */
@SpringBootApplication
@RestController
public class App {

    @RequestMapping("/")
    public String getHello(){
        return "Hello World";
    }
    public static void main(String[] args){
        System.setProperty("zookeeper.connect","localhost:2181");
        SpringApplication.run(App.class,args);

    }
}
