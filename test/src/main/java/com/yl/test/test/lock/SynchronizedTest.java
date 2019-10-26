package com.yl.test.test.lock;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedTest {
    @Test
    public static void main(String[] args) {
        MyThread1 t1 = new MyThread1();
        new Thread(t1, "t11").start();
        new Thread(t1, "t12").start();
        MyThread2 t2 = new MyThread2();
        new Thread(t2, "t21").start();
        new Thread(t2, "t22").start();
    }

    // 修饰普通方法，此时用的锁是对象(内置锁)
    public synchronized void test1() {
        // doSomething
    }

    public void test2() {
        // 修饰代码块，此时用的锁是对象(内置锁)--->this
        // 我们使用synchronized修饰代码块时未必使用this，还可以使用其他的对象(随便一个对象都有一个内置锁)
        synchronized (this) {
//              doSomething
        }
    }
}

class MyThread1 implements Runnable {
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 10; i++)
                System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}

class MyThread2 implements Runnable {
    private ReentrantLock lock = new ReentrantLock();

    public void run() {
        lock.lock();
        try {
            for (int i = 0; i < 5; i++)
                System.out.println(Thread.currentThread().getName() + ":" + i);
        } finally {
            lock.unlock();
        }
    }
}

