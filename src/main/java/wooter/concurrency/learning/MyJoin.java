package wooter.concurrency.learning;

public class MyJoin {

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("first going");
                Thread.sleep(3000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                thread1.join();

                System.out.println("second going");
                Thread.sleep(3000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

    }

}
