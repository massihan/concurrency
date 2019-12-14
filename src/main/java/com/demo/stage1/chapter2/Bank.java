package com.demo.stage1.chapter2;/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */


/**
 * @author yan.zhang
 * @date 2019/6/16 16:42
 */
public class Bank {
    public static void main(String[] args) {
        TicketWindowRunnable twr = new TicketWindowRunnable();
        //创建线程和业务逻辑分离
        Thread windowThread1 = new Thread(twr, "1窗口");
        Thread windowThread2 = new Thread(twr, "2窗口");
        Thread windowThread3 = new Thread(twr, "3窗口");

        windowThread1.start();
        windowThread2.start();
        windowThread3.start();
    }
}
