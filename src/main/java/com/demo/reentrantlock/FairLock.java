package com.demo.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yan.zhang
 * @date 2020/9/16 09:58
 */
public class FairLock {
    /**
     * 非公平锁
     * 获得锁线程：0 线程名称：Thread-0
     * 获得锁线程：0 线程名称：Thread-0
     * 获得锁线程：1 线程名称：Thread-1
     * 获得锁线程：1 线程名称：Thread-1
     * 获得锁线程：2 线程名称：Thread-2
     * 获得锁线程：2 线程名称：Thread-2
     * 获得锁线程：3 线程名称：Thread-3
     * 获得锁线程：3 线程名称：Thread-3
     * 获得锁线程：4 线程名称：Thread-4
     * 获得锁线程：4 线程名称：Thread-4
     * 线程会重复获得锁，如果申请获取锁的线程足够多，会造成某些线程一直获取不到锁，非公平锁"饥饿"问题
     */
    static ReentrantLock lock = new ReentrantLock(false);

    //公平锁演示
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new ReentrantLockDemo(i)).start();
        }
       /* IntStream.rangeClosed(1, 5).forEach(e -> {
            new Thread(new ReentrantLockDemo(e)).start();
        });*/
    }

    static class ReentrantLockDemo implements Runnable {
        private Integer id;

        public ReentrantLockDemo(Integer id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 2; i++) {
                lock.lock();
                System.out.println("获得锁线程：" + id + " 线程名称：" + Thread.currentThread().getName());
                lock.unlock();
            }

        }

    }
}
