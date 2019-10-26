package com.yl.ylapp.camera;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.yl.module_base_utils.Camera.camerasurfaceview.CamerSurfaceInf;
import com.yl.module_base_utils.Camera.camerasurfaceview.CameraSurfaceView;
import com.yl.module_base_utils.CheckPermissionUtils;
import com.yl.module_base_utils.myUtils;
import com.yl.ylapp.R;

import java.io.File;


public class CameraActivity extends AppCompatActivity implements View.OnClickListener {

    private CameraSurfaceView mSurfaceView;

    /**
     * 选择相册
     */
    private Button mLocalImageBtn;
    private TextView mMsessage;
    /**
     * 开始拍照
     */
    private Button mCameraBtn;
    /**
     * 可疑排查
     */
    private Button mCameraBtn2;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CheckPermissionUtils.setContext(this, new CheckPermissionUtils.InterfPermission() {
            @Override
            public void Success() {
                setContentView(R.layout.activity_camera);
                initView();
            }
        }).CheckCamera();
    }

    private void initView() {
        mSurfaceView = (CameraSurfaceView) findViewById(R.id.surfaceView);
        mCameraBtn = (Button) findViewById(R.id.camera_btn);
        mCameraBtn.setOnClickListener(this);
        mLocalImageBtn = (Button) findViewById(R.id.LocalImage_btn);
        mLocalImageBtn.setOnClickListener(this);
        mMsessage = (TextView) findViewById(R.id.Msessage);

        mCameraBtn2 = (Button) findViewById(R.id.camera_btn2);
        mCameraBtn2.setOnClickListener(this);

        int type = getIntent().getIntExtra("type", 0);
        if (type == 0) {
            mCameraBtn.setVisibility(View.VISIBLE);
            mCameraBtn2.setVisibility(View.GONE);
        } else if (type == 1) {
            mCameraBtn.setVisibility(View.GONE);
            mCameraBtn2.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.camera_btn) {
            showProgressDialog("请求正在处理,请稍等...");
            mSurfaceView.setDefaultCamera(true);
            mSurfaceView.capture(new CamerSurfaceInf() {
                @Override
                public void Imgcallback(File f) {
//                    myUtils.log("image", s);

                    updata(f);
                }

            });
        } else if (i == R.id.camera_btn2) {
            showProgressDialog("请求正在处理,请稍等...");
            mSurfaceView.setDefaultCamera(true);
            mSurfaceView.capture(new CamerSurfaceInf() {
                @Override
                public void Imgcallback(File f) {
//                    myUtils.log("image", s);

                    updata2(f);
                }

            });
        } else if (i == R.id.LocalImage_btn) {

        } else {

        }
    }

    public void updata(final File f) {

        myUtils.log("CameraActivity-response", "----上传图片:start------");
        OkGo.<String>post("http://js80.huilongkj.com/afjk/showUserInfo")
                .tag(this)
                .isMultipart(true)
                .params("name", f)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        myUtils.log("CameraActivity-response-success", response.body());
                        String msg = "返回结果：";
                        msg = "";
                        if ("fail".equals(response.body())) {
                            msg += "签到失败！";
                        } else if ("pass".equals(response.body())) {
                            msg += "签到成功！";
                        } else {
                            msg += "-" + response.body();
                        }
//                        mMsessage.setText(msg);
                        showdialog(msg);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);

                        myUtils.log("CameraActivity-response-error", response.body());
                        String msg = "返回失败，原因：" + response.body();
//                        mMsessage.setText(msg);
                        showdialog(response.body() + ":" + response.toString() + ":" + response.message() + ":" + response.getException());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }


                });
    }

    public void updata2(final File f) {

        myUtils.log("CameraActivity-response", "----上传图片:start------");
        OkGo.<String>post("http://js80.huilongkj.com/afjk/dangerWarn")
                .tag(this)
                .isMultipart(true)
                .params("name", f)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        myUtils.log("CameraActivity-response-success", response.body());
                        String msg = "返回结果：";
                        msg = "";
                        if ("warn".equals(response.body())) {
                            msg += "发现异常人员！";
                        } else if ("safe".equals(response.body())) {
                            msg += "未发现异常人员！";
                        } else {
                            msg += "-" + response.body();
                        }
                        showdialog(msg);
//                        mMsessage.setText(msg);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        myUtils.log("CameraActivity-response-error", response.body());
                        String msg = "返回失败，原因：" + response.body();
//                        mMsessage.setText(msg);
                        showdialog(response.body() + ":" + response.toString() + ":" + response.message() + ":" + response.getException());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }


                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        CheckPermissionUtils.setContext(this).onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void showdialog(String msg) {
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage(msg).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

    }

    //加载框变量
    private ProgressDialog progressDialog;

    public void showProgressDialog(String text) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        progressDialog.setMessage(text);    //设置内容
        progressDialog.setCancelable(false);//点击屏幕和按返回键都不能取消加载框
        progressDialog.show();

//        //设置超时自动消失
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //取消加载框
//                if(dismissProgressDialog()){
//                    //超时处理
//                }
//            }
//        }, 60000);//超时时间60秒
    }

    public Boolean dismissProgressDialog() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
                return true;//取消成功
            }
        }
        return false;//已经取消过了，不需要取消
    }

}
