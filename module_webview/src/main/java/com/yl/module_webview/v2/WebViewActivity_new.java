package com.yl.module_webview.v2;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.smtt.sdk.WebView;
import com.yl.module_webview.R;


/**
 * @author vondear
 */
@Route(path = "/webview/WebViewActivity_new")
public class WebViewActivity_new extends Activity {

    private WebView mWebView;
    private String url;

    SmartRefreshLayout refreshLayout;

    ProgressBar progressBar;
    private WebviewUtils webviewUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_new);
        initView();

    }

    protected void initView() {
        refreshLayout = findViewById(R.id.refreshLayout);
        progressBar = findViewById(R.id.progressBar);
        mWebView = findViewById(R.id.web_base);
        progressBar.incrementProgressBy(1);
        url = getIntent().getStringExtra("url");
        refreshLayout();
        webviewUtils = new WebviewUtils(this, mWebView, null, url, refreshLayout, progressBar);
    }

    private void refreshLayout() {
        refreshLayout.setEnableOverScrollBounce(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mWebView.loadUrl(mWebView.getUrl());
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        webviewUtils.destroy();
        setResult(666);
    }

    /**
     * 返回文件选择
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        webviewUtils.getMyActWebChromeClient().onActivityResult(requestCode, resultCode, intent);

    }

    @Override
    public void onBackPressed() {
        if (mWebView.getUrl().contains("isRedirect=true")) {
//            if (mWebView.canGoBackOrForward(-2)) {
//                mWebView.goBackOrForward(-2);
//            }
            finish();
        } else if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            finish();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        webviewUtils.onRestart();
    }
}

