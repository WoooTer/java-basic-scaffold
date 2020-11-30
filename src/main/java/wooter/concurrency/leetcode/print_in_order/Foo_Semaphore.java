package wooter.concurrency.leetcode.print_in_order;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import static wooter.utils.RunnableWrapper.exWrapper;

public class Foo_Semaphore {

    private Semaphore semaphoreFirst = new Semaphore(0);
    private Semaphore semaphoreSecond = new Semaphore(0);

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        semaphoreFirst.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        semaphoreFirst.acquire();
        printSecond.run();
        semaphoreSecond.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        semaphoreSecond.acquire();
        printThird.run();
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Foo_Semaphore foo = new Foo_Semaphore();

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
