package com.yl.module_share.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yl.module_base_utils.myUtils;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;
    public static final String APPID_WX_Share = "wxd69622dbe003a1d8";
    public static final String AppSecret_WX_Share = "3c9775027e3548621179ccd49b4a7c3f";
    public static final String APPID_WX_Login = "wx6cea3a10b3929e11";
    public static final String AppSecret_WX_Login = "419d74084dc49312ada25c13528bc734";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, APPID_WX_Share, false);
        api.handleIntent(getIntent(), this);

//        api = WXAPIFactory.createWXAPI(this, APPID_WX_Login, false);
//        api.handleIntent(getIntent(), this);
//        ListenerManager.getInstance().registerListtener(this);
    }

    @Override
    public void onReq(BaseReq arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onResp(BaseResp arg0) {
        myUtils.log("", "baseResp:" + arg0.errStr + ",\n" + arg0.openId
                + ",\n" + arg0.transaction + ",\n" + arg0.errCode
                + ",\n" + arg0.getType());
        switch (arg0.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                Toast.makeText(this, "分享成功", Toast.LENGTH_SHORT).show();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                Toast.makeText(this, "取消分享。", Toast.LENGTH_LONG).show();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                Toast.makeText(this, "请求被拒绝。", Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(this, "请求返回", Toast.LENGTH_LONG).show();
                break;
        }
        // TODO 微信分享 成功之后调用接口  
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        api.unregisterApp();
    }


}
