/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter4;

/**
 * @author yan.zhang
 * @date 2019/11/2 16:19
 */
public interface LifeCycleListener {

    void onEvent(ObserverRunnable.RunnableEvent runnableEvent);
}
