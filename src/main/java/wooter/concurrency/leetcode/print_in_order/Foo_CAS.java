package wooter.concurrency.leetcode.print_in_order;

import java.util.concurrent.atomic.AtomicInteger;

public class Foo_CAS {

    private AtomicInteger lock = new AtomicInteger(0);

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        lock.set(1);
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (!lock.compareAndSet(1, 2)) {
            Thread.yield();
        }
        printSecond.run();
        lock.set(3);
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (!lock.compareAndSet(3, -1)) {
            Thread.yield();
        }
        printThird.run();
    }
}
