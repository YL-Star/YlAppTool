package com.yl.test;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yl.module_base_utils.ARouter.RouterUtil;
import com.yl.test.daili.DdoM;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Hello World!
     */
    private TextView mTextview;
    /**
     * singleTask
     */
    private Button mButton;
    /**
     * aidl共享数据
     */
    private Button mButton3;
    /**
     * startService()
     */
    private Button mStartService;
    /**
     * bindService()
     */
    private Button mBindService;
    /**
     * stopService()
     */
    private Button mButton4, anr_test;
    private Intent intent2, intent;
    /**
     * stopService()
     */
    private Button mStopService;
    /**
     * unbindService()
     */
    private Button mUnbindService;
    private TestService.Myuser myser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("MainActivity");
        initView();
        Log.d("tag-MainActivity", "----------onCreate--------------");
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
                mTextview.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
//                mTextview.setText("测试");
            }
        }.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void initView() {
        mTextview = (TextView) findViewById(R.id.textview);
        mTextview.setOnClickListener(this);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton3.setOnClickListener(this);
        mStartService = (Button) findViewById(R.id.startService);
        mStartService.setOnClickListener(this);
        mBindService = (Button) findViewById(R.id.bindService);
        mBindService.setOnClickListener(this);
        mButton4 = (Button) findViewById(R.id.stopService);
        mButton4.setOnClickListener(this);
        mStopService = (Button) findViewById(R.id.stopService);
        mStopService.setOnClickListener(this);
        mUnbindService = (Button) findViewById(R.id.unbindService);
        mUnbindService.setOnClickListener(this);
        anr_test = (Button) findViewById(R.id.anr_test);
        anr_test.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.textview:
                RouterUtil.goWith("/webview/WebViewActivity_new")
                        .withString("url", "http://m.qq.com")
                        .navigation();

                break;
            case R.id.button:
                startActivity(new Intent(this, SingleTaskActivity.class));

                break;
            case R.id.button3:
//                MyService.getListPerson().add(new Person(12, "黎明"));
                DdoM ddoM = new DdoM();
                ddoM.dom();


                break;
            case R.id.startService:
                intent = new Intent(this, TestService.class);

                startService(intent);
                break;
            case R.id.bindService:
                intent2 = new Intent(this, TestService.class);
                bindService(intent2, serviceConnection, Service.BIND_AUTO_CREATE);
                break;
            case R.id.stopService:
                if (intent != null) {
                    stopService(intent);
                }
                break;
            case R.id.unbindService:
                if (TestService.Myuser.getBind()) {
                    unbindService(serviceConnection);
                }
                break;
            case R.id.anr_test:
//               intent = new Intent("com.yl.test.MyBroadCastReceiver");
//                intent.putExtra("data", "我是静态广播，我来自Ylapp-->>--到测试APP");
//                sendBroadcast(intent);
                intent = new Intent();
                intent.setComponent(new ComponentName("com.yl.test", "com.yl.test.MyBroadCastReceiver"));
                intent.putExtra("data", "我是静态广播，我来自Ylapp-->>--到测试APP");
                sendBroadcast(intent);
                break;
        }
    }

    /**
     * 通过ServiceConnection来获取service中内部类的类对象，
     * 然后通过这个类对象就可以调用类中的方法，当然这个类需要继承Binder对象
     */
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myser = (TestService.Myuser) iBinder;
            Log.d("TestService", "name:" + myser.getName());

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("tag-MainActivity", "----------onDestroy--------------");
//        bindService开启服务以后，与activity存在关联，退出activity时必须调用unbindService方法，否则会报ServiceConnection泄漏的错误。
        if (TestService.Myuser.getBind()) {

            unbindService(serviceConnection);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
