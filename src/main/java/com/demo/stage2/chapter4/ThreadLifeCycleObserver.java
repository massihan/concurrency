/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter4;

import java.util.List;

/**
 * @author yan.zhang
 * @date 2019/11/2 21:25
 */
public class ThreadLifeCycleObserver implements LifeCycleListener {
    private static final Object LOCK = new Object();

    public void concurrentQuery(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        ids.stream().forEach(id -> {
            new Thread(new ObserverRunnable(this) {
                @Override
                public void run() {
                    try {
                        notifyChange(new RunnableEvent(RunnableStatus.RUNNING, Thread.currentThread(), null));
                        System.out.println("query for the id" + id);
                        Thread.sleep(1000L);
                        notifyChange(new RunnableEvent(RunnableStatus.DONE, Thread.currentThread(), null));
                    } catch (Exception e) {
                        notifyChange(new RunnableEvent(RunnableStatus.ERROR, Thread.currentThread(), e));
                    }
                }
            }).start();
        });
    }

    @Override
    public void onEvent(ObserverRunnable.RunnableEvent runnableEvent) {
        //可能多个线程进行回调
        synchronized (LOCK) {
            System.out.println("The runnable {" +
                    runnableEvent.getThread().getName() +
                    "] data change and state is [" + runnableEvent.getState());
            if (runnableEvent.getCause() != null) {

            }
        }
    }
}
