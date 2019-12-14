/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter9;

import java.util.Objects;
import java.util.Random;

/**
 * @author yan.zhang
 * @date 2019/11/20 22:49
 */
public class ServerThread extends Thread {
    //server端从queue中收取任务
    private final RequestQueue queue;

    private final Random random;

    private volatile boolean flag = true;

    ServerThread(RequestQueue queue) {
        this.queue = queue;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        while (flag) {
            try {
                Request request = queue.getRequest();
                if (Objects.isNull(request)) {
                    //queue.getRequest() 线程wait状态被interrupt会return null
                    System.out.println("Received the empty request!");
                    continue;
                }
                System.out.println("Server -> " + request.getValue());
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    void close() {
        this.flag = false;
        //queue.getRequest() 可能出现线程wait的情况
        this.interrupt();
    }
}
