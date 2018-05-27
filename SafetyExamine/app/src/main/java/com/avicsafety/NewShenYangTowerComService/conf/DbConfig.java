package com.avicsafety.NewShenYangTowerComService.conf;

import android.content.Context;

import com.avicsafety.lib.tools.L;
import com.avicsafety.lib.tools.SDCardUtils;

import org.xutils.DbManager;
import org.xutils.DbManager.DaoConfig;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DbConfig {

	static{
		L.i("DbPath is already :" + Configuration.BASE_PATH);
	}
	
	public static DaoConfig getDbConfig(){
		//Context context = x.app().getApplicationContext();
		DaoConfig dConfig = new DaoConfig()
	    .setDbName(Configuration.DB_NAME)
	    .setDbDir(new File(Configuration.BASE_PATH))
	    .setDbVersion(3)
	    .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
	        @Override
	        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {}
	    });
		return dConfig;
	}

//	public static DaoConfig getLawDbConfig(){
//		//Context context = x.app().getApplicationContext();
//		DaoConfig dConfig = new DbManager.DaoConfig()
//				.setDbName(Configuration.LAW_DB_NAME)
//				.setDbDir(new File(Configuration.BASE_PATH))
//				.setDbVersion(3)
//				.setDbUpgradeListener(new DbManager.DbUpgradeListener() {
//					@Override
//					public void onUpgrade(DbManager db, int oldVersion, int newVersion) {}
//				});
//		return dConfig;
//	}

	public static void initDb(Context context, String dbname) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			L.i("DbConfig", dbname+" init start");
			File file = new File(Configuration.BASE_PATH+File.separator+dbname);
			if (!file.exists()) {
				L.i("DbConfig", dbname+" Init not exists");
				Runtime.getRuntime().exec("chmod 766 " + file);
				if(SDCardUtils.copyAssetsFileToSDCard(context,dbname,file.getAbsolutePath())){
					L.i("DbConfig", dbname+" init base file not exist, copy success");
				}else{
					L.e("DbConfig", dbname+" init base file not exist, copy fail");
				}
			}
			L.i("DbConfig", dbname+" init config success");
		} catch (IOException e) {
			e.printStackTrace();
			L.i("DbConfig", dbname+" Init fail message :" + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


}
