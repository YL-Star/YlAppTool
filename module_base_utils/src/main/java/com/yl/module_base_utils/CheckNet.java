package com.yl.module_base_utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.provider.Settings;



public class CheckNet {
    /**
     * 判断是否有网络连接
     *
     * @return
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) ModuleUtil.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        assert mConnectivityManager != null;
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }

    // 判断当前是否有网络连接
    public static boolean hasInternet(Activity paramActivity) {
        NetworkInfo localNetworkInfo = ((ConnectivityManager) paramActivity
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (localNetworkInfo != null && localNetworkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
//	// 获取手机的设备号
//	public static String getEsn(Context paramContext) {
//		return ((TelephonyManager) paramContext.getSystemService("phone"))
//				.getDeviceId();
//	}
//
//	// 获取手机的imsi码
//	public static String getImsi(Context paramContext) {
//		return ((TelephonyManager) paramContext.getSystemService("phone"))
//				.getSubscriberId();
//	}
//
//	// 获取手机的号码，可能获取得到，也可能获取不到
//	public static String getMdn(Context paramContext) {
//		return ((TelephonyManager) paramContext.getSystemService("phone"))
//				.getLine1Number();
//	}

    // 获取当前的网络制式
    public static String getPreferredDataNetwork(Context paramContext) {
        String str = Settings.System.getString(
                paramContext.getContentResolver(), "default_data_network");
        if ("none".equals(str))
            str = Settings.System.getString(paramContext.getContentResolver(),
                    "saved_data_network");
        if (!"gsm".equals(str))
            str = "cdma";
        return str;
    }

    // 判断当前sd卡是否挂载
    public static boolean isSdCanUsed() {
        try {
            return Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}