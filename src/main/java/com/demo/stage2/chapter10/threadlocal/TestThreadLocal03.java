package com.demo.stage2.chapter10.threadlocal;

/**
 * ThreadLocal:分析上下文环境
 * 起点
 * 1.构造器：哪里调用属于哪里
 * 2.run方法中：属于线程自身
 */
public class TestThreadLocal03 {

    //    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set(10);
        new Thread(new MyRun()).start();
    }

    public static class MyRun implements Runnable {
        //MyRun构造方法，这里是main线程
        public MyRun() throws InterruptedException {
            Thread.sleep(1_000);
            System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());//main-->10
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());//Thread-0-->1
        }
    }
}
