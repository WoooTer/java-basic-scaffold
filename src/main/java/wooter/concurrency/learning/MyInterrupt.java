package wooter.concurrency.learning;

public class MyInterrupt {

    public static void main(String[] args) throws Exception {
        MyInterrupt myInterrupt = new MyInterrupt();
        myInterrupt.sleep_interrupt();

    }

    public void sleep_interrupt() throws InterruptedException{
        Thread myThread = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("Lambda Runnable running");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // We've been interrupted: no more messages.
                    System.out.println("InterruptedException");
                    return;
                }
            }
        });

        myThread.start();

        Thread.sleep(10000);
        myThread.interrupt();
    }

    public void static_interrupted() throws InterruptedException{
        Thread myThread = new Thread(() -> {
            int i = 0;
            while (true) {
                if (i % 1000 == 0){
                    System.out.println(i);
                }
                i ++;

                if (Thread.interrupted()) {
                    System.out.println("interrupt");
                    return;
                }
            }
        });

        myThread.start();

        Thread.sleep(1000);
        System.out.println(myThread.isInterrupted()); // false
        myThread.interrupt();
        System.out.println(myThread.isInterrupted()); // false
    }

    public void nonStatic_isInterrupted() throws InterruptedException{
        class MyThread extends Thread {

            public void run(){
                int i = 0;
                while (true) {
                    if (i % 1000 == 0){
                        System.out.println(i);
                    }
                    i ++;

                    if (this.isInterrupted()) {
                        System.out.println("interrupt");
                        return;
                    }
                }
            }
        }

        Thread myThread = new MyThread();
        myThread.start();

        Thread.sleep(1000);
        System.out.println(myThread.isInterrupted()); // false
        myThread.interrupt();
        System.out.println(myThread.isInterrupted()); // true

    }
}
