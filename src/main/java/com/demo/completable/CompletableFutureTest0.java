/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author yan.zhang
 * @date 2020/10/14 22:41
 */
public class CompletableFutureTest0 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Object> completableFuture = new CompletableFuture<>();
        /**
         * 一直阻塞
         */
//        completableFuture.get();

        /**
         *自定义强制完成一个Future，所有等待这个 Future 的客户端都将得到一个指定的结果，completableFuture.complete()
         */
        completableFuture.complete("finished");
    }

}
