package com.yl.module_webview.v2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.yl.module_base_utils.myUtils;

import java.util.HashMap;
import java.util.Map;



/**
 * Created by yanglei on 2018/7/28;
 */
public class MyWebViewClient extends WebViewClient {
    Activity context;
    RefreshLayout refreshLayout;
    String cacheH5;

    public MyWebViewClient(Activity context, RefreshLayout refreshLayout, String cacheH5) {
        this.context = context;
        this.refreshLayout = refreshLayout;
        this.cacheH5 = cacheH5;
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        myUtils.log("------------------url------onReceivedError------", "");
        super.onReceivedError(view, request, error);
    }

    @Override
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }

    public MyWebViewClient(Activity context, SmartRefreshLayout refreshLayout) {
        this.context = context;
        this.refreshLayout = refreshLayout;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        myUtils.log("------------------url------onPageFinished------", "");
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
        }

    }


    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);

        myUtils.log("------------------onPageStarted------", url);
        if (refreshLayout != null) {
            if (!refreshLayout.getState().isOpening
                    &&
                    !refreshLayout.getState().isDragging
                    &&
                    !refreshLayout.getState().isFinishing) {

            }
        }
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        myUtils.log("------------------shouldOverrideUrlLoading------", url);

        // 如下方案可在非微信内部WebView的H5页面中调出微信支付
        if (url.contains("https://wx.tenpay.com")) {
            Map<String, String> extraHeaders = new HashMap<String, String>();
            extraHeaders.put("Referer", "http://ln241.5ibp.com");
            view.loadUrl(url, extraHeaders);
            view.goBack();
            return true;
        }

        if (url.startsWith("http:") || url.startsWith("https:")) {
            view.loadUrl(url);
            return true;
        }

        // Otherwise allow the OS to handle things like tel, mailto, etc.
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }





}
