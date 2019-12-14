/*

 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter2;

/**
 * @author yan.zhang
 * @date 2019/10/20 16:21
 */
public class WaitSet {

    private static final Object LOCK = new Object();


    /**
     * 1.所有的对象都会有一个waitSet,用来存放调用了该对象wait方法之后进入block状态线程
     * 2.线程被notify之后，不一定立即得到执行
     * 3.线程从waitSet中被唤醒的顺序不一定是 先进先出，无顺序
     * 4.wait的线程被唤醒后，若要继续执行，必须重新获取锁
     */

    public static void work() {
        synchronized (LOCK) {
            System.out.println("begin...");
            try {
                System.out.println("thread will coming in!");
                /**
                 * 这里wait后被notify后，会继续尝试在synchronized处尝试持有锁，
                 * 但是不会执行    System.out.println("begin...");
                 * 是因为线程会记录执行位置，下次继续执行
                 */
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread will out!");
        }
    }

    /**
     * 注意必须持有LOCK对象的monitor才可以notify
     * synchronized会持有锁
     * <p>
     * This method should only be called by a thread that is the owner
     * of this object's monitor. A thread becomes the owner of the
     * t's monitor in one of three ways:
     * <ul>
     * <li>By executing a synchronized instance method of that object.
     * <li>By executing the body of a {@code synchronized} statement
     *     that synchronizes on the object.
     *  <li>For objects of type {@code Class,} by executing a
     *     synchronized static method of that class.
     * </ul>
     * <p>
     * Only one thread at a time can own an object's monitor.
     */
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> work())
                .start();

        //main线程休眠1s
        Thread.sleep(1000);
        //notify() synchronized见上方注释
        //由主线程notify
        synchronized (LOCK) {
            LOCK.notify();
        }

        /*IntStream.rangeClosed(1, 10)
                .forEach(t -> {
                            new Thread(String.valueOf(t)) {
                                @Override
                                public void run() {
                                    synchronized (LOCK) {
                                        try {
                                            Optional.of(Thread.currentThread().getName() + "will come to wait set!")
                                                    .ifPresent(System.out::println);
                                            //block状态 可以理解为执行wait()方法会将线程至于LOCK对象的waitSet中
                                            LOCK.wait();
                                            Optional.of(Thread.currentThread().getName() + "will leave to wait set!")
                                                    .ifPresent(System.out::println);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }.start();
                        }
                );


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        IntStream.rangeClosed(1, 10)
                .forEach(t -> {
                            synchronized (LOCK) {
                                LOCK.notify();
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                );
*/

    }
}
