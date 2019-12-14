/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage1.chapter13;

/**
 * @author yan.zhang
 * @date 2019/10/13 14:47
 */


/**
 * commit任务超过任务队列大小
 * 定义任务丢弃策略
 */
@FunctionalInterface
public interface DiscardPolicy {
    void discard() throws DiscardException;

}
