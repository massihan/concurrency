/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.completable;

import com.demo.mock.ThreadPoolExecutorMock;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yan.zhang
 * @date 2020/10/14 23:23
 */
public class CompletableFutureTest5 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = ThreadPoolExecutorMock.mock();

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Some Result";
        }).thenApplyAsync(result -> {

            System.out.println(Thread.currentThread().getName());
            return result + " Processed Result";
        }, executor);


        String result = completableFuture.get();

        System.out.println("main-thread");

        System.out.println(result);
    }
}
