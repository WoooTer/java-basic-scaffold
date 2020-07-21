package wooter.concurrency.learning;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * [使用CompletableFuture](https://www.liaoxuefeng.com/wiki/1252599548343744/1306581182447650)
 * [深入解读CompletableFuture源码与原理](https://blog.csdn.net/CoderBruis/article/details/103181520)
 * [Guide To CompletableFuture](https://www.baeldung.com/java-completablefuture)
 */
public class MyCompletableFuture {

    public static void main(String[] args) throws Exception {
        MyCompletableFuture myCompletableFuture = new MyCompletableFuture();
        myCompletableFuture.allOf();
    }

    /**
     * 不关心结果值
     */
    public void runAsync() {
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        });
        System.out.println(Thread.currentThread().getName());

        while (true) {
            if (cf.isDone()) {
                System.out.println("CompletedFuture...isDown");
                break;
            }
        }
    }

    /**
     * 消耗结果值
     */
    public void supplyAsync() throws Exception {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            if (Math.random() < 0.3) {
                throw new RuntimeException("fetch price failed!");
            }
            return 5 + Math.random() * 20;
        });
        // 如果执行成功:
        cf.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        // 如果执行异常:
        cf.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(2000);
    }

    public void join() {
        System.out.println("before join");

        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).join();

        System.out.println("after join");
        System.out.println(result);
    }

    public void thenApplyAsync() throws Exception {
        // 第一个任务:
        CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });
        // cfQuery成功后继续执行下一个任务:
        CompletableFuture<String> cfFetch = cfQuery.thenApplyAsync((code) -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return code + " world";
        });
        // cfFetch成功后打印结果:
        cfFetch.thenAcceptAsync((result) -> {
            System.out.println("price: " + result);
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(2000);
    }

    /**
     * 两个都完成—不关心结果值
     */
    public void runAfterBoth() {
        CompletableFuture.supplyAsync(() -> {
            return "s1";
        }).runAfterBothAsync(CompletableFuture.supplyAsync(() -> {
            return "s2";
        }), () -> System.out.println("hello world"));

        while (true) {
        }
    }

    /**
     * 两个都完成—消耗结果值
     */
    public void thenAcceptBoth() {
        CompletableFuture.supplyAsync(() -> {
            return "hello";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            return "world";
        }), (s1, s2) -> System.out.println(s1 + " " + s2));

        while (true) {
        }
    }

    /**
     * 两个都完成—不消耗结果值，只进行流式处理
     */
    public void thenCombine() {
        String result = CompletableFuture.supplyAsync(() -> {
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            return "world";
        }), (s1, s2) -> s1 + " " + s2).join();

        System.out.println(result);
    }

    /**
     * 谁快用哪个—不关心结果值
     */
    public void runAfterEither() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s2";
        }), () -> System.out.println("hello world"));

        while (true) {
        }
    }

    /**
     * 谁快用哪个—消耗结果值
     */
    public void acceptEither() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello world";
        }), System.out::println);

        while (true) {
        }
    }

    /**
     * 高阶 CompletableFuture 打平
     */
    public void thenCompose() {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCompose(string -> CompletableFuture.supplyAsync(() -> string + " world"));

        try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void anyOf() throws Exception {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Object> result = CompletableFuture.anyOf(future1, future2, future3);

        result.thenAccept(e -> {
            System.out.println((String)e);
        });
    }

    public void allOf() throws Exception {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(future1, future2, future3);

        // 方式一
        combinedFuture.thenAccept(e -> {
            try {
                System.out.println("2. " + future1.get() + " " + future2.get() + " " + future3.get());
            } catch (Exception ex){
                ex.printStackTrace();
            }
        });

        // 方式二
        combinedFuture.get(); // 阻塞到全部完成
        System.out.println("1. " + future1.get() + " " + future2.get() + " " + future3.get());
    }

    public void allOf_stream() throws Exception {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "World");

        // 方式三
        String combined = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));

        System.out.println(combined);
    }

}
