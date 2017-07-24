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
    public String getHello() {
        return "Hello World";
    }

    public static void main(String[] args) {
        //        System.setProperty("zookeeper.connect","localhost:2181");
        //        System.setProperty("zookeeper.connect","10.201.80.193:2181");
        if (null == System.getProperty("zookeeper.connect")) {
            System.err.println("require JVM parameter zookeeper.connect : ");
            System.out.println("usage: java -Dzookeeper.connect=xxx");
            System.exit(-1);
        }
        if (null == System.getProperty("node")) {
            System.setProperty("node", "/");
        }
        SpringApplication.run(App.class, args);

    }
}
