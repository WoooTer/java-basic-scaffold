package wooter.concurrency.learning.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * [基于AQS自己实现一个同步器](https://www.cnblogs.com/wyq1995/p/12256550.html)
 * [Java并发之AQS详解](https://cnblogs.com/waterystone/p/4920797.html)
 * [从ReentrantLock的实现看AQS的原理及应用](https://tech.meituan.com/2019/12/05/aqs-theory-and-apply.html)
 * [Thread.sleep、Object.wait、LockSupport.park 区别](https://blog.csdn.net/u013332124/article/details/84647915)
 */
public class MyMutex implements Lock, java.io.Serializable {

    //创建一个具体的MySync来做具体的工作
    private final MySync mySync = new MySync();

    @Override
    public void lock() {
        mySync.acquire(1);
    }

    @Override
    public boolean tryLock() {
        return mySync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return mySync.tryAcquireNanos(1, unit.toNanos(time));

    }

    //带了Interruptibly的方法表示对中断进行响应，就是当一个线程在阻塞队列中被挂起的时候，
    //其他线程调用该线程的中断方法中断了该线程，该线程会抛出InterruptedException异常
    @Override
    public void lockInterruptibly() throws InterruptedException {
        mySync.acquireInterruptibly(1);
    }

    @Override
    public void unlock() {
        mySync.release(1);
    }

    //很方便的获取条件变量
    @Override
    public Condition newCondition() {
        return mySync.newCondition();
    }



    private static class MySync extends AbstractQueuedSynchronizer {

        // 锁是否已经被持有
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        // 如果state为0，就尝试获取锁，将state修改为1
        public boolean tryAcquire(int acquires) {
            assert acquires == 1;
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        // 尝试释放锁，将state设置为0
        protected boolean tryRelease(int releases) {
            assert releases == 1;
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        //提供条件变量接口
        Condition newCondition() {
            return new ConditionObject();
        }
    }

}
