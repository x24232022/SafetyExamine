package com.avicsafety.safety_examine.model;

import java.util.List;

/**
 * Created by Administrator on 2018/6/19 0019.
 */

public class  ProspectTotal {
   static   ProspectTotal mTotal=new ProspectTotal();
    private ProspectTotal() {
    }

    public static ProspectTotal getInstances (){
        return mTotal;
    }
    /**
     * total : 1
     * Msg : Success
     * Code : 200
     * Response : [{"backOrderDate":"","status":"新建","processedUserNames":"","location":"沈阳","no":"111","theme":"测试1","siteNo":"321","projectNo":"123","processedUsers":"","id":"1","getOrderDate":"","processInstanceId":"","processInstanceDbId":"","upSiteDate":"","longitude":"11.11","getOrderName":"","latitude":"22.22","processingUserNames":"刘畅","cooperativeunit":"","createDate":"2018-06-14 00:00:00","activityName":"新建","siteName":"存储","processingUsers":"liuchang"}]
     */


    private int total;
    private String Msg;
    private int Code;
    private List<ResponseBean> Response;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public List<ResponseBean> getResponse() {
        return Response;
    }

    public void setResponse(List<ResponseBean> Response) {
        this.Response = Response;
    }

    public static class ResponseBean {
        /**
         * backOrderDate :
         * status : 新建
         * processedUserNames :
         * location : 沈阳
         * no : 111
         * theme : 测试1
         * siteNo : 321
         * projectNo : 123
         * processedUsers :
         * id : 1
         * getOrderDate :
         * processInstanceId :
         * processInstanceDbId :
         * upSiteDate :
         * longitude : 11.11
         * getOrderName :
         * latitude : 22.22
         * processingUserNames : 刘畅
         * cooperativeunit :
         * createDate : 2018-06-14 00:00:00
         * activityName : 新建
         * siteName : 存储
         * processingUsers : liuchang
         */

        private String backOrderDate;
        private String status;
        private String processedUserNames;
        private String location;
        private String no;
        private String theme;
        private String siteNo;
        private String projectNo;
        private String processedUsers;
        private String id;
        private String getOrderDate;
        private String processInstanceId;
        private String processInstanceDbId;
        private String upSiteDate;
        private String longitude;
        private String getOrderName;
        private String latitude;
        private String processingUserNames;
        private String cooperativeunit;
        private String createDate;
        private String activityName;
        private String siteName;
        private String processingUsers;

        public String getBackOrderDate() {
            return backOrderDate;
        }

        public void setBackOrderDate(String backOrderDate) {
            this.backOrderDate = backOrderDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getProcessedUserNames() {
            return processedUserNames;
        }

        public void setProcessedUserNames(String processedUserNames) {
            this.processedUserNames = processedUserNames;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getTheme() {
            return theme;
        }

        public void setTheme(String theme) {
            this.theme = theme;
        }

        public String getSiteNo() {
            return siteNo;
        }

        public void setSiteNo(String siteNo) {
            this.siteNo = siteNo;
        }

        public String getProjectNo() {
            return projectNo;
        }

        public void setProjectNo(String projectNo) {
            this.projectNo = projectNo;
        }

        public String getProcessedUsers() {
            return processedUsers;
        }

        public void setProcessedUsers(String processedUsers) {
            this.processedUsers = processedUsers;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGetOrderDate() {
            return getOrderDate;
        }

        public void setGetOrderDate(String getOrderDate) {
            this.getOrderDate = getOrderDate;
        }

        public String getProcessInstanceId() {
            return processInstanceId;
        }

        public void setProcessInstanceId(String processInstanceId) {
            this.processInstanceId = processInstanceId;
        }

        public String getProcessInstanceDbId() {
            return processInstanceDbId;
        }

        public void setProcessInstanceDbId(String processInstanceDbId) {
            this.processInstanceDbId = processInstanceDbId;
        }

        public String getUpSiteDate() {
            return upSiteDate;
        }

        public void setUpSiteDate(String upSiteDate) {
            this.upSiteDate = upSiteDate;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getGetOrderName() {
            return getOrderName;
        }

        public void setGetOrderName(String getOrderName) {
            this.getOrderName = getOrderName;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getProcessingUserNames() {
            return processingUserNames;
        }

        public void setProcessingUserNames(String processingUserNames) {
            this.processingUserNames = processingUserNames;
        }

        public String getCooperativeunit() {
            return cooperativeunit;
        }

        public void setCooperativeunit(String cooperativeunit) {
            this.cooperativeunit = cooperativeunit;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public String getSiteName() {
            return siteName;
        }

        public void setSiteName(String siteName) {
            this.siteName = siteName;
        }

        public String getProcessingUsers() {
            return processingUsers;
        }

        public void setProcessingUsers(String processingUsers) {
            this.processingUsers = processingUsers;
        }
    }
}
