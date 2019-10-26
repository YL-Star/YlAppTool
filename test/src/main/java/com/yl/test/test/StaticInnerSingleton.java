package com.yl.test.test;

/**
 * 静态内部类的单例模式
 */
public final class StaticInnerSingleton {

    private StaticInnerSingleton() {
        if (SingletonHolder.instance != null) {
            throw new IllegalStateException();
        }
    }

    private static class SingletonHolder {
        private static StaticInnerSingleton instance = new StaticInnerSingleton();
    }

    public static StaticInnerSingleton getInstance() {
        return SingletonHolder.instance;
    }

}