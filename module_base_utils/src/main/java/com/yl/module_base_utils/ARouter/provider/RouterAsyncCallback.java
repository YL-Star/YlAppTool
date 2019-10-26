package com.yl.module_base_utils.ARouter.provider;

import android.support.annotation.Nullable;

import java.util.Map;

/**
 * Created by masonliu on 2000/3/13.
 */

public interface RouterAsyncCallback {
    void onStart();

    void onSuccess(@Nullable Map<String, Object> result);

    void onFailed(@Nullable Map<String, Object> error);

    void onFinish();
}
