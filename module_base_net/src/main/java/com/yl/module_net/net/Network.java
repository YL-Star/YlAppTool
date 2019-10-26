package com.yl.module_net.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.yl.module_net.net.ServiceUrl.*;


/**
 * 网络操作类
 * <p>
 * Author: nanchen
 * Email: liushilin520@foxmail.com
 * Date: 2017-06-30  13:20
 */

public class Network {
    private static Api api, api2;
    private static Api apiTestUrl;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static ResponseInterceptor responseInterceptor = new ResponseInterceptor();
    private static RequestInterceptor requestInterceptor = new RequestInterceptor();
    //OkHttpClient
    private static OkHttpClient.Builder builder = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(requestInterceptor)
            .addInterceptor(responseInterceptor);//
    private static OkHttpClient mOkHttpClient = builder.build();

    public static Api getApi() {
        if (api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ServiceUrl)
                    .client(mOkHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//新的配置
                    .build();
            api = retrofit.create(Api.class);
        }
        return api;
    }

    /**
     *
     * 不使用过滤器，
     * 微信三方接口有用到。
     *
     * @return
     */
    public static Api getApiNoInterceptor() {
        if (api2 == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ServiceUrl)
                    .client(new OkHttpClient.Builder().build())
                    .addConverterFactory(ScalarsConverterFactory.create())//请求返回String类型
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//新的配置
                    .build();
            api2 = retrofit.create(Api.class);
        }
        return api2;
    }

    /**
     * 测试地址
     *
     * @return
     */
    public static Api getApiTest() {
        if (apiTestUrl == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(TestServiceUrl)
                    .client(mOkHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//新的配置
                    .build();
            apiTestUrl = retrofit.create(Api.class);
        }
        return apiTestUrl;
    }

}
