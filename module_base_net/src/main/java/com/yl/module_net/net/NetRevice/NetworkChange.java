package com.yl.module_net.net.NetRevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanglei on 2018/6/3;
 *https://blog.csdn.net/ca13660049466/article/details/52669115
 *
 * IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
 registerReceiver(NetworkChange.getInstance(), filter);

 *private NetworkChange.OnNetWorkChange home_onChange = new NetworkChange.OnNetWorkChange() {
 @Override
 public void onChange(int wifi, int mobile, int none, int oldStatus, int newStatus) {
 if (newStatus == none){
 //没有网络
 }
 if (newStatus == mobile){
 //移动网络
 }
 if (newStatus == wifi){
 //wifi网络
 if (oldStatus == mobile) {
 //从移动网络切换到wifi网络
 }
 }
 }
 };

 //注册监听网络变化
 private void registerReceiver() {
 NetworkChange.getInstance().setOnNetWorkChange(home_onChange);
 }

 //取消监听网络变化
 private void unregisterReceiver() {
 NetworkChange.getInstance().delOnNetWorkChange(home_onChange);
 *
 */
public class NetworkChange extends BroadcastReceiver {


    public final int wifi = 2, mobile = 1, none = 0;
    public int oldState = none;
    public List<OnNetWorkChange> onNetWorkChange = new ArrayList<>();
    private static NetworkChange networkChange;

    public static NetworkChange getInstance() {
        if (networkChange == null) {
            networkChange = new NetworkChange();
        }
        return networkChange;
    }

    //回调接口
    public interface OnNetWorkChange {
        //返回各个（wifi,移动网络，没有网络）状态的值，上一个网络状态的值，当前的网络状态的值
        void onChange(int wifi, int mobile, int none, int oldStatus, int newStatus);
    }

    /**
     * 增加网络变化监听回调对象
     * 如果设置多个回调，请务必不要设置相同名字的OnNetWorkChange对象，否则会无效
     *
     * @param onNetWorkChange 回调对象
     */
    public void setOnNetWorkChange(OnNetWorkChange onNetWorkChange) {
        if (this.onNetWorkChange.contains(onNetWorkChange)) {
            return;
        }
        this.onNetWorkChange.add(onNetWorkChange);
        Log.i("网络状态", "添加一个回调。已设置：" + this.onNetWorkChange.size());
    }

    /**
     * 取消网络变化监听监听回调
     *
     * @param onNetWorkChange 回调对象
     */
    public void delOnNetWorkChange(OnNetWorkChange onNetWorkChange) {
        if (this.onNetWorkChange.contains(onNetWorkChange)) {
            this.onNetWorkChange.remove(onNetWorkChange);
            Log.i("网络状态", "删除一个回调。还有：" + this.onNetWorkChange.size());
        }
    }

    /**
     * 触发网络状态监听回调
     *
     * @param nowStatus 当前网络状态
     */
    private void setChange(int nowStatus) {
        for (OnNetWorkChange change : onNetWorkChange) {
            change.onChange(wifi, mobile, none, oldState, nowStatus);
        }
        oldState = nowStatus;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (onNetWorkChange == null) {
            //当没有设置回调的时候，什么都不做
            return;
        }
        if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
            Log.i("通知", "网络不可以用");
            setChange(none);
        } else if (mobNetInfo.isConnected()) {
            Log.i("通知", "仅移动网络可用");
            setChange(mobile);
        } else if (wifiNetInfo.isConnected()) {
            Log.i("通知", "Wifi网络可用");
            setChange(wifi);
        }
    }
}
