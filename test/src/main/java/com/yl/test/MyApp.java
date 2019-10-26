package com.yl.test;

import android.app.Application;

import com.yl.module_base_utils.ARouter.RouterUtil;
import com.yl.module_base_utils.ModuleUtil;

/**
 * Created by yanglei on 2019/5/16;
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ModuleUtil.init(this);
        RouterUtil.init(this);
    }
}
