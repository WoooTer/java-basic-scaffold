package wooter.redis.jedis.pub_sub;

import redis.clients.jedis.Jedis;
import wooter.redis.jedis.RedisConfig;

import java.util.Date;
import java.util.concurrent.*;

public class Main {

    private static final MyListener myListener = new MyListener();
    private static final CountDownLatch latch = new CountDownLatch(5); // pub 5次后终止

    private static final ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(3);
    private static final ExecutorService pool = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws Exception {
        sub();
        pub();
        shutdownDelay();
    }

    public static void sub() {
        pool.execute(() -> {
            System.out.println("【sub start】");
            // Note that subscribe is a blocking operation because it will poll Redis for responses on the thread that calls subscribe
            new Jedis(RedisConfig.HOST).subscribe(myListener, "foo");
        });
    }

    public static void pub() throws Exception {
        Jedis jedis = new Jedis(RedisConfig.HOST);

        scheduledPool.scheduleWithFixedDelay(() -> {
            String message = new Date().toString();
            jedis.publish("foo", message);
            System.out.println("pub：" + message + "[channel:foo]");
            latch.countDown();
        }, 2, 1, TimeUnit.SECONDS);
    }

    public static void shutdownDelay() {
        pool.execute(() -> {
            try {
                latch.await();
            } catch (Exception e){
                e.printStackTrace();
            }

            System.out.println("【unsub start】");
            myListener.unsubscribe("foo");
            scheduledPool.shutdown();
            pool.shutdown();
        });
    }
}
