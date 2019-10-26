package com.yl.module_net.net;

/**
 * Created by yanglei on 2018/7/20;
 */
public class ServiceUrl {

    ///////////////////////////////h5////////////////////////////////////////////////
    public static final String ServiceUrl = "http://ln241.5ibp.com/";
    public static final String TestServiceUrl = "http://ln241.huilongkj.com/";

    ///////////////////////////////接口////////////////////////////////////////////////


    //版本更新
    public static String appVersionUrl = ServiceUrl + "apk/update_app.json";
    //热修复
    public static String appfix = ServiceUrl + "apk/andfix/fix.apatch";
    //发展分销--分享二维码扫码地址
    public static String add_fenxiaoShareUrl = ServiceUrl + "lnfx_oto_yxs/userJoin?zwphone=";

    public static String AddFenXiaoUrl = ServiceUrl + "lnfx_oto_yxs/userJoin?appFlag=1&zwphone=";
    public static String FxItem_url = ServiceUrl + "lnfx_oto_yxs/order/ordListFxIndex?appFlag=1&zwphone=";

}
