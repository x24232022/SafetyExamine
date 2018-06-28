package com.avicsafety.safety_examine.dao;

import android.database.Cursor;

import com.avicsafety.lib.tools.ConnectionUtil;
import com.avicsafety.safety_examine.model.M_IpLib;

import org.xutils.DbManager;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class D_IpLib {

	/**
	 * 通过主键获得实体
	 * @param db
	 * @param id
	 * @return
	 */
	public M_IpLib getModel(DbManager db, int id){
		M_IpLib model = null;
		try {
			model = db.selector(M_IpLib.class).where("id","=",id).findFirst();
		} catch (DbException e) {
			e.printStackTrace();
		}
		return model;
	}
	
	/**
	 * 获取对象列表 所有 不分页
	 * @param db
	 * @return
	 */
	public List<M_IpLib> getList(DbManager db){
		return getList(db, "");
	}
	
	/**
	 * 获取对象列表 按排序 不分页
	 * @param db
	 * @param order
	 * @return
	 */
	public List<M_IpLib> getList(DbManager db, String order){
		return getList(db, null, order);
	}
	
	/**
	 * 获取对象列表 按条件 不分页
	 * @param db
	 * @param wb
	 * @return
	 */
	public List<M_IpLib> getList(DbManager db, WhereBuilder wb){
		return getList(db, wb, "");
	}

	/**
	 * 获取对象列表 按排序和条件 不分页
	 * @param db
	 * @param wb
	 * @param order
	 * @return
	 */
	public List<M_IpLib> getList(DbManager db, WhereBuilder wb, String order){
		List<M_IpLib> list = null;
		try {
			String[] v = checkOrderBy(order);
			boolean flag = Boolean.valueOf(v[0]);
			order = v[1];
			if(order.length()>0){
				if(wb==null)
					list = db.selector(M_IpLib.class).orderBy(order,flag).findAll();
				else
					list = db.selector(M_IpLib.class).where(wb).orderBy(order,flag).findAll();
			}else{
				if(wb==null)
					list = db.selector(M_IpLib.class).findAll();
				else
					list = db.selector(M_IpLib.class).where(wb).findAll();
			}
		} catch (DbException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取对象列表 所有 分页
	 * @param db
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<M_IpLib> getList(DbManager db, int pageIndex, int pageSize){
		return getList(db,null,"",pageIndex,pageSize);
	}
	
	/**
	 * 获取对象列表 按排序 分页
	 * @param db
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<M_IpLib> getList(DbManager db, String order, int pageIndex, int pageSize){
		return getList(db,null,order,pageIndex,pageSize);
	}
	
	/**
	 * 获取对象列表 按条件 分页
	 * @param db
	 * @param wb
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<M_IpLib> getList(DbManager db, WhereBuilder wb, int pageIndex, int pageSize){
		return getList(db,wb,"",pageIndex,pageSize);
	}
	
	/**
	 * 获取对象列表 按排序和条件 分页
	 * @param db
	 * @param wb
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<M_IpLib> getList(DbManager db, WhereBuilder wb, String order, int pageIndex, int pageSize){
		List<M_IpLib> list = null;
		try {
        	String[] v = checkOrderBy(order);
			boolean flag = Boolean.valueOf(v[0]);
			order = v[1];
			if(order.length()>0){
				if(wb==null)
					list = db.selector(M_IpLib.class).orderBy(order,flag).limit(pageSize).offset(pageIndex*pageSize-pageSize).findAll();
				else
					list = db.selector(M_IpLib.class).where(wb).orderBy(order,flag).limit(pageSize).offset(pageIndex*pageSize-pageSize).findAll();
			}else{
				if(wb==null)
					list = db.selector(M_IpLib.class).limit(pageSize).offset(pageIndex*pageSize-pageSize).findAll();
				else
					list = db.selector(M_IpLib.class).where(wb).limit(pageSize).offset(pageIndex*pageSize-pageSize).findAll();
			}
			
			}catch (DbException e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取列表按SQL语句
	 * @param db
	 * @param sql
	 * @return
	 */
	public List<M_IpLib> getListBySql(DbManager db, String sql){
		List<M_IpLib> list = new ArrayList<M_IpLib>();
		
		try {
			Cursor cursor = db.execQuery(sql);
			ConnectionUtil u = new ConnectionUtil();
			Collection collection = u.get(cursor, M_IpLib.class);
			for (Iterator it = collection.iterator(); it.hasNext();) {
				list.add((M_IpLib) it.next());
			}
		} catch (DbException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 插入一个对象
	 * @param db
	 * @param model
	 */
	public void add(DbManager db, M_IpLib model) {
		try {
			db.save(model);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新一个对象
	 * @param db
	 * @param model
	 */
	public void update(DbManager db, M_IpLib model) {
		try {
			db.saveOrUpdate(model);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}	

	/**
	 * 通过主键更新一组字段内容
	 * @param db
	 * @param id
	 * @param kv
	 */
	public void update(DbManager db, int id, KeyValue... kv) {
		try {
			db.update(M_IpLib.class, WhereBuilder.b("id", "=", id),kv);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据给定条件更新一组字段内容
	 * @param db
	 * @param wb
	 * @param kv
	 */
	public void update(DbManager db, WhereBuilder wb, KeyValue... kv) {
		try {
			db.update(M_IpLib.class, wb,kv);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过主键删除数据
	 * @param db
	 * @param id
	 */
	public void delete(DbManager db, int id) {
		try {
			db.delete(M_IpLib.class, WhereBuilder.b("id","=",id));
		} catch (DbException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过给定的条件删除数据
	 * @param db
	 * @param wb
	 */
	public void delete(DbManager db, WhereBuilder wb) {
		try {
			db.delete(M_IpLib.class, wb);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除所有数据
	 * @param db
	 */
	public void deleteAll(DbManager db) {
		try {
			db.delete(M_IpLib.class);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 得到数量
	 * @param db
	 * @return
	 */
	public long getCount(DbManager db){
		long v = 0;
		try {
			v = db.selector(M_IpLib.class).count();
		} catch (DbException e) {
			e.printStackTrace();
		}
		return v;
	}
	
	/**
	 * 得到给定的条件的数据数量
	 * @param db
	 * @param wb
	 * @return
	 */
	public long getCount(DbManager db, WhereBuilder wb){
		long v = 0;
		try {
			v = db.selector(M_IpLib.class).where(wb).count();
		} catch (DbException e) {
			e.printStackTrace();
		}
		return v;
	}
    
     /**
	 * 执行一条SQL语句
	 * @param db
	 * @param wb
	 * @return
	 */
	public void execute(DbManager db, String sql){
		try {
			db.execQuery(sql);
		} catch (DbException e) {
			e.printStackTrace();
		}
		return;
	}
	
	private String[] checkOrderBy(String order) {
		// TODO Auto-generated method stub
		if(order==null||order.length()==0){
			return new String[]{"false",""};
		}
		String isdesc;
		if(order.contains("desc")){
			isdesc = "true";
			order = order.trim().replace("desc", "").trim();
		}
		else{
			isdesc = "false";
			order = order.trim().replace("asc", "").trim();
		}
		return new String[]{isdesc, order};
	}

}