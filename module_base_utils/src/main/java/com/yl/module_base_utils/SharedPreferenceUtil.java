package com.yl.module_base_utils;

/**
 * Created by yanglei on 2018/10/19;
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * SharedPreferences工具类，可以保存object对象
 * <p>
 * 存储时以object存储到本地，获取时返回的也是object对象，需要自己进行强制转换
 * <p>
 * 也就是说，存的人和取的人要是同一个人才知道取出来的东西到底是个啥 ^_^
 */
public class SharedPreferenceUtil {
    private static final String FILE_NAME = "sharePref";
    /**
     * 单例模式
     */
    private static SharedPreferenceUtil instance = null;
    private static SharedPreferences.Editor editor;
    private static SharedPreferences preferences;

    private SharedPreferenceUtil(Context context) {
        preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    private static synchronized void syncInit(Context context) {
        if (instance == null)
            instance = new SharedPreferenceUtil(context);
    }

    public static SharedPreferenceUtil getInstance(Context context) {
        if (instance == null)
            syncInit(context);
        return instance;
    }

    /**
     * writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
     * 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
     *
     * @param object 待加密的转换为String的对象
     * @return String   加密后的String
     */
    private static String Object2String(Object object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            String string = new String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
            objectOutputStream.close();
            return string;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用Base64解密String，返回Object对象
     *
     * @param objectString 待解密的String
     * @return object      解密后的object
     */
    private static Object String2Object(String objectString) {
        byte[] mobileBytes = Base64.decode(objectString.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mobileBytes);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 使用SharedPreference保存对象
     *
     * @param //fileKey  储存文件的key
     * @param key        储存对象的key
     * @param saveObject 储存的对象
     */
    public static void save(String key, Object saveObject) {
//        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences(fileKey, Activity.MODE_MULTI_PROCESS);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
        String string = Object2String(saveObject);
        editor.putString(key, string);
        Boolean is = editor.commit();
        myUtils.log("istrue", is + "");
    }

    /**
     * 获取SharedPreference保存的对象
     *
     * @param //fileKey 储存文件的key
     * @param key       储存对象的key
     * @return object 返回根据key得到的对象
     */
    public static Object get(String key) {
        String string = preferences.getString(key, null);
//        myUtils.log("对象长度", string);
        if (string != null) {
            Object object = String2Object(string);
            return object;
        } else {
            return null;
        }
    }


}
