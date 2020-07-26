/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.countdownlatch.stage3;

import java.util.concurrent.CountDownLatch;

/**
 * @author yan.zhang
 * @date 2020/7/26 23:25
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Employee a = new Employee("A", 3000, countDownLatch);
        Employee b = new Employee("B", 3000, countDownLatch);
        Employee c = new Employee("C", 4000, countDownLatch);


        b.start();
        c.start();

        countDownLatch.await();

        System.out.println("B,C第一阶段准备完成");

        a.start();
    }
}
