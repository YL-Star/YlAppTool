package com.yl.ylapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.yl.module_webview.v2.WebViewActivity_new;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Activity基类
 * Created by geyifeng on 2017/5/9.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected
    Toolbar toolbar;
    private InputMethodManager imm;
    protected ImmersionBar mImmersionBar;
    protected Activity context;
    protected ImageButton Back;
    /**
     *
     */
    protected TextView TitleNameLeft;
    /**
     *
     */
    protected TextView TitleNameCenter;
    protected ImageButton TitleImageButtonRight;
    protected ImageButton TitleImageButtonRightOne;
    private KillAllReceiver receiver;
    private LocalBroadcastManager localBroadcastManager;

    private class KillAllReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_base);
        //   查询第一条记录
//        USERACCOUNT = SQLite.select().from(UserPojo.DataBean.class).querySingle();
        initViewBase();
        context = this;
        if (setLayoutId() != 0) {
            View view = this.getLayoutInflater().inflate(setLayoutId(), null);
            ((LinearLayout) findViewById(R.id.llBase)).addView(view);
        }
//        setContentView(setLayoutId());
        //绑定控件
        //初始化沉浸式
        if (isImmersionBarEnabled())
            initImmersionBar();
        //view与数据绑定
        initView();
        //初始化数据
        initData();
        //设置监听
        setListener();
        receiver = new KillAllReceiver();

        IntentFilter filter = new IntentFilter("app_killAll");
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(receiver, filter);

    }


    protected void initViewBase() {
        Back = (ImageButton) findViewById(R.id.Back);
        Back.setOnClickListener(this);
        TitleNameLeft = (TextView) findViewById(R.id.TitleNameLeft);
        TitleNameCenter = (TextView) findViewById(R.id.TitleNameCenter);
        TitleImageButtonRight = (ImageButton) findViewById(R.id.Title_ImageButton_Right);
        TitleImageButtonRightOne = (ImageButton) findViewById(R.id.Title_ImageButton_Right_one);
    }

    /**
     * 管理所有建立的链接,在onDestroy中清空 mCompositeDisposable
     */
    private CompositeDisposable mCompositeDisposable;

    protected void addDisposable(Disposable disposable) {

        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.imm = null;
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //在BaseActivity里销毁

        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }

        if (localBroadcastManager != null) {
            localBroadcastManager.unregisterReceiver(receiver);
        }

//        removeObserver();
        EventBus.getDefault().unregister(this);
    }

    protected abstract int setLayoutId();

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
        mImmersionBar.titleBar(R.id.toolbar)
                .navigationBarColor(R.color.colorAccent)
                .keyboardEnable(true)
                .init();
    }

    protected void initData() {
    }

    protected void initView() {

    }

    protected void setListener() {
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    public void finish() {
        super.finish();
        hideSoftKeyBoard();
    }

    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }

//    @OnClick({R.id.Back})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.Back:
//                finish();
//                break;
//
//        }
//    }

    protected void Toast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    protected void Toasts(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    protected void StartAct(Class z) {
        startActivity(new Intent(context, z));
    }

    protected void StartActPutExtra(Class z, String key, boolean value) {
        startActivity(new Intent(context, z).putExtra(key, value));
    }

    protected void StartActPutExtra(Class z, Object s) {
        startActivity(new Intent(this, z).putExtra(z.getName(), (Serializable) s));
    }

    protected void StartWebView(String url) {
        startActivity(new Intent(context, WebViewActivity_new.class).putExtra("url", url));
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent data) {
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEvent(MessageEvent dat) {
    }

    public void SendEventBusMsg(MessageEvent data) {
        EventBus.getDefault().post(data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.Back:
                finish();
                break;
        }
    }
//
//    protected KProgressHUD kProgressHUD;
//
//    /**
//     * 显示加载框
//     *
//     * @param showMsg
//     */
//    protected void showProgressHUD(String showMsg) {
//        if (kProgressHUD == null)
//            kProgressHUD = KProgressHUD.create(context);
//        kProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
//        kProgressHUD.setLabel(showMsg);
//        kProgressHUD.setCancellable(true);
//        kProgressHUD.setAnimationSpeed(2);
//        kProgressHUD.setDimAmount(0.5f);
//        kProgressHUD.show();
//    }
//
//    /**
//     * 关闭加载框
//     */
//    protected void closeProgressHUD() {
//        if (kProgressHUD != null && kProgressHUD.isShowing())
//            kProgressHUD.dismiss();
//    }
//

}
