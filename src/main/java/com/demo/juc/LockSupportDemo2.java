/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yan.zhang
 * @date 2020/11/9 22:42
 */
public class LockSupportDemo2 {

    static Object LOCK = new Object();

    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) {
//        synchronizedWaitNotify();


        /**
         * condition.await();和condition.signal()一样必须在同步代码块中使用
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
//            synchronized (LOCK) {
            System.out.println(Thread.currentThread().getName() + "\t" + "-----come in-----");
            try {
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "-----come out-----");
//            }
        }).start();

        new Thread(() -> {
//            synchronized (LOCK) {
            System.out.println(Thread.currentThread().getName() + "\t" + "唤醒....");
            LOCK.notify();
//            }
        }).start();
    }
}
