package wooter.redis.jedis.pipeline;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import wooter.redis.jedis.RedisConfig;

public class Main {

    public static Jedis jedis = RedisConfig.jedis;

    public static void main(String[] args) {

        Pipeline t = jedis.pipelined();
        t.set("foo", "bar");
        Response<String> fooResultRes = t.get("foo");

        t.sync();

        String fooResult = fooResultRes.get();
        System.out.println(fooResult);
    }
}
