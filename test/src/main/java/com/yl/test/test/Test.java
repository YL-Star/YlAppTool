package com.yl.test.test;

import android.graphics.BitmapFactory;

/**
 * @date: $date$ $time$
 * @author: yanglei
 * @description
 */
public class Test {
    char[] value = new char[5];
    final char[] values = new char[0];
    public static final String name = "sam";
    private volatile String age = "";

    @org.junit.Test
    public void Test() {
        StringBuffer sb = new StringBuffer();
        String s = "s";
        value[0] = 'a';
        value[1] = 'b';
        for (int i = 0; i < value.length; i++) {
            System.out.println(value[i]);
        }
//        values[0]='c';
//        values[1]='d';
        char c = 'a';
        char b;
        b = 'b';
        StaticInnerSingleton staticInnerSingleton = StaticInnerSingleton.getInstance();
        long freeMemory = Runtime.getRuntime().freeMemory();
        System.out.println("FREE_MEMORY = " + freeMemory + "(字节)" + (freeMemory / (double) 1024 / 1024) + "MB");

    }
}
