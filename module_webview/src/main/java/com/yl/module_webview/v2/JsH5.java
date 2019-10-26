package com.yl.module_webview.v2;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.tencent.smtt.sdk.WebView;
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
    public void Actfinish() {
        context.finish();
    }


    /**
     * //分享
     *
     * @param tyep1:   0 QQ好友 1qq空间 2 微信好友 3微信朋友圈 4 短信
     * @param type2    :0网页 1 图片 2文字，
     * @param title    标题	Title
     * @param content  详情	Content
     * @param imageUrl 图片 url	ImageUrl
     * @param webUrl   网页 url	WebUrl
     *                 <p>
     *                 方法名称	shareToApp()
     */

    @JavascriptInterface
    public void shareToApp(int tyep1, int type2, String title, String content, String imageUrl, String webUrl) {
        if (tyep1 == 0) {
            ShareUtils.init(context)
                    .regToQQ()
                    .setTitle(title)
                    .setDesc(content)
                    .setUrl(webUrl)
                    .setImageUrl(imageUrl)
                    .QQFriendShare();
        } else if (tyep1 == 2) {
            ShareUtils.init(context)
                    .regToQQ()
                    .setTitle(title)
                    .setDesc(content)
                    .setUrl(webUrl)
                    .setImageUrl(imageUrl)
                    .QQshareToQzone();
        } else if (tyep1 == 2) {
            if (type2 == 0) {

                ShareUtils.init(context)
                        .regToWx()
                        .setTitle(title)
                        .setDesc(content)
                        .setUrl(webUrl)
                        .setImageUrl(imageUrl)
                        .setWX_share_Type(1)
                        .WX_webpage();
            } else if (type2 == 1) {
                ShareUtils.init(context)
                        .regToWx()
                        .setTitle(title)
                        .setDesc(content)
                        .setUrl(webUrl)
                        .setImageUrl(imageUrl)
                        .setWX_share_Type(1)
                        .Wx_Image();
            }

        } else if (tyep1 == 3) {
            if (type2 == 0) {
                ShareUtils.init(context)
                        .regToWx()
                        .setTitle(title)
                        .setDesc(content)
                        .setUrl(webUrl)
                        .setImageUrl(imageUrl)
                        .setWX_share_Type(0)
                        .WX_webpage();
            } else if (type2 == 1) {
                ShareUtils.init(context)
                        .regToWx()
                        .setTitle(title)
                        .setDesc(content)
                        .setUrl(webUrl)
                        .setImageUrl(imageUrl)
                        .setWX_share_Type(0)
                        .Wx_Image();
            }

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

