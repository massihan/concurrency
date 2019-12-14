/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.singleton;

/**
 * @author yan.zhang
 * @date 2019/10/14 23:02
 */
public class SingletonObject1 {

    //解决指令重排引起的空指针问题

    //volatile 可以保证内存可见性和有序性
    private static volatile SingletonObject1 instance;

    private SingletonObject1() {

    }

    public SingletonObject1 getInstance() {

        if (null == instance) {
            synchronized (SingletonObject1.class) {
                if (null == instance) {
                    return new SingletonObject1();
                }
            }
        }
        return instance;
    }
}
