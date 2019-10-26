package com.yl.test.test.lock.wait_sleep;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * wait 用法
 */
public class App_lock extends Thread {
    private Lock reentrantLock = new ReentrantLock();
    private Condition condition = reentrantLock.newCondition();
    private Object lock;
    public App_lock(Object lock) {
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName()
                    + " : start to wait.");
            condition.wait();
            System.out.println(Thread.currentThread().getName()
                    + " : wait ends, execute again.");

        } catch (Exception e) {

        } finally {
            reentrantLock.unlock();
        }
    }

    static class App2 extends Thread {
        private Lock reentrantLock = new ReentrantLock();
        private Object lock;
        private Condition condition = reentrantLock.newCondition();
        public App2(Object lock) {
            super();
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                reentrantLock.lock();
                System.out.println(Thread.currentThread().getName()
                        + " : Start notify.");
                condition.signal();
                System.out.println(Thread.currentThread().getName()
                        + " : notify ends, start to execute again.");
            } catch (Exception e) {

            } finally {
                reentrantLock.unlock();
            }

        }


        @Test
        public static void main(String[] args) {
            try {
                Object lock = new Object();
                App_lock app1 = new App_lock(lock);
                app1.start();
                Thread.sleep(5000);
                App2 app2 = new App2(lock);
                app2.start();
            } catch (Exception e) {
            }
        }
    }
}


