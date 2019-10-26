package com.yl.ylapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yl.ylapp.MainActivity;
import com.yl.ylapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * main-LOGIN
     */
    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mBtnLogin = (Button) findViewById(R.id.BtnLogin);
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.BtnLogin:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
