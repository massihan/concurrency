/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage1.chapter13;

/**
 * @author yan.zhang
 * @date 2019/10/1 15:42
 */
public class SimpleThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        SimpleThreadPool threadPool = new SimpleThreadPool();
        for (int i = 0; i < 40; i++) {
            threadPool.submit(() -> {
                System.out.println("the runnable " + " be serviced by " + Thread.currentThread() + "start");
                try {
                    Thread.sleep(3_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("the runnable " + " be serviced by " + Thread.currentThread() + "finished");
            });
        }

        Thread.sleep(10_000);
        threadPool.shutDown();
        threadPool.submit(() -> System.out.println("============="));
    }
}
