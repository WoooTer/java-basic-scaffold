package wooter.concurrency.leetcode.print_in_order;

import java.util.concurrent.*;

import static wooter.utils.RunnableWrapper.exWrapper;

public class Foo_BlockingQueue {

    private BlockingQueue<Boolean> queueFirst = new LinkedBlockingDeque<>();
    private BlockingQueue<Boolean> queueSecond = new LinkedBlockingDeque<>();

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        queueFirst.put(true);
    }

    public void second(Runnable printSecond) throws InterruptedException {
        queueFirst.take();
        printSecond.run();
        queueSecond.put(true);
    }

    public void third(Runnable printThird) throws InterruptedException {
        queueSecond.take();
        printThird.run();
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Foo_BlockingQueue foo = new Foo_BlockingQueue();

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
