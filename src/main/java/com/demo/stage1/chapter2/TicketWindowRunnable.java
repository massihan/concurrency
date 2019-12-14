package com.demo.stage1.chapter2;/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */


/**
 * @author yan.zhang
 * @date 2019/6/16 16:32
 */
public class TicketWindowRunnable implements Runnable {
    /**
     * 模拟叫号机取号流程
     * <p>
     * 思路
     * 1.业务数据和线程控制数据分离
     */
    private int index = 1;
    private static final int MAX = 50;

    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println(Thread.currentThread().getName() + "index:" + index++);
        }
    }

}
