package com.yl.module_update_app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * @author stAdore_art<Wanghao>
 * @version V1.0
 * @category 自定义消息弹窗
 * @Description: TODO
 * @Copyright: Copyright (c)2013
 * @Company: st all individuals
 * @date 2014-1-22上午10:42:25
 */
public class LscMsgDialog extends Dialog {
    private TextView msg_content;
    private Context context;
    private String btname = "下载";
    public Button ok, cancle;
    private boolean is;
    private String description;
    private ProgressBar progress;


    public LscMsgDialog(Context context, int theme, String description, boolean is) {
        super(context, theme);
        this.context = context;
        this.description = description;
        this.is = is;
    }

    public LscMsgDialog(Context context, int theme, String btname) {
        super(context, theme);
        this.context = context;
        this.btname = btname;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_util_custom);
        msg_content = (TextView) findViewById(R.id.dialog_content);
        if (!TextUtils.isEmpty(description)) {
            msg_content.setText(description);
        }
        LayoutParams params = getWindow().getAttributes();
        params.height = LayoutParams.MATCH_PARENT;
        params.width = LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(
                (android.view.WindowManager.LayoutParams) params);
        ok = findViewById(R.id.button_ok);
        cancle = findViewById(R.id.button_cancel);
        progress = findViewById(R.id.progress);
        ok.setText(btname);
        if (is) {
            cancle.setVisibility(View.VISIBLE);
        }
        if (!is) {
            cancle.setVisibility(View.GONE);
        }
        ok.setOnClickListener(onClickListener);
        cancle.setOnClickListener(onClickListener1);

    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setProgress(int pro) {
        progress.setProgress(pro);
    }

    public void setMsg_content(String content) {
        msg_content.setText(content);
    }

    public void setProShow() {
        progress.setVisibility(View.VISIBLE);
        ok.setVisibility(View.GONE);
        cancle.setVisibility(View.GONE);

    }

    public void setMax(int max) {
        progress.setMax(max);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            dismiss();
        }

    };

    private View.OnClickListener onClickListener1 = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            dismiss();
        }

    };

}
