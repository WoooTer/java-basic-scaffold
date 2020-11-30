package wooter.concurrency.learning.semaphore;

import java.util.concurrent.CyclicBarrier;

/**
 * The barrier is called cyclic because it can be re-used after the waiting threads are released.
 */
public class MyCyclicBarrier {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            System.out.println("BarrierAction executed");
        });

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " waiting at barrier 1");
                barrier.await();
                System.out.println(Thread.currentThread().getName() + " after waiting at barrier 1");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " waiting at barrier 1");
                barrier.await();
                System.out.println(Thread.currentThread().getName() + " after waiting at barrier 1");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

}
