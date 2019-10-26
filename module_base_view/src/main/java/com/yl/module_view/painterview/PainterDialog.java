package com.yl.module_view.painterview;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yl.module_base_utils.Base64Util;
import com.yl.module_base_utils.myUtils;
import com.yl.module_view.R;

//import android.webkit.WebView;


/**
 * Created by yanglei on 2018/7/24;
 */
public class PainterDialog extends AlertDialog implements View.OnClickListener {
    /**
     * 取消
     */
    private Button mTvcancel;
    /**
     * 确定
     */
    private Button mTvSubmint;
    private PainterView mPainterView;
    private Activity context;
    WebView webView;
    private String painterViewBase64String;

    public PainterDialog(@NonNull Activity context, WebView webView) {
        super(context, R.style.painter_dialog);

        this.context = context;
        this.webView = webView;
    }

    public PainterDialog(Activity activity) {
        super(activity, R.style.painter_dialog);
        this.context = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_painter_bottom);
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        WindowManager m = window.getWindowManager();
//        Display d = m.getDefaultDisplay();
//        WindowManager.LayoutParams p =window.getAttributes();
//        p.width = d.getWidth(); //设置dialog的宽度为当前手机屏幕的宽度,方法过时。。

        DisplayMetrics dm = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int SCREEN_WIDTH = dm.widthPixels;
        int SCREEN_HEIGHT = dm.heightPixels;
        WindowManager.LayoutParams p = window.getAttributes();
        p.width = SCREEN_WIDTH;
        getWindow().setAttributes(p);
        initView();
    }

    private void initView() {
        mTvcancel = (Button) findViewById(R.id.Tvcancel);
        mTvcancel.setOnClickListener(this);
        mTvSubmint = (Button) findViewById(R.id.TvSubmint);
        mTvSubmint.setOnClickListener(this);
        mPainterView = (PainterView) findViewById(R.id.painterView);

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.Tvcancel) {
            dismiss();
            mPainterView.clear();

        } else if (i == R.id.TvSubmint) {
            Bitmap bitmap = mPainterView.creatBitmap();

            painterViewBase64String = Base64Util.encode(BitmapUtil.Bitmap2Bytes(bitmap));
            myUtils.log("painterViewBase64String", painterViewBase64String);
            context.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    webView.loadUrl("javascript:huaban_return(\"" + painterViewBase64String + "\")");
                }
            });
            mPainterView.clear();
            dismiss();

        } else {
        }
    }
}
