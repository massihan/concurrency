/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter5;

/**
 * @author yan.zhang
 * @date 2019/11/4 20:35
 */
public class Gate {
    private int counter = 0;
    private String name = "Nobody";
    private String address = "Nowhere";


    /**
     * if (name.charAt(0) != address.charAt(0))成立时，可能线程中断，baoBao,beiJin调用pass方法
     * 传入name,address造成线程安全问题
     */
    public synchronized void pass(String name, String address) {
        this.counter++;
        this.name = name;
        this.address = address;
        verify();
    }

    private void verify() {
        if (name.charAt(0) != address.charAt(0)) {
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("*****BROKEN*****" + toString());
        }
    }

    @Override
    public String toString() {
        return "No." + counter + ":" + name + "," + address;
    }

}
