/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */

/**
 * @author yan.zhang
 * @date 2019/11/21 9:49
 */
public class Demo {
    public static void main(String[] args) throws InterruptedException {
//        demo1();

        //线程中断状态 线程不活动，isInterrupted(),interrupted() 均返回false
        demo2();
    }


    public static void demo1() throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("i=" + (i + 1));
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "被中断");

                    //isInterrupted() 测试此线程是否已被中断，线程的中断状态不受此方法的影响，线程中断被忽略，因为在中断时线程不活动，此方法返回false
                    System.out.println("第一次调用thread.isInterrupted()：" + Thread.currentThread().isInterrupted());
                    System.out.println("第二次调用thread.isInterrupted()：" + Thread.currentThread().isInterrupted());

                    System.out.println("===================================================================");

                    //interrupted() 测试当前线程是否已经被中断，此方法清除线程的中断状态，如果连续两次调用此方法，第二次返回false
                    System.out.println("第一次调用thread.interrupted()：" + Thread.interrupted());
                    System.out.println("第二次调用thread.interrupted()：" + Thread.interrupted());

                    System.out.println("===================================================================");

                    System.out.println("thread是否存活：" + Thread.currentThread().isAlive());
                    break;
                }

            }
        });
        thread.start();
        thread.interrupt();

        Thread.sleep(1_000);
        System.out.println("thread是否存活：" + thread.isAlive());
    }

    private static void demo2() throws InterruptedException {

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "被中断");

                //isInterrupted() 测试此线程是否已被中断，线程的中断状态不受此方法的影响，线程中断被忽略，因为在中断时线程不活动，此方法返回false
                System.out.println("第一次调用thread.isInterrupted()：" + Thread.currentThread().isInterrupted());
                System.out.println("第二次调用thread.isInterrupted()：" + Thread.currentThread().isInterrupted());

                System.out.println("===================================================================");

                //interrupted() 测试当前线程是否已经被中断，此方法清除线程的中断状态，如果连续两次调用此方法，第二次返回false
                System.out.println("第一次调用thread.interrupted()：" + Thread.interrupted());
                System.out.println("第二次调用thread.interrupted()：" + Thread.interrupted());

                System.out.println("===================================================================");

                System.out.println("thread是否存活：" + Thread.currentThread().isAlive());
            }
        });

        thread.start();
        thread.interrupt();

        Thread.sleep(1_000);
        System.out.println("thread是否存活：" + thread.isAlive());
    }

}
