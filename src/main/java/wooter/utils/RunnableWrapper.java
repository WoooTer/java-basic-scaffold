package wooter.utils;

public class RunnableWrapper {

    public static Runnable exWrapper(ThrowableRunnable runnable) {
        return () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                e.printStackTrace();
                // 恢复被中断的状态
                Thread.currentThread().interrupt();
            }
        };
    }
}
