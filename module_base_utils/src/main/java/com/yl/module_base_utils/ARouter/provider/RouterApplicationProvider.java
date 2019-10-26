package com.yl.module_base_utils.ARouter.provider;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Created by masonliu on 2000/4/13.
 */
public abstract class RouterApplicationProvider implements IProvider {
    private Context context;

    public abstract void onCreate(Application application);

    @Override
    public void init(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
}
