/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage1.chapter8;

/**
 * @author yan.zhang
 * @date 2019/7/20 22:08
 */
public class DeadLock {
    private final Object lock = new Object();

    private OtherService otherService;

    public DeadLock(OtherService otherService) {
        this.otherService = otherService;
    }

    public void m1() {
        synchronized (lock) {
            System.out.println("threadName:" + Thread.currentThread().getName() + " DeadLock...m1()");
            otherService.s1();
        }
    }

    public void m2() {
        synchronized (lock) {
            System.out.println("threadName:" + Thread.currentThread().getName() + " DeadLock...m2()");
        }
    }
}
