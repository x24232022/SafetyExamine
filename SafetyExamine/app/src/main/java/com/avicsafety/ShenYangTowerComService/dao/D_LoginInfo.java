package com.avicsafety.ShenYangTowerComService.dao;

import com.avicsafety.ShenYangTowerComService.model.M_LoginInfo;

import org.apache.commons.lang3.StringUtils;
import org.xutils.DbManager;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;


public class D_LoginInfo {

	/**
	 * 获取当前登录用户数量
	 * @param db
	 * @return
     */
	public long getActiveCount(DbManager db){
		long count = 0;
		try {
			count = (int) db.selector(M_LoginInfo.class).where("active","=",1).count();
		} catch (DbException e) {
			e.printStackTrace();
		}
		return count;
	}

	public void save(DbManager db, M_LoginInfo model){
		try {
			db.saveOrUpdate(model);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}


	public void logout(DbManager db){
		try {
			db.update(M_LoginInfo.class, WhereBuilder.b("active", "=", 1), new KeyValue("active",0));
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	public M_LoginInfo getLoginInfo(DbManager db){
		M_LoginInfo model = null;
		try {
			model = db.selector(M_LoginInfo.class).where("active","=", 1).findFirst();
		} catch (DbException e) {
			e.printStackTrace();
		}
		return model;
	}

	public String getLoginUsername(DbManager db){
		String userName = getLoginInfo(db).getUserName();
		if(StringUtils.isBlank(userName)){
			userName = "";
		}
		return userName;
	}
}
