package com.avicsafety.safety_examine.service;

import com.avicsafety.safety_examine.conf.DbConfig;

import org.xutils.DbManager;
import org.xutils.x;

public abstract class abstract_service {
	public static DbManager base_db;
	public static DbManager law_db;
	public abstract_service(){
		base_db = x.getDb(DbConfig.getDbConfig());
		//law_db = x.getDb(DbConfig.getLawDbConfig());
	}
	
	static {
		abstract_service.init();
	}
	
	public static void init(){
		base_db = x.getDb(DbConfig.getDbConfig());
		//law_db = x.getDb(DbConfig.getLawDbConfig());
	}


//	protected <T> T getListByJSON(JSONArray jsonArray) {
//		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializerUtils()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//		java.lang.reflect.Type type = new TypeToken<List<T>>() {}.getType();
//		T list = gson.fromJson(jsonArray.toString(), type);
//		return list;
//	}
//
//	protected <T> T getModelByJSON(JSONObject jsonObject) {
//		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializerUtils()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//		java.lang.reflect.Type type = new TypeToken<T>() {}.getType();
//		T model = gson.fromJson(jsonObject.toString(), type);
//		return model;
//	}
}
