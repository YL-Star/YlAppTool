package com.yl.ylapp.dagger2.Test2;

import javax.inject.Inject;

/**
 * Present类，调用Model层的业务方法，更新View层的界面展示
 */
public class TestPresent {
    IView mView;
    @Inject
    TestModel mModel;//Dagger2遇到@Inject标记的成员属性，就会去查看该成员类的构造函数，如果构造函数也被@Inject标记,则会自动初始化，完成依赖注入。

    //TestPresent的构造函数也被@Inject注解修饰
    @Inject
    public TestPresent(IView view) {
        this.mView = view;
    }

    public void updateUI() {
        mView.updateUI(mModel.getText());
    }
}