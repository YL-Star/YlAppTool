package com.yl.ylapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

import static com.lzy.okgo.utils.HttpUtils.runOnUiThread;

/**
 * 其中priority决定优先级，一个优先级对应一个拦截器，拦截器是对所有Actvity都有效的，
 * 除非你在内部通过path剔除。
 * 通过callback.onContinue(postcard)放行，
 * 通过callback.onInterrupt(new RuntimeException(""));中断。
 * 注意：process方法内部不是主线程。当我们不希望拦截器拦截到我们，
 * 还可以设置绿色通道：// 使用绿色通道(跳过所有的拦截器)
 * ARouter.getInstance().build("/home/main").greenChannel().navigation();

 */
@Interceptor(priority = 7, name = "aasj")
public class Test1Interceptor implements IInterceptor {
    Context mContext;

    /**
     * 拦截进行一些操作后 决定是否放行
     */
    @Override
    public void process(final Postcard postcard, final InterceptorCallback callback) {
        if ("/webview/WebViewActivity".equals(postcard.getPath())) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext, "您需要进行登录才能使用该功能", Toast.LENGTH_SHORT).show();
                }
            });
        } else {

            callback.onContinue(postcard);
        }
    }

    /**
     * 初始化
     */
    @Override
    public void init(Context context) {
        mContext = context;
        Log.e("testService", Test1Interceptor.class.getName() + " has init.");
    }
}
