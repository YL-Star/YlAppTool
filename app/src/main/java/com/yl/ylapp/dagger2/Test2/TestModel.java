package com.yl.ylapp.dagger2.Test2;

import javax.inject.Inject;

/**
 * Model类，实现具体的业务逻辑
 */
public class TestModel {
    //构造函数用@Inject修饰
    @Inject
    public TestModel(){
    }
    public String getText(){
        return "Dagger2应用实践...";
    }
}