package com.yl.ylapp;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yl.module_base_utils.ARouter.RouterUtil;
import com.yl.module_base_utils.CheckPermissionUtils;
import com.yl.module_base_utils.Entity;
import com.yl.module_base_utils.ImageUtils;
import com.yl.module_base_utils.myUtils;
import com.yl.module_net.net.okgo.PublicService;
import com.yl.module_net.net.okgo.YlCallback2;
import com.yl.module_update_app.CheckAppVersion;
import com.yl.module_view.painterview.PainterDialog;
import com.yl.ylapp.aidl.AidlTestActivity;
import com.yl.ylapp.bean.HomeModelBean;
import com.yl.ylapp.bean.Patch;
import com.yl.ylapp.camera.CameraActivity;
import com.yl.ylapp.camera.SufaceActivity;
import com.yl.ylapp.contacts.PhoneListActivity;

import java.io.File;
import java.lang.ref.SoftReference;

/**
 * https://www.jianshu.com/p/8098961bd30c
 * https://www.jianshu.com/p/6021f3f61fa6
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;
    private Button mButton6;
    private Button mButton7;
    private Button mButton8;
    private Button mButton9;
    private Button mButton10;
    private Button mButton11;
    private Button mButton12;
    private Button mButton13;
    private Button mButton14;
    private Button mButton15;
    private CheckAppVersion checkAppVersion;
    private Context context;
    private Intent intent;
    private Button mButton16;
    private Button mButton17;
    private Button mButton18;
    private Button mButton19;

    private Button mButton20;

    //    static {
//        System.loadLibrary("native-lib");
//    }
    public native String stringFromJNI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initView();
        mButton7.setText("获取设备信息");
        mButton8.setText("拍照");
//       myUtils.log("JNI",stringFromJNI());

    }

    private void initView() {
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton3.setOnClickListener(this);
        mButton4 = (Button) findViewById(R.id.button4);
        mButton4.setOnClickListener(this);
        mButton5 = (Button) findViewById(R.id.button5);
        mButton5.setOnClickListener(this);
        mButton6 = (Button) findViewById(R.id.button6);
        mButton6.setOnClickListener(this);
        mButton7 = (Button) findViewById(R.id.button7);
        mButton7.setOnClickListener(this);
        mButton8 = (Button) findViewById(R.id.button8);
        mButton8.setOnClickListener(this);
        mButton9 = (Button) findViewById(R.id.button9);
        mButton9.setOnClickListener(this);
        mButton10 = (Button) findViewById(R.id.button10);
        mButton10.setOnClickListener(this);
        mButton11 = (Button) findViewById(R.id.button11);
        mButton11.setOnClickListener(this);
        mButton12 = (Button) findViewById(R.id.button12);
        mButton12.setOnClickListener(this);
        mButton13 = (Button) findViewById(R.id.button13);
        mButton13.setOnClickListener(this);
        mButton14 = (Button) findViewById(R.id.button14);
        mButton14.setOnClickListener(this);
        mButton15 = (Button) findViewById(R.id.button15);
        mButton15.setOnClickListener(this);
        mButton16 = (Button) findViewById(R.id.button16);
        mButton16.setOnClickListener(this);
        mButton17 = (Button) findViewById(R.id.button17);
        mButton17.setOnClickListener(this);
        mButton18 = (Button) findViewById(R.id.button18);
        mButton18.setOnClickListener(this);
        mButton19 = (Button) findViewById(R.id.button19);
        mButton19.setOnClickListener(this);
        mButton20 = (Button) findViewById(R.id.button20);
        mButton20.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button:
//                startActivity(new Intent(this, WebViewActivity.class).putExtra("url", "http://www.baidu.com"));
//                如果跳转的时候我们不设置Activity,比如：ARouter.getInstance().build("/xxx/xxx").navigation();
//                那么默认的Flag就是：Intent.FLAG_ACTIVITY_NEW_TASK，Intent.FLAG_ACTIVITY_NEW_TASK类型
//                launchMode="SingleTask"
//                这是非常影响性能的操作，所以不建议这些写.

                RouterUtil.goWith("/webview/WebViewActivity")
                        .withString("url", "http://www.baidu.com")
                        .navigation(this);
                break;
            case R.id.button2:
//                startActivity(new Intent(this, WebViewActivity_new.class).putExtra("url", "http://www.baidu.com"));
                RouterUtil.goWith("/webview/WebViewActivity_new")
                        .withString("url", "http://m.qq.com")
                        .navigation(this, 200);
                break;
            case R.id.button3:
                CheckPermissionUtils.setContext(this, new CheckPermissionUtils.InterfPermission() {
                    @Override
                    public void Success() {
                        checkAppVersion = new CheckAppVersion(MainActivity.this);
                    }
                }).CheckSD();

                break;
            case R.id.button4:
                final PainterDialog painterDialog = new PainterDialog(this);
                painterDialog.show();
                break;
            case R.id.button5:
                CheckPermissionUtils.setContext(this, new CheckPermissionUtils.InterfPermission() {
                    @Override
                    public void Success() {
                        startActivity(new Intent(context, SufaceActivity.class));
                    }
                }).CheckCamera();
                break;
            case R.id.button6:

                CheckPermissionUtils.setContext(this, new CheckPermissionUtils.InterfPermission() {
                    @Override
                    public void Success() {
                        download();
                    }
                }).CheckSD();
                break;
            case R.id.button7:
                StringBuffer sb = new StringBuffer();
                sb.append(Build.MODEL + "\n");
                sb.append(Build.SERIAL + "\n");
                sb.append(Build.FINGERPRINT + "\n");
                sb.append(Build.MANUFACTURER + "\n");
                sb.append(Build.PRODUCT + "\n");
                sb.append(Build.DEVICE + "\n");
                sb.append(Build.BRAND + "\n");

                myUtils.log("手机信息", sb.toString());
                break;
            case R.id.button8:

                CheckPermissionUtils.setContext(this, new CheckPermissionUtils.InterfPermission() {
                    @Override
                    public void Success() {
                        startActivity(new Intent(context, CameraActivity.class));
                    }
                }).CheckCamera();
                break;
            case R.id.button9:
                CheckPermissionUtils.setContext(this, new CheckPermissionUtils.InterfPermission() {
                    @Override
                    public void Success() {
                        startActivity(new Intent(context, PhoneListActivity.class));
                    }
                }).CheckContacts();
                break;
            case R.id.button10:
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("hl://lnyxs:8888/googleApp?token=100"));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button11:
                new NetWorkSpeedUtils(this, mHnadler).startShowNetSpeed();
                myUtils.log("当前网速：", "---");
                break;
            case R.id.button12:
                startActivity(new Intent(this, AidlTestActivity.class));
                break;
            case R.id.button13:
                startActivity(new Intent(this, AutoSizeActivity.class));
                break;
            case R.id.button14:
                //静态广播接收不到的问题。
                /**
                 * 发送广播时带上包名，即该广播注册的清单文件所对应的包名：package="com.android.phone"
                 * 1) 使用setPackage，intent.setPackage("com.android.phone");//参数是包名
                 * Intent intent = new Intent("com.mediatek.provider.action.MY_BROADCAST");
                 * intent.setPackage("com.android.phone");
                 * 2) 使用ComponentName，参数1是包名，参数2是自定义广播的路径
                 * intent.setComponent(new ComponentName("com.android.phone", "com.mediatek.settings.MyBroadcastReceiver"));
                 * sendBroadcast(intent);
                 *
                 */
                intent = new Intent();
                intent.setComponent(new ComponentName("com.yl.test", "com.yl.test.MyBroadCastReceiver"));
                intent.putExtra("data", "我是静态广播，我来自Ylapp-->>--到测试APP");
                sendBroadcast(intent);
                break;
            case R.id.button15:
                //播放视频
                intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
                intent.setDataAndType(uri, "video/*");
                startActivity(intent);
                break;
            case R.id.button16:
                ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                int LargememoryClass = activityManager.getLargeMemoryClass();
                int memoryClass = activityManager.getMemoryClass();
                myUtils.log("最大内存", LargememoryClass + "-" + memoryClass);
                SoftReference softReference = new SoftReference("123");
                myUtils.log("str", softReference.get() + "");
                break;
            case R.id.button17:
                PublicService.getInstance().getData("http://yxs.ah.189.cn/msty/update_app.json",
                        new YlCallback2<Patch>(this, "更新") {
                            @Override
                            public void onSuccess(Patch patch) {
                                if (patch.getCode().equals("000")) {
                                    myUtils.log("downlourl", patch.getData().getDownloadUrl() + "");
                                } else {
                                    Toast(patch.getMsg());
                                }
                            }

                        });
                String json = "{\"ciphertext\":\"ofkkdejdmodbkbbgeoolgahlohicjncgajkbkehehdlidoajkjkhopphkabnpomdogeiimllmifdknaceehafldhjcglgnmf\"}";
                String url = "http://yxs.ah.189.cn/mstyapp/moduleInfo/secondPhaseModule";
                PublicService.getInstance().getData(json, url, new YlCallback2<HomeModelBean>(this, "首页模块") {
                    @Override
                    public void onSuccess(HomeModelBean homeModelBean) {
                        myUtils.log("首页模块", homeModelBean.getData().get(0).getModuleList().get(0).getModuleName());
                    }

                });
                break;
            case R.id.button18:
                AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle("标题")
                        .setMessage("内容").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();

                break;
            case R.id.button19:
