/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage1.chapter10;

import java.util.stream.Stream;

/**
 * @author yan.zhang
 * @date 2019/9/15 17:45
 */
public class DiffOfWaitAndSleep {
    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        Stream.of("T1", "T2").forEach(name -> {
            new Thread(name) {
                @Override
                public void run() {
//                  m1();   //sleep不会释放锁
                    m2();  //T1线程wait后会释放锁，故T1 T2线程几乎同时打印
                }
            }.start();
        });


//      m2();
    }

    private static void m1() {
        synchronized (LOCK) {
            System.out.println(Thread.currentThread().getName() + " enter");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private static void m2() {
        synchronized (LOCK) {
            try {
                System.out.println(Thread.currentThread().getName() + " enter");
                LOCK.wait(); //Exception in thread "main" java.lang.IllegalMonitorStateException
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
