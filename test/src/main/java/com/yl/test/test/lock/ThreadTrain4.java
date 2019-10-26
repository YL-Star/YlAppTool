package com.yl.test.test.lock;

import org.junit.Test;

/**
 * 出现了数据错误，这里我们不做猜测，只做验证，静态的同步函数锁的是当前类的字节码文件，
 */
public class ThreadTrain4 implements Runnable {
    private static int tickets = 100;
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
    public static synchronized void sale() {
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
        ThreadTrain4 tt = new ThreadTrain4();
        Thread th1 = new Thread(tt, "1号窗口");
        Thread th2 = new Thread(tt, "2号窗口");
        th1.start();
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ThreadTrain4.flag = false;
        th2.start();
    }
}