package wooter.concurrency.leetcode.print_in_order;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static wooter.utils.RunnableWrapper.exWrapper;

public class Foo_notify {

    private final Object sync = new Object();
    private int stage = 1; // 可不加 volatile

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (sync) {
            printFirst.run();
            stage = 2;
            sync.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (sync) {
            while (stage != 2){
                sync.wait();
            }
            printSecond.run();
            stage = 3;
            sync.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (sync) {
            while (stage != 3){
                sync.wait();
            }
            printThird.run();
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Foo_notify foo = new Foo_notify();

        executor.execute(exWrapper(() -> {
            foo.third(() -> System.out.print("third"));
        }));
        executor.execute(exWrapper(() -> {
            foo.second(() -> System.out.print("second"));
        }));
        executor.execute(exWrapper(() -> {
            foo.first(() -> System.out.print("first"));
        }));

        executor.shutdown();
    }
}
