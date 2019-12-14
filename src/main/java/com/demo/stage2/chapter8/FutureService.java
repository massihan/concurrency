/*
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter8;

import java.util.function.Consumer;

/**
 * 立即返回Future<T>,不阻塞主线程
 */
public class FutureService {
    /**
     * 期望得到立即返回
     * FutureTask执行完成之后将结果放置到Future中
     * 通过Future的get方法获取最终结果
     */
    public <T> Future<T> submit(FutureTask<T> task) {
        AsyncFuture asyncFuture = new AsyncFuture();
        new Thread(() -> {
            T result = task.call();
            //指定完call()后 将结果"通知给"Future
            asyncFuture.done(result);
        }).start();
        return asyncFuture;
    }


    /**
     * Future.get()是阻塞方法
     * 解决：处理结果产生后，通过回调函数解决
     */
    public <T> Future<T> submit(FutureTask<T> task, Consumer<T> consumer) {
        AsyncFuture asyncFuture = new AsyncFuture();
        new Thread(() -> {
            T result = task.call();
            //指定完call()后 将结果"通知给"Future
            asyncFuture.done(result);
            //操作结束后,发起回调
            consumer.accept(result);
        }).start();
        return asyncFuture;
    }
}
