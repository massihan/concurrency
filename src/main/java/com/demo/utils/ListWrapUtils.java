/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.utils;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yan.zhang
 * @date 2020/8/11 10:20
 */
public class ListWrapUtils {
    public static <T> T safeGetFirst(List<T> list) {
        return safeGet(list, 0);
    }

    public static <T> T safeGet(List<T> list, int i) {
        if (list == null || list.size() <= 0 || i >= list.size()) {
            return null;
        }
        return list.get(i);
    }

    /**
     * 集合切分
     *
     * @param list
     * @param count
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> split(List<T> list, int count) {
        if (list == null || list.size() < 1) {
            return Collections.emptyList();
        }

        int sourceSize = list.size();
        List<List<T>> result;

        if (sourceSize < count) {
            result = new ArrayList<>(1);
            result.add(list);
        } else {
            int slice = sourceSize / count;
            int remain = sourceSize % count;
            int resLen = remain == 0 ? slice : slice + 1;
            result = new ArrayList<>(resLen);

            for (int i = 0; i < slice; i++) {
                ArrayList<T> sliceRes = new ArrayList<>(count);
                sliceRes.addAll(list.subList(i * count, ((i + 1) * count)));
                result.add(sliceRes);
            }

            //余数部分
            if (remain != 0) {
                result.add(list.subList(count * slice, sourceSize));
            }
        }

        return result;
    }
}
