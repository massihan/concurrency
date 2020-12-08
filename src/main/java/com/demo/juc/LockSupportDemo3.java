/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yan.zhang
 * @date 2020/11/9 22:42
 */
public class LockSupportDemo3 {

    /**
     * 结论：
     * 1.Object类中的wait,notify,notifyAll用于线程唤醒的方法，都必须在synchronized内部执行，也就是必须用到synchronized
     * 2.notify在wait前程序，线程无法唤醒
     * <p>
     * 3.synchronized 和 lock实现等待/环境机制 有两个条件约束
     * 1）线程必须获得并持有锁，必须在同步代码块中（synchronized或lock）中
     * 2）必须要先等待后唤醒，线程才能够被唤醒
     */
    static final Object LOCK = new Object();

    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) {
//        synchronizedWaitNotify();

        /**
         *
         */
        synchronizedAwaitSignal();
    }

    private static void synchronizedAwaitSignal() {
        new Thread(() -> {
//            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "-----come in-----");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t" + "-----被唤醒-----");
            } finally {
//                lock.unlock();
            }
        }, "A").start();


        new Thread(() -> {
//            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t" + "-----通知-----");
            } finally {
//                lock.unlock();
            }
        }, "B").start();
    }

    private static void synchronizedWaitNotify() {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (LOCK) {
                System.out.println(Thread.currentThread().getName() + "\t" + "-----come in-----");
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t" + "-----come out-----");
            }
        }).start();

        new Thread(() -> {
            synchronized (LOCK) {
                System.out.println(Thread.currentThread().getName() + "\t" + "唤醒....");
                LOCK.notify();
            }
        }).start();
    }
}
