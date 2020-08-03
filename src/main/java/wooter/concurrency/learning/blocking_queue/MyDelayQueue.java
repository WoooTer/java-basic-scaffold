package wooter.concurrency.learning.blocking_queue;

import java.util.concurrent.*;

public class MyDelayQueue {

    private Executor executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws Exception {
        MyDelayQueue myDelayQueue = new MyDelayQueue();
        myDelayQueue.delayQueue();
    }

    public void delayQueue() throws InterruptedException {
        BlockingQueue<DelayObject> queue = new DelayQueue<>();

        executor.execute(() -> {
            try {
                System.out.println("taking");
                DelayObject result = queue.take();
                System.out.println("take: " + result.getData());
            } catch (Exception e){ e.printStackTrace(); }
        });

        executor.execute(() -> {
            try {
                queue.put(new DelayObject("wow", 3000));
            } catch (Exception e){ e.printStackTrace(); }
        });
    }

}

class DelayObject implements Delayed {
    private String data;
    private long startTime;

    public DelayObject(String data, long delayInMilliseconds) {
        this.data = data;
        this.startTime = System.currentTimeMillis() + delayInMilliseconds;
    }

    /**
     * When the implementations of the Delayed message getDelay() method return a negative number,
     * that means the given element has already expired.
     * In this situation, the producer will consume that element immediately
     */
    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int)(this.startTime - ((DelayObject) o).startTime);
    }

    public String getData() {
        return this.data;
    }
}
