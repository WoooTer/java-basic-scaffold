package wooter.redis.redission;

import org.redisson.Redisson;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class Main {

    public static void main(String[] args) throws Exception {
        // 1. Create config object
        Config config = new Config();
        config.useClusterServers()
            // use "rediss://" for SSL connection
            .addNodeAddress("redis://127.0.0.1:7181");
        RedissonClient redisson = Redisson.create(config);

        RCountDownLatch latch = redisson.getCountDownLatch("myCountDownLatch");

        latch.trySetCount(1);
        // await for count down
        latch.await();

        // in other thread or JVM
        RCountDownLatch latch2 = redisson.getCountDownLatch("myCountDownLatch");
        latch2.countDown();
    }
}
