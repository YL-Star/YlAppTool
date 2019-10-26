package com.yl.module_view;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;


/**
 * Created by Administrator on 2017/6/1.
 */

public class CamDialog extends Dialog {
    Context context;
    public CamDialog(Context context, int theme){
        super(context, theme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_camera);
    }
}
