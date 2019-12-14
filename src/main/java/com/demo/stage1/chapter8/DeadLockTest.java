/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage1.chapter8;

/**
 * @author yan.zhang
 * @date 2019/7/20 22:17
 */
public class DeadLockTest {
    /**
     * win命令行：
     * 1.jps
     * 2.jstack 进程号  ag：jstack 10292
     */

    public static void main(String[] args) {
        OtherService otherService = new OtherService();
        DeadLock deadLock = new DeadLock(otherService);
        otherService.setDeadLock(deadLock);

        new Thread(() -> {
            while (true) {
                deadLock.m1();
            }
        },"thread-1").start();

        new Thread(() -> {
            while (true) {
                otherService.s2();
            }
        },"thread-2").start();
    }

}
