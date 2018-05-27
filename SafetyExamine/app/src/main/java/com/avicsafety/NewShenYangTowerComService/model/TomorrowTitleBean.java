package com.avicsafety.NewShenYangTowerComService.model;

public class TomorrowTitleBean {
    String data;
    String week;

    public TomorrowTitleBean(String data, String week) {
        this.data = data;
        this.week = week;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getData() {

        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
