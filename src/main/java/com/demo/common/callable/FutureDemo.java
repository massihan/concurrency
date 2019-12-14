/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.common.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author yan.zhang
 * @date 2019/9/5 9:55
 */
public class FutureDemo {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            //产生Future对象
            Future<String> future = exec.submit(new TaskWithResult(i));
            results.add(future);
            //isDone检查Future是否完成
            System.out.println(future.isDone());
        }
        try {
            for (Future<String> fs : results) {
                System.out.println(fs.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            exec.shutdown();
        }

    }
}

class TaskWithResult implements Callable<String> {
    private int id;

    TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() {
        return "result of TaskWithResult " + id;
    }
}

