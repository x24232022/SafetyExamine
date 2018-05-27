package com.avicsafety.NewShenYangTowerComService.PowerManager.push.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static SimpleDateFormat sdf;
	public static String getDateForString(Object date,String type){
		sdf=(SimpleDateFormat) SimpleDateFormat.getInstance();
		sdf.applyPattern(type);
		return sdf.format(date);
	}
	public static Date getDate(String date,String type){
		try {
			sdf = (SimpleDateFormat) SimpleDateFormat.getInstance();
			sdf.applyPattern(type);
			return sdf.parse(date);
		} catch (Exception e) {
			return new Date();
		}
	}
	public static long getDateForLong(String date,String type){
		try {
			sdf = (SimpleDateFormat) SimpleDateFormat.getInstance();
			sdf.applyPattern(type);
			return sdf.parse(date).getTime();
		} catch (Exception e) {
			return new Date().getTime();
		}
	}
	
	//在某一时间的基础上加几个小时
	public static long getDateForString(String data,double adddate){
		Date date=getDate(data, "yyyy-MM-dd HH:mm:ss");
		long addtime=(long) (adddate*3600000);
		long time=date.getTime()+addtime;
		return time;
	}
	
	public static long getLongForString(double date){
		long addtime=(long) (date*3600000);
		return addtime; 
	}
	
	public static boolean getCompared(String startDate,String endDate,String type){
		sdf = (SimpleDateFormat) SimpleDateFormat.getInstance();
		sdf.applyPattern(type);
		try {
			Date date=sdf.parse(startDate);
			Date date2=sdf.parse(endDate);
			//date2>date=true date2<=date=false
			return date2.after(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean getEqualCompared(String startDate,String endDate,String type){
		sdf = (SimpleDateFormat) SimpleDateFormat.getInstance();
		sdf.applyPattern(type);
		try {
			Date date=sdf.parse(startDate);
			Date date2=sdf.parse(endDate);
			//date<date2
			return date.before(date2);
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}
}
