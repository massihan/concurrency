package com.demo.common.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 相较于Runable接口，方法可以有返回值，可以抛出异常
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadCallable tc = new ThreadCallable();
        //需要多一步接收返回值的步骤  需要futureTask实现类的支持，用于接收运算结果
        FutureTask<Integer> result = new FutureTask<>(tc);

        new Thread(result).start();
        System.out.println("------------");
        //接收结果
        Integer sum = result.get();  //闭锁 这里子线程执行完才会执行这里
        System.out.println("------------");

        System.out.println(sum);

    }

}

class ThreadCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }
}

