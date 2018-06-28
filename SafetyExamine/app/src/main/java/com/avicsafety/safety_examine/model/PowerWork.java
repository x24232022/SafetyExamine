package com.avicsafety.safety_examine.model;

import java.io.Serializable;

/**
 * Created by 刘畅 on 2017/8/25.
 */

public class PowerWork implements Serializable {
    private int id; // 唯一性主键
    private String orderid; // 系统生成(存储过程)
    private String sitename; // 基站名称
    private double longitude; // 经度基站
    private double latitude; // 纬度基站
    private String address; // 地址
    private String pgstarttime;//停电开始时间
    private String pgendtime;//停电结束时间
    private String pglevel; // 发电等级
    private double dcxhtime; // 电池续航时间
    private double cartime; // 开车预计时间
    private String jyequipoli; // 建议配备油机
    private String jycanlelen; // 建议发电电缆长度
    private String fzdepartment; // 负责单位
    private String pgperson; // 发电负责人
    private String joinordertime; // 接单时间
    private String fapgperson; // 发电人
    private String tel; // 联系方式
    private String pgtime; // 发电时间
    private String pgaddress; // 发电地址
    private String endtime; // 结束时间
    private String sysassperson; // 系统分配处理人员
    private String status; // 工单状态
    private String faulttime ;//故障时间
    private int dispatchNum;//工单分派次数
    private String now;//储存当前时间
    private String ssoperta;//运营商
    //新增
    private String Sended;//用于传递参数判断业务逻辑
    private String notSend;//用于传递参数判断业务逻辑

    private double perlongitude ;//人员经度
    private double perlatitude;//人员纬度

    private String type;//判断是事故工单还是计划工单

    private String vtype;//供电类型
    private String theme;//主题
    private String locationStr;//地市
    private String area;//区域
    private String btsType;//基站类型
    private String faultNo;//故障工单号
    private String creator;//创建人姓名
    private String troubleNo;//集团工单编号
    public String getVtype() {
        return vtype;
    }

    public void setVtype(String vtype) {
        this.vtype = vtype;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getLocationStr() {
        return locationStr;
    }

    public void setLocationStr(String locationStr) {
        this.locationStr = locationStr;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBtsType() {
        return btsType;
    }

    public void setBtsType(String btsType) {
        this.btsType = btsType;
    }

    public String getFaultNo() {
        return faultNo;
    }

    public void setFaultNo(String faultNo) {
        this.faultNo = faultNo;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTroubleNo() {
        return troubleNo;
    }

    public void setTroubleNo(String troubleNo) {
        this.troubleNo = troubleNo;
    }


    /**
     * @param set 唯一性主键
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param get 唯一性主键
     */
    public int getId() {
        return id;
    }

    /**
     * @param set 系统生成(存储过程)
     */
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    /**
     * @param get 系统生成(存储过程)
     */
    public String getOrderid() {
        return orderid;
    }

    /**
     * @param set 基站名称
     */
    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    /**
     * @param get 基站名称
     */
    public String getSitename() {
        return sitename;
    }

    /**
     * @param set 经度
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @param get 经度
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param set 纬度
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @param get 纬度
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param set 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @param get 地址
     */
    public String getAddress() {
        return address;
    }


    /**
     * @param set 发电等级
     */
    public void setPglevel(String pglevel) {
        this.pglevel = pglevel;
    }

    /**
     * @param get 发电等级
     */
    public String getPglevel() {
        return pglevel;
    }

    /**
     * @param set 电池续航时间
     */
    public void setDcxhtime(double dcxhtime) {
        this.dcxhtime = dcxhtime;
    }

    /**
     * @param get 电池续航时间
     */
    public double getDcxhtime() {
        return dcxhtime;
    }

    /**
     * @param set 开车预计时间
     */
    public void setCartime(double cartime) {
        this.cartime = cartime;
    }

    /**
     * @param get 开车预计时间
     */
    public double getCartime() {
        return cartime;
    }

    /**
     * @param set 建议配备油机
     */
    public void setJyequipoli(String jyequipoli) {
        this.jyequipoli = jyequipoli;
    }

    /**
     * @param get 建议配备油机
     */
    public String getJyequipoli() {
        return jyequipoli;
    }

    /**
     * @param set 建议发电电缆长度
     */
    public void setJycanlelen(String jycanlelen) {
        this.jycanlelen = jycanlelen;
    }

    /**
     * @param get 建议发电电缆长度
     */
    public String getJycanlelen() {
        return jycanlelen;
    }

    /**
     * @param set 负责单位
     */
    public void setFzdepartment(String fzdepartment) {
        this.fzdepartment = fzdepartment;
    }

    /**
     * @param get 负责单位
     */
    public String getFzdepartment() {
        return fzdepartment;
    }

    /**
     * @param set 发电负责人
     */
    public void setPgperson(String pgperson) {
        this.pgperson = pgperson;
    }

    /**
     * @param get 发电负责人
     */
    public String getPgperson() {
        return pgperson;
    }

    /**
     * @param set 接单时间
     */
    public void setJoinordertime(String joinordertime) {
        this.joinordertime = joinordertime;
    }

    /**
     * @param get 接单时间
     */
    public String getJoinordertime() {
        return joinordertime;
    }

    /**
     * @param set 发电人
     */
    public void setFapgperson(String fapgperson) {
        this.fapgperson = fapgperson;
    }

    /**
     * @param get 发电人
     */
    public String getFapgperson() {
        return fapgperson;
    }

    /**
     * @param set 联系方式
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @param get 联系方式
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param set 发电时间
     */
    public void setPgtime(String pgtime) {
        this.pgtime = pgtime;
    }

    /**
     * @param get 发电时间
     */
    public String getPgtime() {
        return pgtime;
    }

    /**
     * @param set 发电地址
     */
    public void setPgaddress(String pgaddress) {
        this.pgaddress = pgaddress;
    }

    /**
     * @param get 发电地址
     */
    public String getPgaddress() {
        return pgaddress;
    }

    /**
     * @param set 结束时间
     */
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    /**
     * @param get 结束时间
     */
    public String getEndtime() {
        return endtime;
    }

    public String getSended() {
        return Sended;
    }

    public void setSended(String sended) {
        Sended = sended;
    }

    public String getNotSend() {
        return notSend;
    }

    public void setNotSend(String notSend) {
        this.notSend = notSend;
    }

    public double getPerlongitude() {
        return perlongitude;
    }

    public void setPerlongitude(double perlongitude) {
        this.perlongitude = perlongitude;
    }

    public double getPerlatitude() {
        return perlatitude;
    }

    public void setPerlatitude(double perlatitude) {
        this.perlatitude = perlatitude;
    }

    public String getSysassperson() {
        return sysassperson;
    }

    public void setSysassperson(String sysassperson) {
        this.sysassperson = sysassperson;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFaulttime() {
        return faulttime;
    }

    public void setFaulttime(String faulttime) {
        this.faulttime = faulttime;
    }

    public int getDispatchNum() {
        return dispatchNum;
    }

    public void setDispatchNum(int dispatchNum) {
        this.dispatchNum = dispatchNum;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPgstarttime() {
        return pgstarttime;
    }

    public void setPgstarttime(String pgstarttime) {
        this.pgstarttime = pgstarttime;
    }

    public String getPgendtime() {
        return pgendtime;
    }

    public void setPgendtime(String pgendtime) {
        this.pgendtime = pgendtime;
    }

    public String getSsoperta() {
        return ssoperta;
    }

    public void setSsoperta(String ssoperta) {
        this.ssoperta = ssoperta;
    }
}
