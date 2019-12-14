package com.demo.common.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程轮询:
 * 编写一个程序，开启三个线程，三个线程的ID分别为ABC,每个线程将自己的ID打印10遍，要求输出的结果必须按顺序显示
 * 考点：线程间的通讯
 */
public class TestAlternate {

    public static void main(String[] args) {
        AlternateDemo ad = new AlternateDemo();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                ad.loopA(i);
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                ad.loopB(i);
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                ad.loopC(i);
            }
        }, "C").start();
    }
}

class AlternateDemo {
    //标识，线程ABC之间的通讯标识
    private int flag = 1; //当前正在执行线程标记
    private Lock lock = new ReentrantLock();
    private Condition con1 = lock.newCondition();
    private Condition con2 = lock.newCondition();
    private Condition con3 = lock.newCondition();

    public void loopA(int loop) {
        lock.lock();
        try {
            if (flag != 1) {
                con1.await();
            }
            for (int i = 0; i < 1; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i + "第" + loop + "轮");
            }
            //唤醒B线程
            flag = 2;
            con2.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

    }

    public void loopB(int loop) {
        lock.lock();
        try {
            if (flag != 2) {
                con2.await();
            }

            for (int i = 0; i < 1; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i + "第" + loop + "轮");
            }

            flag = 3;
            con3.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

    }

    public void loopC(int loop) {
        lock.lock();
        try {
            if (flag != 3) {
                con3.await();
            }

            for (int i = 0; i < 1; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i + "第" + loop + "轮");
            }

            flag = 1;
            con1.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

    }
}
