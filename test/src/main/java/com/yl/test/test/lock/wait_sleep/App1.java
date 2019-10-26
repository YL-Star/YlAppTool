package com.yl.test.test.lock.wait_sleep;

import org.junit.Test;

/**
 * wait 用法  释放锁
 * notify不释放锁
 */
public class App1 extends Thread {
    private Object lock;
    public App1(Object lock) {
        super();
        this.lock = lock;
    }
    @Override
    public void run() {
        try {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName()
                                  + " : start to wait.");
                lock.wait();
                System.out.println(Thread.currentThread().getName() 
                                               + " : wait ends, execute again.");
            }
        } catch (Exception e) {}
    }
   static class App2 extends Thread {
        private Object lock;

        public App2(Object lock) {
            super();
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName()
                        + " : Start notify.");
                lock.notify();
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println(Thread.currentThread().getName()
                        + " : notify ends, start to execute again.");
            }
        }

        @Test
        public static void main(String[] args) {
            try {
                Object lock = new Object();
                App1 app1 = new App1(lock);
                app1.start();
                Thread.sleep(5000);
                App2 app2 = new App2(lock);
                app2.start();
            } catch (Exception e) {
            }
        }
    }
}


