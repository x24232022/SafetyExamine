package com.avicsafety.safety_examine.PowerManager.push.Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.avicsafety.safety_examine.PowerManager.push.View.AbPullToRefreshView;
import com.avicsafety.safety_examine.R;
import com.avicsafety.safety_examine.model.MUser;
import com.avicsafety.lib.tools.L;
import com.avicsafety.lib.tools.SPUtils;

public class Utils {
	public static ProgressDialog dialog;
	public final static int vesion=20170704;
	public static String space(String content){
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(content);
		return m.replaceAll("");
	}
	
	public static void showProgress(String message,boolean canable,Activity activity){
		if(activity.isFinishing()){
			return;
		}
		if(dialog==null){
			dialog=new ProgressDialog(activity,3);
			dialog.setMessage(message);
			dialog.setCancelable(canable);
			dialog.setCanceledOnTouchOutside(canable);
			dialog.show();
		}
	}
	public static void hideProgress(Activity activity){
		if(activity.isFinishing()){
			dialog=null;
			return;
		}
		if(dialog!=null&&dialog.isShowing()){
			dialog.dismiss();
			dialog=null;
		}
	}
	
	public static int getHeight(Activity activity){
		DisplayMetrics metric = new DisplayMetrics();  
		activity.getWindowManager().getDefaultDisplay().getMetrics(metric);  
		int height = metric.heightPixels;   // 屏幕高度（像素）  
		return height;
	}
	public static int getWidth(Activity activity){
		DisplayMetrics metric = new DisplayMetrics();  
		activity.getWindowManager().getDefaultDisplay().getMetrics(metric);  
		int width = metric.widthPixels;     // 屏幕宽度（像素）  
		return width; 
	}
	
	//隐藏刷新布局
	public static void hideRefresh(AbPullToRefreshView mRefreshView){
		mRefreshView.onFooterLoadFinish();
		mRefreshView.onHeaderRefreshFinish();
	}
	
	/**
	 * 加载下拉框
	 * @param context
	 * @param spinner
	 * @param list
	 */
	public static void loadSpinner(Context context,Spinner spinner,String[] list){
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.simple_spinner_item , list);
		adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
	}
	
	public static void loadSpinner(Context context,Spinner spinner, List<String> list){
		loadSpinner(context, spinner, list.toArray(new String[list.size()]));
		
	}

	
	/**
     * 获取屏幕尺寸与密度.
     *
     * @param context the context
     * @return mDisplayMetrics
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        Resources mResources;
        if (context == null){
            mResources = Resources.getSystem();
            
        }else{
            mResources = context.getResources();
        }
        DisplayMetrics mDisplayMetrics = mResources.getDisplayMetrics();
        return mDisplayMetrics;
    }
	
	public static boolean detection(Context context){
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if(info==null){
			ToastUtils.showToast(context, "网络连接不可用");
			return false;
		}else{
			return true;
		}
	}
	
	
	/**
	 * 保存用户信息到共享字段
	 * 
	 * @param oAuth_1
	 * @param context
	 */
	public static void saveOAuth(MUser oAuth_1, Context context) {

		// 创建字节输出流
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			// 创建对象输出流，并封装字节流
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			// 将对象写入字节流
			oos.writeObject(oAuth_1);
			// 将字节流编码成base64的字符窜
			String oAuth_Base64 = new String(Base64.encode(baos
					.toByteArray(),Base64.DEFAULT));
			//SharedUtils.putShared(context, "userBean", oAuth_Base64);
			SPUtils.put(context,"userBean1",oAuth_Base64);
		} catch (IOException e) {
			L.v("为什么会没save进来呢:",e.getMessage().toString());
		}
	}

	/**
	 * 从共享字段中获取用户信息
	 * 
	 * @param context
	 * @return
	 */
	public static MUser readOAuth(Context context) {

		MUser oAuth_1 = null;
		String productBase64 =(String)SPUtils.get(context,"userBean1","");;
		// 读取字节
		byte[] base64 = Base64.decode(productBase64.getBytes(), Base64.DEFAULT);
		// 封装到字节流
		ByteArrayInputStream bais = new ByteArrayInputStream(base64);
		try {
			// 再次封装
			ObjectInputStream bis = new ObjectInputStream(bais);
			try {
				// 读取对象
				oAuth_1 = (MUser) bis.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return oAuth_1;
	}
	
	public static  String calculateOrientation(float value) {
		String direction="";
		if (value>= -5 && value< 5) {
			direction="正北";
		} else if (value>= 5 && value< 85) {
			// Log.i(TAG, "东北";
			direction="东北";
		} else if (value>= 85 && value<= 95) {
			// Log.i(TAG, "正东";
			direction="正东";
		} else if (value>= 95 && value< 175) {
			// Log.i(TAG, "东南";
			direction="东南";
		} else if ((value>= 175 && value<= 180)
				|| (value) >= -180 && value< -175) {
			// Log.i(TAG, "正南";
			direction="正南";
		} else if (value>= -175 && value< -95) {
			// Log.i(TAG, "西南";
			direction="西南";
		} else if (value>= -95 && value< -85) {
			// Log.i(TAG, "正西";
			direction="正西";
		} else if (value>= -85 && value< -5) {
			// Log.i(TAG, "西北";
			direction="西北";
		}
		Log.d("DD", direction);
		return direction;
	}
	
	public static PointDouble c2s(PointDouble pt,double X[],double Y[]) {
		int cnt = 10;
		double x = pt.x, y = pt.y;
		while (cnt-- > 0) {
			if (x < 71.9989d || x > 137.8998d || y < 9.9997d || y > 54.8996d)
				return pt;
			int ix = (int) (10.0d * (x - 72.0d));
			int iy = (int) (10.0d * (y - 10.0d));
			double dx = (x - 72.0d - 0.1d * ix) * 10.0d;
			double dy = (y - 10.0d - 0.1d * iy) * 10.0d;
			x = (x + pt.x - (1.0d - dx) * (1.0d - dy) * X[ix + 660 * iy] - dx
					* (1.0d - dy) * X[ix + 660 * iy + 1] - dx * dy
					* X[ix + 660 * iy + 661] - (1.0d - dx) * dy
					* X[ix + 660 * iy + 660] + x) / 2.0d;
			y = (y + pt.y - (1.0d - dx) * (1.0d - dy) * Y[ix + 660 * iy] - dx
					* (1.0d - dy) * Y[ix + 660 * iy + 1] - dx * dy
					* Y[ix + 660 * iy + 661] - (1.0d - dx) * dy
					* Y[ix + 660 * iy + 660] + y) / 2.0d;
		}
		return new PointDouble(x, y);
	}
	
	public static int totalPages(int size){
		int count=size/10;
		if(size%10==0){
			return count;
		}else{
			return count+1;
		}
	}
	
	/**
	 * 保留小数点后几位
	 * 
	 * @return
	 */
	public static double doubleFormat(double d, int max) {
		BigDecimal b = new BigDecimal(d);
		double d2 = b.setScale(max, BigDecimal.ROUND_HALF_UP).doubleValue();
		return d2;
	}
}
