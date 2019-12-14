/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage1.chapter8;

/**
 * @author yan.zhang
 * @date 2019/7/20 22:13
 */
public class OtherService {

    private final Object lock = new Object();
    private DeadLock deadLock;

    public void setDeadLock(DeadLock deadLock) {
        this.deadLock = deadLock;
    }

    public void s1() {
        synchronized (lock) {
            System.out.println("threadName:" + Thread.currentThread().getName() + " OtherService...s1()");
        }
    }

    public void s2() {
        //lock 锁被线程2持有
        synchronized (lock) {
            System.out.println("threadName:" + Thread.currentThread().getName()+" OtherService...s2()");
            //DeadLock 中的锁被线程1持有
            deadLock.m2();
        }
    }
}
