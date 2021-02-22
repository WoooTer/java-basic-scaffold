package wooter.concurrency.leetcode.print_in_order;

import java.util.concurrent.atomic.AtomicInteger;

public class Foo_CAS {

    private AtomicInteger lock = new AtomicInteger(0);

    public void first(Runnable printFirst) throws InterruptedException {
        while (!lock.compareAndSet(0, 1)) {
            Thread.yield();
        }
        printFirst.run();
        lock.set(2);
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (!lock.compareAndSet(2, 3)) {
            Thread.yield();
        }
        printSecond.run();
        lock.set(4);
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (!lock.compareAndSet(4, -1)) {
            Thread.yield();
        }
        printThird.run();
    }
}
