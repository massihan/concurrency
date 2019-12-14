/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter10.demo;

import java.util.stream.IntStream;

/**
 * @author yan.zhang
 * @date 2019/11/23 21:17
 */
public class ContextTest {
    public static void main(String[] args) {
        IntStream.range(1, 2).forEach(i -> new Thread(new ExecutionTask()).start());
    }
}
