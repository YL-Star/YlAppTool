package com.yl.test.test.lock;

import org.junit.Test;

/**
 * 多运行几次，就会出现最后结果有不到1000的情况，也就证明了volatile不会保证原子性。
 */
public class VolatileTest extends Thread{
    private volatile static int count = 0;
//    private static int count = 0;
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            count++;
        }
        System.out.println(Thread.currentThread().getName()+":"+count);
    }
    @Test
    public static void main(String[] args) {
        VolatileTest[] list = new VolatileTest[100];
        for (int i = 0; i < list.length; i++) {
            list[i] = new VolatileTest();
        }
        for (int i = 0; i < list.length; i++) {
            list[i].start();
        }
    }
}