package com.yl.ylapp.bean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by yanglei on 2018/5/25;
 */
public class HomeModelBean implements Serializable {
    private String MyEditModuleMore = "[{\"moduleType\":-100,\"moduleName\":\"更多属性\",\"moduleTotal\":1,\"moduleMaxSize\":1,\"moduleList\":[{\"begin\":0,\"channel\":\"\",\"createOpr\":\"\",\"createTime\":\"2018-06-28 22:20:34\",\"createTimeEnd\":null,\"createTimeStart\":null,\"currentPage\":1,\"end\":21,\"id\":386,\"isDel\":0,\"isFixed\":1,\"isPerson\":1,\"le\":\"3,4,5,0\",\"maxSize\":0,\"moduleCode\":\"m_-100\",\"moduleId\":\"module_more\",\"moduleImgUrl\":\"vectorIntellObj.png\",\"moduleName\":\"更多\",\"moduleStatus\":0,\"moduleType\":1,\"moduleUrl\":\"\",\"openType\":0,\"pId\":0,\"pageSize\":20,\"params\":\"\",\"recommend\":1,\"rows\":0,\"selCity\":\"\",\"selLv\":0,\"sortId\":3066,\"totalPage\":0,\"updateOpr\":\"\",\"updateTime\":\"\",\"userInfo\":null,\"userName\":\"\",\"userPhone\":\"\"}]}]";
    /**
     * code : 000
     * msg : 查询成功!
     * data : [{"moduleType":1,"moduleName":"圈子营销","moduleTotal":1,"moduleMaxSize":4,"moduleList":[{"begin":0,"channel":"渠道,电渠,校园,政企,异企,公众","createOpr":"18055199998","createTime":"2018-05-31 16:33:42","createTimeEnd":null,"createTimeStart":null,"currentPage":1,"end":21,"id":43,"isDel":0,"le":"3,4,5","maxSize":0,"moduleCode":"m_04","moduleId":"MI4412018546CC","moduleImgUrl":"201805/2018053116313765832.png","moduleName":"活动专区","moduleStatus":1,"moduleType":1,"moduleUrl":SERVICEURL + "mstyapp/user/game?phone=18056001400&devcode=&latnId=551","openType":1,"pId":0,"pageSize":20,"params":"phone={phone}&devcode={threeG}&latnId={lantId}","rows":0,"selCity":"","selLv":0,"sortId":258,"totalPage":0,"updateOpr":"","updateTime":"","userInfo":null,"userName":"","userPhone":""}]},{"moduleType":5,"moduleName":"常用工具","moduleTotal":1,"moduleMaxSize":12,"moduleList":[{"begin":0,"channel":"渠道,电渠,校园,政企,异企,公众","createOpr":"18055199998","createTime":"2018-05-31 16:20:25","createTimeEnd":null,"createTimeStart":null,"currentPage":1,"end":21,"id":41,"isDel":0,"le":"3,4,5","maxSize":0,"moduleCode":"m_04_05","moduleId":"MI8647ED6CDA65","moduleImgUrl":"201805/2018053116193374785.png","moduleName":"翼支付辅导","moduleStatus":0,"moduleType":5,"moduleUrl":"http://yzf.ah163.com/ahwapplat/www/TelecomProject/sendStampsIndex.html?phone=18056001400&develop3g=&develop4g=","openType":1,"pId":0,"pageSize":20,"params":"phone={phone}&develop3g={threeG}&develop4g={fourG}","rows":0,"selCity":"","selLv":0,"sortId":256,"totalPage":0,"updateOpr":"","updateTime":"","userInfo":null,"userName":"","userPhone":""}]},{"moduleType":3,"moduleName":"存量维系","moduleTotal":4,"moduleMaxSize":4,"moduleList":[{"begin":0,"channel":"政企,异企,公众","createOpr":"18055199998","createTime":"2018-05-30 21:41:27","createTimeEnd":null,"createTimeStart":null,"currentPage":1,"end":21,"id":27,"isDel":0,"le":"5","maxSize":0,"moduleCode":"m_12","moduleId":"MI114A12D3B381","moduleImgUrl":"201805/2018053021405582002.jpg","moduleName":"宽带提速","moduleStatus":1,"moduleType":3,"moduleUrl":"http://i.ah.189.cn?dev_code=&latnId={latnId}@&dev_nbr=18056001400&channel_id=23","openType":1,"pId":0,"pageSize":20,"params":"dev_code={threeG}&latnId={latnId}@&dev_nbr={phone}&channel_id=23","rows":0,"selCity":"","selLv":0,"sortId":6,"totalPage":0,"updateOpr":"","updateTime":"","userInfo":null,"userName":"","userPhone":""},{"begin":0,"channel":"渠道,电渠,校园,政企,异企,公众","createOpr":"18055199998","createTime":"2018-05-31 16:22:51","createTimeEnd":null,"createTimeStart":null,"currentPage":1,"end":21,"id":42,"isDel":0,"le":"3,4,5","maxSize":0,"moduleCode":"m_04_03","moduleId":"MIDC373174B5C2","moduleImgUrl":"201805/2018053116224988085.png","moduleName":"刷流量神器","moduleStatus":0,"moduleType":3,"moduleUrl":"http://ah.189.cn/sllsq?dev_code=&dev_nbr=18056001400","openType":1,"pId":0,"pageSize":20,"params":"dev_code={threeG}&dev_nbr={phone}","rows":0,"selCity":"","selLv":0,"sortId":257,"totalPage":0,"updateOpr":"","updateTime":"","userInfo":null,"userName":"","userPhone":""},{"begin":0,"channel":"渠道,电渠,校园,政企,异企,公众","createOpr":"18055199998","createTime":"2018-05-31 16:43:32","createTimeEnd":null,"createTimeStart":null,"currentPage":1,"end":21,"id":44,"isDel":0,"le":"3,4,5","maxSize":0,"moduleCode":"m_11","moduleId":"MIC916C0D82ADE","moduleImgUrl":"201805/2018053116374144154.png","moduleName":"一键续约","moduleStatus":1,"moduleType":3,"moduleUrl":"http://ah.189.cn/cms/r/cms/ah/default/activity/renewal/html/index.html?dev_code=&latnId=551&dev_nbr=18056001400&channel_id=23","openType":1,"pId":0,"pageSize":20,"params":"dev_code={threeG}&latnId={lantId}&dev_nbr={phone}&channel_id=23","rows":0,"selCity":"","selLv":0,"sortId":259,"totalPage":0,"updateOpr":"","updateTime":"","userInfo":null,"userName":"","userPhone":""},{"begin":0,"channel":"渠道,电渠,校园,政企,异企,公众","createOpr":"18055199998","createTime":"2018-05-31 16:45:48","createTimeEnd":null,"createTimeStart":null,"currentPage":1,"end":21,"id":45,"isDel":0,"le":"3,4,5","maxSize":0,"moduleCode":"m_41","moduleId":"MI1852FF5FDC79","moduleImgUrl":"201805/2018053116450382023.png","moduleName":"嗨卡续约","moduleStatus":1,"moduleType":3,"moduleUrl":"http://ah.189.cn/cms/r/cms/ah/default/v2017/wxActivity/ahyk/wap/home.html?landtype=3&dev_code=&dev_nbr=18056001400&latnId=551&channel_id=23","openType":1,"pId":0,"pageSize":20,"params":"landtype=3&dev_code={threeG}&dev_nbr={phone}&latnId={lantId}&channel_id=23","rows":0,"selCity":"","selLv":0,"sortId":260,"totalPage":0,"updateOpr":"","updateTime":"","userInfo":null,"userName":"","userPhone":""}]}]
     */
    private String code;
    private String msg;
    private List<DataBean> data = new ArrayList<>();
    private DataBean dataBean;

