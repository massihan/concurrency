/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.countdownlatch.stage2;

import java.util.concurrent.CountDownLatch;

/**
 * @author yan.zhang
 * @date 2020/7/26 23:21
 */
public class CountDownLatchTest {

    /**
     * CountDownLatch中我们主要用到两个方法一个是await()方法，
     * 调用这个方法的线程会被阻塞，另外一个是countDown()方法，
     * 调用这个方法会使计数器减一，当计数器的值为0时，
     * 因调用await()方法被阻塞的线程会被唤醒，继续执行。
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Employee a = new Employee("A", 3000, countDownLatch);
        Employee b = new Employee("B", 3000, countDownLatch);
        Employee c = new Employee("C", 4000, countDownLatch);

        b.start();
        c.start();

        countDownLatch.await();

        System.out.println("B,C准备完成");

        a.start();
    }
}
