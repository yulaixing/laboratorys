package cn.techwolf.rpc.nameserver;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author yl.xing
 * @create:2020-06-26
 * @describe
 **/
public class ZKUtils {

    public static String ip="47.104.211.207:2181";


    public static void main(String[] args) {

//        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000,3,5000);
//        CuratorFramework zkClient = CuratorFrameworkFactory.builder()
//                .connectString(ip)
//                .sessionTimeoutMs(5000)
//                .connectionTimeoutMs(5000)
//                .retryPolicy(retryPolicy)
//                .build();
//        //很重要 一定要调用start来创建session链接
//        zkClient.start();
//
//        try {
//            Thread.sleep(Integer.MAX_VALUE);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        System.out.println(14140240%13);

        System.out.println(4733568%13);


    }



}
