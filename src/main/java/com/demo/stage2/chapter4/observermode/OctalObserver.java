/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter4.observermode;

/**
 * @author yan.zhang
 * @date 2019/11/2 13:01
 */
public class OctalObserver extends Observer {

    public OctalObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("octal String " + Integer.toOctalString(subject.getState()));
    }
}

