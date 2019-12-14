package com.demo.stage2.chapter10.threadlocal;

/**
 * InheritableThreadLocal:继承上下文环境数据，从起点拷贝一份给子线程
 * 注意：是拷贝，不是共享，子线程可以修改ThreadLocal，并不影响其他线程
 */
public class TestThreadLocal04 {

    private static ThreadLocal<Integer> threadLocal01 = new ThreadLocal<>();

    private static ThreadLocal<Integer> threadLocal02 = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal01.set(2);
        threadLocal02.set(3);
        System.out.println(Thread.currentThread().getName() + "-->" + threadLocal01.get());
        new Thread(new MyRun()).start();
    }

    public static class MyRun implements Runnable {
        //构造器，属于main线程
        public MyRun() {
            System.out.println(Thread.currentThread().getName() + "我是ThreadLocal-->" + threadLocal01.get());//2
            System.out.println(Thread.currentThread().getName() + "我是InheritableThreadLocal-->" + threadLocal02.get());//3
        }

        @Override
        public void run() {
            //null 属于MyRun线程，ThreadLocal，线程局部区域，因为没有set所以为null
            System.out.println(Thread.currentThread().getName() + "我是ThreadLocal-->" + threadLocal01.get());
            //InheritableThreadLocal 输出结果3 找起点，该线程由main线程开辟，从main线程拷贝一份
            System.out.println(Thread.currentThread().getName() + "我是InheritableThreadLocal-->" + threadLocal02.get());
            threadLocal02.set(300);
            System.out.println(Thread.currentThread().getName() + "我是InheritableThreadLocal-->" + threadLocal02.get());
        }
    }
}
