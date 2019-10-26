package com.yl.module_update_app;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.View;

import com.yl.module_base_utils.myUtils;
import com.yl.module_net.net.MyObserver;
import com.yl.module_net.net.Network;
import com.yl.module_res.UpdataInfo;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;



public class CheckAppVersion {

    private Context context;
    private LscMsgDialog dialog;

    public LscMsgDialog getDialog() {
        return dialog;
    }

    public CheckAppVersion(Context cc) {
        this.context = cc;
        Network.getApi().AppUpdate(BuildConfig.appVersionUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<UpdataInfo>(context, "版本更新", false) {
                    @Override
                    public void onNext(UpdataInfo updataInfo) {
                        myUtils.log("版本更新", updataInfo.toString());
                        if (updataInfo.getData().getVersion() > getVersionCode()) {
                            updateapp(updataInfo);
                        }
                    }

                    @Override
                    public void getDisposable(Disposable disposable) {
                    }
                });
    }

    protected void updateapp(final UpdataInfo data) {
        dialog = new LscMsgDialog(context,
                R.style.dialog, data.getData().getDescription(), true);//定义主题，目前的主题透明色
        dialog.setCancelable(false);

        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = v.getId();
                if (i == R.id.button_ok) {
                    dialog.setMsg_content("正在下载");
                    dialog.setProShow();
                    new DownWithProTask(context, data.getData().getUrl(), dialog).execute();

                } else if (i == R.id.button_cancel) {
                    dialog.dismiss();

                }
            }
        });
        dialog.show();
    }

    private int getVersionCode() {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(),
                    0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo.versionCode;
    }

}
