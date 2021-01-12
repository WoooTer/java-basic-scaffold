package wooter.concurrency.leetcode.print_in_order;

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

        new Thread(() -> {
            foo.first();
        }).start();

        new Thread(() -> {
            foo.second();
        }).start();

        new Thread(() -> {
            foo.third();
        }).start();

    }
}
