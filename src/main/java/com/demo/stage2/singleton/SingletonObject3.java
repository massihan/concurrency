/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.singleton;

/**
 * @author yan.zhang
 * @date 2019/10/26 22:28
 */
public class SingletonObject3 {

    private enum Singleton {
        INSTANCE;
        private final SingletonObject3 instance;

        Singleton() {
            instance = new SingletonObject3();
        }

        public SingletonObject3 getInstance() {
            return instance;
        }
    }

    public static SingletonObject3 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

}
