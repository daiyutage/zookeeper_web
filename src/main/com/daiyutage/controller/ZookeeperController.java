package com.daiyutage.controller;

import com.alibaba.fastjson.JSONObject;
import com.daiyutage.bean.HttpResponse;
import com.daiyutage.util.ZKUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/23.
 */
@RestController
public class ZookeeperController {
    @RequestMapping("/zookeeper")
    public String index() {
        return "zookeeper";
    }

    @RequestMapping("/zookeeper/getAllNodes")
    public HttpResponse getAllZSTreeNode() {
        try {
            List<Map<String, Object>> zsTreeList = ZKUtil.getZsTreeJson(ZKUtil.getAllNodes("/"));
            return new HttpResponse(JSONObject.toJSONString(zsTreeList), true, 200);
        } catch (Exception e) {
            return new HttpResponse(e.getMessage(), false, 10001);
        }

    }
}