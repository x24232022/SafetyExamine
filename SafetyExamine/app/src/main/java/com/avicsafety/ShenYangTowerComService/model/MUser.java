package com.avicsafety.ShenYangTowerComService.model;

import java.io.Serializable;

public class MUser implements Serializable {

	private String locationValue;
	private String location;
	private String department;
	private String name;
	private String departmentId;
	private String mobile;
	private String locationId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String userName;

	public String getDepartment() {
		return department;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public String getLocation() {
		return location;
	}

	public String getLocationId() {
		return locationId;
	}

	public String getLocationValue() {
		return locationValue;
	}

	public String getMobile() {
		return mobile;
	}

	public String getName() {
		return name;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public void setLocationValue(String locationValue) {
		this.locationValue = locationValue;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setName(String name) {
		this.name = name;
	}

}
