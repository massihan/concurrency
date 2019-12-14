/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter6;

import java.util.Arrays;

/**
 * @author yan.zhang
 * @date 2019/11/9 12:13
 */
public class SharedData {
    private final char[] buffer;
    private final ReadWriteLock LOCK = new ReadWriteLock();

    public SharedData(int size) {
        buffer = new char[size];
        for (int i = 0; i < size; i++) {
            buffer[i] = '*';
        }
    }

    public char[] read() throws InterruptedException {
        try {
            LOCK.readLock();
            return doRead();
        } finally {
            LOCK.readUnLock();
        }
    }

    private char[] doRead() {
        char[] newBuf = new char[buffer.length];
        System.arraycopy(buffer, 0, newBuf, 0, newBuf.length);
        slowly(50);
        return newBuf;
    }

    void write(char c) throws InterruptedException {
        try {
            LOCK.writeLock();
            doWrite(c);
        } finally {
            LOCK.writeUnLock();
        }
    }

    private void doWrite(char c) {
        Arrays.fill(buffer, c);
        //写操作之后后sleep
        slowly(10);
    }

    private void slowly(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
