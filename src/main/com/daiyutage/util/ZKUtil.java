package com.daiyutage.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.zookeeper.data.Stat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/23.
 */
public class ZKUtil {

    private static Map<String,Object> zkNodeData = new HashMap<>();
    public static void createNode(String node, String data) throws Exception {
        try {
            ZKConnection.getZKClient().create().creatingParentsIfNeeded().forPath(node, data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public static String getNode(String node) throws Exception {
        try {
            byte[] bytes = ZKConnection.getZKClient().getData().forPath(node);
            return new String(bytes);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());

        }
    }

    /**
     *  获得Node节点的stat信息
     * @param node
     * @param stat
     * @return
     * @throws Exception
     */
    public static String getNode(String node,Stat stat)throws Exception{
        try {
            byte[] bytes = ZKConnection.getZKClient().getData().storingStatIn(stat).forPath(node);
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());

        }
    }


    /**
     * 获得所有节点(包括子节点)
     * @param node
     * @return
     * @throws Exception
     */
    public static Map getAllNodes(String node) throws Exception {
        try {
            Map map = new HashMap<>();
            List<String> strings = ZKConnection.getZKClient().getChildren().forPath(node);
            for (String s : strings) {
                if(!node.endsWith("/")){
                    node+="/";
                }
                String nodeStr = node + s;
                System.out.println(node + s + "-->" + getNode(node + s));
                map.put(s, getAllNodes(nodeStr));
                Map<String,Object> nodeInfo = new HashMap<>();
                Stat stat = new Stat();
                nodeInfo.put("data",getNode(node + s,stat));
                nodeInfo.put("czxid",stat.getCzxid());
                nodeInfo.put("ctime",stat.getCtime());
                nodeInfo.put("mzxid",stat.getMzxid());
                nodeInfo.put("mtime",stat.getMtime());
                nodeInfo.put("pzxid",stat.getPzxid());
                zkNodeData.put(s,nodeInfo);
                getAllNodes(node + s);
            }
            System.out.println("map:"+map);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

    }

    /**
     * 将Map转换为ZsTree的Json格式
     * @param map
     * @return
     */
    public static List<Map<String, Object>> getZsTreeJson(Map<String, Object> map) {
        List<Map<String, Object>> zsTreeList = new ArrayList<>();
        for (Map.Entry<String, Object> m : map.entrySet()) {
            Map<String, Object> node = new HashMap<>();
            node.put("name", m.getKey());
            System.out.println(JSONObject.toJSONString(zkNodeData.get(m.getKey())));
            String nodeInfoJson = JSONObject.toJSONString(zkNodeData.get(m.getKey()));
            //将Json value的双引号替换为@符号，以免前端zsTree中的click传递参数报错
            String nodeInfoStr =  nodeInfoJson.replace("\"","@");
            node.put("click","alertNodeInfo('"+ nodeInfoStr + "')");
            Map<String, Object> value = (Map<String, Object>) m.getValue();
            if (value.size() == 0) {
                zsTreeList.add(node);
            } else {
                node.put("open", true);
                node.put("children", getZsTreeJson(value));
                zsTreeList.add(node);
            }
        }
        return zsTreeList;
    }
}
