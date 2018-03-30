package com.avicsafety.ShenYangTowerComService.photoUtils;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Environment;


import java.io.File;

/**
 * Created by 刘畅 on 2017/6/13.
 */

public class Configuration {

    public static Application app;
    public static final boolean DEBUG = true;
    public static final boolean CHECK_SERVER_CONFIG = true;
    public static String BASE_PATH = "";
    public static String DOWNLOAD_PATH = "";
    public static String IMAGE_PATH = "";
    public static String HTML_PATH = "";

//    public static String DB_NAME = "db.db02";
 //   public static String LAW_DB_NAME = "law.db02";
    public static String DOMAIN_URL = "http://www.avicsafety.com";

    //配置信息
    public Configuration(){
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    //MYAPPLICATION 初始化
    public static void init(Application app) {
        if(Configuration.app==null) {
            Configuration.app = app;
            Context context = app.getApplicationContext();
            if(Build.VERSION.SDK_INT>=20){
                BASE_PATH = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
                DOWNLOAD_PATH = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
                IMAGE_PATH = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
                HTML_PATH = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
            }else{
                String path = SDCardUtils.getSDCardPath() + app.getPackageName() + File.separator;
                BASE_PATH = path + "Documents";
                DOWNLOAD_PATH = path + "Downloads";
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
        if(Build.VERSION.SDK_INT>=20){
        }else{
            //在Android 4的机器上创建一个文件夹
            File app_dir = new File(SDCardUtils.getSDCardPath()+app.getPackageName()+ File.separator);
            if(!app_dir.exists()){
                app_dir.mkdir();
            }
        }

        File base_dir = new File(Configuration.BASE_PATH);
        if (!base_dir.exists()) {
            base_dir.mkdir();
        }else{
        }

        File down_dir = new File(Configuration.DOWNLOAD_PATH);
        if (!down_dir.exists()) {
            down_dir.mkdir();
        }else{
        }

        File html_dir = new File(Configuration.HTML_PATH);
        if (!html_dir.exists()) {
            html_dir.mkdir();
        }else{
        }

        File image_dir = new File(Configuration.IMAGE_PATH);
        if (!image_dir.exists()) {
            image_dir.mkdir();
        }else{
        }
    }

}
