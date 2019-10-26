package com.yl.test.test.lock;

import org.junit.Test;

/**
 * 多线程死锁
 * 同步中嵌套同步，锁没有来得及释放，一直等待，就导致死锁。
 * 下面这段代码，多运行几次就会出现死锁，思路是开启两个线程，
 * 让这两个线程执行的代码获取的锁的顺序不同，第一个线程需要先获得obj对象锁，
 * 然后再获得this锁，才可以执行代码，然后释放两把锁。线程2需要先获得this锁，
 * 再获取obj对象锁才可执行代码，然后释放两把锁。但是，当线程1获得了obj锁之后，
 * 线程2获得了this锁，这时候线程1需要获得this锁才可执行，但是线程2也无法获取到obj对象锁执行代码并释放，
 * 所以两个线程都拿着一把锁不释放，这就产生了死锁。
 */

public class ThreadTrain6 implements Runnable {
    private static int tickets = 100;
    private static Object obj = new Object();
    private static boolean flag = true;

    @Override
    public void run() {
        if (flag) {
            while (true) {
                System.out.println("111111");
                synchronized (obj) {// 同步代码块
                    sale();
                }
            }
        } else {

            while (true) {
                System.out.println(222222);
                sale();
            }
        }
    }

    public synchronized void sale() {
        synchronized (obj) {
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
    }

    @Test
    public static void main(String[] args) {
        ThreadTrain6 tt = new ThreadTrain6();
        Thread th1 = new Thread(tt, "1号窗口");
        Thread th2 = new Thread(tt, "2号窗口");
        th1.start();
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ThreadTrain6.flag = false;
        System.out.println(flag);
        th2.start();
    }
}