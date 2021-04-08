package wooter.redis.jedis.data_structure;

import redis.clients.jedis.Jedis;
import wooter.redis.jedis.JedisConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HashOperation {

    public static Jedis jedis = JedisConfig.jedis;

    public static void main(String[] args) {
        hsetnx();
    }

    public static void hset() {
        long result1 = jedis.hset("myhash", "k1", "v1");
        long result2 = jedis.hset("myhash", "k2", "v2");
        System.out.println(result1);
        System.out.println(result2);
    }

    public static void hmset() {
        Map<String, String> map = new HashMap<>();
        map.put("k3", "v3");
        String result = jedis.hmset("myhash", map);
        System.out.println(result);
    }

    public static void hget() {
        String result = jedis.hget("myhash", "k1");
        System.out.println(result);
    }

    public static void hgetAll() {
        Map<String, String> result = jedis.hgetAll("myhash");
        System.out.println(result);
    }

    public static void hkeys() {
        Set<String> result = jedis.hkeys("myhash");
        System.out.println(result);
    }

    public static void hvals() {
        List<String> result = jedis.hvals("myhash");
        System.out.println(result);
    }

    public static void hsetnx() {
        long result = jedis.hsetnx("myhash", "k1", "_v1");
        System.out.println(result);
    }

}