    @Override
    public String toString() {
        return "HomeModelBean{" +
                "MyEditModuleMore='" + MyEditModuleMore + '\'' +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", dataBean=" + dataBean +
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
//        @TargetApi(Build.VERSION_CODES.KITKAT)
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            DataBean dataBean = (DataBean) o;
//            return moduleType == dataBean.moduleType &&
//                    moduleTotal == dataBean.moduleTotal &&
//                    moduleMaxSize == dataBean.moduleMaxSize &&
//                    Objects.equals(moduleName, dataBean.moduleName) &&
//                    Objects.equals(moduleList, dataBean.moduleList);
//        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DataBean dataBean = (DataBean) o;
            return moduleType == dataBean.moduleType &&
                    moduleTotal == dataBean.moduleTotal &&
                    moduleMaxSize == dataBean.moduleMaxSize &&
                    moduleName.equals(dataBean.moduleName) &&
                    moduleList.equals(dataBean.moduleList);
        }

        @Override
        public int hashCode() {
            return Objects.hash(moduleType, moduleName, moduleTotal, moduleMaxSize, moduleList);
        }

        /**
         * moduleType : 1
         * moduleName : 圈子营销
         * moduleTotal : 1
         * moduleMaxSize : 4
         * moduleList : [{"begin":0,"channel":"渠道,电渠,校园,政企,异企,公众","createOpr":"18055199998","createTime":"2018-05-31 16:33:42","createTimeEnd":null,"createTimeStart":null,"currentPage":1,"end":21,"id":43,"isDel":0,"le":"3,4,5","maxSize":0,"moduleCode":"m_04","moduleId":"MI4412018546CC","moduleImgUrl":"201805/2018053116313765832.png","moduleName":"活动专区","moduleStatus":1,"moduleType":1,"moduleUrl":SERVICEURL + "mstyapp/user/game?phone=18056001400&devcode=&latnId=551","openType":1,"pId":0,"pageSize":20,"params":"phone={phone}&devcode={threeG}&latnId={lantId}","rows":0,"selCity":"","selLv":0,"sortId":258,"totalPage":0,"updateOpr":"","updateTime":"","userInfo":null,"userName":"","userPhone":""}]
         */

