package com.yl.ylapp.dagger2.Test2;

import dagger.Component;

/**
 *Component必须是一个接口类或者抽象
 */
@Component(modules = TestModule.class)
public interface TestComponent {
    void inject(DaggerTest2Activity testActivity);
}