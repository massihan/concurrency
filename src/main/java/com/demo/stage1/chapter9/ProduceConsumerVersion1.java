/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage1.chapter9;

/**
 * @author yan.zhang
 * @date 2019/7/20 22:55
 */
public class ProduceConsumerVersion1 {
    /**
     * 生产者消费者模型
     */
    private int i = 1;
    private final Object lock = new Object();

    private void producer() {
        synchronized (lock) {
            System.out.println("P->" + i++);
        }
    }

    private void consumer() {
        synchronized (lock) {
            System.out.println("C->" + i);
        }
    }

    public static void main(String[] args) {

        /**
         * 线程间无相互通讯
         */
        ProduceConsumerVersion1 version1 = new ProduceConsumerVersion1();
        new Thread("T1") {
            @Override
            public void run() {
                while (true) {
                    version1.producer();
                }
            }
        }.start();

        new Thread("T2") {
            @Override
            public void run() {
                while (true) {
                    version1.consumer();
                }
            }
        }.start();
    }
}
