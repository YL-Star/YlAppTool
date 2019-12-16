package com.yl.ylapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.yl.module_base_utils.CheckNet;
import com.yl.module_base_utils.myUtils;
import com.yl.module_net.net.NetRevice.NetUtil;
import com.yl.module_webview.v2.WebViewActivity_new;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static com.yl.module_net.net.NetRevice.NetUtil.NETWORK_MOBILE;
import static com.yl.module_net.net.NetRevice.NetUtil.NETWORK_NONE;
import static com.yl.module_net.net.NetRevice.NetUtil.NETWORK_WIFI;

/**
 * Created by Administrator on 2018/1/30.
 */

public class BaseFragment extends Fragment {
//        implements Observerable {

    protected Activity mActivity;
    protected View mRootView;
//    private Unbinder unbinder;
    protected ImmersionBar mImmersionBar;

//    protected UserPojo.DataBean USERACCOUNT;
    protected Boolean isProgress = true;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayoutId(), container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

        initData();
        setListener();
//        registerReceiver();
        EventBus.getDefault().register(this);
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
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {

        } else {
            initImmersionBar();
        }
    }


    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        myUtils.log("fragment初始化沉浸式", "-----------" + this.getClass().getName() + "-------------");
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.keyboardEnable(true)
//                .statusBarView(R.id.top_view)
                .fullScreen(false)
                .navigationBarColor(R.color.colorAccent)
                .navigationBarWithKitkatEnable(true).init();
    }


    /**
     * Sets layout id.
     *
     * @return the layout id
     */
    protected int setLayoutId() {
        return 0;
    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    /**
     * view与数据绑定
     */
    protected void initView() {

    }

    /**
     * 设置监听
     */
    protected void setListener() {

    }

    /**
     * 找到activity的控件
     *
     * @param <T> the type parameter
     * @param id  the id
     * @return the t
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T findActivityViewById(@IdRes int id) {
        return (T) mActivity.findViewById(id);
    }

    protected void Toast(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    protected void StartAct(Class z) {
        startActivity(new Intent(mActivity, z));
    }

    protected void StartActF(Class z, Object d) {
        startActivity(new Intent(mActivity, z).putExtra("data", (Serializable) d));
    }

    protected void StartActF(Class z, String d) {
        startActivity(new Intent(mActivity, z).putExtra("data", d));
    }

    protected void StartWebView(String url) {
        startActivity(new Intent(mActivity, WebViewActivity_new.class).putExtra("url", url));
    }

//    private List<Observer> list = new ArrayList<Observer>();
//
//
//    @Override
//    public void registerObserver(Observer o) {
//        list.add(o);
//    }
//
//    @Override
//    public void removeObserver() {
//        if (!list.isEmpty()) {
//            for (int i = 0; i < list.size(); i++) {
//                list.remove(list.get(i));
//            }
//        }
//    }
//
//    @Override
//    public void notifyObserver(String type, String data) {
//        for (int i = 0; i < list.size(); i++) {
//            Observer oserver = list.get(i);
//            oserver.update(type, data);
//        }
//    }

    IntentFilter filter;

    public void registerReceiver() {
        if (netBroadcastReceiver == null) {
            netBroadcastReceiver = new NetBroadcastReceiver();
            if (filter == null) {
                filter = new IntentFilter();
                filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
                filter.addAction("android.net.wifi.STATE_CHANGE");
            }
            localBroadcastManager = LocalBroadcastManager.getInstance(mActivity);
            localBroadcastManager.registerReceiver(netBroadcastReceiver, filter);
            netMobileold = NetUtil.getNetWorkState(mActivity);
        }
    }


    public class NetBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            // 如果相等的话就说明网络状态发生了变化
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                int netWorkState = NetUtil.getNetWorkState(context);
                // 接口回调传过去状态的类型
                onNetChange(netWorkState);
            }
        }
    }

    /**
     * 网络变化之后的类型
     */

    public void onNetChange(int netMobile) {
        // TODO Auto-generated method stub
        this.netMobile = netMobile;
        isNetConnect();

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
    public void onDestroy() {
        super.onDestroy();
//        unbinder.unbind();
//        if (localBroadcastManager!=null) {
//
//            localBroadcastManager.unregisterReceiver(netBroadcastReceiver);
//        }
        netMobileold = NETWORK_NONE;
        netMobile = 0;
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
//        removeObserver();
        EventBus.getDefault().unregister(this);
    }

    private NetBroadcastReceiver netBroadcastReceiver;
    private int netMobileold = NETWORK_NONE;
    private int netMobileold2 = NETWORK_MOBILE;
    private int netMobile = 0;

    /**
     * 判断有无网络 。
     *
     * @return true 有网, false 没有网络.
     */
    public void isNetConnect() {
        if (netMobile == NETWORK_WIFI) {

            if (netMobileold == NETWORK_MOBILE) {

                myUtils.log("网络变化", "从4g切换到WiFi");

            } else {
                if ( netMobileold2 != NETWORK_NONE) return;
                NetWorkConnect();
                myUtils.log("网络变化", "WiFi网络");
            }
            netMobileold = NETWORK_WIFI;
            netMobileold2 = NETWORK_WIFI;
        } else if (netMobile == NETWORK_MOBILE) {

            if (netMobileold == NETWORK_WIFI) {
                myUtils.log("网络变化", "从WiFi切换到移动网络");

            } else {

                if ( netMobileold2 != NETWORK_NONE) return;
                NetWorkConnect();
                myUtils.log("网络变化", "移动网络");
            }

            netMobileold = NETWORK_MOBILE;
            netMobileold2 = NETWORK_MOBILE;
        } else if (netMobile == NETWORK_NONE) {
            myUtils.log("网络变化", "没有网络");
            netMobileold2 = NETWORK_NONE;
//            Toast("当前网络异常,请检查您的网络连接......");

            boolean isNet = CheckNet.isNetworkConnected();
            if (!isNet) {

            }

        }
    }

    protected void NetWorkConnect() {

    }

//    protected KProgressHUD kProgressHUD;
//
//    /**
//     * 显示加载框
//     *
//     * @param showMsg
//     */
//    private void showProgressHUD(String showMsg) {
//
//        if (kProgressHUD == null)
//            kProgressHUD = KProgressHUD.create(mActivity);
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
//    private void closeProgressHUD() {
//        if (kProgressHUD != null && kProgressHUD.isShowing())
//            kProgressHUD.dismiss();
//    }
}
