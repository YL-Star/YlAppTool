package com.yl.test.test.lock;

import org.junit.Test;

public class ThreadTrain2 implements Runnable {
    private int tickets = 50;
    private static Object obj = new Object();//锁的对象，可以是任意的对象

    @Override
    public void run() {
        while (tickets > 0) {
            synchronized (obj) {// 同步代码块
                if (tickets > 0) {
                    System.out.println(Thread.currentThread().getName() + "卖了第" + (50 - tickets + 1) + "张票");
                    tickets--;
                }
            }
        }
    }

    @Test
    public static void main(String[] args) {
        ThreadTrain2 tt = new ThreadTrain2();
        Thread th1 = new Thread(tt, "1号窗口");
        Thread th2 = new Thread(tt, "2号窗口");
        th1.start();
        th2.start();
    }
}