package com.demo.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author yan.zhang
 * @date 2020/12/11 14:21
 */
public class SemaphoreTest3 {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);

        for (int i = 0; i < 10; i++) {
            new Thread(new MyWorker(semaphore)).start();
        }

        System.out.println("Action...Go...");

        semaphore.release(5);

        System.out.println("Wait for permits off");

        while (semaphore.availablePermits() != 0) {
            try {
                Thread.sleep(100L);
                System.out.println("sleeping....");
            } catch (InterruptedException ignored) {

            }
        }
        System.out.println("Action...Go...Again...");
        semaphore.release(5);
    }


    static class MyWorker implements Runnable {
        private Semaphore semaphore;

        public MyWorker(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(2);
                semaphore.acquire();
                System.out.println("executed!");
            } catch (InterruptedException ignored) {

            }
        }
    }
}

