package com.avicsafety.NewShenYangTowerComService.PowerManager.push.Utils;

import android.content.Context;
import android.content.SharedPreferences;
@SuppressWarnings("static-access")
public class SharedUtils {
	public static SharedPreferences mShared;
	
	public static void putShared(Context context,String key,boolean value){
		mShared=context.getSharedPreferences("power", context.MODE_PRIVATE);
		mShared.edit().putBoolean(key, value).commit();
	}
	public static boolean getBooleanShared(Context context,String key){
		mShared=context.getSharedPreferences("power", context.MODE_PRIVATE);
		return mShared.getBoolean(key, false);
	}
	
	public static void putShared(Context context,String key,int value){
		mShared=context.getSharedPreferences("power", context.MODE_PRIVATE);
		mShared.edit().putInt(key, value).commit();
	}
	public static void putShared(Context context,String key,long value){
		mShared=context.getSharedPreferences("power", context.MODE_PRIVATE);
		mShared.edit().putLong(key, value).commit();
	}
	public static int getIntShared(Context context,String key){
		mShared=context.getSharedPreferences("power", context.MODE_PRIVATE);
		return mShared.getInt(key,0);
	}
	public static long getLongShared(Context context,String key){
		mShared=context.getSharedPreferences("power", context.MODE_PRIVATE);
		return mShared.getLong(key,0);
	}
	
	public static void putShared(Context context,String key,String value){
		mShared=context.getSharedPreferences("power", context.MODE_PRIVATE);
		mShared.edit().putString(key, value).commit();
	}
	
	public static String getStringShared(Context context,String key){
		mShared=context.getSharedPreferences("power", context.MODE_PRIVATE);
		return mShared.getString(key,"");
	}
	
	

	
	

}
