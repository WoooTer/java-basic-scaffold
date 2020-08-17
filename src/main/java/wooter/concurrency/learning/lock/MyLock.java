package wooter.concurrency.learning.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * - 可保证锁的公平
 * - 可设置锁的超时中断
 * - 加锁和释放锁可在不同的方法中
 */
public class MyLock {

    public static void main(String[] args) {
        MyLock myLock = new MyLock();

    }

    public void reentrantLock(){
        Lock lock = new ReentrantLock();

        lock.lock();
        //critical section
        lock.unlock();
    }

    public void reentrantReadWriteLock(){
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        readWriteLock.readLock().lock();
        // multiple readers can enter this section
        // if not locked for writing, and not writers waiting
        // to lock for writing.
        readWriteLock.readLock().unlock();

        readWriteLock.writeLock().lock();
        // only one writer can enter this section,
        // and only if no threads are currently reading.
        readWriteLock.writeLock().unlock();
    }

}
