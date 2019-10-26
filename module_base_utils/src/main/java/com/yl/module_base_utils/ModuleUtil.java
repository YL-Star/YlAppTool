package com.yl.module_base_utils;

import android.content.Context;

/**
 * Created by yanglei on 2019/3/14;
 */
public class ModuleUtil {
    public static Context context;
    private static ModuleUtil moduleUtil;

    public ModuleUtil(Context context) {
        this.context = context;
    }

    public static ModuleUtil init(Context context) {
        if (moduleUtil == null) {
            synchronized (ModuleUtil.class) {
                if (moduleUtil == null) {

                    moduleUtil = new ModuleUtil(context);
                }
            }
        }
        return moduleUtil;
    }

    public static Context getContext() {
        return context;
    }
}
