package wooter.concurrency.leetcode.print_in_order;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static wooter.utils.RunnableWrapper.exWrapper;

public class Foo_CyclicBarrier {

    private CyclicBarrier barrierFirst = new CyclicBarrier(2);
    private CyclicBarrier barrierSecond = new CyclicBarrier(2);

    public void first(Runnable printFirst) throws InterruptedException, BrokenBarrierException {
        printFirst.run();
        barrierFirst.await();
    }

    public void second(Runnable printSecond) throws InterruptedException, BrokenBarrierException {
        barrierFirst.await();
        printSecond.run();
        barrierSecond.await();
    }

    public void third(Runnable printThird) throws InterruptedException, BrokenBarrierException {
        barrierSecond.await();
        printThird.run();
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Foo_CyclicBarrier foo = new Foo_CyclicBarrier();

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
