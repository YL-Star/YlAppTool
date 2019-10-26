package com.yl.module_update_app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;


public class DownWithProTask extends AsyncTask<String, Void, Boolean> {

    private String downUrl;
    private Context context;
    private LscMsgDialog dialog;

    public DownWithProTask(Context cc, String url, LscMsgDialog dg) {
        this.downUrl = url;
        context = cc;
        this.dialog = dg;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }
    @Override
    protected Boolean doInBackground(String... params) {
        try {
            File file = DownManager.getFileFromServer(downUrl, dialog);
            Thread.sleep(3000);
            install(file);
            dialog.dismiss(); // 结束掉进度条对话框
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        dialog.dismiss();
        if (!result) {
            LscMsgDialog dialog = new LscMsgDialog(context, R.style.AppTheme,"下载失败");
            dialog.show();
        }
    }

    /**
     * 通过隐式意图调用系统安装程序安装APK
     */
    public void install(File file) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        // 由于没有在Activity环境下启动Activity,设置下面的标签
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 24) { //判读版本是否在7.0以上
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri = FileProvider.getUriForFile(context, context.getPackageName()+".fileprovider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }
}