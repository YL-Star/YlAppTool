package com.yl.ylapp;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.lzy.okgo.OkGo;
import com.yl.module_base_utils.ARouter.RouterUtil;
import com.yl.module_base_utils.ModuleUtil;
import com.yl.ylapp.dagger2.Test.AppComponent;

/**
 * Created by yanglei on 2019/3/14;
 */
public class MyApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        ModuleUtil.init(this);
        OkGo.getInstance().init(this);
        RouterUtil.init(this);
//        appComponent=DaggerAppComponent.builder()
//                .appModule(new AppModule(this))
//                .build();
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
    private AppComponent appComponent;


    public static Context getContext(){
        return context;
    }




    public AppComponent getAppComponent() {
        return appComponent;
    }
}
