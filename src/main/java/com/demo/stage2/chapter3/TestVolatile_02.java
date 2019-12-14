/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter3;

/**
 * @author yan.zhang
 * @date 2019/10/22 22:13
 */


/**
 * volatile使用场景
 * 1.状态量标记
 * //保证start每次都从主内存中读取
 * volatile boolean start = true;
 * while(start) {...}
 *
 * void close(){start = false;}
 * <p>
 * <p>
 * 2.保障前后一致性，即不会被编译器优化
 * <p>
 * 3.volatile boolean init;
 * ------------Thread 1-----------
 * //....
 * obj = createObj;
 * //volatile可以保证init=true在obj=createObj之后执行
 * init = true;
 * ------------Thread-2-----------
 * while(!init){
 * sleep();
 * }
 * useObj；
 */
public class TestVolatile_02 {
    /**
     * 共享数据
     */
    private static int INIT_VALUE = 0;
    //    private volatile static int INIT_VALUE = 0;
    private static int MAX_VALUE = 5;

    public static void main(String[] args) {
        //读线程
        new Thread(() -> {
            int localVal = INIT_VALUE;
            while (localVal < MAX_VALUE) {
                //localVal 无法感知到 INIT_VALUE的变化
                if (localVal != INIT_VALUE) {
                    System.out.printf("the value updated to [%d]\n", INIT_VALUE);
                    localVal = INIT_VALUE;
                    INIT_VALUE = localVal;
                }
            }
        }, "READ").start();

        //写线程
        new Thread(() -> {
            int localVal = INIT_VALUE;
            while (localVal < MAX_VALUE) {
                try {
                    System.out.printf("updated value to [%d]\n", ++localVal);
                    INIT_VALUE = localVal;
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "UPDATER").start();

    }
}
