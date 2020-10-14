/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author yan.zhang
 * @date 2020/10/14 22:41
 */
public class CompletableFutureTest1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {


        /**
         * https://juejin.im/post/6844903594165026829
         *
         * runAsync() 运行异步计算
         * 如果你想异步的运行一个后台任务并且不想改任务返回任务东西，这时候可以使用 CompletableFuture.runAsync()方法，它持有一个Runnable 对象，并返回 CompletableFuture<Void>。
         *
         * supplyAsync() 运行一个异步任务并且返回结果
         * 当任务不需要返回任何东西的时候， CompletableFuture.runAsync() 非常有用。
         * CompletableFuture.supplyAsync() 就是你的选择。
         * 它持有supplier<T> 并且返回CompletableFuture<T>，T 是通过调用 传入的supplier取得的值的类型。
         *
         */
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                return "Result of the asynchronous computation";
            }
        });

        /**
         * CompletableFuture.get()方法是阻塞的
         */
        String result = completableFuture.get();
        System.out.println(result);
    }
}
