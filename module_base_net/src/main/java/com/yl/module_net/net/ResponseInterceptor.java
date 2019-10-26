package com.yl.module_net.net;


import com.yl.module_base_utils.myUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by yanglei on 2018/5/1;
 * <p>
 * 结果拦截器,这个类的执行时间是返回结果返回的时候,返回一个json的String,对里面一些特殊字符做处理
 * 主要用来处理一些后台上会出现的bug,比如下面声明的这三种情况下统一替换为:null
 *
 * 备注：只有首页的json格式不符合，也没有单独区分。
 * {"out":{"code":"1","data":[{"moduleType":1,"moduleName":"首页顶部","moduleTotal":4,
 * "moduleMaxSize":4,"moduleList":[{"begin":0,"channel":"实体,电渠,政企,直销","cr
 */
public class ResponseInterceptor implements Interceptor {
    private String emptyString = ":\"\"";
    private String emptyObject = ":{}";
    private String emptyArray = ":[]";
    private String newChars = ":null";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.body();
        if (responseBody != null) {

            String json = responseBody.string();
//             json = "{\"code\":\"0\",\"data\":{},\"msg\":\"信息查询失败！\"}";
//             json = "{\"code\":\"0\",\"msg\":\"信息查询失败！\"}";

            myUtils.log("////////////网络返回response/////////////////////\n", json);

            MediaType contentType = responseBody.contentType();
            if (!(json.contains("code") && json.contains("msg"))) {
                json = "{\"code\":\"0\",\"msg\":\"[错误信息]:\"" + json + ",\"data\":null}";
                ResponseBody body = ResponseBody.create(contentType, json);

                return response.newBuilder().body(body).build();
            }

            if (!json.contains(emptyString)) {
                ResponseBody body = ResponseBody.create(contentType, json);

                return response.newBuilder().body(body).build();
            } else {
                String replace = json.replace(emptyString, newChars);
                String replace1 = replace.replace(emptyObject, newChars);
                String replace2 = replace1.replace(emptyArray, newChars);
                ResponseBody body = ResponseBody.create(contentType, replace2);
                return response.newBuilder().body(body).build();
            }
        }

        return response;
    }


//    @Override
//    public Response intercept(Chain chain) throws IOException {
//
//        Request request = chain.request();
//        Response response = chain.proceed(request);
//        ResponseBody responseBody = response.body();
//        if (responseBody != null) {
//            String json = responseBody.string();
//            MediaType contentType = responseBody.contentType();
//
//
//            ResponseBody body = ResponseBody.create(contentType, AES.Decrypt(json));
//            return response.newBuilder().body(body).build();
//
//
//        }else {
//
//            return response;
//        }
//    }
}
