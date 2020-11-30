package wooter.concurrency.leetcode.print_in_order;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static wooter.utils.RunnableWrapper.exWrapper;

public class Foo_noLock {

    private volatile int stage = 1;

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        stage = 2;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (stage != 2) {
            Thread.yield();
        }
        printSecond.run();
        stage = 3;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (stage != 3) {
            Thread.yield();
        }
        printThird.run();
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Foo_noLock foo = new Foo_noLock();

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
