/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.singleton;

/**
 * @author yan.zhang
 * @date 2019/10/14 23:16
 */

/**
 * 线程安全，懒加载，无锁效率高
 */
public class SingletonObject2 {

    private SingletonObject2() {

    }


    /**
     * static 可以保证在jvm中唯一性
     * 需要时才会加载
     * 当线程调用getInstance() class InstanceHolder 会主动加载，并初始化构建instance对象
     * <p>
     * java内存模型保证类初始化是线程安全的
     */
    private static class InstanceHolder {
        private static final SingletonObject2 instance = new SingletonObject2();
    }

    private static SingletonObject2 getInstance() {
        return InstanceHolder.instance;
    }
}
