package com.yl.module_net.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;


import com.yl.module_base_utils.CheckNet;
import com.yl.module_base_utils.myUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by ziabo on 2017/5/9.
 * 结果回调回来之后的接口的实现类
 * 有兴趣的话可以翻阅这里 http://reactivex.io/documentation/observable.html
 */

public abstract class MyObserver<R> implements Observer<R> {
    private String typemsg;
    private Context context;
    private Boolean isProgress = true;

    public MyObserver(Context context, String logmsg) {
        typemsg = logmsg;
        this.context = context;

    }

    public MyObserver(Context context, String logmsg, Boolean isPg) {
        typemsg = logmsg;
        this.context = context;
        isProgress = isPg;
    }

    /**
     * 建立链接的时候调用并生成Disposable对象,此处相当于1.x的onStart()方法我做了如下处理
     * 有更好建议的可以私聊我,或者评论
     *
     * @param d 链接状态对象
     */
    @Override
    public void onSubscribe(Disposable d) {
        if (!CheckNet.isNetworkConnected()) {
            if (d != null && !d.isDisposed()) {
                d.dispose();
            }
            Toast("[" + typemsg + "]网络连接异常，请检查您的网络连接...");

            onFinished();
        } else {
            getDisposable(d);
            if (isProgress) showProgressDialog("加载中...");
        }
    }


    /**
     * 此处和1.x的onNext()基本没有什么变化,所以我选择注释,让实现类自己处理
     * 之前我是写了的,看过这篇博客的应该有印象
     *
     * @param r 返回的结果,没网络时提示
     */
//    @Override
//    public void onNext(R r) {
//
//    }
    //
//    public abstract void onSuccess(R r);

    /**
     * 出现异常的时候会走这里,我们统一放在 onFinished();处理
     */
    @Override
    public void onError(Throwable e) {
        onFinished();
        //e instanceof HttpException ||
        if ( e instanceof ConnectException
                || e instanceof SocketTimeoutException
                || e instanceof TimeoutException) {
            onNetworkException(e);
        }else {
            onUnknownException(e);
        }
    }

    /**
     * 不管成功与失败,这里都会走一次,所以加onFinished();方法
     */
    @Override
    public void onComplete() {
        onFinished();
    }

    /**
     * 请求结束之后的回调,无论成功还是失败,此处一般无逻辑代码,经常用来写ProgressBar的dismiss
     */
    public void onFinished() {
        dismissProgressDialog();
    }

    /**
     * 向子类暴露 Disposable
     */
    public abstract void getDisposable(Disposable disposable);

    private void onNetworkException(Throwable e) {
        e.printStackTrace();
        String errorMsg = e.toString();
        Toast("[" + typemsg + "]网络异常！请检查您的网络...,详情:" + errorMsg);
    }

    private void onUnknownException(Throwable e) {
        e.printStackTrace();
        Toast("[" + typemsg + "-错误信息]：" + e.toString());
    }

    private void Toast(String msg) {
        myUtils.log("\"[\"" + typemsg + " \"]", msg);
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }


    //加载框变量
    private ProgressDialog progressDialog;

    public void showProgressDialog(String msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        progressDialog.setMessage(msg);    //设置内容
        progressDialog.setCancelable(false);//点击屏幕和按返回键都不能取消加载框
        progressDialog.show();

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
