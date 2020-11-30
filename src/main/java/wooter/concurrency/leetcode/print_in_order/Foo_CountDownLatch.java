package wooter.concurrency.leetcode.print_in_order;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static wooter.utils.RunnableWrapper.exWrapper;

/**
 * https://leetcode-cn.com/problems/print-in-order/
 */
public class Foo_CountDownLatch {

    private CountDownLatch latchFirst = new CountDownLatch(1);
    private CountDownLatch latchSecond = new CountDownLatch(1);

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        latchFirst.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        latchFirst.await();
        printSecond.run();
        latchSecond.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        latchSecond.await();
        printThird.run();
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Foo_CountDownLatch foo = new Foo_CountDownLatch();

        executor.execute(exWrapper(() -> {
            foo.third(() -> System.out.print("third"));
        }));
        executor.execute(exWrapper(() -> {
            Thread.sleep(2000);
            foo.second(() -> System.out.print("second"));
        }));
        executor.execute(exWrapper(() -> {
            foo.first(() -> System.out.print("first"));
        }));

        executor.shutdown();
    }
}
