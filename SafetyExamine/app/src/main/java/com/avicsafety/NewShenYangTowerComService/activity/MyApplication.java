package com.avicsafety.NewShenYangTowerComService.activity;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDex;
import android.util.Log;


import com.avicsafety.NewShenYangTowerComService.PowerManager.push.Utils.CrashHandler;
import com.avicsafety.NewShenYangTowerComService.PowerManager.push.Utils.DaoMaster;
import com.avicsafety.NewShenYangTowerComService.PowerManager.push.Utils.DaoSession;
import com.avicsafety.lib.tools.L;
import com.avicsafety.NewShenYangTowerComService.conf.Configuration;
import com.baidu.mapapi.SDKInitializer;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

import org.litepal.LitePalApplication;
import org.xutils.x;

import java.io.InputStream;
import java.io.ObjectInputStream;


public class MyApplication extends LitePalApplication {

//	public final static double[] X = new double[660 * 450];
//	public final static double[] Y = new double[660 * 450];
//	private DaoMaster.DevOpenHelper helper;
//	private SQLiteDatabase db;
//	private DaoMaster daoMaster;
//	private DaoSession daoSession;
//	private CrashHandler crashHandler;
//	public DaoSession getDaoSession() {
//		if(daoSession==null){
//			initDB();
//		}
//		return daoSession;
//	}

//	private void initDB() {
//		helper = new DaoMaster.DevOpenHelper(this,"POSITION_BEAN.db",null);
//		db = helper.getWritableDatabase();
//		daoMaster = new DaoMaster(db);
//		daoSession = daoMaster.newSession();
//	}

	@Override
	public void onCreate() {
		super.onCreate();
		x.Ext.init(this);
		Configuration.init(this);
//		XGPushConfig.enableDebug(this, true);
		x.Ext.setDebug(Configuration.DEBUG);
		L.isDebug = Configuration.DEBUG;
		//SDKInitializer.initialize(getApplicationContext());
//		crashHandler=CrashHandler.getInstance();
//		crashHandler.init(this);
//		initDB();
//		initXy();
		//InitPath(); 由于6.0权限问题 该功能已经转移到 MainActivity wel()方法中 By shili 2017-4-20
	}

//	private void initXy() {
//		if (X[6] == 0 || X[90] == 0) {
//			new Thread(new Runnable() {
//
//				@Override
//				public void run() {
//					try {
//						InputStream in;
//						int id=getResources().getIdentifier("axisoffset", "raw",
//								getPackageName());
//						in = getResources().openRawResource(id);
//						init(in);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}).start();
//		}
//	}
//
//	//初始化文件数据
//	public void init(InputStream inputStream) throws Exception {
//		ObjectInputStream in = new ObjectInputStream(inputStream);
//		try {
//			int i = 0;
//			while (in.available() > 0) {
//				if ((i & 1) == 1) {
//					Y[(i - 1) >> 1] = in.readInt() / 100000.0d;
//				} else {
//					X[i >> 1] = in.readInt() / 100000.0d;
//				}
//				i++;
//			}
//		} finally {
//			if (in != null)
//				in.close();
//		}
//	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}
}
