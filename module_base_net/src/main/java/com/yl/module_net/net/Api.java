package com.yl.module_net.net;
import com.yl.module_base_utils.ARouter.RouterUtil;
import com.yl.module_res.UpdataInfo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by yanglei on 2018/4/27;
 */
public interface Api {

    //版本迭代信息，
    @GET
    Observable<UpdataInfo> AppUpdate(@Url String url);


//
//    @POST("lnfx_oto_yxs/api/login/loginon")
//    Observable<BBean> getLogin(@Body RequestBody body);
//
    //登录
//    @FormUrlEncoded
//    @POST("lnfx_oto_yxs/api/login/loginon")
//    Observable<BBean> getLogin(@Field("name") String name);
//
//    //获取用户信息
//    @FormUrlEncoded
//    @POST("lnfx_oto_yxs/api/user/getUserInfo")
//    Observable<UserPojo> getUserInfo(@Field("name") String name);


    //tinker
    //?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
//    @GET("apk/tinker/update_app.json")
//    Observable<TinkerInfo> app_andfix();

}
