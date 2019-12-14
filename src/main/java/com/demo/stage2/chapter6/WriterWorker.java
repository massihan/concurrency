/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter6;

import java.util.Random;

/**
 * @author yan.zhang
 * @date 2019/11/9 12:27
 */
public class WriterWorker extends Thread {
    private final SharedData data;
    private final String filler;
    private int index = 0;
    private Random random = new Random(System.currentTimeMillis());

    public WriterWorker(SharedData data, String filler) {
        this.data = data;
        this.filler = filler;
    }

    @Override
    public void run() {
        char c = nextChar();
        try {
            while (true) {
                data.write(c);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private char nextChar() {
        char c = filler.charAt(index);
        index++;
        if (index >= filler.length()) {
            index = 0;
        }
        return c;
    }

}
