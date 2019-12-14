/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage1.chapter7;

/**
 * @author yan.zhang
 * @date 2019/7/7 17:50
 */
public class SynchronizedThis {
    //测试this锁

    public static void main(String[] args) {
        //测试对象锁，两个线程，T1，T2使用一个锁this
        ThisLock thisLock = new ThisLock();
        new Thread("T1") {
            @Override
            public void run() {
                thisLock.m1();
            }
        }.start();

        new Thread("T2") {
            @Override
            public void run() {
                thisLock.m2();
            }
        }.start();
    }
}

class ThisLock {

    private Object LOCK = new Object();

    public synchronized void m1() {
        try {
            //输出拿到锁的线程
            System.out.println(Thread.currentThread().getName());

            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

  /*  public synchronized void m2() {
        try {
            //输出拿到锁的线程
            System.out.println(Thread.currentThread().getName());

            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/


    //T1,T2线程使用不同的锁
    public void m2() {
        synchronized (LOCK) {
            try {
                //输出拿到锁的线程
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
