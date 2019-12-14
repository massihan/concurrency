/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter4.observermode;

import java.util.ArrayList;
import java.util.List;


/**
 * @author yan.zhang
 * @date 2019/11/2 12:51
 */
public class Subject {
    private List<Observer> observerList = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void attach(Observer observer) {
        observerList.add(observer);
    }

    public void notifyAllObserver() {
        observerList.stream().forEach(Observer::update);
    }
}
