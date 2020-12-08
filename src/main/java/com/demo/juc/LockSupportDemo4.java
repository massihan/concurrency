/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author yan.zhang
 * @date 2020/12/5 12:02
 */
public class LockSupportDemo4 {
    /**
     * LockSupport用来创建锁和其他同步类的基本线程和阻塞原语
     * park() / park(Object blocker) :阻塞单前线程/阻塞传入的具体线程
     * <p>
     * unPark(Object blocker) : 唤醒处于阻塞状态的指定线程
     * <p>
     * unPark(Object blocker)可以在park()之前执行
     */
    public static void main(String[] args) {
        lockSupport();
    }

    private static void lockSupport() {
        Thread aThread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "-----come in..-----");

            //阻塞 类比 wait(),等待通知
            LockSupport.park();

            System.out.println(Thread.currentThread().getName() + "\t" + "-----come in..-----");
        }, "a");

        aThread.start();


        //睡眠3s
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {

        }

        //由b线程唤醒a线程
        Thread bThread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "-----come in..-----");
            //b唤醒a
            LockSupport.unpark(aThread);
        }, "b");

        bThread.start();
    }
}
