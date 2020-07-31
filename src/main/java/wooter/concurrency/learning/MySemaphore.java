package wooter.concurrency.learning;

import java.util.concurrent.Semaphore;

/**
 * [Semaphore应用](http://tutorials.jenkov.com/java-util-concurrent/semaphore.html)
 * [Semaphore理论](http://tutorials.jenkov.com/java-concurrency/semaphores.html)
 */

/**
 * If the call to acquire() happens before the call to release()
 * the thread calling release() will still know that acquire() was called,
 * because the signal is stored internally in the signal variable.
 * This is not the case with wait() and notify().
 */
public class MySemaphore {

    public static void main(String[] args) throws Exception {
        MySemaphore mySemaphore = new MySemaphore();
        mySemaphore.using_Semaphores_for_Signaling();
    }

    public void using_Semaphores_for_Signaling() throws Exception {
        Semaphore semaphore = new Semaphore(0);

        SendingThread sender = new SendingThread(semaphore);
        ReceivingThread receiver = new ReceivingThread(semaphore);

        receiver.start();
        sender.start();
    }
}

/**
 * To restrict the number of threads than can access some resource
 */
class Pool {
    private static final int MAX_AVAILABLE = 100;
    private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);

    public Object getItem() throws InterruptedException {
        available.acquire();
        return getNextAvailableItem();
    }

    public void putItem(Object x) {
        if (markAsUnused(x))
            available.release();
    }

    // Not a particularly efficient data structure; just for demo
    protected Object[] items = new Object[100];
    protected boolean[] used = new boolean[MAX_AVAILABLE];

    protected synchronized Object getNextAvailableItem() {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (!used[i]) {
                used[i] = true;
                return items[i];
            }
        }
        return null; // not reached
    }

    protected synchronized boolean markAsUnused(Object item) {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (item == items[i]) {
                if (used[i]) {
                    used[i] = false;
                    return true;
                } else
                    return false;
            }
        }
        return false;
    }
}

/**
 *  Using Semaphores for Signaling
 */
class SendingThread extends Thread {
    private Semaphore semaphore;

    public SendingThread(Semaphore semaphore){
        this.semaphore = semaphore;
    }

    public void run(){
        while(true){
            //do something, then signal
            try {
                System.out.println("sending start");
                Thread.sleep(3000);
                this.semaphore.release();
                System.out.println("sending end");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

class ReceivingThread extends Thread {
    private Semaphore semaphore;

    public ReceivingThread(Semaphore semaphore){
        this.semaphore = semaphore;
    }

    public void run(){
        while(true){
            try {
                //receive signal, then do something...
                this.semaphore.acquire();
                System.out.println("receive signal");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}