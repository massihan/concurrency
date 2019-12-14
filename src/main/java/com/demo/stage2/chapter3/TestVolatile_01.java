package com.demo.stage2.chapter3;

/**
 * 原因分析：
 * jvm为每一个线程开辟一块缓存，线程1将主存中 共享数据  copy到线程1缓存中，修改后再写回主存
 * 在while(true)循环中 线程2没有机会从主存中获取最新的共享变量
 * <p>
 * 当多个线程操作共享数据时，彼此不可见
 * 解决：
 * synchronized   //效率低
 * volatile：当多个线程操作共享数据时，保证内存中数据是可见的，可以近似的理解为，被volatile声明的共享变量，
 * 修改操作是在主存中进行的。注意：JVM有底层优化，重排序，使用volatile不会重排序
 * <p>
 * 注意：volatile不具备互斥性   并且不能保证变量的原子性。
 * synchronized具备互斥性
 */


public class TestVolatile_01 {

    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();
        //循环判断flag标识
        while (true) {
//            synchronized(td){
            if (td.isFlag()) {
                System.out.println("-------------");
                break;
            }
//            }
        }
    }
}

class ThreadDemo implements Runnable {


    private boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
            flag = true;
            System.out.println("flag is " + flag);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
