/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage1.chapter6;

/**
 * @author yan.zhang
 * @date 2019/7/6 16:39
 */
public class ThreadCloseForece {
    public static void main(String[] args) {
        ThreadService threadService = new ThreadService();

        long start = System.currentTimeMillis();
        threadService.executeThread(() -> {
            //处理任务5秒
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadService.shutdown(10000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
