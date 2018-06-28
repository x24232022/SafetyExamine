package com.avicsafety.safety_examine.model;

import java.util.List;

/**
 * Created by 刘畅 on 2017/8/24.
 */

public class PowerWorkBeanMyImpl {

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
    private List<PowerWork> rows;

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

    public List<PowerWork> getRows() {
        return rows;
    }

    public void setRows(List<PowerWork> rows) {
        this.rows = rows;
    }
}