        private int moduleType;
        private String moduleName;
        private int moduleTotal;
        private int moduleMaxSize;
        private List<ModuleListBean> moduleList = new ArrayList<>();

        @Override
        public String toString() {
            return "DataBean{" +
                    "moduleType=" + moduleType +
                    ", moduleName='" + moduleName + '\'' +
                    ", moduleTotal=" + moduleTotal +
                    ", moduleMaxSize=" + moduleMaxSize +
                    ", moduleList=" + moduleList +
                    '}';
        }

        public int getModuleType() {
            return moduleType;
        }

        public void setModuleType(int moduleType) {
            this.moduleType = moduleType;
        }

        public String getModuleName() {
            if (moduleName == null) {
                return "";
            } else {
                return moduleName;
            }
        }

        public void setModuleName(String moduleName) {
            this.moduleName = moduleName;
        }

        public int getModuleTotal() {
            return moduleTotal;
        }

        public void setModuleTotal(int moduleTotal) {
            this.moduleTotal = moduleTotal;
        }

        public int getModuleMaxSize() {
            return moduleMaxSize;
        }

        public void setModuleMaxSize(int moduleMaxSize) {
            this.moduleMaxSize = moduleMaxSize;
        }

        public List<ModuleListBean> getModuleList() {
            return moduleList;
        }

        public void setModuleList(List<ModuleListBean> moduleList) {
            this.moduleList = moduleList;
        }

        public static class ModuleListBean implements Serializable {
            /**
             * begin : 0
             * channel : 渠道,电渠,校园,政企,异企,公众
             * createOpr : 18055199998
             * createTime : 2018-05-31 16:33:42
             * createTimeEnd : null
             * createTimeStart : null
             * currentPage : 1
             * end : 21
             * id : 43
             * isDel : 0
             * le : 3,4,5
             * maxSize : 0
             * moduleCode : m_04
             * moduleId : MI4412018546CC
             * moduleImgUrl : 201805/2018053116313765832.png
             * moduleName : 活动专区
             * moduleStatus : 1
             * moduleType : 1
             * moduleUrl : http://yxs.ah.189.cn/mstyapp/user/game?phone=18056001400&devcode=&latnId=551
             * openType : 1
             * pId : 0
             * pageSize : 20
             * params : phone={phone}&devcode={threeG}&latnId={lantId}
             * rows : 0
             * selCity :
             * selLv : 0
             * sortId : 258
             * totalPage : 0
             * updateOpr :
             * updateTime :
             * userInfo : null
             * userName :
             * userPhone :
             */

            private int begin;
            private String channel;
            private String createOpr;
            private String createTime;
            private Object createTimeEnd;
            private Object createTimeStart;
            private int currentPage;
            private int end;
            private int id;
            private int isDel;
            private String le;
            private int maxSize;
            private String moduleCode;
            private String moduleId;
            private String moduleImgUrl;
            private String moduleName;
            private int moduleStatus;
            private int moduleType;
            private String moduleUrl;
            private int openType;
            private int pId;
            private int pageSize;
            private String params;
            private int rows;
            private String selCity;
            private int selLv;
            private int sortId;
            private int totalPage;
            private String updateOpr;
            private String updateTime;
            private Object userInfo;
            private String userName;
            private String userPhone;
            private int moduleHomeItemVisibleCount = 0;//杨磊新增
            private String recommondDescribe;//模块详情

