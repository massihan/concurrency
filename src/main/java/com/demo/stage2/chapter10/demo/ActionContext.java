/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter10.demo;

/**
 * @author yan.zhang
 * @date 2019/11/23 20:42
 */
public class ActionContext {
    private ActionContext() {
    }

    //持有Context对象
    private static final ThreadLocal<Context> threadLocal = new ThreadLocal<Context>() {
        @Override
        protected Context initialValue() {
            System.out.println("创建了一个新的context");
            return new Context();
        }
    };

    //ActionContext单例
    private static class ContextHolder {
        private static final ActionContext actionContext = new ActionContext();
    }

    public static ActionContext getActionContext() {
        return ContextHolder.actionContext;
    }

    public Context getContext() {
        System.out.println("threadLocal: " + threadLocal);
        return threadLocal.get();
    }

}
