package com.avicsafety.ShenYangTowerComService.yd.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.avicsafety.lib.tools.L;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper {

	public static boolean assetsCopyData(Context c, String strAssetsFilePath,
                                         String strDesFilePath) {
		boolean bIsSuc = true;
		InputStream inputStream = null;
		OutputStream outputStream = null;

		File file = new File(strDesFilePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
				Runtime.getRuntime().exec("chmod 766 " + file);
			} catch (IOException e) {
				bIsSuc = false;
			}

		} else {// 存在
			return true;
		}

		try {
			inputStream = c.getAssets().open(strAssetsFilePath);
			outputStream = new FileOutputStream(file);

			int nLen = 0;

			byte[] buff = new byte[1024 * 1];
			while ((nLen = inputStream.read(buff)) > 0) {
				outputStream.write(buff, 0, nLen);
			}

			// 完成
		} catch (IOException e) {
			bIsSuc = false;
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}

				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				bIsSuc = false;
			}

		}

		return bIsSuc;
	}


	public static String getSDPath(Context context) {
		String sdDir = null;
			sdDir = context.getApplicationContext().getFilesDir().getAbsolutePath();
		return sdDir;
	}

	private final String DATABASE_FILENAME;

	private String DATABASE_PATH;

	@SuppressLint("SdCardPath")
	public DBHelper(Context context) {
		// TODO Auto-generated constructor stub
		// 这里直接给数据库名
			DATABASE_PATH = getSDPath(context) + File.separator + "CTCS";
			DATABASE_PATH = context.getApplicationContext().getFilesDir().getAbsolutePath();
		DATABASE_FILENAME = "ctcs20160330.db";
	}

	// 得到操作数据库的对象
	public SQLiteDatabase openDatabase(Activity activity) {
		try {
			boolean b = false;
			// 得到数据库的完整路径名
			//AssetManager am = activity.getAssets();
			//InputStream is = am.open(DATABASE_FILENAME);
			String databaseFullName = DATABASE_PATH + File.separator
					+ DATABASE_FILENAME;
			L.v("!!!!!!!!!!!!!!!!!!!!!!!:",databaseFullName);
			// 将数据库文件从资源文件放到合适地方（资源文件也就是数据库文件放在项目的res下的raw目录中）
			// 将数据库文件复制到内存中
			File dir = new File(DATABASE_PATH);
			if (!dir.exists())
				b = dir.mkdir();
			// 判断是否存在该文件

			if (!(new File(databaseFullName)).exists()) {
				assetsCopyData(activity.getApplicationContext(),
						DATABASE_FILENAME, databaseFullName);
			}

			// 得到SQLDatabase对象
			SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(
					databaseFullName, null);
			return database;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}