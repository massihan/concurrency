/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter9;

import java.util.LinkedList;

/**
 * @author yan.zhang
 * @date 2019/11/11 22:25
 */
public class RequestQueue {

    private final LinkedList<Request> queue = new LinkedList<>();

    /**
     * Queue中取出任务
     */
    public Request getRequest() {
        synchronized (queue) {
            while (queue.size() <= 0) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }
            return queue.removeFirst();
        }
    }

    /**
     * Queue中添加任务
     */
    public void putRequest(Request request) {
        synchronized (queue) {
            queue.addLast(request);
            queue.notifyAll();
        }
    }
}
