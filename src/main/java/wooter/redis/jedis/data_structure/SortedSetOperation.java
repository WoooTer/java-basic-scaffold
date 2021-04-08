package wooter.redis.jedis.data_structure;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ZParams;
import wooter.redis.jedis.JedisConfig;

import java.util.Set;

public class SortedSetOperation {

    public static Jedis jedis = JedisConfig.jedis;

    public static void main(String[] args) {
        zscore();
    }

    public static void zadd() {
        long result = jedis.zadd("myzset", 1.1, "a");
        System.out.println(result);
    }

    public static void zrem() {
        long result = jedis.zrem("myzset", "b");
        System.out.println(result);
    }

    public static void zremrangeByRank () {
        long result = jedis.zremrangeByRank("myzset", 0, 1);
        System.out.println(result);
    }

    public static void zremrangeByScore() {
        long result = jedis.zremrangeByScore("myzset", 0, 1);
        System.out.println(result);
    }

    public static void zremrangeByzremrangeByLexScore() {
        long result = jedis.zremrangeByLex("myzset", "[a", "[b");
        System.out.println(result);
    }

    public static void zscore() {
        double result = jedis.zscore("myzset", "b");
        System.out.println(result);
    }

    public static void zcount() {
        long result = jedis.zcount("myzset", 1, 2);
        System.out.println(result);
    }

    /**
     * score 都相同的 zset 执行此命令才有意义
     * https://redis.io/commands/zlexcount
     * https://redis.io/commands/zrangebylex
     */
    public static void zlexcount () {
        long result = jedis.zlexcount("myzset", "[a", "[b");
        long result2 = jedis.zlexcount("myzset", "-", "+");
        System.out.println(result);
        System.out.println(result2);
    }

    /**
     * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
     * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
     * 其中成员的位置按分数值递增(从小到大)来排序。
     */
    public static void zrange() {
        Set<String> result = jedis.zrange("myzset", 1, 2);
        System.out.println(result);
    }

    /**
     * https://www.runoob.com/redis/sorted-sets-zrangebyscore.html
     */
    public static void zrangebyscore() {
        Set<String> result = jedis.zrangeByScore("myzset", "(2", "3");
        System.out.println(result);
    }

    public static void zrangebylex() {
        Set<String> result = jedis.zrangeByLex("myzset", "[a", "g");
        System.out.println(result);
    }

    /**
     * https://www.runoob.com/redis/sorted-sets-zrank.html
     */
    public static void zrank() {
        long result = jedis.zrank("myzset", "a");
        System.out.println(result);
    }

    public static void zrevrank() {
        long result = jedis.zrevrank("myzset", "a");
        System.out.println(result);
    }

    public static void zincrby() {
        double result = jedis.zincrby("myzset", 0.02, "b");
        System.out.println(result);
    }

    /**
     * https://www.runoob.com/redis/sorted-sets-zinterstore.html
     * > zinter 2 myzset myzset2 weights 1.5 2 aggregate sum withscores
     * > zinterstore myOutput 2 myzset myzset2 weights 1.5 2 aggregate sum
     */
    public static void zinterstore() {
        long result = jedis.zinterstore("myOutput", "myzset", "myzset2");
        System.out.println(result);

        ZParams zparams = new ZParams().weights(1.5, 2).aggregate(ZParams.Aggregate.SUM);
        long result2 = jedis.zinterstore("myOutput2", zparams, "myzset", "myzset2");
        System.out.println(result2);
    }

}
