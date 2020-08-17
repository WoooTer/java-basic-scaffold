package wooter.concurrency.learning.thread_pool;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * [线程池之ThreadPoolExecutor概述](https://www.jianshu.com/p/c41e942bcd64)
 * [ThreadPoolExecutor线程池运行机制分析-线程复用原理](https://blog.csdn.net/weixin_33814685/article/details/88001794)
 */
public class MyThreadPoolExecutor {

    private Executor singlePool = Executors.newSingleThreadExecutor();
    private Executor fixedPool = Executors.newFixedThreadPool(10);
    private Executor cachedPool = Executors.newCachedThreadPool();

    private Executor scheduledPool = Executors.newScheduledThreadPool(10);
    private Executor workStealingPool = Executors.newWorkStealingPool();

}
