package com.yl.ylapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yl.ylapp.camera.CameraActivity;

public class OnePageActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * xxxxxxxxxxxxxxxxxxxxxxxxxxx
     */
    private TextView mContent;
    /**
     * 签到入口
     */
    private Button mCameraBtn;
    /**
     * 可疑排查入口
     */
    private Button mCameraBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onepage);
        initView();
    }

    private void initView() {
        mContent = (TextView) findViewById(R.id.content);
        mCameraBtn = (Button) findViewById(R.id.camera_btn);
        mCameraBtn.setOnClickListener(this);
        mCameraBtn2 = (Button) findViewById(R.id.camera_btn2);
        mCameraBtn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.camera_btn:
                startActivity(new Intent(this, CameraActivity.class).putExtra("type", 0));
                break;
            case R.id.camera_btn2:
                startActivity(new Intent(this, CameraActivity.class).putExtra("type", 1));
                break;
        }
    }
}
