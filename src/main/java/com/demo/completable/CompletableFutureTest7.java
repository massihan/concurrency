/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.completable;

import com.demo.mock.BigCollectionMock;
import com.demo.mock.ThreadPoolExecutorMock;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yan.zhang
 * @date 2020/10/14 23:23
 */
public class CompletableFutureTest7 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = ThreadPoolExecutorMock.mock();
        List<Integer> list = BigCollectionMock.mock();

        CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("1");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1 + 3 + 5 + 7 + 9;
        }, executor).thenAccept(res -> {
            System.out.println(res + 1);
        });

        TimeUnit.SECONDS.sleep(1);

        System.out.println("---------");

        CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("2");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1 + 3 + 5 + 7 + 9;
        }, executor).thenAccept(res -> {
            System.out.println(res + 100);
        });

        //sleep主线程
        TimeUnit.SECONDS.sleep(6);

    }
}
