package com.avicsafety.ShenYangTowerComService.PowerManager.push.entity;

import java.io.Serializable;

public class UserBean implements Serializable {
	private String  deptName;//所属组织
	private String sessionId;//sessionId
	private String name;//用户名称
	private int deptLevel;//级别
	private String account;//用户账号
	private int code;//返回编码
	private int deptId;//组织id
	private String isPower;//=是就是发电人员
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDeptLevel() {
		return deptLevel;
	}
	public void setDeptLevel(int deptLevel) {
		this.deptLevel = deptLevel;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getIsPower() {
		return isPower;
	}
	public void setIsPower(String isPower) {
		this.isPower = isPower;
	}
}
