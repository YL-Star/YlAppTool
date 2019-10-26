package com.yl.test;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class TestService extends Service {
    private static final String TAG = "TestService";

    public TestService() {
        Log.d(TAG, "---------TestService()---------");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "---------onCreate()---------");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "---------onStartCommand()---------");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "---------onDestroy()---------");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "---------onBind()---------");
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        Myuser.setBind(true);
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "---------onUnbind()---------");
        Myuser.setBind(false);
        return super.onUnbind(intent);

    }


    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "---------onRebind()---------");
        super.onRebind(intent);
    }


    public static class Myuser extends Binder {
        private static Boolean isBind = false;
        private String name="张三";

        public static Boolean getBind() {
            return isBind;
        }

        public static void setBind(Boolean bind) {
            isBind = bind;
        }

        public  String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
