package com.yl.ylapp.dagger2.Test2;

import dagger.Module;
import dagger.Provides;

/**
 * Module类提供那些没有构造函数的类的依赖，如第三方类库，系统类，接口类
 */
@Module
public class TestModule {
    private IView mView;
    public TestModule(IView iView){
        this.mView=iView;
    }
    //@Provides注解的方法，提供IView类的依赖。
    @Provides
    public IView provideIView(){
        return this.mView;
    }
}