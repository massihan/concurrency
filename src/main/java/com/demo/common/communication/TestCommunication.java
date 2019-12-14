package com.demo.common.communication;


//wait():令当前线程挂起并放弃cpu，同步资源，使别的线程可以访问并且修改共享资源，而当前线程排队等候再次对资源的访问
//notify():唤醒正在排队等待同步资源的线程中优先级最高者，使其结束等待
//notifyAll():唤醒正在排队
//当前线程”在调用wait()/notify()时，必须拥有该对象的同步锁


//使用两个线程打印 1-100 线程1 线程2 交替打印
public class TestCommunication {
    public static void main(String[] args) {
        PrintNumber pn = new PrintNumber();

        new Thread(pn, "thread_01").start();
        new Thread(pn, "thread_02").start();
    }
}

class PrintNumber implements Runnable {

    int num = 1;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                this.notify();//唤醒
                try {
                    Thread.sleep(20);
                    if (num <= 100) {
                        System.out.println(Thread.currentThread().getName() + " num:" + num++);
                    } else {
                        break;
                    }
                    this.wait();//释放锁，等待另一个线程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
