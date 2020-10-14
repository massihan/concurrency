/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author yan.zhang
 * @date 2020/10/14 23:23
 */
public class CompletableFutureTest3 {
    /*
      Executed in the same thread where the supplyAsync() task is executed
      or in the main thread If the supplyAsync() task completes immediately (Remove sleep() call to verify)

      在thenApply()中的任务和在supplyAsync()中的任务执行在相同的线程中
    */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Some Result";
        }).thenApply(result -> {
            System.out.println(Thread.currentThread().getName());
            return result + " Processed Result";
        });


        String result = completableFuture.get();

        System.out.println("main-thread");

        System.out.println(result);
    }
}
