package com.avicsafety.NewShenYangTowerComService.model;

import java.util.List;

/**
 * Created by 刘畅 on 2017/8/24.
 */

public class PowerWorkBean {

    /**
     * pageNo : 1
     * genre :
     * pageSize : 10
     * totalRows : 2
     * rows : [{"address":"东莞市厚街镇1000号","allSize":0,"cartime":0,"dcxhtime":0,"endtime":"","fapgperson":"fufanglin","fromIndex":0,"fzdepartment":"元道","id":637,"idstr":"","joinordertime":"","jycanlelen":"0","jyequipoli":"0","latitude":321.321654,"longitude":113.123654,"notSend":"","now":"","operta":"移动","orderby":"","orderid":"201708043000095","pageNo":1,"pageSize":10,"perlatitude":0,"perlongitude":0,"pgaddress":"","pgendtime":"2017-08-08 13:09:59","pglevel":"1","pgperson":"fufanglin","pgstarttime":"2017-08-09 12:10:00","pgtime":"","sended":"","sitecode":"","sitename":"站址1000号","ssoperta":"移动","status":"已派单","tel":"15268114637","toIndex":10,"vtype":""},{"address":"丹东市东港市大东港客运站外","allSize":0,"cartime":0,"dcxhtime":0,"endtime":"","fapgperson":"fufanglin","fromIndex":0,"fzdepartment":"元道","id":636,"idstr":"","joinordertime":"","jycanlelen":"0","jyequipoli":"0","latitude":39.855108,"longitude":124.151405,"notSend":"","now":"","operta":"电信","orderby":"","orderid":"201708043000023","pageNo":1,"pageSize":10,"perlatitude":0,"perlongitude":0,"pgaddress":"","pgendtime":"2017-08-07 19:10:10","pglevel":"3","pgperson":"fufanglin","pgstarttime":"2017-08-06 12:10:09","pgtime":"","sended":"","sitecode":"210681010000000172","sitename":"中免外供","ssoperta":"电信","status":"已派单","tel":"15268114637","toIndex":10,"vtype":""}]
     */

    private int pageNo;
    private String genre;
    private int pageSize;
    private int totalRows;
    private List<RowsBean> rows;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * address : 东莞市厚街镇1000号
         * allSize : 0
         * cartime : 0
         * dcxhtime : 0
         * endtime :
         * fapgperson : fufanglin
         * fromIndex : 0
         * fzdepartment : 元道
         * id : 637
         * idstr :
         * joinordertime :
         * jycanlelen : 0
         * jyequipoli : 0
         * latitude : 321.321654
         * longitude : 113.123654
         * notSend :
         * now :
         * operta : 移动
         * orderby :
         * orderid : 201708043000095
         * pageNo : 1
         * pageSize : 10
         * perlatitude : 0
         * perlongitude : 0
         * pgaddress :
         * pgendtime : 2017-08-08 13:09:59
         * pglevel : 1
         * pgperson : fufanglin
         * pgstarttime : 2017-08-09 12:10:00
         * pgtime :
         * sended :
         * sitecode :
         * sitename : 站址1000号
         * ssoperta : 移动
         * status : 已派单
         * tel : 15268114637
         * toIndex : 10
         * vtype :
         */

        private String address;
        private int allSize;
        private int cartime;
        private int dcxhtime;
        private String endtime;
        private String fapgperson;
        private int fromIndex;
        private String fzdepartment;
        private int id;
        private String idstr;
        private String joinordertime;
        private String jycanlelen;
        private String jyequipoli;
        private double latitude;
        private double longitude;
        private String notSend;
        private String now;
        private String operta;
        private String orderby;
        private String orderid;
        private int pageNo;
        private int pageSize;
        private int perlatitude;
        private int perlongitude;
        private String pgaddress;
        private String pgendtime;
        private String pglevel;
        private String pgperson;
        private String pgstarttime;
        private String pgtime;
        private String sended;
        private String sitecode;
        private String sitename;
        private String ssoperta;
        private String status;
        private String tel;
        private int toIndex;
        private String vtype;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getAllSize() {
            return allSize;
        }

