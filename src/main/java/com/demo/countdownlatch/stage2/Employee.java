/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.countdownlatch.stage2;

import java.util.concurrent.CountDownLatch;

/**
 * @author yan.zhang
 * @date 2020/7/26 23:13
 */
public class Employee extends Thread {
    private String employeeName;
    private long time;

    private CountDownLatch countDownLatch;

    public Employee(String employeeName, long time, CountDownLatch countDownLatch) {
        this.employeeName = employeeName;
        this.time = time;
        this.countDownLatch = countDownLatch;
    }


    @Override
    public void run() {
        try {
            System.out.println(employeeName + "开始准备");
            Thread.sleep(time);
            System.out.println(employeeName + "准备完成");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            countDownLatch.countDown();
        }
    }
}
