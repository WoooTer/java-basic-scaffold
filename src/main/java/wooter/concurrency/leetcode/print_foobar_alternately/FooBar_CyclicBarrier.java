package wooter.concurrency.leetcode.print_foobar_alternately;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static wooter.utils.RunnableWrapper.exWrapper;

public class FooBar_CyclicBarrier {

    private CyclicBarrier barrierFoo = new CyclicBarrier(2);
    private CyclicBarrier barrierBar = new CyclicBarrier(2);
    private volatile boolean start = true;

    private int n;

    public FooBar_CyclicBarrier(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            try {
                if (!start) {
                    barrierFoo.await();
                } else {
                    start = false;
                }
                printFoo.run();
                barrierBar.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            try {
                barrierBar.await();
                printBar.run();
                if (i != n - 1) {
                    barrierFoo.await();
                }
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        FooBar_CyclicBarrier foo = new FooBar_CyclicBarrier(1);

        new Thread(exWrapper(() -> {
            foo.foo(() -> System.out.print("foo"));
        })).start();

        new Thread(exWrapper(() -> {
            foo.bar(() -> System.out.print("bar"));
        })).start();
    }
}
