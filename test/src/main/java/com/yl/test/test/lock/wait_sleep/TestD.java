package com.yl.test.test.lock.wait_sleep;

/**
 * java中的sleep()和wait()的区别
 *
 * @author Hongten
 * @date 2013-12-10
 */
import org.junit.Test;
//https://www.cnblogs.com/loren-Yang/p/7538482.html
//https://www.cnblogs.com/kaituorensheng/p/9795454.html

/**
 * java 中sleep和wait区别
 *
 * 这两个方法来自不同的类分别是Thread和Object
 * 最主要是sleep方法没有释放锁，而wait方法释放了锁，使得其他线程可以使用同步控制块或者方法(锁代码块和方法锁)。
 * wait，notify和notifyAll只能在同步控制方法或者同步控制块里面使用，而sleep可以在任何地方使用(使用范围)
 * sleep必须捕获异常，而wait，notify和notifyAll不需要捕获异常
 */
public class TestD {
    @Test
    public static void main(String[] args) {
        new Thread(new Thread1()).start();
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //wait，notify和notifyAll只能在同步控制方法或者同步控制块里面使用
        // 而sleep可以在任何地方使用(使用范围)
        //Exception in thread "main" java.lang.IllegalMonitorStateException
//        try {
//            TestD.class.wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//
//        }
        new Thread(new Thread2()).start();
    }

    private static class Thread1 implements Runnable {
        @Override
        public void run() {
            synchronized (TestD.class) {
                System.out.println("enter thread1...");
                System.out.println("thread1 is waiting...");
                try {
                    //调用wait()方法，线程会放弃对象锁，进入等待此对象的等待锁定池
                    TestD.class.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 is going on ....");
                System.out.println("thread1 is over!!!");
            }
        }
    }

    private static class Thread2 implements Runnable {
        @Override
        public void run() {
            synchronized (TestD.class) {
                System.out.println("enter thread2....");
                System.out.println("thread2 is sleep....");
                try {
                    //在调用sleep()方法的过程中，线程不会释放对象锁。
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 is going on....");
                System.out.println("thread2 is over!!!");
                //如果我们把代码：TestD.class.notify();给注释掉，即TestD.class调用了wait()方法，但是没有调用notify()
                //方法，则线程永远处于挂起状态。
                TestD.class.notify();
            }
        }
    }
}