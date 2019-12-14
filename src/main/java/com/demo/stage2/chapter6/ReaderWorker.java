/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter6;

/**
 * @author yan.zhang
 * @date 2019/11/9 15:00
 */
public class ReaderWorker extends Thread {
    private final SharedData data;

    public ReaderWorker(SharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char[] readBuf = data.read();
                System.out.println(Thread.currentThread().getName() + " readers " + String.valueOf(readBuf));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

