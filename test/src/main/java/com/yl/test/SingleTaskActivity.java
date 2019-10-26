package com.yl.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SingleTaskActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * MainActivity
     */
    private Button mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);
        setTitle("SingleTaskActivity");
        initView();
        Log.d("tag-SingleTaskActivity","----------onCreate--------------");
    }

    private void initView() {
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button2:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("tag-SingleTaskActivity","----------onNewIntent--------------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("tag-SingleTaskActivity","----------onDestroy--------------");

    }
}
