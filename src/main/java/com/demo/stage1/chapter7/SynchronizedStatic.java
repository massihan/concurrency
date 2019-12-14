/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage1.chapter7;

/**
 * @author yan.zhang
 * @date 2019/7/7 18:04
 */
public class SynchronizedStatic {

    static {
        //
        synchronized (SynchronizedStatic.class) {
            try {
                System.out.println("static " + Thread.currentThread().getName());
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Object LOCK = new Object();

    public static synchronized void m1() {
        try {
            //输出拿到锁的线程
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static synchronized void m2() {
        try {
            //输出拿到锁的线程
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
