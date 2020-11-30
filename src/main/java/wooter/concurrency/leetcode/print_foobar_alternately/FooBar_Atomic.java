package wooter.concurrency.leetcode.print_foobar_alternately;

import java.util.concurrent.atomic.AtomicInteger;

import static wooter.utils.RunnableWrapper.exWrapper;

public class FooBar_Atomic {

    private volatile AtomicInteger stage = new AtomicInteger(0);

    private int n;

    public FooBar_Atomic(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (stage.get() != 0) {
                Thread.yield();
            }
            printFoo.run();
            stage.incrementAndGet();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (stage.get() != 1) {
                Thread.yield();
            }
            printBar.run();
            stage.decrementAndGet();
        }
    }

    public static void main(String[] args) {
        FooBar_Atomic foo = new FooBar_Atomic(5);

        new Thread(exWrapper(() -> {
            foo.foo(() -> System.out.print("foo"));
        })).start();

        new Thread(exWrapper(() -> {
            foo.bar(() -> System.out.print("bar"));
        })).start();
    }

}
