package com.avicsafety.safety_examine.conf;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.avicsafety.lib.tools.L;
import com.avicsafety.lib.tools.SDCardUtils;

import java.io.File;

public class Configuration {


    public static Application app;
	public static final boolean DEBUG = true;
	public static final boolean CHECK_SERVER_IP = true;

	public static String BASE_PATH = "";
	public static String DOWNLOAD_PATH = "";
	public static String IMAGE_PATH = "";
	public static String HTML_PATH = "";
	public static String DOMAIN_URL = "";

	public static String DB_NAME = "db.db03";

	//配置信息
	public Configuration(){
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	//MYAPPLICATION 初始化
	public static void init(Application app) {
		if(Configuration.app==null) {
			Configuration.app = app;
			Context context = app.getApplicationContext();
			if(android.os.Build.VERSION.SDK_INT>=20){
				BASE_PATH = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
				DOWNLOAD_PATH = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
				//APK_Calculator_PATH = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
				IMAGE_PATH = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
				HTML_PATH = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
			}else{
				String path = SDCardUtils.getSDCardPath() + app.getPackageName() + File.separator;
				BASE_PATH = path + "Documents";
				DOWNLOAD_PATH = path + "Downloads";
				//APK_Calculator_PATH = DOWNLOAD_PATH;
				IMAGE_PATH = path + "Pictures";
				HTML_PATH = path + "Documents";
			}
		}else{
			throw new UnsupportedOperationException("Error");
		}
	}

	//MAINACTIVITY 初始化
	public static void InitPath() {
		// TODO Auto-generated method stub
		if(android.os.Build.VERSION.SDK_INT>=20){
			L.i("this Sdk Version don't createDirectory");
		}else{
			//在Android 4的机器上创建一个文件夹
			File app_dir = new File(SDCardUtils.getSDCardPath()+app.getPackageName()+File.separator);
			if(!app_dir.exists()){
				app_dir.mkdir();
			}
		}

		File base_dir = new File(Configuration.BASE_PATH);
		if (!base_dir.exists()) {
			base_dir.mkdir();
			L.i("Configuration", "base path can't find, create it");
		}else{
			L.i("Configuration", "base path ok");
		}

		File down_dir = new File(Configuration.DOWNLOAD_PATH);
		if (!down_dir.exists()) {
			down_dir.mkdir();
			L.i("Configuration", "download path can't find, create it");
		}else{
			L.i("Configuration", "download path ok");
		}

		File html_dir = new File(Configuration.HTML_PATH);
		if (!html_dir.exists()) {
			html_dir.mkdir();
			L.i("Configuration", "HTML path can't find, create it");
		}else{
			L.i("Configuration", "HTML path ok");
		}

		File image_dir = new File(Configuration.IMAGE_PATH);
		if (!image_dir.exists()) {
			image_dir.mkdir();
			L.i("Configuration", "IMAGE path can't find, create it");
		}else{
			L.i("Configuration", "IMAGE path ok");
		}

		//等同DOWNLOAD目录
//		File apk_dir = new File(Configuration.APK_Calculator_PATH);
//		if (!apk_dir.exists()) {
//			apk_dir.mkdir();
//			L.i("Configuration", "APK path can't find, create it");
//		}else{
//			L.i("Configuration", "APK path ok");
//		}
	}

}
