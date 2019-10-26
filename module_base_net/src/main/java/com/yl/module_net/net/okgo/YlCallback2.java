package com.yl.module_net.net.okgo;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yl.module_base_utils.NetWorkUtils;
import com.yl.module_base_utils.myUtils;

import java.lang.reflect.ParameterizedType;

import okhttp3.ResponseBody;

/**
 * IC回调封装
 * 网络请求错误、接口错误、数据解析错误的提示都写在这里了。
 *
 * @author IoCan created at 2016/3/30
 */
public abstract class YlCallback2<T> extends AbsCallback<T> {
    private Context context;
    private String typemsg;
    private Boolean isprogress = true;

    public YlCallback2(Context context, String logmsg) {
        this.context = context;
        typemsg = logmsg;
    }


    public YlCallback2(Context context, String logmsg, Boolean ispro) {
        this.context = context;
        typemsg = logmsg;
        isprogress = ispro;
    }

    @Override
    public void onSuccess(Response<T> r) {
        T response = r.body();
        onSuccess(response);
        myUtils.log(typemsg, "[网络数据-解析后]:" + response.toString());
    }

    public abstract void onSuccess(T t);

    @Override
    public T convertResponse(okhttp3.Response response) {
        ResponseBody body = response.body();
        myUtils.log(typemsg, "[网络数据-解析前]:" + body+"");
        if (body==null) {
            return null;
        }
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return (T) new Gson().fromJson(response.body().charStream(), entityClass);
    }

    @Override
    public void onError(Response<T> e) {

        dismissProgressDialog();
        String errorMsg = e.getException().toString();

        myUtils.log("[-----" + typemsg + "---onError-----]:", errorMsg);
        if (errorMsg.contains("ConnectException")) {
            Toast("网络异常！请检查您的网络...,详情" + errorMsg);
        } else if (errorMsg.contains("SocketTimeoutException")) {
            Toast("网络链接超时,详情" + errorMsg);
        } else if (errorMsg.contains("RuntimeException")) {
            Toast("[" + typemsg + "网络连接异常]：" + errorMsg);
        } else {
            Toast("[" + typemsg + "-错误提示]：" + e.toString());
        }
    }


    @Override
    public void onFinish() {
        super.onFinish();
        dismissProgressDialog();
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);

        boolean isNet = NetWorkUtils.isConnected(context);
        if (!isNet) {
            Toast("网络异常！请检查您的网络...");
        }

        if (isprogress) {
//            getDialog().setLoadingText("加载中...").show();
            showProgressDialog(context, "加载中...");
        }
    }


    protected void Toast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    //加载框变量
    private ProgressDialog progressDialog;

    public void showProgressDialog(Context mContext, String text) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        progressDialog.setMessage(text);    //设置内容
        progressDialog.setCancelable(false);//点击屏幕和按返回键都不能取消加载框
        progressDialog.show();

//        //设置超时自动消失
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //取消加载框
//                if(dismissProgressDialog()){
//                    //超时处理
//                }
//            }
//        }, 60000);//超时时间60秒
    }

    public Boolean dismissProgressDialog() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
                return true;//取消成功
            }
        }
        return false;//已经取消过了，不需要取消
    }


}
