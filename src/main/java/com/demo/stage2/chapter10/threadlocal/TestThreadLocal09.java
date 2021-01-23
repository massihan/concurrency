package com.demo.stage2.chapter10.threadlocal;

import java.util.concurrent.CountDownLatch;

public class TestThreadLocal09 {
    /**
     * 结论：
     * 1.每个ThreadLocal hashCode相同，表明每个线程访问的是同一个ThreadLocal对象
     * 2.Instance hashcode每个线程不同，表明每个线程使用的StringBuilder不同
     * 3.三个线程最终结果是：Value:0123，表明每个线程将字符串追加进各自的StringBuilder内
     */
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        InnerClass innerClass = new InnerClass();
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                for (int j = 0; j < 4; j++) {
                    innerClass.add(String.valueOf(j));
                    innerClass.print();
                }
                innerClass.set("hello world");
                countDownLatch.countDown();
            }, "thread - " + i).start();


        }
        countDownLatch.await();
    }

    private static class InnerClass {

        public void add(String newStr) {
            StringBuilder str = Counter.counter.get();
            Counter.counter.set(str.append(newStr));
        }

        public void print() {
            System.out.printf("Thread name:%s , ThreadLocal hashcode:%s, Instance hashcode:%s, Value:%s\n", Thread.currentThread().getName(), Counter.counter.hashCode(), Counter.counter.get().hashCode(), Counter.counter.get().toString());
        }

        public void set(String words) {
            Counter.counter.set(new StringBuilder(words));
            System.out.printf("Set, Thread name:%s , ThreadLocal hashcode:%s,  Instance hashcode:%s, Value:%s\n",
                    Thread.currentThread().getName(),
                    Counter.counter.hashCode(),
                    Counter.counter.get().hashCode(),
                    Counter.counter.get().toString());
        }
    }

    private static class Counter {

        private static ThreadLocal<StringBuilder> counter = new ThreadLocal<StringBuilder>() {
            @Override
            protected StringBuilder initialValue() {
                return new StringBuilder();
            }
        };

    }

}
