/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage1.chapter9;

/**
 * @author yan.zhang
 * @date 2019/7/20 23:03
 */

import java.util.stream.Stream;

/**
 * 生产后通知消费者
 */
public class ProduceConsumerVersion4 {

    private int i = 0;
    private final Object LOCK = new Object();
    private volatile boolean isProduced = false;

    private void producer() {
        synchronized (LOCK) {

            /**
             * 这里使用if 配合notifyAll会引入新问题
             * 生产两次消费1次
             * 原因：当isProduced为true, p1,p2线程生产后  LOCK.wait();
             *      消费者线程消费后会notifyAll(),p1,p2线程唤醒，都会生产
             *
             * 所以使用notifyAll() 尽量配合while循环使用
             */
            if (isProduced) {
                //已经生产，LOCK
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //未生产，生产，notify
            isProduced = true;
            System.out.println("P->" + ++i);
            LOCK.notifyAll();
        }
    }

    private void consumer() {
        synchronized (LOCK) {
            if (isProduced) {
                //已经生产，则消费
                System.out.println("C->" + i);
                isProduced = false;
                LOCK.notifyAll();
            }
            try {
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        ProduceConsumerVersion4 version2 = new ProduceConsumerVersion4();

        /**
         * 该版本的线程通讯存在的问题：
         * 在多线程环境下 所有线程wait，注意这里不是死锁
         * 使用notifyAll()解决
         *
         */
        Stream.of("P1", "P2", "P3").forEach(n -> {
            new Thread(() -> {
                while (true) {
                    version2.producer();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        });
        Stream.of("C1", "C2", "C3").forEach(n -> {
            new Thread(() -> {
                while (true) {
                    version2.consumer();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        });

    }
}
