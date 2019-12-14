package com.demo.stage2.singleton;

/**
 * 多线程下的单例模式(懒汉式)
 * 1.构造器私有，避免new对象
 * 2.提供私有静态属性，存储对象
 * 3.提供公共静态方法访问，获取属性
 */

public class DoubleCheckedLocking {

    //使用volatile避免指令重排
    private static volatile DoubleCheckedLocking instance;

    private DoubleCheckedLocking() {
    }

    public static DoubleCheckedLocking getInstance() {

        //已经存在对象，避免不必要的同步
        if (null != instance) {
            return instance;
        }
        synchronized (DoubleCheckedLocking.class) {
            //3.这里有可能发生指令重排
            if (null == instance) {
                instance = new DoubleCheckedLocking();
                //new一个对象 1.开辟空间 2.初始化对象信息 3.返回对象的地址给引用
                //假设new这个对象很耗时(可能存在大量成员变量)，可能存在先执行3，再执行2
                //ag:A线程在初始化对象，3步骤先于2执行，先返回引用，假设此时B线程执行if判断，则返回instance，
                // 但这里instance并没有完成初始化，在使用中可能会产生NPE异常
            }
        }
        return instance;
    }
}
