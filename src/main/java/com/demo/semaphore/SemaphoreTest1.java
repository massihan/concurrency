package com.demo.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


/**
 * @author yan.zhang
 * @date 2020/9/26 18:54
 */
public class SemaphoreTest1 {
    private static final int THREAD_COUNT = 30;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
    private static Semaphore s = new Semaphore(10);

    /**
     * 在代码中，虽然有30个线程在执行，但是只允许10个并发的执行。Semaphore的构造方法Semaphore(int permits) 接受一个整型的数字，
     * 表示可用的许可证数量。
     * Semaphore(10)表示允许10个线程获取许可证，也就是最大并发数是10。
     * Semaphore的用法也很简单，首先线程使用Semaphore的acquire()获取一个许可证，使用完之后调用release()归还许可证。
     * 还可以用tryAcquire()方法尝试获取许可证。
     *
     * @param args
     */
    public static void main(String[] args) {

        for (int i = 0; i < THREAD_COUNT; i++) {

            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        s.acquire();
                        //每个业务线程执行2s
                        TimeUnit.SECONDS.sleep(2);

                        System.out.println("save data");
                    } catch (InterruptedException ignored) {
                    } finally {
                        s.release();
                    }
                }
            });

        }
        threadPool.shutdown();

    }
}