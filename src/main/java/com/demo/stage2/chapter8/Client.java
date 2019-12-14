/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter8;

/**
 * @author yan.zhang
 * @date 2019/11/9 18:18
 */

/**
 * Future:代表获取未来结果的一个凭据
 * FutureTask:将调用逻辑隔离
 * FutureService：桥接 Future和FutureTask
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        FutureService service = new FutureService();
        /*Future<String> future = service.submit(() -> {
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Finished!";
        });
        System.out.println(future.get());*/

        service.submit(() -> {
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Finished!";
        }, System.out::println);

        System.out.println("main Thread do other things!");
        Thread.sleep(2_000);
        System.out.println("main Thread Finished!");



    }
}
