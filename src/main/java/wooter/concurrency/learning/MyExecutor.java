package wooter.concurrency.learning;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * [Java ExecutorService](http://tutorials.jenkov.com/java-util-concurrent/executorservice.html)
 * [Java Future的实现原理](https://www.jianshu.com/p/69a6ae850736)
 */
public class MyExecutor {

    public static void main(String[] args) throws Exception {
        MyExecutor myExecutor = new MyExecutor();
        myExecutor.submit_runnable();
    }

    public void execute_runnable() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.execute(new Runnable() {
            public void run() {
                System.out.println("Asynchronous task");
            }
        });

        executorService.shutdown();
    }

    public void submit_runnable() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        System.out.println("start submit");
        Future future = executorService.submit(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Asynchronous task");
            }
        });
        System.out.println("end submit");

        //returns null if the task has finished correctly.
        System.out.println(future.get());

        executorService.shutdown();
        System.out.println("exit");
    }

    public void submit_callable() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future future = executorService.submit(new Callable<String>() {
            public String call() throws Exception {
                System.out.println("Asynchronous Callable");
                return "Callable Result";
            }
        });

        System.out.println("future.get() = " + future.get());
        executorService.shutdown();
    }

    public void invokeAny() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Set<Callable<String>> callables = new HashSet<Callable<String>>();

        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 1";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 2";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 3";
            }
        });

        String result = executorService.invokeAny(callables);  // 阻塞
        System.out.println("result = " + result);

        executorService.shutdown();
    }

    public void invokeAll() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Set<Callable<String>> callables = new HashSet<Callable<String>>();

        callables.add(new Callable<String>() {
            public String call() throws Exception {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "Task 1";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "Task 2";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "Task 3";
            }
        });

        System.out.println("start");
        List<Future<String>> futures = executorService.invokeAll(callables);  // 阻塞
        System.out.println("end");

        for(Future<String> future : futures){
            System.out.println("future.get = " + future.get());
        }

        executorService.shutdown();
    }

}
