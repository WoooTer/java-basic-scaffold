package wooter.concurrency.leetcode.print_in_order;

import static wooter.utils.RunnableWrapper.exWrapper;

public class Foo {

    public void first() {
        System.out.print("first");
    }

    public void second() {
        System.out.print("second");
    }

    public void third() {
        System.out.print("third");
    }

    public static void main(String[] args) {
        Foo foo = new Foo();

        Thread first = new Thread(() -> {
            foo.first();
        });

        Thread second = new Thread(exWrapper(() -> {
            first.join();
            foo.second();
        }));

        Thread third = new Thread(exWrapper(() -> {
            second.join();
            foo.third();
        }));

        first.start();
        second.start();
        third.start();
    }
}
