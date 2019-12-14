/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter6;

/**
 * @author yan.zhang
 * @date 2019/11/9 12:24
 */
public class Client {
    public static void main(String[] args) {
        SharedData data = new SharedData(10);
        new ReaderWorker(data).start();
        new ReaderWorker(data).start();
        new ReaderWorker(data).start();
        new ReaderWorker(data).start();
        new ReaderWorker(data).start();

        new WriterWorker(data, "zhangyan").start();
    }
}
