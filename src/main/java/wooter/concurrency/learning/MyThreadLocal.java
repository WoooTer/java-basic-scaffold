package wooter.concurrency.learning;

/**
 *  [使用ThreadLocal-廖雪峰](https://www.liaoxuefeng.com/wiki/1252599548343744/1306581251653666)
 */
public class MyThreadLocal {

    public static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> -1);

    public static void main(String[] args) {
        new Thread(() -> {
            step();
        }).start();

        new Thread(() -> {
            step();
        }).start();
    }

    public static void step(){
        threadLocal.set((int) (Math.random() * 100D));
        try {
            Thread.sleep(1000);
            System.out.println(threadLocal.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            threadLocal.remove();
        }

    }


}
