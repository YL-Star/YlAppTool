package com.yl.test;

public class Main {

    public void main(String[] args) {
        String str0 = "ABCDEF";
        String str1 = "A";
        int num = 0;
        char char0 = str0.charAt(0);
        char char1 = str1.charAt(0);
        for (int i = 0; i < str0.length(); i++) {
            if (char0 == char1) {
                num++;
            }
        }
        System.out.print(num + "");
    }
}