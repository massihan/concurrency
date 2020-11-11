/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.juc;

import java.util.concurrent.TimeUnit;

/**
 * @author yan.zhang
 * @date 2020/11/9 22:42
 */
public class LockSupportDemo3 {

    /**
     * 结论：
     * 1.Object类中的wait,notify,notifyAll用于线程唤醒的方法，都必须在synchronized内部执行，也就是必须用到synchronized
     * 2.notify在wait前程序，线程无法唤醒
     */
    static Object LOCK = new Object();

    public static void main(String[] args) {
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
