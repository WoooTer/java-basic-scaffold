package wooter.redis.jedis.data_structure;

import redis.clients.jedis.Jedis;
import wooter.redis.jedis.RedisConfig;

import java.util.Set;

public class SetOperation {

    public static Jedis jedis = RedisConfig.jedis;

    public static void main(String[] args) {
        sismember();
    }

    public static void sadd() {
        long result = jedis.sadd("myset", "a", "b", "c");
        System.out.println(result);
    }

    public static void sismember() {
        boolean result = jedis.sismember("myset", "a");
        System.out.println(result);
    }

    /**
     * 【差集】
     * 返回第一个集合与其他集合之间的差异，也可以认为说第一个集合中独有的元素。不存在的集合 key 将视为空集。
     * key1 = {a,b,c,d}
     * key2 = {c}
     * key3 = {a,c,e}
     * SDIFF key1 key2 key3 = {b,d}
     */
    public static void sdiff() {
        Set<String> result = jedis.sdiff("myset", "myset2");
        System.out.println(result);
    }

    /**
     * 【交集】
     */
    public static void sinter() {
        Set<String> result = jedis.sinter("myset", "myset2");
        System.out.println(result);
    }

}
