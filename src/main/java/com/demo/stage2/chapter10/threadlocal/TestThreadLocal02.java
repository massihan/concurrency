package com.demo.stage2.chapter10.threadlocal;

public class TestThreadLocal02 {
    //更改初始化值
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new Thread(new MyRun()).start();
        }
    }


    public static class MyRun implements Runnable {
        @Override
        public void run() {
            Integer left = threadLocal.get();
            System.out.println(Thread.currentThread().getName() + "得到了-->" + left);
            threadLocal.set(left - 1);
            //线程之间互不影响
            System.out.println(Thread.currentThread().getName() + "剩余-->" + threadLocal.get());
        }
    }
}
