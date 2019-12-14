/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter10.demo;

/**
 * @author yan.zhang
 * @date 2019/11/23 20:52
 */
public class ExecutionTask implements Runnable {

    private QueryFromDBAction dbAction = new QueryFromDBAction();
    private QueryFromHttpAction httpAction = new QueryFromHttpAction();


    @Override
    public void run() {
        Context context = ActionContext.getActionContext().getContext();
        dbAction.execute();
        httpAction.execute();
        System.out.println("the name is " + context.getName() + " the idCard is " + context.getCardId());
//        System.out.println(ActionContext.getActionContext());
//        System.out.println(context);
    }
}
