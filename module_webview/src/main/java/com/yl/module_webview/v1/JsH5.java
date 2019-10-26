package com.yl.module_webview.v1;

import android.app.Activity;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.yl.module_share.ShareUtils;


/**
 * Created by yanglei on 2018/7/27;
 */
public class JsH5 {
    Activity context;
    WebView webView;


    public JsH5(Activity context, WebView webView) {
        this.context = context;
        this.webView = webView;
    }

    @JavascriptInterface
    public void goBackHome() {
        context.finish();
    }



    /**
     * //分享
     *
     * @param tyep1:   0 QQ好友 1qq空间 2 微信好友 3微信朋友圈 4 短信
     * @param type2    :0网页 1 图片 2文字，
     * @param title
     * @param content
     * @param imageUrl
     * @param webUrl
     */

    @JavascriptInterface
    public void shareToApp(int tyep1, int type2, String title, String content, String imageUrl, String webUrl) {
        if (tyep1 == 2) {

            ShareUtils.init(context)
                    .regToWx()
                    .setTitle(title)
                    .setDesc(content)
                    .setUrl(webUrl)
                    .setImageUrl(imageUrl)
                    .setWX_share_Type(1)
                    .WX_webpage();

        } else if (tyep1 == 3) {

            ShareUtils.init(context)
                    .regToWx()
                    .setTitle(title)
                    .setDesc(content)
                    .setUrl(webUrl)
                    .setImageUrl(imageUrl)
                    .setWX_share_Type(0)
                    .WX_webpage();
        }

    }

    //发展分销---回退键
    @JavascriptInterface
    public void goBackLastPage() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            context.finish();
        }
    }

    private void StartAct(Class z) {
        context.startActivity(new Intent(context, z));
    }


    protected void Toast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    protected void StartActForResult(Class classz, int code) {
        context.startActivityForResult(new Intent(context, classz), code);
    }

}

