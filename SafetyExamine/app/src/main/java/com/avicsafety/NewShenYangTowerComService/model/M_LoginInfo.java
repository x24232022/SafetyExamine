package com.avicsafety.NewShenYangTowerComService.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name="t_login_info")
public class M_LoginInfo {
			 
    @Column(name = "userId",isId=true)    
	private int userId;
			
    @Column(name = "userName")    
	private String userName;
			
    @Column(name = "password")    
	private String password;
			
    @Column(name = "name")    
	private String name;
			
    @Column(name = "mobile")    
	private String mobile;
			
    @Column(name = "location")    
	private String RegionStr;  //所属地区
    
    @Column(name="locationId")
    private int locationId;
			
    @Column(name = "locationValue")    
	private String locationValue;
			
    @Column(name = "department")    
	private String type;  //职位  主要控制否为管理员
			
    @Column(name = "departmentId")    
	private int departmentId;
			
    @Column(name = "active")    
	private int active;


	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(String locationValue) {
		this.locationValue = locationValue;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}

	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getRegionStr() {
		return RegionStr;
	}
	public void setRegionStr(String regionStr) {
		RegionStr = regionStr;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}