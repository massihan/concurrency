/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author yan.zhang
 * @date 2019/11/2 11:35
 */
public class ExecutorServiceExample2 {
    public static void main(String[] args) throws InterruptedException {
        executeRunnableError();
    }

    private static void executeRunnableError() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10, new MyThreadFactory());
        IntStream.rangeClosed(0, 10).forEach(i -> executorService.execute(new MyTask(i) {
            @Override
            protected void doInit() {
                System.out.println("The no: " + i + " init");
            }

            @Override
            protected void doExecute() {
                System.out.println("The no: " + i + " execute");
            }

            @Override
            protected void done() {
                System.out.println("result:" + 1 / 0);
                System.out.println("The no: " + i + " done");
            }

            @Override
            protected void error(Exception e) {
                System.out.println("The no: " + i + " error");
            }
        }));

        executorService.shutdown();
        executorService.awaitTermination(3, TimeUnit.MINUTES);
    }


    /**
     * 对异常较为常用的处理方式
     */
    private static abstract class MyTask implements Runnable {
        private final int no;

        public MyTask(int no) {
            this.no = no;
        }

        @Override
        public void run() {
            /* try {*/
                this.doInit();
                this.doExecute();
                this.done();
           /* } catch (Exception e) {
                this.error(e);
            }*/
        }

        protected abstract void doInit();

        protected abstract void doExecute();

        protected abstract void done();

        protected abstract void error(Exception e);
    }


    /**
     * 很少使用，一般常用于无法更改Runnable的场景使用
     */
    public static class MyThreadFactory implements ThreadFactory {
        private static final AtomicInteger SEQ = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("My-Thread-" + SEQ.getAndIncrement());
            thread.setUncaughtExceptionHandler((t, e) -> {
                System.out.println("the thread " + t.getName() + " execute failed!");
                e.printStackTrace();
                System.out.println("=============");
            });
            return thread;
        }
    }


}
