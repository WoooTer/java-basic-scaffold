package wooter.concurrency.leetcode.print_foobar_alternately;

import static wooter.utils.RunnableWrapper.exWrapper;

public class FooBar_notify {

    private final Object sync = new Object();
    private int stage = 1;  // 可不加 volatile

    private int n;

    public FooBar_notify(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        synchronized (sync) {
            for (int i = 0; i < n; i++) {
                while (stage != 1) {
                    sync.wait();
                }
                printFoo.run();
                stage = 2;
                sync.notifyAll();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        synchronized (sync) {
            for (int i = 0; i < n; i++) {
                while (stage != 2) {
                    sync.wait();
                }
                printBar.run();
                stage = 1;
                sync.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        FooBar_notify foo = new FooBar_notify(3);

        new Thread(exWrapper(() -> {
            foo.foo(() -> System.out.print("foo"));
        })).start();

        new Thread(exWrapper(() -> {
            foo.bar(() -> System.out.print("bar"));
        })).start();
    }
}
