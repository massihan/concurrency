/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage1.chapter7;

/**
 * @author yan.zhang
 * @date 2019/7/7 18:08
 */
public class SynchronizedStaticTest {
    public static void main(String[] args) {
        /**
         * 分析：
         * 1.T1线程调用m1方法时，会在 SynchronizedStatic static静态代码块中持有锁
         * 2.等待5s后 调用m1方法
         *
         */
        new Thread("T1") {
            @Override
            public void run() {
                SynchronizedStatic.m1();
            }
        }.start();

        new Thread("T2") {
            @Override
            public void run() {
                SynchronizedStatic.m2();
            }
        }.start();
    }
}

