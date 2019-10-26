package com.yl.test.test.lock.wait_sleep;

import org.junit.Test;

/**
 * wait 用法  释放锁
 * notify不释放锁
 */
public class App_join {
    private Object lock = new Object();

    @Test
    public static void main(String[] args) {
        try {
            thread2.start();
//            Thread.sleep(1000);
            thread1.start();

        } catch (Exception e) {
        }
    }

    static Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                synchronized (this) {
                    System.out.println(Thread.currentThread().getName()
                            + " : start to wait.");
                    thread2.join();

                    System.out.println(Thread.currentThread().getName()
                            + " : wait ends, execute again.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()
                            + " : ---end-----");
                }
            } catch (Exception e) {
            }
        }
    });
    static  Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName()
                        + " : Start notify.");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()
                        + " : notify ends, start to execute again.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()
                        + " : ---end-----");
            }
        }
    });
}


