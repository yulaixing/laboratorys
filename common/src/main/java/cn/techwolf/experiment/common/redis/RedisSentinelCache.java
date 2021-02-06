package cn.techwolf.experiment.common.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yl.xing
 * @create:2020-07-10
 * @describe
 **/
public class RedisSentinelCache {

    private static JedisSentinelPool redisSentinelJedisPool = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(500);
        config.setMaxWaitMillis(2000);
        // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
        config.setTestOnBorrow(true);
        //在return给pool时，是否提前进行validate操作
        config.setTestOnReturn(true);
        //在空闲时检查有效性，默认false
        config.setTestWhileIdle(true);

        String nodes = "47.104.211.207:26379";

        String masterName = "mymaster";
        // 哨兵模式
        Set<String> sentinels = new HashSet<String>();
        for (String sentinel : nodes.split(",")) {
            sentinels.add(sentinel);
        }
        redisSentinelJedisPool = new JedisSentinelPool(masterName, sentinels, config, 3000);
    }

    public synchronized  static Jedis getJedis() {
        return redisSentinelJedisPool.getResource();
    }


    public static void  set(String key, String value) {
        Jedis jedis = getJedis();
        try {
            jedis.set(key, value);
        } catch (Exception e) {
            System.out.println("设置值失败：" + e.getMessage());
            throw new RuntimeException();
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }


    public static String get(String key) {
        Jedis jedis = getJedis();
        if (jedis == null) {
            System.out.println("Jedis实例获取失败！");
            throw new RuntimeException();
        }

        try {
            return jedis.get(key);
        } catch (Exception e) {
            System.out.println("获取值失败：" + e.getMessage());
            throw new RuntimeException();
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public void test1(){
        set("test","test123");

        String test = get("test");

        System.out.println(test);



    }



}

