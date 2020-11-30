package wooter.concurrency.leetcode.print_foobar_alternately;

import java.util.concurrent.atomic.AtomicInteger;

import static wooter.utils.RunnableWrapper.exWrapper;

public class FooBar_CAS {

    private volatile AtomicInteger stage = new AtomicInteger(0);

    private int n;

    public FooBar_CAS(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (!stage.compareAndSet(0, 1)) {
                Thread.yield();
            }
            printFoo.run();
            stage.set(2);
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (!stage.compareAndSet(2, 3)) {
                Thread.yield();
            }
            printBar.run();
            stage.set(0);
        }
    }

    public static void main(String[] args) {
        FooBar_CAS foo = new FooBar_CAS(5);

        new Thread(exWrapper(() -> {
            foo.foo(() -> System.out.print("foo"));
        })).start();

        new Thread(exWrapper(() -> {
            foo.bar(() -> System.out.print("bar"));
        })).start();
    }
}
