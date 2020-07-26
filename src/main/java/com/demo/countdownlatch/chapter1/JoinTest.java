/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.countdownlatch.chapter1;

/**
 * @author yan.zhang
 * @date 2020/7/26 23:15
 */
public class JoinTest {
    /**
     * 当前线程中，如果调用某个thread的join方法，那么当前线程就会被阻塞，
     * 直到thread线程执行完毕，当前线程才能继续执行。join的原理是，
     * 不断的检查thread是否存活，如果存活，那么让当前线程一直wait，
     * 直到thread线程终止，线程的this.notifyAll 就会被调用。
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        Employee a = new Employee("A", 3000);
        Employee b = new Employee("B", 3000);
        Employee c = new Employee("C", 4000);

        b.start();
        c.start();

        b.join();
        c.join();

        System.out.println("b,c准备完成");

        a.start();
    }
}