            public String getRecommondDescribe() {
                return recommondDescribe;
            }

            public void setRecommondDescribe(String recommondDescribe) {
                this.recommondDescribe = recommondDescribe;
            }

            public int getModuleHomeItemVisibleCount() {
                return moduleHomeItemVisibleCount;
            }

            public void setModuleHomeItemVisibleCount(int moduleHomeItemVisibleCount) {
                this.moduleHomeItemVisibleCount = moduleHomeItemVisibleCount;
            }

            public int getBegin() {
                return begin;
            }

            public void setBegin(int begin) {
                this.begin = begin;
            }

            public String getChannel() {
                return channel;
            }

            public void setChannel(String channel) {
                this.channel = channel;
            }

            public String getCreateOpr() {
                return createOpr;
            }

            public void setCreateOpr(String createOpr) {
                this.createOpr = createOpr;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getCreateTimeEnd() {
                return createTimeEnd;
            }

            public void setCreateTimeEnd(Object createTimeEnd) {
                this.createTimeEnd = createTimeEnd;
            }

            public Object getCreateTimeStart() {
                return createTimeStart;
            }

            public void setCreateTimeStart(Object createTimeStart) {
                this.createTimeStart = createTimeStart;
            }

            public int getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public int getEnd() {
                return end;
            }

            public void setEnd(int end) {
                this.end = end;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIsDel() {
                return isDel;
            }

            public void setIsDel(int isDel) {
                this.isDel = isDel;
            }

            public String getLe() {
                return le;
            }

            public void setLe(String le) {
                this.le = le;
            }

            public int getMaxSize() {
                return maxSize;
            }

            public void setMaxSize(int maxSize) {
                this.maxSize = maxSize;
            }

            public String getModuleCode() {
                return moduleCode;
            }

            public void setModuleCode(String moduleCode) {
                this.moduleCode = moduleCode;
            }

            public String getModuleId() {
                return moduleId;
            }

            public void setModuleId(String moduleId) {
                this.moduleId = moduleId;
            }

            public String getModuleImgUrl() {
                return moduleImgUrl;
            }

            public void setModuleImgUrl(String moduleImgUrl) {
                this.moduleImgUrl = moduleImgUrl;
            }

            public String getModuleName() {
                return moduleName;
            }

            public void setModuleName(String moduleName) {
                this.moduleName = moduleName;
            }

            public int getModuleStatus() {
                return moduleStatus;
            }

            public void setModuleStatus(int moduleStatus) {
                this.moduleStatus = moduleStatus;
            }

            public int getModuleType() {
                return moduleType;
            }

            public void setModuleType(int moduleType) {
                this.moduleType = moduleType;
            }

            public String getModuleUrl() {
                return moduleUrl;
            }

            public void setModuleUrl(String moduleUrl) {
                this.moduleUrl = moduleUrl;
            }

            public int getOpenType() {
                return openType;
            }

            public void setOpenType(int openType) {
                this.openType = openType;
            }

            public int getPId() {
                return pId;
            }

            public void setPId(int pId) {
                this.pId = pId;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public String getParams() {
                return params;
            }

            public void setParams(String params) {
                this.params = params;
            }

            public int getRows() {
                return rows;
            }

            public void setRows(int rows) {
                this.rows = rows;
            }

            public String getSelCity() {
                return selCity;
            }

            public void setSelCity(String selCity) {
                this.selCity = selCity;
            }

            public int getSelLv() {
                return selLv;
            }

            public void setSelLv(int selLv) {
                this.selLv = selLv;
            }

            public int getSortId() {
                return sortId;
            }

            public void setSortId(int sortId) {
                this.sortId = sortId;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public String getUpdateOpr() {
                return updateOpr;
            }

            public void setUpdateOpr(String updateOpr) {
                this.updateOpr = updateOpr;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public Object getUserInfo() {
                return userInfo;
            }

            public void setUserInfo(Object userInfo) {
                this.userInfo = userInfo;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserPhone() {
                return userPhone;
            }

            public void setUserPhone(String userPhone) {
                this.userPhone = userPhone;
            }

            @Override
            public String toString() {
                return "ModuleListBean{" +
                        "begin=" + begin +
                        ", channel='" + channel + '\'' +
                        ", createOpr='" + createOpr + '\'' +
                        ", createTime='" + createTime + '\'' +
                        ", createTimeEnd=" + createTimeEnd +
                        ", createTimeStart=" + createTimeStart +
                        ", currentPage=" + currentPage +
                        ", end=" + end +
                        ", id=" + id +
                        ", isDel=" + isDel +
                        ", le='" + le + '\'' +
                        ", maxSize=" + maxSize +
                        ", moduleCode='" + moduleCode + '\'' +
                        ", moduleId='" + moduleId + '\'' +
                        ", moduleImgUrl='" + moduleImgUrl + '\'' +
                        ", moduleName='" + moduleName + '\'' +
                        ", moduleStatus=" + moduleStatus +
                        ", moduleType=" + moduleType +
                        ", moduleUrl='" + moduleUrl + '\'' +
                        ", openType=" + openType +
                        ", pId=" + pId +
                        ", pageSize=" + pageSize +
                        ", params='" + params + '\'' +
                        ", rows=" + rows +
                        ", selCity='" + selCity + '\'' +
                        ", selLv=" + selLv +
                        ", sortId=" + sortId +
                        ", totalPage=" + totalPage +
                        ", updateOpr='" + updateOpr + '\'' +
                        ", updateTime='" + updateTime + '\'' +
                        ", userInfo=" + userInfo +
                        ", userName='" + userName + '\'' +
                        ", userPhone='" + userPhone + '\'' +
                        ", moduleHomeItemVisibleCount=" + moduleHomeItemVisibleCount +
                        ", recommondDescribe='" + recommondDescribe + '\'' +
                        '}';
            }

            //            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                ModuleListBean that = (ModuleListBean) o;
                return begin == that.begin &&
                        currentPage == that.currentPage &&
                        end == that.end &&
                        id == that.id &&
                        isDel == that.isDel &&
                        maxSize == that.maxSize &&
                        moduleStatus == that.moduleStatus &&
                        moduleType == that.moduleType &&
                        openType == that.openType &&
                        pId == that.pId &&
                        pageSize == that.pageSize &&
                        rows == that.rows &&
                        selLv == that.selLv &&
                        sortId == that.sortId &&
                        totalPage == that.totalPage &&
                        moduleHomeItemVisibleCount == that.moduleHomeItemVisibleCount &&
                        Objects.equals(channel, that.channel) &&
                        Objects.equals(createOpr, that.createOpr) &&
                        Objects.equals(createTime, that.createTime) &&
                        Objects.equals(createTimeEnd, that.createTimeEnd) &&
                        Objects.equals(createTimeStart, that.createTimeStart) &&
                        Objects.equals(le, that.le) &&
                        Objects.equals(moduleCode, that.moduleCode) &&
                        Objects.equals(moduleId, that.moduleId) &&
                        Objects.equals(moduleImgUrl, that.moduleImgUrl) &&
                        Objects.equals(moduleName, that.moduleName) &&
                        Objects.equals(moduleUrl, that.moduleUrl) &&
                        Objects.equals(params, that.params) &&
                        Objects.equals(selCity, that.selCity) &&
                        Objects.equals(updateOpr, that.updateOpr) &&
                        Objects.equals(updateTime, that.updateTime) &&
                        Objects.equals(userInfo, that.userInfo) &&
                        Objects.equals(userName, that.userName) &&
                        Objects.equals(userPhone, that.userPhone);
            }

//            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public int hashCode() {
                return Objects.hash(begin, channel, createOpr, createTime, createTimeEnd, createTimeStart, currentPage, end, id, isDel, le, maxSize, moduleCode, moduleId, moduleImgUrl, moduleName, moduleStatus, moduleType, moduleUrl, openType, pId, pageSize, params, rows, selCity, selLv, sortId, totalPage, updateOpr, updateTime, userInfo, userName, userPhone, moduleHomeItemVisibleCount);
            }
        }
    }

//    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HomeModelBean that = (HomeModelBean) o;
        return Objects.equals(MyEditModuleMore, that.MyEditModuleMore) &&
                Objects.equals(code, that.code) &&
                Objects.equals(msg, that.msg) &&
                Objects.equals(data, that.data) &&
                Objects.equals(dataBean, that.dataBean);
    }

//    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(MyEditModuleMore, code, msg, data, dataBean);
    }
}
