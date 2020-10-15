package com.demo.cyclicbar;

import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

/**
 * @author yan.zhang
 * @date 2020/9/16 12:23
 */
public class CyclicBarrierTest {
    /**
     * CountDownLatch是使一批线程等待另一批线程执行完后再执行,而CyclicBarrier只是使等待的线程达到一定数目后再让它们继续执行
     * 每调用一次await()方法都将使阻塞的线程数+1,只有阻塞的线程数达到设定值时屏障才会打开,允许阻塞的所有线程继续执行
     * <p>
     * 1.CyclicBarrier的计数器可以重置而CountDownLatch不行,这意味着CyclicBarrier实例可以被重复使用而CountDownLatch只能被使用一次
     * 2.CyclicBarrier允许用户自定义barrierAction操作,这是个可选操作,可以在创建CyclicBarrier对象时指定
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> System.out.println("线程名称：" + Thread.currentThread().getName() + " 执行barrierAction操作!"));

        IntStream.rangeClosed(1, 10).forEach(e -> {
            new Thread(new CyclicBarrierThread(cyclicBarrier, e)).start();
        });
    }

    private static class CyclicBarrierThread implements Runnable {
        private CyclicBarrier cyclicBarrier;
        private int taskNum;

        public CyclicBarrierThread(CyclicBarrier cyclicBarrier, int taskNum) {
            this.cyclicBarrier = cyclicBarrier;
            this.taskNum = taskNum;
        }

        @Override
        public void run() {
            //执行子任务
            System.out.println("线程名称：" + Thread.currentThread().getName() + " 子任务：" + taskNum + " 执行完毕!");
            try {
                /**
                 * 等待所有子任务执行完成
                 * 每调用一次await()方法都将使阻塞的线程数+1,只有阻塞的线程数达到设定值时屏障才会打开,允许阻塞的所有线程继续执行
                 *
                 */
                cyclicBarrier.await();

            } catch (Exception e) {
                e.printStackTrace();
            }
            //释放资源
            System.out.println("线程：" + taskNum + " 释放资源!");
        }
    }
}
