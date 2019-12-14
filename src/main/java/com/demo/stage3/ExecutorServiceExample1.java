/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yan.zhang
 * @date 2019/11/2 10:19
 */
public class ExecutorServiceExample1 {
    public static void main(String[] args) {
//        isShutDown();
        isTerminated();

    }

    private static void isTerminated() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("任务执行结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
        System.out.println(executorService.isShutdown());
        // Returns {@code true} if all tasks have completed following shut down.
        System.out.println(executorService.isTerminated());
    }

    private static void isShutDown() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("任务执行结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(executorService.isShutdown());
        executorService.shutdown();
        System.out.println(executorService.isShutdown());
        //调用shutdown后继续添加任务,会拒绝继续添加任务
       /* executorService.execute(() -> {
            System.out.println("I will be executed after shutdown!");
        });*/
    }
}
