/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.common.priority;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yan.zhang
 * @date 2019/9/5 11:56
 */


class SimplePriority implements Runnable {
    private int priority;
    private int countDown = 5;
    private volatile double d;

    SimplePriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return Thread.currentThread().getName() + ":" + countDown;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            for (int i = 0; i < 10000; i++) {
                d += (Math.PI + Math.E);
                if (i % 1000 == 0) {
                    Thread.yield();
                }
            }
            System.out.println(this);
            if (--countDown == 0) return;
        }
    }
}

public class PriorityDemo {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new SimplePriority(Thread.MIN_PRIORITY));
            if (i == 4) {
                exec.execute(new SimplePriority(Thread.MAX_PRIORITY));
                return;
            }
        }
//        exec.execute(new SimplePriority(Thread.MAX_PRIORITY));
        exec.shutdown();
    }
}


