package com.demo.stage2.chapter10.threadlocal;

import java.util.Random;

public class TestThreadLocal05 {
    //private static int data;
    // private static Map<Thread, Integer> threadData = new HashMap<Thread, Integer>();
    private static java.lang.ThreadLocal<Integer> x = new java.lang.ThreadLocal<Integer>();

    public static void main(String[] args) {

        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                //为data赋值
                int data = new Random().nextInt();
                System.out.println(Thread.currentThread().getName() + " has put data:" + data);
//              threadData.put(Thread.currentThread(), data);
                x.set(data);
                new A().getData();
                new B().getData();
            }
            ).start();
        }
    }

    static class A {
        private int getData() {
            int data = x.get();
            System.out.println("A from " + Thread.currentThread().getName() + "get data " + data);
            return data;
        }
    }

    static class B {
        private int getData() {
            int data = x.get();
            System.out.println("B from " + Thread.currentThread().getName() + "get data " + data);
            return data;
        }
    }
}

