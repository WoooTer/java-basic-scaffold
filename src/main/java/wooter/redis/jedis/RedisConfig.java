package wooter.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/**
 * https://github.com/redis/jedis/wiki
 * https://javadoc.io/doc/redis.clients/jedis/3.5.2/redis/clients/jedis/Jedis.html
 *
 * https://www.runoob.com/redis/redis-strings.html
 * https://redis.io/commands
 */
public class RedisConfig {

    public static final String HOST = "192.168.1.157";

    public static Jedis jedis = new Jedis(RedisConfig.HOST);

    public static void main(String[] args) {
        singleInstance();
    }

    public static void singleInstance() {
        Jedis jedis = new Jedis(HOST);
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println(value);
    }

    public static void jedisPool() {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), HOST);

        // Jedis implements Closeable. Hence, the jedis instance will be auto-closed after the last statement.
        try (Jedis jedis = pool.getResource()) {
            /// ... do stuff here ... for example
            jedis.set("foo", "bar");
            String foobar = jedis.get("foo");
            jedis.zadd("sose", 0, "car");
            jedis.zadd("sose", 0, "bike");
            Set<String> sose = jedis.zrange("sose", 0, -1);
            System.out.println(sose);
        }
        // ... when closing your application:
        pool.close();
    }


}
