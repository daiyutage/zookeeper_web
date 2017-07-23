package com.daiyutage.util;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;

/**
 * Created by Administrator on 2017/7/23.
 */
public class ZKConnection {
    private static CuratorFramework client = null;
    public static  CuratorFramework getZKClient(){
        try{
            if(null == client){
                client = CuratorFrameworkFactory.newClient(System.getProperty("zookeeper.connect"),new RetryNTimes(3,1000));
                client.start();
                return client;
            }else{
                return client;

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }
}
