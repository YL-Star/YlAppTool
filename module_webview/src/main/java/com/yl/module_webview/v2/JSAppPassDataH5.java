package com.yl.module_webview.v2;

import android.app.Activity;
import android.os.Build;

import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by yanglei on 2018/9/28;
 */
public class JSAppPassDataH5 {
    /**
     * js交互
     *
     * @author yanglei
     * @version [1.0, 2019.]
     */
    public static void public_return(Activity activity, final WebView webView, final String js_return, final String str) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                    webView.loadUrl("javascript:" + js_return + "(\'" + str + "\')");
                } else {
                    webView.evaluateJavascript(js_return + "(\'" + str + "\')", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                            //此处为 js 返回的结果
                        }
                    });
                }

            }
        });
    }

}
