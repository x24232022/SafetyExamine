package com.avicsafety.ShenYangTowerComService.PowerManager.push;

import java.util.List;

/**
 * Created by 刘畅 on 2017/10/24.
 */

public class Mddrwsl {

    /**
     * pageNo : 1
     * genre :
     * pageSize : 10
     * totalRows : 11
     * rows : []
     */

    private int pageNo;
    private String genre;
    private int pageSize;
    private int totalRows;
    private List<?> rows;

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

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
