/*
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter8;

/**
 * @author yan.zhang
 * @date 2019/11/9 18:26
 */
public class AsyncFuture<T> implements Future<T> {
    private volatile boolean done = false;
    private T result;

    void done(T result) {
        synchronized (this) {
            this.result = result;
            done = true;
            this.notifyAll();
        }
    }

    @Override
    public T get() throws InterruptedException {
        synchronized (this) {
            while (!done) {
                this.wait();
            }
        }
        return result;
    }
}
