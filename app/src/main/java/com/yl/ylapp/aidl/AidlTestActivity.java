package com.yl.ylapp.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yl.test.IMyAidlInterface;
import com.yl.test.Person;
import com.yl.ylapp.R;

import java.util.ArrayList;

public class AidlTestActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * TextView
     */
    private TextView mTextView;
    /**
     * aidl_add
     */
    private Button mBtnAidl;
    ServiceConnection conn = new ServiceConnection() {
        //绑定上服务的时候调用
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
        }

        //断开服务的时候调用
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            iMyAidlInterface = null;
        }
    };
    private IMyAidlInterface iMyAidlInterface;
    /**
     * OPEN_TestAPK
     */
    private Button mButton16;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_test);
        initView();
        bindService();
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.textView);
        mBtnAidl = (Button) findViewById(R.id.btnAidl);
        mBtnAidl.setOnClickListener(this);
        mButton16 = (Button) findViewById(R.id.button16);
        mButton16.setOnClickListener(this);
    }

    private ArrayList<Person> listPerson = new ArrayList<>();
    int age = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btnAidl:
                try {
                    int sum = iMyAidlInterface.add(10, 10);
                    String str = iMyAidlInterface.getTest("1");
                    mTextView.setText("aidl: num0+num1=" + sum + "\nstr:" + str);

                    ArrayList<Person> list = (ArrayList<Person>) iMyAidlInterface.addPerson(new Person(age++, "张三" + age++));
                    mTextView.setText("aidl: num0+num1=" + sum + "\nstr=" + str + "\nlist=" + list.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                    mTextView.setText(e.toString());
                }
                break;
            case R.id.button16:
                ComponentName componentName = new ComponentName("com.yl.test", "com.yl.test.MainActivity");
                Intent intent = new Intent();
                intent.setComponent(componentName);
                startActivity(intent);

                break;
        }
    }

    private void bindService() {
        ComponentName componentName = new ComponentName("com.yl.test", "com.yl.test.MyService");
        intent = new Intent();
        intent.setComponent(componentName);

        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intent);
    }
}
