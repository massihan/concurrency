/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter8;

/**
 * @author yan.zhang
 * @date 2019/11/9 18:17
 */
public interface Future<T> {
    /**
     * get方法获取真正的执行结果
     */
    T get() throws InterruptedException;
}
