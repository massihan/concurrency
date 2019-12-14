/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage1.chapter6;

/**
 * @author yan.zhang
 * @date 2019/7/6 16:13
 */

public class ThreadService {
    //ThreadService实现暴力结束线程

    private Thread executeThread;
    private boolean finished = false;

    /**
     * 执行线程
     * task为任务线程
     * 执行线程超过指定时间会结束
     */
    public void executeThread(final Runnable task) {
        executeThread = new Thread() {
            @Override
            public void run() {
                Thread runner = new Thread(task);
                //设置runner为守护线程 executeThread执行结束  守护线程结束
                runner.setDaemon(true);
                runner.start();
                try {
                    //等待runner线程执行结束
                    runner.join();
                    finished = true;
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            }
        };
        executeThread.start();
    }

    public void shutdown(long mills) {
        long currentMills = System.currentTimeMillis();
        while (!finished) {
            //任务没有执行结束
            if ((System.currentTimeMillis() - currentMills) >= mills) {
                //执行超时，强制终端执行线程
                System.out.println("执行超时，强制中断！");
                executeThread.interrupt();
                break;
            }
            //短暂休眠
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断！");
                break;
            }
        }
        finished = false;
    }
}
