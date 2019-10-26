package com.yl.ylapp.bean;

/**
 * @date: $date$ $time$
 * @author: yanglei
 * @description
 */
public class Patch {

    /**
     * code : 000
     * msg :
     * data : {"downloadUrl":"http://yxs.ah.189.cn/msty/patch_signed.apk","versionName":"1.0.2","patchMessage":"","md5":""}
     */

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * downloadUrl : http://yxs.ah.189.cn/msty/patch_signed.apk
         * versionName : 1.0.2
         * patchMessage :
         * md5 :
         */

        private String downloadUrl;
        private String versionName;
        private String patchMessage;
        private String md5;

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getPatchMessage() {
            return patchMessage;
        }

        public void setPatchMessage(String patchMessage) {
            this.patchMessage = patchMessage;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "downloadUrl='" + downloadUrl + '\'' +
                    ", versionName='" + versionName + '\'' +
                    ", patchMessage='" + patchMessage + '\'' +
                    ", md5='" + md5 + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Patch{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
