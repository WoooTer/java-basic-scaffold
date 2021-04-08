package wooter.redis.jedis.transaction;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;
import wooter.redis.jedis.RedisConfig;

import java.util.List;

/**
 * https://github.com/redis/jedis/wiki/AdvancedUsage
 *
 * Redis 事务可以一次执行多个命令， 并且带有以下三个重要的保证：
 * 1. 批量操作在发送 EXEC 命令前被放入队列缓存。
 * 2. 收到 EXEC 命令后进入事务执行，事务中任意命令执行失败，其余的命令依然被执行。
 * 3. 在事务执行过程，其他客户端提交的命令请求不会插入到事务执行命令序列中。
 */
public class Main {

    public static Jedis jedis = RedisConfig.jedis;

    public static void main(String[] args) throws Exception {

        jedis.watch("foo");
        Transaction t = jedis.multi();
        t.set("foo", "bar");
        Response<String> fooResultRes = t.get("foo");

        Thread.sleep(10 * 1000);
        List<Object> result = t.exec();
        System.out.println(result);

        String fooResult = fooResultRes.get();
        System.out.println(fooResult);

    }


}
