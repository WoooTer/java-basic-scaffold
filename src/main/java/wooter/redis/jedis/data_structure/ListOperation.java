package wooter.redis.jedis.data_structure;

import redis.clients.jedis.Jedis;
import wooter.redis.jedis.RedisConfig;

import java.util.List;

public class ListOperation {

    public static Jedis jedis = RedisConfig.jedis;

    public static void main(String[] args) {
        ltrim();
    }

    public static void lpush() {
        long result = jedis.lpush("mylist", "1", "2");
        System.out.println(result);
    }

    public static void rpush() {
        long result = jedis.rpush("mylist", "3", "4");
        System.out.println(result);
    }

    public static void lpop() {
        String result = jedis.lpop("mylist");
        System.out.println(result);
    }

    public static void rpop() {
        String result = jedis.rpop("mylist");
        System.out.println(result);
    }

    public static void blpop() {
        List<String> result = jedis.blpop(0, "mylist", "mylist2");
        System.out.println(result);
    }

    public static void brpop() {
        List<String> result = jedis.brpop(0, "mylist", "mylist2");
        System.out.println(result);
    }

    public static void brpoplpush() {
        String result = jedis.brpoplpush("mylist", "mylist2", 0);
        System.out.println(result);
    }

    /**
     * COUNT 的值可以是以下几种：
     * count > 0 : 从表头开始向表尾搜索，移除与 VALUE 相等的元素，数量为 COUNT 。
     * count < 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值。
     * count = 0 : 移除表中所有与 VALUE 相等的值。
     */
    public static void lrem() {
        jedis.lrem("mylisy", 1, "2");
    }

    public static void lindex() {
        String result = jedis.lindex("mylist", 1);
        System.out.println(result);
    }

    public static void lset() {
        String result = jedis.lset("mylist", 1, "lsetV");
        System.out.println(result);
    }

    public static void lrange() {
        List<String> result = jedis.lrange("mylist", 1, 2);
        System.out.println(result);
    }

    public static void ltrim() {
        String result = jedis.ltrim("mylist", 1, 2);
        System.out.println(result);
    }
}
