package com.demo.stage2.chapter10.threadlocal;

/**
 * @author yan.zhang
 * @date 2021/1/21 18:01
 */
public class TestThreadLocal06 {

    /**
     * ThreadLocal.withInitial(Object::new);
     * 为每个线程创建一个object对象
     */
    private static ThreadLocal<Object> threadLocal = ThreadLocal.withInitial(Object::new);

    public static void main(String[] args) {
        Object o1 = new Object();

        new Thread(new MyRun()).start();

        new Thread(new MyRun()).start();

    }


    public static class MyRun implements Runnable {
        private Object obj;

        public MyRun(Object obj) {
            this.obj = obj;
        }

        public MyRun() {
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "--->" + threadLocal.get());
        }
    }
}
