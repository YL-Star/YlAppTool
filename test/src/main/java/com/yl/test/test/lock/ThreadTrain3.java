package com.yl.test.test.lock;

import org.junit.Test;

/**
 * 这段代码最后会出现数据冲突的情况，因为两个线程拿到的不是同一把锁，也证明了同步函数锁的是this。
 * 明白了同步函数的锁是this，那么加上static以后，锁的对象会不会发生改变，还是依然是this？？？
 * 先锁this，验证是否是this：
 */
public class ThreadTrain3 implements Runnable {
    private int tickets = 100;
    private static Object obj = new Object();
    private static boolean flag = true;
    @Override
    public void run() {
        if (flag) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
//            synchronized (obj) {// 同步代码块  不是同一把锁。
            synchronized (this) {// 同步代码块
                while (tickets > 0) {
                    if (tickets > 0) {
                        System.out.println(Thread.currentThread().getName() + "卖了第" + (100 - tickets + 1) + "张票");
                        tickets--;
                    }
                }
            }
        } else {
            while (tickets > 0) {
                sale();
            }
        }
    }
    public synchronized void sale() {
        if (tickets > 0) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
            System.out.println(Thread.currentThread().getName() + "卖了第" + (100 - tickets + 1) + "张票");
            tickets--;
        }
    }
    @Test
    public static void main(String[] args) {
        ThreadTrain3 tt = new ThreadTrain3();
        Thread th1 = new Thread(tt, "1号窗口");
        Thread th2 = new Thread(tt, "2号窗口");
        th1.start();
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ThreadTrain3.flag = false;
        th2.start();
    }
}