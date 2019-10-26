package com.yl.module_webview.v2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.WebView;
import com.yl.module_base_utils.CheckNet;

//import android.webkit.DownloadListener;
//import android.webkit.WebView;


/**
 * Created by Administrator on 2018/1/17.
 * 隐藏了title，注销了title的逻辑。
 */

public class WebviewUtils  {
    private WebView webView;
    private String url;
    private Activity mContext;
    private boolean loadError;
    private String cacheH5;
    private JsH5 jsH5;
    private MyWebChromeClient_new myActWebChromeClient_new;
    private SmartRefreshLayout refreshLayout;
    private ProgressBar progressBar;

    public MyWebChromeClient_new getMyActWebChromeClient() {
        return myActWebChromeClient_new;
    }

    public WebviewUtils(Activity context, WebView webView, String cacheH5, String url, SmartRefreshLayout refreshLayout, ProgressBar progressBar) {
        this.mContext = context;
        this.webView = webView;
        this.cacheH5 = cacheH5;
        this.url = url;
        this.refreshLayout = refreshLayout;
        this.progressBar = progressBar;
        webViewSetting();

    }


    private void webViewSetting() {
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //设置自适应屏幕，两者合用
        webView.getSettings().setUseWideViewPort(true); //将图片调整到适合webview的大小
        webView.getSettings().setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        jsH5 = new JsH5(mContext, webView);
        webView.addJavascriptInterface(jsH5, "itcast");
        myActWebChromeClient_new = new MyWebChromeClient_new(mContext, webView, progressBar);
        webView.setWebChromeClient(myActWebChromeClient_new);
        webView.setWebViewClient(new MyWebViewClient(mContext, refreshLayout));
        FileImageSave.newInstance().saveImage(webView,mContext);
        webView.loadUrl(url);

        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String paramAnonymousString2, String paramAnonymousString3, String paramAnonymousString4, long paramAnonymousLong) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(url));
                mContext.startActivity(intent);
            }
        });
        if (!CheckNet.isNetworkConnected()) {
            if (cacheH5 != null) {
                webView.loadUrl(cacheH5);
            }
            Toast.makeText(mContext, "网络连接异常，请检查您的网络连接...", Toast.LENGTH_SHORT).show();
        }
    }

    public void destroy() {
        webView.clearCache(true);
        webView.clearHistory();
        webView.clearFormData();
        webView.destroy();

    }

    public void update(final String data) {
        JSAppPassDataH5.public_return(mContext, webView, "binding_return", data);
    }

    public void onRestart() {
        if (webView.getUrl().contains("lnfx_oto_yxs/wd/tiXianIndex")) {//提现，刷新页面
            webView.loadUrl(webView.getUrl());
        }
    }


}