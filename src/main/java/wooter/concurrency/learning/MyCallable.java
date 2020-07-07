package wooter.concurrency.learning;

import java.util.concurrent.*;

public class MyCallable {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        class Task implements Callable<String> {
            public String call() throws Exception {
                Thread.sleep(3000);
                return "wow";
            }
        }

        ExecutorService executor = Executors.newFixedThreadPool(4);
        Callable<String> task = new Task();
        Future<String> future = executor.submit(task);

//        Thread.sleep(4000);
        System.out.println("start");
        String result = future.get(); // 可能阻塞

        System.out.println(result);

    }

}
