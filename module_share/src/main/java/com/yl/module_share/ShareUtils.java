package com.yl.module_share;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.yl.module_base_utils.myUtils;

import java.io.ByteArrayOutputStream;

import static com.yl.module_share.wxapi.WXEntryActivity.APPID_WX_Login;
import static com.yl.module_share.wxapi.WXEntryActivity.APPID_WX_Share;

/**
 * Created by yanglei on 2018/8/13;
 * http://wiki.open.qq.com/index.php?=45038&title=Android_API%E8%B0%83%E7%94%A8%E8%AF%B4%E6%98%8E
 */
public class ShareUtils {
    private Activity context;
    private IWXAPI api;
    private Tencent mTencent;

    private String url;
    private String title;
    private String desc;
    private String imageUrl;
    private static final int WX_THUMB_SIZE = 100;
    private int WX_share_Type;//0,微信朋友圈。1,微信好友
    public static ShareUtils shareUtils;
    private Bitmap bmp;


    public static ShareUtils init(Activity context) {
            shareUtils = new ShareUtils(context);
        return shareUtils;
    }


    public ShareUtils setWX_share_Type(int WX_share_Type) {
        this.WX_share_Type = WX_share_Type;

        return shareUtils;
    }

    public ShareUtils setUrl(String url) {
        this.url = url;
        return shareUtils;
    }

    public ShareUtils setTitle(String title) {
        this.title = title;
        return shareUtils;
    }

    public ShareUtils setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return shareUtils;
    }

    public ShareUtils setDesc(String desc) {
        this.desc = desc;
        return shareUtils;
    }



    private ShareUtils(Activity context) {
        this.context = context;
    }

    public ShareUtils regToWxLogin() {
        api = WXAPIFactory.createWXAPI(context, APPID_WX_Login, false);
        api.registerApp(APPID_WX_Login);
//        api = WXAPIFactory.createWXAPI(context, APPID_WX_Login);
        return shareUtils;
    }


    public ShareUtils regToWx() {
        api = WXAPIFactory.createWXAPI(context, APPID_WX_Share, true);
        api.registerApp(APPID_WX_Share);
        return shareUtils;
    }

    public ShareUtils regToQQ() {
        // Tencent类是SDK的主要实现类，开发者可通过Tencent类访问腾讯开放的OpenAPI。
        // 其中APP_ID是分配给第三方应用的appid，类型为String。
        mTencent = Tencent.createInstance("101402726", context.getApplicationContext());
        // 1.4版本:此处需新增参数，传入应用程序的全局context，可通过activity的getApplicationContext方法获取
        // 初始化视图
        return shareUtils;
    }

    /**
     * QQ
     * 分享图文消息
     */
    private void onClickShare() {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, desc);
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://www.qq.com/news/1.html");
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "辽宁轻销售");
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, 1);
        mTencent.shareToQQ(context, params, new BaseUiListener());
    }

    /**
     * QQ
     * 分享应用
     * 应用分享后，发送方和接收方在聊天窗口中点击消息气泡即可进入应用的详情页。
     */
    public void QQFriendShare() {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_APP);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, desc);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, url);
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "辽宁轻销售");
        mTencent.shareToQQ(context, params, new BaseUiListener());
    }


    /**
     * QQ空间
     * 图文分享
     */
    public void QQshareToQzone() {
        final Bundle params = new Bundle();
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, title);//必填
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, desc);//选填
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, url);//必填
//        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, "");
        mTencent.shareToQzone(context, params, new BaseUiListener());
    }

    public void Wx_Image() {
        Resources res = context.getResources();
        Bitmap bmp;
        if (imageUrl == null) {
            bmp = BitmapFactory.decodeResource(res, R.mipmap.nomal);
        } else {
            bmp = myUtils.getBitmap(imageUrl);
        }
        WXImageObject imgObj = new WXImageObject(bmp);

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;

        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, WX_THUMB_SIZE, WX_THUMB_SIZE, true);
        bmp.recycle();
        msg.thumbData = bmpToByteArray(thumbBmp, true);

        // 构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.scene = WX_share_Type == 0 ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;

        req.transaction = buildTransaction("img");
        req.message = msg;

        // 调用api接口发送数据到微信
        api.sendReq(req);
    }

    public void WX_webpage() {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;//跳链的url地址
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;//标题
        msg.description = desc;//描述
        Resources res = context.getResources();
        if (imageUrl == null) {
            bmp = BitmapFactory.decodeResource(res, R.mipmap.nomal);
        } else {
            bmp = myUtils.getBitmap(imageUrl);
        }
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, WX_THUMB_SIZE, WX_THUMB_SIZE, true);
        msg.thumbData = bmpToByteArray(thumbBmp, true);

        // 构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.scene = WX_share_Type == 0 ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        api.sendReq(req);
    }

    public void WX_Login() {
        api = WXAPIFactory.createWXAPI(context, APPID_WX_Login, false);
        api.registerApp(APPID_WX_Login);
        myUtils.log("-----------------三方登录----","start");
        // send oauth request
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_lnyxs_login";
        api.sendReq(req);
        myUtils.log("-----------------三方登录----","end");
    }

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object o) {

        }

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 80, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
