package com.avicsafety.NewShenYangTowerComService.model;

import org.litepal.crud.DataSupport;

public class WorkOrderBean extends DataSupport{
    String userid;
    int type;
    String ticketid;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTicketid() {
        return ticketid;
    }

    public void setTicketid(String ticketid) {
        this.ticketid = ticketid;
    }

    public String getUserid() {

        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
