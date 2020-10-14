/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.mock;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yan.zhang
 * @date 2020/10/14 22:55
 */
public class ThreadPoolExecutorMock {
    public static ThreadPoolExecutor mock() {

        return new ThreadPoolExecutor(5, 10,
                60L,
                TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {
            AtomicInteger atomicInteger = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                int index = atomicInteger.getAndIncrement();
                System.out.println("ThreadPoolExecutorMock: create no " + index + " thread");
                return new Thread(r, "mock-thread-" + index);
            }
        }, new ThreadPoolExecutor.CallerRunsPolicy());

    }
}
