package com.demo.stage2.chapter10.threadlocal;

/**
 * ThreadLocal:每个线程自身的局部区域，修改不会影响其他线程数据
 * * ThreadLocal多线程情况下，保证成员变量的安全性
 * * JKD建议使用private static修饰ThreadLocal
 * * 常用方法get/set/initialValue
 */
public class TestThreadLocal01 {
    private static ThreadLocal<Integer> threadLocal01 = new ThreadLocal<>();
    //更改初始化值
    private static ThreadLocal<Integer> threadLocal02 = ThreadLocal.withInitial(() -> 200);

    public static void main(String[] args) throws InterruptedException {
        //null
        System.out.println(Thread.currentThread().getName() + "-->" + threadLocal01.get());

        System.out.println("------------------------------------------------------------");

        //设置值
        threadLocal02.set(99);
        threadLocal02.set(100);
        System.out.println(Thread.currentThread().getName() + "-->" + threadLocal02.get());

        //开启新线程
        Thread t1 = new Thread(() -> {
            threadLocal02.set((int) (Math.random() * 99));
            System.out.println(threadLocal02);
            System.out.println(Thread.currentThread().getName() + "-->" + threadLocal02.get());
        });
        t1.start();

        Thread.sleep(1000);
//        threadLocal02.set(1);

        System.out.println(Thread.currentThread().getName() + "-->" + threadLocal02.get());
        System.out.println(threadLocal02);
    }
}
