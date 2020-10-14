/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.mock;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author yan.zhang
 * @date 2020/10/14 22:44
 */
public class BigCollectionMock {
    public static List<Integer> mock() {
        int[] ints = IntStream.rangeClosed(1, 1000 * 10).toArray();
        return Arrays.stream(ints).boxed().collect(Collectors.toList());
    }

}
