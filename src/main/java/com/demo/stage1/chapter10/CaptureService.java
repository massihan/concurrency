/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage1.chapter10;

/**
 * @author yan.zhang
 * @date 2019/9/22 17:04
 */

import java.util.*;

/**
 * 模拟实现线程池
 */
public class CaptureService {
    /**
     * controls 用于控制多个线程
     */
    private static LinkedList<Control> controls = new LinkedList<>();


    public static void main(String[] args) {
        List<Thread> worker = new ArrayList<>();
        //定义启动线程
        Arrays.asList("M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10")
                .stream()
                .map(CaptureService::createCaptureThread)
                .forEach(t -> {
                    t.start();
                    worker.add(t);
                });

        //定义工作线程用于处理任务
        worker.stream().forEach(t -> {
            try {
                /**
                 * join Main线程
                 * 模拟场景：子线程采集数据，子线程采集完成由main线程汇总的场景
                 */
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //子线程执行完全部任务，main线程打印
        Optional.of("All of capture work finished!").ifPresent(System.out::println);

    }

    private static Thread createCaptureThread(String name) {
        return new Thread(() -> {
            Optional.of("the work [" + Thread.currentThread().getName() + "] begin capture data...").ifPresent(System.out::println);
            synchronized (controls) {
                /**
                 * 当大于1个工作线程执行任务，新的子线程则会wait
                 * 即：线程池中有10个线程，但是只能有一个线程在执行任务
                 */
                while (controls.size() >= 1) {
                    try {
                        controls.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //并发执行，synchronized保护controls变量
                controls.addLast(new Control());
            }
            Optional.of("the work [" + Thread.currentThread().getName() + "] is working...").ifPresent(System.out::println);
            //模拟执行任务
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //任务执行完成
            synchronized (controls) {
                Optional.of("the work [" + Thread.currentThread().getName() + "] end capture data...").ifPresent(System.out::println);
                controls.removeFirst();
                //controls移除一个，唤醒其他线程
                controls.notifyAll();
            }
        }, name);

    }

    private static class Control {
    }
}
