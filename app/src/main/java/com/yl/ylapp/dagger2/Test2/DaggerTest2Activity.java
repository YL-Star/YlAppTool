package com.yl.ylapp.dagger2.Test2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.yl.ylapp.R;

import javax.inject.Inject;

/**
 * View层，负责界面的展示
 */
public class DaggerTest2Activity extends AppCompatActivity implements IView {
    //当一个成员变量被@Inject注解修饰，并且它的类型构造函数也被@Inject注解修饰,dagger2就会自动实例化该成员类型，并注入到该成员变量
    @Inject
    TestPresent mPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        DaggerTestComponent.builder().testModule(new TestModule(this)).build().inject(this);//@Component负责连接起@Inject和@Module注解
        mPresent.updateUI();
    }

    @Override
    public void updateUI(String text) {
        ((TextView) findViewById(R.id.textview)).setText(text);
    }
}