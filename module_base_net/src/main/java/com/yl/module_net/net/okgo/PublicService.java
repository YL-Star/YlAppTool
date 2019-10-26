package com.yl.module_net.net.okgo;

import com.lzy.okgo.OkGo;
import com.yl.module_base_utils.myUtils;

/**
 * [通用的接口]和[个别处模块新增的接口]。
 * Created by yanglei on 2018/5/10;
 */
public class PublicService {
    private static PublicService publicService;

    public static PublicService getInstance() {
        if (publicService == null) {
            synchronized (PublicService.class) {
                if (publicService == null) {

                    publicService = new PublicService();
                }
            }
        }
        return publicService;
    }


    private String log = "publicService getdata()";

    public PublicService() {
        log = "publicService getdata()";
    }

    public PublicService(String type) {
        log = type;
    }

    public void getData(String url, YlCallback2 callback) {
        myUtils.log("[" + log + "-参数]", url);
        OkGo.get(url)
                .tag(this)
                .execute(callback);
    }

    //    {"ciphertext":"ofkkdejdmodbkbbgeoolgahlohicjncgajkbkehehdlidoajkjkhopphkabnpomdogeiimllmifdknaceehafldhjcglgnmf"}
    public void getData(String json, String url, YlCallback2 callback) {
        myUtils.log("[" + log + "-参数]", url);
        OkGo.post(url)
//                .headers("Content-Type","application/json;charset=utf-8")
                .tag(this)
//                .upJson(json)
                .upString(json)
                .execute(callback);
    }


}