        public void setAllSize(int allSize) {
            this.allSize = allSize;
        }

        public int getCartime() {
            return cartime;
        }

        public void setCartime(int cartime) {
            this.cartime = cartime;
        }

        public int getDcxhtime() {
            return dcxhtime;
        }

        public void setDcxhtime(int dcxhtime) {
            this.dcxhtime = dcxhtime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getFapgperson() {
            return fapgperson;
        }

        public void setFapgperson(String fapgperson) {
            this.fapgperson = fapgperson;
        }

        public int getFromIndex() {
            return fromIndex;
        }

        public void setFromIndex(int fromIndex) {
            this.fromIndex = fromIndex;
        }

        public String getFzdepartment() {
            return fzdepartment;
        }

        public void setFzdepartment(String fzdepartment) {
            this.fzdepartment = fzdepartment;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIdstr() {
            return idstr;
        }

        public void setIdstr(String idstr) {
            this.idstr = idstr;
        }

        public String getJoinordertime() {
            return joinordertime;
        }

        public void setJoinordertime(String joinordertime) {
            this.joinordertime = joinordertime;
        }

        public String getJycanlelen() {
            return jycanlelen;
        }

        public void setJycanlelen(String jycanlelen) {
            this.jycanlelen = jycanlelen;
        }

        public String getJyequipoli() {
            return jyequipoli;
        }

        public void setJyequipoli(String jyequipoli) {
            this.jyequipoli = jyequipoli;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getNotSend() {
            return notSend;
        }

        public void setNotSend(String notSend) {
            this.notSend = notSend;
        }

        public String getNow() {
            return now;
        }

        public void setNow(String now) {
            this.now = now;
        }

        public String getOperta() {
            return operta;
        }

        public void setOperta(String operta) {
            this.operta = operta;
        }

        public String getOrderby() {
            return orderby;
        }

        public void setOrderby(String orderby) {
            this.orderby = orderby;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPerlatitude() {
            return perlatitude;
        }

        public void setPerlatitude(int perlatitude) {
            this.perlatitude = perlatitude;
        }

        public int getPerlongitude() {
            return perlongitude;
        }

        public void setPerlongitude(int perlongitude) {
            this.perlongitude = perlongitude;
        }

        public String getPgaddress() {
            return pgaddress;
        }

        public void setPgaddress(String pgaddress) {
            this.pgaddress = pgaddress;
        }

        public String getPgendtime() {
            return pgendtime;
        }

        public void setPgendtime(String pgendtime) {
            this.pgendtime = pgendtime;
        }

        public String getPglevel() {
            return pglevel;
        }

        public void setPglevel(String pglevel) {
            this.pglevel = pglevel;
        }

        public String getPgperson() {
            return pgperson;
        }

        public void setPgperson(String pgperson) {
            this.pgperson = pgperson;
        }

        public String getPgstarttime() {
            return pgstarttime;
        }

        public void setPgstarttime(String pgstarttime) {
            this.pgstarttime = pgstarttime;
        }

        public String getPgtime() {
            return pgtime;
        }

        public void setPgtime(String pgtime) {
            this.pgtime = pgtime;
        }

        public String getSended() {
            return sended;
        }

        public void setSended(String sended) {
            this.sended = sended;
        }

        public String getSitecode() {
            return sitecode;
        }

        public void setSitecode(String sitecode) {
            this.sitecode = sitecode;
        }

        public String getSitename() {
            return sitename;
        }

        public void setSitename(String sitename) {
            this.sitename = sitename;
        }

        public String getSsoperta() {
            return ssoperta;
        }

        public void setSsoperta(String ssoperta) {
            this.ssoperta = ssoperta;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public int getToIndex() {
            return toIndex;
        }

        public void setToIndex(int toIndex) {
            this.toIndex = toIndex;
        }

        public String getVtype() {
            return vtype;
        }

        public void setVtype(String vtype) {
            this.vtype = vtype;
        }
    }
}
