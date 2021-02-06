package cn.techwolf.experiment.common.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author yl.xing 集群是集群 哨兵是哨兵。
 * @create:2020-07-10
 * @describe
 **/
public class Cluster {


    private static JedisCluster jedis;

    static {

        /**服务节点集合**/
        Set<HostAndPort> hostAndPortSet = new HashSet<>();

        hostAndPortSet.add(new HostAndPort("47.104.211.207", 6379));
        hostAndPortSet.add(new HostAndPort("47.104.211.207", 6479));

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        // 最大空闲连接数, 默认8个
        jedisPoolConfig.setMaxIdle(100);
        // 最大连接数, 默认8个
        jedisPoolConfig.setMaxTotal(500);
        //最小空闲连接数, 默认0
        jedisPoolConfig.setMinIdle(0);
        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        jedisPoolConfig.setMaxWaitMillis(2000); // 设置2秒
        //对拿到的connection进行validateObject校验
        jedisPoolConfig.setTestOnBorrow(true);

        jedis = new JedisCluster(hostAndPortSet,jedisPoolConfig);


    }


    /**
     * 测试key:value数据
     * 集群中flushDB、keys废弃
     */
    public void testKey() throws InterruptedException {

//        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();



        //System.out.println("清空数据："+jedis.flushDB());
        System.out.println("判断某个键是否存在：" + jedis.exists("username"));
        System.out.println("新增<'username','wukong'>的键值对：" + jedis.set("username", "xiaohai"));
        System.out.println("是否存在:" + jedis.exists("username"));
        System.out.println("新增<'password','password'>的键值对：" + jedis.set("password", "123456"));
        System.out.println("删除键password:" + jedis.del("password"));
        System.out.println("判断键password是否存在：" + jedis.exists("password"));
        System.out.println("设置键username的过期时间为10s:" + jedis.expire("username", 10));
        TimeUnit.SECONDS.sleep(2); // 线程睡眠2秒System.out.println("查看键username的剩余生存时间："+jedis.ttl("username"));
        System.out.println("查看键username的剩余生存时间：" + jedis.ttl("username"));
        System.out.println("移除键username的生存时间：" + jedis.persist("username"));
        System.out.println("查看键username的剩余生存时间：" + jedis.ttl("username"));
    }
}
