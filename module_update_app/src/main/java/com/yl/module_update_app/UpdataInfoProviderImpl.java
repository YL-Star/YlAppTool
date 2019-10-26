package com.yl.module_update_app;

import android.app.Activity;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yl.module_base_utils.ARouter.provider.RouterProvider;
import com.yl.module_update_app.bean.UpdataInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanglei on 2019/5/16;
 */
@Route(path = "/update_app/UpdataInfoProviderImpl")
public class UpdataInfoProviderImpl extends RouterProvider {
    @Nullable
    @Override
    public Map<String, Object> doAction(@Nullable Activity activity, Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        result.put("UpdataInfo", UpdataInfo.class);
        return result;
    }

    /**
     *  {
     *         Map<String, Object> ress = RouterUtil.exec(null,
     *                 "/update_app/UpdataInfoProviderImpl");
     *         UpdataInfo updataInfo =(UpdataInfo)ress.get("UpdataInfo");
     *     }
     */
}
