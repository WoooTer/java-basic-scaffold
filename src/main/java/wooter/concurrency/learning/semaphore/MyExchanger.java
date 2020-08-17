package wooter.concurrency.learning.semaphore;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyExchanger {

    private static Executor executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        executor.execute(() -> {
            try {
                System.out.println("A start");
                System.out.println("A get: " + exchanger.exchange("i am A"));
            } catch (Exception e){ e.printStackTrace(); }
        });

        executor.execute(() -> {
            try {
                System.out.println("B start");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("B get: " + exchanger.exchange("i am B"));
            } catch (Exception e){ e.printStackTrace(); }
        });

    }

}
