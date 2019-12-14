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
public class ProduceConsumerVersion3 {

    private int i = 0;
    private final Object LOCK = new Object();
    private volatile boolean isProduced = false;

    private void producer() {
        synchronized (LOCK) {
            if (isProduced) {
                //已经生产，LOCK
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                //未生产，生产，notify
                isProduced = true;
                System.out.println("P->" + ++i);
                LOCK.notify();

            }
        }
    }

    private void consumer() {
        synchronized (LOCK) {
            if (isProduced) {
                //已经生产，则消费
                System.out.println("C->" + i);
                isProduced = false;
                LOCK.notify();
            } else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProduceConsumerVersion3 version2 = new ProduceConsumerVersion3();

        /**
         * 该版本的线程通讯存在的问题：
         * 在多线程环境下 所有线程wait，注意这里不是死锁
         * 使用notifyAll()解决
         */
        Stream.of("P1", "P2").forEach(n -> {
            new Thread(() -> {
                while (true) {
                    version2.producer();
                }
            }).start();
        });
        Stream.of("C1", "C2").forEach(n -> {
            new Thread(() -> {
                while (true) {
                    version2.consumer();
                }
            }).start();
        });

    }
}
