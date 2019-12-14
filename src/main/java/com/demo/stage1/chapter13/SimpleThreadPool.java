/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage1.chapter13;

/**
 * @author yan.zhang
 * @date 2019/10/1 12:05
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 自定义简单线程池
 */
public class SimpleThreadPool {
    private final int size;
    private final int queueSize;

    private static volatile int seq = 0;

    private final static int DEFAULT_TASK_QUEUE_SIZE = 1000;

    /**
     * 任务队列
     * 使用线程池方提供Runnable接口实现,提供线程池中线程执行的具体任务
     */
    private static final LinkedList<Runnable> taskQueue = new LinkedList<>();

    private static final String THREAD_PREFIX = "SIMPLE_THREAD_POLL-";

    private static final ThreadGroup GROUP = new ThreadGroup("POOL_GROUP");

    /**
     * 线程队列
     */
    private static final List<WorkTask> THREAD_QUEUE = new ArrayList<>();

    /**
     * 拒绝策略：默认抛出异常
     */
    private final DiscardPolicy discardPolicy;

    /**
     * 是否销毁标识
     */
    private volatile boolean destroy = false;


    /**
     * 默认丢弃策略
     */
    private static final DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException("discard this task!");
    };

    public SimpleThreadPool() {
        this(10, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    public SimpleThreadPool(int size, int queueSize, DiscardPolicy discardPolicy) {
        this.size = size;
        this.discardPolicy = discardPolicy;
        this.queueSize = queueSize;
        init();
    }

    /**
     * 初始化线程池
     */
    private void init() {
        for (int i = 0; i < size; i++) {
            createWorkTask();
        }
    }

    /**
     * 停止线程池 （任务队列中没有任务可以停止）
     */
    public void shutDown() throws InterruptedException {
        while (!taskQueue.isEmpty()) {
            Thread.sleep(100);
        }
        //shutDown():检查线程队列中所有的线程运行状态是否都是BLOCKED
        int initVal = THREAD_QUEUE.size();
        while (initVal > 0) {
            for (WorkTask task : THREAD_QUEUE) {
                if (task.getTaskState() == TaskState.BLOCKED) {
                    task.interrupt();
                    task.close();
                    initVal--;
                } else {
                    Thread.sleep(50);
                }
            }
        }
        this.destroy = true;
        System.out.println("The Thread pool disposed!");
    }


    /**
     * 创建工作线程
     */
    private void createWorkTask() {
        WorkTask task = new WorkTask(GROUP, THREAD_PREFIX + seq++);
        task.start();
        THREAD_QUEUE.add(task);
    }

    /**
     * 提交任务到线程池中的线程队列中
     */
    public void submit(Runnable runnable) {
        if (destroy) {
            throw new IllegalStateException("the thread pool already destroy and not allow submit task!");
        }
        synchronized (taskQueue) {
            if (taskQueue.size() > queueSize) discardPolicy.discard();
            taskQueue.addLast(runnable);
            /**
             * 通知所有被阻塞的线程
             */
            taskQueue.notifyAll();
        }
    }

    static class WorkTask extends Thread {

        private volatile TaskState taskState = TaskState.FREE;

        public TaskState getTaskState() {
            return this.taskState;
        }

        public WorkTask(ThreadGroup group, String name) {
            super(group, name);
        }


        private void close() {
            this.taskState = TaskState.DEAD;
        }

        /**
         * 线程执行完成后线程不能销毁
         */
        @Override
        public void run() {
            //任务队列中取任务执行
            OUTER:
            while (getTaskState() != TaskState.DEAD) {
                Runnable runnable;
                synchronized (taskQueue) {
                    //加锁，每次只能有一个线程从任务队列中取任务
                    while (taskQueue.isEmpty()) {
                        //任务队列为空，当前线程线程wait
                        try {
                            taskState = TaskState.BLOCKED;
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            //被打断，break循环
                            break OUTER;
                        }
                    }
                    //任务队列不为空
                    runnable = taskQueue.removeFirst();
                }
                if (null != runnable) {
                    taskState = TaskState.RUNNING;
                    //手动调用run方法执行，run方法执行完成继续下面代码
                    runnable.run();
                    taskState = TaskState.FREE;
                }
            }
        }
    }

    private enum TaskState {
        FREE, RUNNING, BLOCKED, DEAD;
    }


}
