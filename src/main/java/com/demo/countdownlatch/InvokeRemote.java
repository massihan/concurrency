/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author yan.zhang
 * @date 2019/7/17 20:55
 */
public class InvokeRemote {
    /**
     * 模拟200线程并发访问
     */
    private static final int MAX_THREAD_NUMS = 200;
    private static CountDownLatch cdl = new CountDownLatch(MAX_THREAD_NUMS);

    public static void invoke() {
        //模拟并发
        for (int i = 0; i < MAX_THREAD_NUMS; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    cdl.await();
                    System.out.println(finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //这里远程调用接口
            }).start();
            cdl.countDown();
        }
    }


    public static class TicketRequest implements Runnable {
        @Override
        public void run() {
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        invoke();
        /*for (int i = 0; i < MAX_THREAD_NUMS; i++) {
            new Thread(new TicketRequest()).start();
            cdl.countDown();
        }*/
    }
}
