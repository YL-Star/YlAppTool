package com.yl.module_base_utils;

import android.os.Environment;


/**
 * Created by yanglei on 2018/6/19;
 */
public class Entity {
    public static final int Capture_UIM_INT = 0;//扫描uim
    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_CHOOSE = 2;
    public final static int VIDEO_REQUEST = 3;
    public static final int CameraSurface_INT = 4;//自定义拍照
    public static final int LocalImage_INT = 5;//选则照片
    public static final int BLUETOOTH_INT = 6;//蓝牙
    public static String pathSD = Environment.getExternalStorageDirectory().getPath() + "/" + BuildConfig.downloadPathFolderName + "/";
    public static String pathPackage = ModuleUtil.getContext() != null ? (ModuleUtil.getContext().getExternalCacheDir() != null ? ModuleUtil.getContext().getExternalCacheDir().getAbsolutePath() + "/" + BuildConfig.downloadPathFolderName + "/" : pathSD) : pathSD;

}