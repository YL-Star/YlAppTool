package com.yl.test;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;


/**
 * @date: $date$ $time$
 * @author: yanglei
 * @description
 */
public class MyBroadCastReceiver extends BroadcastReceiver {

    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        Toast.makeText(mContext, intent.getStringExtra("data"), Toast.LENGTH_LONG).show();
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    sleep(15000);
//                    handler.sendEmptyMessage(0);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//        try {
//            sleep(15000);
//            handler.sendEmptyMessage(0);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        synchronized (Thread.currentThread()) {
//            try {
//                Thread.currentThread().wait(15000);
//                Toast.makeText(mContext, "ANR_TEST", Toast.LENGTH_LONG).show();
//                handler.sendEmptyMessage(0);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        while (true){
            Log.d("tag", "ANR_TEST");
        }

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            Toast.makeText(mContext, "ANR_TEST", Toast.LENGTH_LONG).show();
            Log.d("tag", "ANR_TEST");
        }
    };

}
