package wooter.concurrency.leetcode.print_foobar_alternately;

import java.util.concurrent.Semaphore;

import static wooter.utils.RunnableWrapper.exWrapper;

public class FooBar_Semaphore {

    private Semaphore semaphoreFoo = new Semaphore(1);
    private Semaphore semaphoreBar = new Semaphore(0);

    private int n;

    public FooBar_Semaphore(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            semaphoreFoo.acquire();
            printFoo.run();
            semaphoreBar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            semaphoreBar.acquire();
            printBar.run();
            semaphoreFoo.release();
        }
    }

    public static void main(String[] args) {
        FooBar_Semaphore foo = new FooBar_Semaphore(3);

        new Thread(exWrapper(() -> {
            foo.foo(() -> System.out.print("foo"));
        })).start();

        new Thread(exWrapper(() -> {
            foo.bar(() -> System.out.print("bar"));
        })).start();
    }
}
