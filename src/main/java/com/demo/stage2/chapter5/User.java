/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter5;

/**
 * @author yan.zhang
 * @date 2019/11/4 21:02
 */
public class User extends Thread {
    private final String myName;
    private final String myAddress;
    private final Gate gate;

    public User(String myName, String myAddress, Gate gate) {
        this.myName = myName;
        this.myAddress = myAddress;
        this.gate = gate;
    }

    @Override
    public void run() {
        while (true) {
            this.gate.pass(myName, myAddress);
        }
    }
}
