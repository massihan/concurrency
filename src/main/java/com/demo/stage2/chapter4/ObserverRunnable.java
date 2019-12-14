/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter4;

/**
 * @author yan.zhang
 * @date 2019/11/2 16:02
 */
public abstract class ObserverRunnable implements Runnable {
    private final LifeCycleListener listener;

    public ObserverRunnable(LifeCycleListener listener) {
        this.listener = listener;
    }

    void notifyChange(final RunnableEvent runnableEvent) {
        listener.onEvent(runnableEvent);
    }

    public enum RunnableStatus {
        RUNNING, DONE, ERROR;
    }

    public static class RunnableEvent {
        private final RunnableStatus state;
        private final Thread thread;
        private final Throwable cause;

        public RunnableEvent(RunnableStatus state, Thread thread, Throwable cause) {
            this.state = state;
            this.thread = thread;
            this.cause = cause;
        }

        public RunnableStatus getState() {
            return state;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getCause() {
            return cause;
        }
    }

}
