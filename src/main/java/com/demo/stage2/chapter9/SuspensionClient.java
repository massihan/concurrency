/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter9;

/**
 * @author yan.zhang
 * @date 2019/11/20 22:46
 */

/**
 * 担保挂起设计模式：
 * A线程正在工作，新任务到来，A线程确保处理完成当前任务后执行B任务
 */
public class SuspensionClient {

    public static void main(String[] args) throws InterruptedException {
        final RequestQueue queue = new RequestQueue();
        new ClientThread(queue, "Alex").start();
        ServerThread serverThread = new ServerThread(queue);
        serverThread.start();

        Thread.sleep(10_000);

        serverThread.close();
    }
}
