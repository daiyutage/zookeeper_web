package com.daiyutage.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/23.
 */
public class ZKUtil {

    public static void createNode(String node,String data) throws Exception{
        try {
            ZKConnection.getZKClient().create().creatingParentsIfNeeded().forPath(node,data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
    public static String getNode(String node) throws Exception{
        try{
            byte[] bytes = ZKConnection.getZKClient().getData().forPath(node);
            return new String(bytes);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());

        }
    }
    public static Map getAllNodes(String node)throws  Exception{
        try {
            Map map = new HashMap<>();
            List<String> strings = ZKConnection.getZKClient().getChildren().forPath(node);
            for(String s:strings){
                if(!node.equals("/")){
                    node = node+"/";
                }
                String nodeStr = node+s;
                System.out.println(node+s+":"+getNode(node+s));
                map.put(s,getAllNodes(nodeStr));
                getAllNodes(node+s);
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new  Exception(e.getMessage());
        }

    }
    public  static List<Map<String,Object>> getZsTreeJson(Map<String,Object> map){
        List<Map<String,Object>> zsTreeList = new ArrayList<>();
        for(Map.Entry<String,Object> m:map.entrySet()){
            Map<String,Object> node = new HashMap<>();
            node.put("name",m.getKey());
            Map<String,Object> value =(Map<String, Object>) m.getValue();
            if(value.size() == 0){
                zsTreeList.add(node);
            }else{
                node.put("open",true);
                node.put("children",getZsTreeJson(value));
                zsTreeList.add(node);
            }
        }
        return zsTreeList;
    }
}
