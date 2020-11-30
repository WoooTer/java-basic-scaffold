package wooter.concurrency.leetcode.print_foobar_alternately;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static wooter.utils.RunnableWrapper.exWrapper;

/**
 * 公平锁
 */
public class FooBar_Lock {

    private Lock lock = new ReentrantLock(true);
    private volatile boolean permitFoo = true;

    private int n;

    public FooBar_Lock(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; ) {
            lock.lock();
            try {
                if (permitFoo) {
                    printFoo.run();
                    i++;
                    permitFoo = false;
                } else {
                    Thread.yield();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; ) {
            lock.lock();
            try {
                if (!permitFoo){
                    printBar.run();
                    i++;
                    permitFoo = true;
                } else {
                    Thread.yield();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FooBar_Lock foo = new FooBar_Lock(5);

        new Thread(exWrapper(() -> {
            foo.foo(() -> System.out.print("foo"));
        })).start();

        new Thread(exWrapper(() -> {
            foo.bar(() -> System.out.print("bar"));
        })).start();
    }
}
