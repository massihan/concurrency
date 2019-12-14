/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo;

/**
 * @author yan.zhang
 * @date 2019/9/23 21:39
 */
public class hock {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("the application will be exist!");
            notifyAndRelease();
        }));

        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("system error！");

    }

    private static void notifyAndRelease() {
        System.out.println("notify to admin!");
    }
}
