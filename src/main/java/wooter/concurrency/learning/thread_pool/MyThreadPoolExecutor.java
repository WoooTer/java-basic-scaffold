package wooter.concurrency.learning.thread_pool;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * [线程池之ThreadPoolExecutor概述](https://www.jianshu.com/p/c41e942bcd64)
 * [ThreadPoolExecutor线程池运行机制分析-线程复用原理](https://blog.csdn.net/weixin_33814685/article/details/88001794)
 */
public class MyThreadPoolExecutor {

    private Executor singlePool = Executors.newSingleThreadExecutor();
    private Executor fixedPool = Executors.newFixedThreadPool(10);
    private Executor cachedPool = Executors.newCachedThreadPool();

    private ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(10);
    private Executor workStealingPool = Executors.newWorkStealingPool();

    public static void main(String[] args) {
        MyThreadPoolExecutor myThreadPoolExecutor = new MyThreadPoolExecutor();
        myThreadPoolExecutor.scheduled_withFixedDelay();
    }

    public void scheduled_delay() {
        scheduledPool.schedule(() -> {
            System.out.println("Executed!");
        }, 5, TimeUnit.SECONDS);
    }

    public void scheduled_withFixedDelay() {
        scheduledPool.scheduleWithFixedDelay (() -> {
            System.out.println("Executed!");
        }, 3, 1, TimeUnit.SECONDS);
    }

}
