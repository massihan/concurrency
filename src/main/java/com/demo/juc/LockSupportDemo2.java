/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.juc;

/**
 * @author yan.zhang
 * @date 2020/11/9 22:42
 */
public class LockSupportDemo2 {

    static Object LOCK = new Object();

    public static void main(String[] args) {
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
