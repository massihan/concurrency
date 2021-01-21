package com.demo.stage2.chapter10.threadlocal;

/**
 * @author yan.zhang
 * @date 2021/1/21 18:01
 */
public class TestThreadLocal07 {

    /**
     * Thread-0--->java.lang.Object@21aae193
     * Thread-1--->java.lang.Object@21aae193
     * <p>
     * 线程内使用threadLocal.set(obj);
     * 每个线程绑定的是同一个obj对象
     */
    private static ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        Object o1 = new Object();

        new Thread(new MyRun(o1, 1)).start();

        new Thread(new MyRun(o1, 2)).start();

    }


    public static class MyRun implements Runnable {
        private Object obj;
        private Integer tid;

        public MyRun(Object obj) {
            this.obj = obj;
        }

        public MyRun(Object obj, Integer tid) {
            this.obj = obj;
            this.tid = tid;
        }

        @Override
        public void run() {

            threadLocal.set(obj);

            System.out.println(Thread.currentThread().getName() + "--->" + threadLocal.get());
        }
    }
}
