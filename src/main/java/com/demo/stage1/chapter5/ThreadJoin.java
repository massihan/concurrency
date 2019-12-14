/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage1.chapter5;

/**
 * @author yan.zhang
 * @date 2019/6/30 15:33
 */
public class ThreadJoin {
    /**
     * join用法
     */
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread t1 = new Thread(new CaptureRunnable("machine1", 10 * 1000));
        Thread t2 = new Thread(new CaptureRunnable("machine2", 15 * 1000));
        Thread t3 = new Thread(new CaptureRunnable("machine3", 20 * 1000));
        long endTime = System.currentTimeMillis();

        t1.start();
        t2.start();
        t3.start();

        //join():插入当前线程前执行，等待t1线程执行完毕，当前线程才开始执行--->这里是main线程
        t1.join();
        t2.join();
        t3.join();

        //这种情况 t1,t2,t3线程没执行完 main线程已经结束
        System.out.printf("save data begin timestamp is:%s, end timestamp is:%s", startTime, endTime);
    }
}

class CaptureRunnable implements Runnable {

    private String machineName;
    private long spendTime;

    public CaptureRunnable(String machineName, long spendTime) {
        this.machineName = machineName;
        this.spendTime = spendTime;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(spendTime);
            System.out.println(machineName + "  completed data capture and successfully!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public String getResult() {
        return machineName + "finish";
    }

}
