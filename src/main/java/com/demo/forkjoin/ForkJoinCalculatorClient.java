/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.forkjoin;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * @author yan.zhang
 * @date 2020/9/26 23:01
 */
public class ForkJoinCalculatorClient {
    public static void main(String[] args) {
        long[] array = LongStream.rangeClosed(0, 10000000).toArray();
        Calculator calculator = new ForkJoinCalculator();
        Instant start = Instant.now();
        System.out.println(calculator.sumUp(array));
        Instant end = Instant.now();

        System.out.println("耗时：" + Duration.between(start, end).toMillis());
    }
}
