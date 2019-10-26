package com.yl.test.test.lock;

/**
 * 不加锁
 * 车票卖重复了。。。
 */
public class ThreadTrain implements Runnable {
    private int tickets = 50;

    @Override
    public void run() {
        while (tickets > 0) {
            if (tickets > 0) {
                System.out.println(Thread.currentThread().getName() + "卖了第" + (50 - tickets + 1) + "张票");
                tickets--;
            }
        }
    }


    public static void main(String[] args) {
        ThreadTrain tt = new ThreadTrain();
        Thread th1 = new Thread(tt, "1号窗口");
        Thread th2 = new Thread(tt, "2号窗口");
        th1.start();
        th2.start();
    }
}