//package com.yl.lnyxs.wxapi;
//
//import android.app.Activity;
//import android.net.Network;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import com.tencent.mm.opensdk.modelbase.BaseReq;
//import com.tencent.mm.opensdk.modelbase.BaseResp;
//import com.tencent.mm.opensdk.modelmsg.SendAuth;
//import com.tencent.mm.opensdk.openapi.IWXAPI;
//import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
//import com.tencent.mm.opensdk.openapi.WXAPIFactory;
//import com.yl.module_base_utils.myUtils;
//
//
//public class WXLoginEntryActivity extends Activity implements IWXAPIEventHandler, IListener {
//
//    private IWXAPI api;
//
//    public static final String APPID_WX_Login = "wx6cea3a10b3929e11";
//    public static final String AppSecret_WX_Login = "419d74084dc49312ada25c13528bc734";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        // TODO Auto-generated method stub
//        super.onCreate(savedInstanceState);
//        api = WXAPIFactory.createWXAPI(this, APPID_WX_Login, false);
//        api.handleIntent(getIntent(), this);
//        ListenerManager.getInstance().registerListtener(this);
//    }
//
//    @Override
//    public void onReq(BaseReq arg0) {
//        // TODO Auto-generated method stub
//    }
//
//    @Override
//    public void onResp(BaseResp arg0) {
//        myUtils.log("", "baseResp:" + arg0.errStr + ",\n" + arg0.openId
//                + ",\n" + arg0.transaction + ",\n" + arg0.errCode
//                + ",\n" + arg0.getType());
//        switch (arg0.errCode) {
//            case BaseResp.ErrCode.ERR_OK:
//
//                String code = ((SendAuth.Resp) arg0).code;
//                String state = ((SendAuth.Resp) arg0).state;
//                myUtils.log("分享回调","code:"+code+";state:"+state);
//                if (state.equals("wechat_sdk_lnyxs_login")) {
//                    Network.getApiNoInterceptor().wx_login(APPID_WX_Login, AppSecret_WX_Login, code, "authorization_code")
//                            .subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(new MyObserver<String>(this, "微信登录", false) {
//                                @Override
//                                public void onNext(String s) {
//                                    myUtils.log("微信登录", s);
//                                    Toast.makeText(WXLoginEntryActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//                                    //消息更新，通知所有观察者
//                                    ListenerManager.getInstance().sendBroadCast(s);
//                                }
//
//                                @Override
//                                public void getDisposable(Disposable disposable) {
//
//                                }
//                            });
//                } else {
//                    Toast.makeText(this, "分享成功", Toast.LENGTH_SHORT).show();
//                }
//
//                break;
//            case BaseResp.ErrCode.ERR_USER_CANCEL:
//                Toast.makeText(this, "取消分享。", Toast.LENGTH_LONG).show();
//                break;
//            case BaseResp.ErrCode.ERR_AUTH_DENIED:
//                Toast.makeText(this, "请求被拒绝。", Toast.LENGTH_LONG).show();
//                break;
//            default:
//                Toast.makeText(this, "请求返回", Toast.LENGTH_LONG).show();
//                break;
//        }
//        // TODO 微信分享 成功之后调用接口
//        this.finish();
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        api.unregisterApp();
//        ListenerManager.getInstance().unRegisterListener(this);
//    }
//
//    @Override
//    public void notifyAllActivity(String str) {
//
//    }
//}
