/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter6;

/**
 * @author yan.zhang
 * @date 2019/11/9 11:52
 */
public class ReadWriteLock {
    //正在读线程
    private int readingReaders = 0;
    //正在等待读线程 waitSet队列中
    private int waitingReaders = 0;
    //正在写的线程数
    private int writingWriters = 0;
    private int waitingWriters = 0;

    public synchronized void readLock() throws InterruptedException {
        //可能进入wait状态，如果进入wait装填，则waitingReaders++
        this.waitingReaders++;
        //写线程>0,此时不能read
        try {
            while (writingWriters > 0) {
                this.wait();
            }
            //write结束,可以read
            readingReaders++;
        } finally {
            this.waitingReaders--;
        }
    }

    public synchronized void readUnLock() {
        readingReaders--;
        this.notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        this.waitingWriters++;
        try {
            //正在写或者正在读 线程wait
            while (readingReaders > 0 || writingWriters > 0) {
                this.wait();
            }
            this.writingWriters++;
        } finally {
            this.waitingWriters--;
        }
    }

    public synchronized void writeUnLock() {
        this.writingWriters--;
        this.notifyAll();
    }
}


