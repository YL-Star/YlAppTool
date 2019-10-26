package com.yl.module_base_utils.ARouter.provider;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.template.IProvider;

import java.util.Map;

/**
 * Created by masonliu on 2000/4/13.
 */
public abstract class RouterAsyncProvider implements IProvider {
    private Context context;

    @Nullable
    public abstract void doAction(@Nullable Activity activity, Map<String, String> params, RouterAsyncCallbackWrapper callback);

    @Override
    public void init(Context context) {
        this.context = context.getApplicationContext();
    }

    public Context getContext() {
        return context;
    }
}
