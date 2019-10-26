package com.yl.test.test.lock.wait_sleep;
import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AppOfficial {

    /**
     * BoundedBuffer 是一个定长100的集合，当集合中没有元素，take方法需要等待，直到有元素时才返回元素
     * 当集合满了，要等待直到元素被take之后才执行put的操作
     */
    static class BoundedBuffer {
     //定义一个锁对象
        final Lock lock = new ReentrantLock();
     //定义两种被唤醒的条件：非满、非空
        final Condition notFull = lock.newCondition();
        final Condition notEmpty = lock.newCondition();

        final Object[] items = new Object[100];
        int putptr, takeptr, count;

        public void put(Object x) throws InterruptedException {
            System .out.println("put wait lock");
        //加锁进行put操作
            lock.lock();
            System.out.println("put get lock");
            try {
              //当集合满了，则等待 非满 情况的发生
                while (count == items.length) {
                    System.out.println("buffer full, please wait");   

                  //在 非满 条件下挂起，等待非满条件的唤醒操作才继续执行下去
                    notFull.await();
                }
                    
                items[putptr] = x;
                if (++putptr == items.length)
                    putptr = 0;
                ++count;
            //执行完插入操作后，集合非空，通知 非空 条件下挂起的线程 可以继续进行take操作了
                notEmpty.signal();
            } finally {
                lock.unlock();
            }
        }




        public Object take() throws InterruptedException {
            System.out.println("take wait lock");
            lock.lock();
            System.out.println("take get lock");
            try {
                while (count == 0) {
                    System.out.println("no elements, please wait");                             
                   //在 非空 条件下挂起
                    notEmpty.await();
                }
                Object x = items[takeptr];
                if (++takeptr == items.length)
                    takeptr = 0;
                --count;
                //集合不是满的了，唤醒在 非满 情况下挂起的put线程可以继续执行put操作
                notFull.signal();
                return x;
            } finally {
                lock.unlock();
            }
        }
    }
    @Test
    public static void main(String[] args) {
        final BoundedBuffer boundedBuffer = new BoundedBuffer();
        
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 run");
                for (int i=0;i<1000;i++) {
                    try {
                        System.out.println("putting..");
                        boundedBuffer.put(Integer.valueOf(i));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            
        }) ;
        
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<1000;i++) {
                    try {
                        Object val = boundedBuffer.take();
                        System.out.println(val);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            
        }) ;
        
        t1.start();
        t2.start();
    }
}