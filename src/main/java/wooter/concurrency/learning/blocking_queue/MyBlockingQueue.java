package wooter.concurrency.learning.blocking_queue;

import java.util.concurrent.*;

/**
 * [Java BlockingQueue](http://tutorials.jenkov.com/java-util-concurrent/blockingqueue.html)
 */
public class MyBlockingQueue {

    private Executor executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws Exception {
        MyBlockingQueue myBlockingQueue = new MyBlockingQueue();
        myBlockingQueue.arrayBlockingQueue();
    }

    public void arrayBlockingQueue() throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1024);

        executor.execute(() -> {
            try {
                System.out.println("taking");
                String result = queue.take();
                System.out.println("take: " + result);
            } catch (Exception e){ e.printStackTrace(); }
        });

        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                queue.put("wow");
            } catch (Exception e){ e.printStackTrace(); }
        });
    }


}
