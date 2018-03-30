package com.avicsafety.ShenYangTowerComService.conf;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;

import com.alibaba.fastjson.JSON;
import com.avicsafety.ShenYangTowerComService.model.MUser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class Constants {

	// 故障正式ip地址
	public static final String BASE_URL = "http://42.56.72.9:10005/";
//	public static final String BASE_URL = "http://192.168.56.1:8080/";

	// 测试ip,,
//	public static final String BASE_URL = "http://192.168.1.116:8080/";
	//dan东
	public static final String DDBASE_URL = "http://42.56.70.110:10008/DDTT";

	public static final String BASE_SCWJ = BASE_URL + "phoneServices/uploadFileServlet";

//	public static final String DDBASE_URL = "http://113.107.235.66:9928/DDTT";
	public static final String DDLOGIN = DDBASE_URL + "/system/terminalLoginout!login";

	//工单处理
	public static final String BASE_GDCL = BASE_URL + "phoneServices/electricityGenerationServlet";
	public static final String BASE_SCTP = BASE_URL + "phoneServices/uploadFileBase64Servlet";
	public static final String BASE_RWSL = BASE_URL + "phoneServices/electricityGenerationServlet";
	public static final String LOGIN = BASE_URL + "phoneServices/phoneLoginServlet";
    //签收,审核,重新处理
    public static final String BASE_QSSHCXCL = BASE_URL + "phoneServices/electricityGenerationServlet";
    public static final String BASE_RWLB = BASE_URL + "phoneServices/electricityGenerationServlet";
    public static final String BASE_PFZPTHBHXTYZ = BASE_URL + "phoneServices/electricityGenerationServlet";
    public static final String BASE_GDQX = BASE_URL + "phoneServices/electricityGenerationServlet";
    public static final String BASE_KSYZM = BASE_URL + "phoneServices/electricityGenerationServlet";
    public static final String BASE_XBM_XFZ_XRY = BASE_URL + "phoneServices/";



	public static int RWSL = 1;
	public static int WDRW = 2;
	public static int GDXQ = 3;
	public static int QS = 4;
	public static int ZP = 6;
	public static int GDCL = 5;
	public static int TH = 7;
	public static int MJZZXHQ = 8;
	public static int HQKSYZM = 9;
	public static int HQJSYZM = 10;


	public static String[] cities = { "省分公司", "沈阳", "大连", "鞍山", "抚顺", "本溪",
			"丹东", "锦州", "营口", "阜新", "辽阳", "铁岭", "朝阳", "盘锦", "葫芦岛" };

	public static int[] cityValues = { 2, 4, 15, 1, 13, 17, 14, 10, 6, 12, 9,
			7, 16, 8, 3 };

	public static String getSubmitUser(Context context) {

		SharedPreferences userInfo = context.getSharedPreferences("uInfo",
				Context.MODE_PRIVATE);

		return userInfo.getString("userName", "");
	}

	public static String getVersionName(Context context){
		String PackageName = context.getPackageName();
		PackageInfo info = null;
		try {
			info = context.getPackageManager().getPackageInfo(PackageName, 0);
			return info.versionName;
		}catch (Exception e){
			return "";
		}
	}

    public static void setSubmitUser(String submitUser, Context context) {

        SharedPreferences userInfo = context.getSharedPreferences("uInfo",
                Context.MODE_PRIVATE);
        userInfo.edit().putString("userName", submitUser).commit();

    }
	public static MUser getUserInfo(Activity oThis) {
		// TODO 自动生成的方法存根
		SharedPreferences sp = oThis.getSharedPreferences("userInfo",
				Context.MODE_PRIVATE);

		String jsonStr = sp.getString("userInfo", "");

		if (TextUtils.isEmpty(jsonStr)) {
			return null;
		} else {
			return JSON.parseObject(jsonStr, MUser.class);
		}
	}

    public static void setUserInfo(Context oThis, String userInfoStr) {
        // TODO 自动生成的方法存根

        // 在本地保存当前用户信息
        SharedPreferences sp = oThis.getSharedPreferences("userInfo",
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();
        if (!TextUtils.isEmpty(userInfoStr)) {
            editor.putString("userInfo", userInfoStr);
        } else {
            editor.clear();
        }
        editor.commit();
    }

	/**
	 * 将图片转化为一个BASE64的编码
	 * @param path
	 * @return
	 */
	public static String Bitmap2StrByBase64(String path){
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
		Bitmap bit = BitmapFactory.decodeFile(path, opts);
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		bit.compress(Bitmap.CompressFormat.JPEG, 80, bos);//参数100表示不压缩
		byte[] bytes=bos.toByteArray();
		return Base64.encodeToString(bytes, Base64.DEFAULT).replace("\n","");
	}

	/**
	 * 将文件转化喂一个BASE64的编码
	 */
	public static String encodeBase64File(String path) throws Exception {
		File file = new File(path);
		FileInputStream inputFile = new FileInputStream(file);
		byte[] buffer = new byte[(int)file.length()];
		inputFile.read(buffer);
		inputFile.close();
		return Base64.encodeToString(buffer,Base64.DEFAULT);
	}


	public static final String CHECK_SERVERIP_URL = Configuration.DOMAIN_URL+"/ip_check.aspx";
	public static final String SEARCH_SERVERIP_URL = Configuration.DOMAIN_URL+"/ip_search.aspx";









	public static final String COMMON_PHONE_DOWNLOAD_URL="/phoneDownloadData";

}
