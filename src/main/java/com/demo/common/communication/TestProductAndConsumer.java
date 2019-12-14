package com.demo.common.communication;

/**
 * 生产者消费者案例：
 */
public class TestProductAndConsumer {
    public static void main(String[] args) {
        Clerk c = new Clerk();

        Producer pro = new Producer(c);
        Consumer con = new Consumer(c);

        new Thread(pro, "生产者").start();
        new Thread(con, "消费者").start();

    }
}

//店员：具备进货和售货功能
class Clerk {
    private int product = 0;

    //进货
    public synchronized void get() throws InterruptedException {
        if (product >= 1) {
            System.out.println("产品已满");
            this.wait();
        } else {
            System.out.println(Thread.currentThread().getName() + " : " + ++product);
            this.notifyAll();
        }
    }

    //售货
    public synchronized void sale() throws InterruptedException {
        if (product <= 0) {
            System.out.println("产品缺货");
            this.wait();
        } else {
            System.out.println(Thread.currentThread().getName() + " : " + --product);
            this.notifyAll();
        }
    }
}

//生产者：生产出产品给店员
class Producer implements Runnable {
    Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20000; i++) {
            //调用店员进货方法，进货20次
            try {
                clerk.get();
            } catch (InterruptedException e) {

            }
        }
    }
}


//消费者
class Consumer implements Runnable {
    Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20000; i++) {
            //调用店员销售方法，消费20次
            try {
                Thread.sleep(200);
                clerk.sale();
            } catch (InterruptedException e) {

            }
        }
    }
}
