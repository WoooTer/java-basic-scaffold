package wooter.concurrency.learning;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * https://www.baeldung.com/java-timer-and-timertask
 * [How is the Timer class in Java sensitive to the system clock?](https://stackoverflow.com/questions/18803695/how-is-the-timer-class-in-java-sensitive-to-the-system-clock)
 * [一次用System.nanoTime()填坑System.currentTimeMills()的实例记录](https://www.cnblogs.com/andy-songwei/p/10784049.html)
 */
public class MyTimer {

    private TimerTask task = new TimerTask() {
        public void run() {
            System.out.println("start");
            try {
                Thread.sleep(100);
            } catch (Exception e){}
            System.out.println("Task performed on: " + new Date() + "n" +
                    "Thread's name: " + Thread.currentThread().getName());
        }
    };

    public static void main(String[] args) {
        MyTimer myTimer = new MyTimer();
        myTimer.fixedDelay();
    }

    /**
     * 延时执行
     */
    public void givenUsingTimer_whenSchedulingTaskOnce_thenCorrect() {
        Timer timer = new Timer("Timer");

        timer.schedule(task, 3000L);
    }

    /**
     * 指定时间执行
     */
    public void ExecAtCertainTime(){
        LocalDateTime twoSecondsLater = LocalDateTime.now().plusSeconds(3);
        Date twoSecondsLaterAsDate = Date.from(twoSecondsLater.atZone(ZoneId.systemDefault()).toInstant());

        new Timer().schedule(task, twoSecondsLaterAsDate);
    }

    /**
     * 指定间隔重复执行
     */
    public void fixedDelay(){
        new Timer().schedule(task, 0, 1000);
    }

    /**
     * 指定频率重复执行
     */
    public void fixedRate(){
        new Timer().scheduleAtFixedRate(task, 0, 1000);
    }

}
