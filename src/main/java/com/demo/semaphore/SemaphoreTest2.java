package com.demo.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


/**
 * @author yan.zhang
 * @date 2020/9/16 15:31
 */
public class SemaphoreTest2 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        IntStream.rangeClosed(1, 10).forEach(e -> {
            new Thread(new SemaphoreThread(semaphore, e)).start();
        });
    }

    private static class SemaphoreThread implements Runnable {
        private Semaphore semaphore;
        private int taskNum;

        public SemaphoreThread(Semaphore semaphore, int taskNum) {
            this.semaphore = semaphore;
            this.taskNum = taskNum;
        }

        @Override
        public void run() {
            try {
                //获取许可
                semaphore.acquire(1);

                TimeUnit.SECONDS.sleep(1);

                System.out.println("用户 " + taskNum + " 访问资源,时间:" + System.currentTimeMillis());
                //释放许可
                semaphore.release();
            } catch (InterruptedException e) {

            }
        }
    }
}
