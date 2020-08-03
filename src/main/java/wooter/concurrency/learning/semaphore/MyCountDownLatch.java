package wooter.concurrency.learning.semaphore;

import java.util.concurrent.CountDownLatch;

public class MyCountDownLatch {

    public static void main(String[] args) throws Exception {

        CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " count down");
                latch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " count down");
                latch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        latch.await();
        System.out.println("continue");
    }
}