//                final EditText editText = new EditText(context);
//                AlertDialog alertDialog1 = new AlertDialog.Builder(context)
//                        .setTitle("标题")
//                        .setView(editText)
//                        .setPositiveButton("拨打电话", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Toast.makeText(MainActivity.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
//                                String str = editText.getText().toString().trim();
//                                Intent intent = new Intent(Intent.ACTION_DIAL);
//                                intent.setData(Uri.parse("tel:"+str));
//                                startActivity(intent);
//                            }
//                        }).setNegativeButton("发短信", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Toast.makeText(MainActivity.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
//                                String str = editText.getText().toString().trim();
//                                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(str));
//                                startActivity(intent);
//                            }
//                        }).show();
                new ImageUtils(this);
                break;
            case R.id.button20:
                startActivity(new Intent(this,OpenCVTestActivity.class));
                break;
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler mHnadler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:

                    myUtils.log("当前网速：", msg.obj.toString());
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private void download() {
        OkGo.<File>get("http://ln241.5ibp.com/apk/tinker/patch_signed.apk")//
                .tag(this)
                .execute(new FileCallback(Entity.pathSD, "patch-signed.apk") {
                    @Override
                    public void onError(Response<File> response) {
                        super.onError(response);
                        Toast.makeText(MainActivity.this, response.message() + "--", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onStart(Request<File, ? extends Request> request) {
                        super.onStart(request);
                    }

                    @Override
                    public void onSuccess(Response<File> response) {
                        File file = response.body();
                        Toast.makeText(MainActivity.this, "end-download", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void downloadProgress(Progress progress) {
                        super.downloadProgress(progress);
                        Toast.makeText(MainActivity.this, "start-download", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        CheckPermissionUtils.setContext(this).onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //优化新增，下载等耗时错误及其他强制退出APP，关闭弹出。
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (checkAppVersion != null) {
            if (checkAppVersion.getDialog() != null) {
                checkAppVersion.getDialog().dismiss();
            }
        }

    }

    /**
     * 第一个参数：这个整数requestCode用于与startActivityForResult中的requestCode中值进行比较判断，是以便确认返回的数据是从哪个Activity返回的。
     * <p>
     * 　　第二个参数：这整数resultCode是由子Activity通过其setResult()方法返回。适用于多个activity都返回数据时，来标识到底是哪一个activity返回的值。
     * <p>
     * 　　第三个参数：一个Intent对象，带有返回的数据。可以通过data.getXxxExtra( );方法来获取指定数据类型的数据，
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(context, "arouter回调：" + requestCode + ":" + resultCode + "", Toast.LENGTH_SHORT).show();
    }
}
