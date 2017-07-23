package com.daiyutage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/23.
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(Map<String,Object> map){
        map.put("hello","from IndexController.helloHtml");
        return "index";
    }
}
