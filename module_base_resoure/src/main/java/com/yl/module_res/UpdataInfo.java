package com.yl.module_res;

/**
 * 公用类下沉
 */
public class UpdataInfo {


    /**
     * code : 1
     * msg :
     * data : {"version":2,"url":"http://ln241.5ibp.com/apk/app-release.apk","description":"请下载更新，否则影响使用"}
     */

    private String code;
    private String msg;
    private DataBean data;

    @Override
    public String toString() {
        return "UpdataInfo{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

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
         * version : 2
         * url : http://ln241.5ibp.com/apk/app-release.apk
         * description : 请下载更新，否则影响使用
         */

        private int version;
        private String url;
        private String description;

        @Override
        public String toString() {
            return "DataBean{" +
                    "version=" + version +
                    ", url='" + url + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
