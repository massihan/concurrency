/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter9;

import java.util.Random;

/**
 * @author yan.zhang
 * @date 2019/11/20 22:40
 */
public class ClientThread extends Thread {
    private final RequestQueue queue;

    private final Random random;

    private final String sendValue;

    public ClientThread(RequestQueue requestQueue, String sendValue) {
        this.queue = requestQueue;
        this.sendValue = sendValue;
        //随机因子
        random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("Client -> request " + sendValue);
            queue.putRequest(new Request(sendValue));
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
