/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter4.observermode;

/**
 * @author yan.zhang
 * @date 2019/11/2 13:04
 */
public class ObserverClient {
    public static void main(String[] args) {
        Subject subject = new Subject();

        BinaryObserver bo = new BinaryObserver(subject);
        OctalObserver oo = new OctalObserver(subject);

        subject.setState(10);
        subject.notifyAllObserver();
    }
}
