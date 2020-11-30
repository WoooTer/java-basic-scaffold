package wooter.concurrency.leetcode.print_in_order;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static wooter.utils.RunnableWrapper.exWrapper;

/**
 * 超出限制时间
 */
public class Foo_Condition {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private volatile int stage = 1;

    public void first(Runnable printFirst) throws InterruptedException {
        lock.lock();
        try {
            printFirst.run();
            stage = 2;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        try {
            while (stage != 2) {
                condition.await();
            }
            printSecond.run();
            stage = 3;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        lock.lock();
        try {
            while (stage != 3) {
                condition.await();
            }
            printThird.run();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Foo_Condition foo = new Foo_Condition();

        executor.execute(exWrapper(() -> {
            Thread.sleep(1000);
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
