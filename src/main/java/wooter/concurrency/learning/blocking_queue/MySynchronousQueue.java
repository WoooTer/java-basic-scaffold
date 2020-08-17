package wooter.concurrency.learning.blocking_queue;

import java.util.concurrent.*;

public class MySynchronousQueue {

    private Executor executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        MySynchronousQueue mySynchronousQueue = new MySynchronousQueue();
        mySynchronousQueue.t2();
    }

    /**
     * A thread inseting an element into the queue is blocked
     * until another thread takes that element from the queue
     */
    public void t1(){
        BlockingQueue<String> queue = new SynchronousQueue<>();

        executor.execute(() -> {
            try {
                System.out.println("before put");
                queue.put("wow");
                System.out.println("after put");
            } catch (Exception e){ e.printStackTrace(); }
        });

        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("before take");
                String result = queue.take();
                System.out.println("after take: " + result);
            } catch (Exception e){ e.printStackTrace(); }
        });
    }

    /**
     * if a thread tries to take an element and no element is currently present,
     * that thread is blocked until a thread insert an element into the queue.
     */
    public void t2(){
        BlockingQueue<String> queue = new SynchronousQueue<>();

        executor.execute(() -> {
            try {
                System.out.println("before take");
                String result = queue.take();
                System.out.println("after take: " + result);
            } catch (Exception e){ e.printStackTrace(); }
        });

        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("before put");
                queue.put("wow");
                System.out.println("after put");
            } catch (Exception e){ e.printStackTrace(); }
        });
    }

}
