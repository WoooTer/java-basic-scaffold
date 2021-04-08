package wooter.redis.jedis.data_structure;

import redis.clients.jedis.Jedis;
import wooter.redis.jedis.JedisConfig;

import java.util.List;

public class StringOperation {

    public static Jedis jedis = JedisConfig.jedis;

    public static void main(String[] args) {
        get();
    }

    public static void set() {
        String result = jedis.set("foo", "123456789");
        System.out.println(result);
    }

    public static void get() {
        String result = jedis.get("foo");
        System.out.println(result);
    }

    public static void getrange() {
        String result = jedis.getrange("foo", 1, 3);
        System.out.println(result);
    }

    public static void mget() {
        List<String> result = jedis.mget("foo", "foo2");
        System.out.println(result);
    }

    public static void incr() {
        long result = jedis.incr("foo");
        System.out.println(result);
    }

    public static void incrBy() {
        long result = jedis.incrBy("foo", 2);
        System.out.println(result);
    }

    public static void decr() {
        long result = jedis.decr("foo");
        System.out.println(result);
    }

    public static void decrBy() {
        long result = jedis.decrBy("foo", 2);
        System.out.println(result);
    }

    public static void setex() {
        String result = jedis.setex("timeoutkey", 10, "v");
        System.out.println(result);
    }

    public static void setnx() {
        long result = jedis.setnx("mysetnx", "_v1");
        System.out.println(result);
    }

}
