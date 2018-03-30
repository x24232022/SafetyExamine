package com.avicsafety.ShenYangTowerComService.PowerManager.push.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import android.os.Environment;
import android.util.Base64;


public class FileUtils {
	public static final String path=Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;

	//创建文件夹
	public static String createFolder(String name){
		File file=new File(path+name);
		if(!file.exists()){
			file.mkdir();
		}
		return file.getAbsolutePath();
	}
	//创建文件夹
	public static String createFile(String name) throws IOException{
		File file=new File(path+name);
		if(!file.exists()){
			file.createNewFile();
		}
		return file.getAbsolutePath();
	}

	//创建子文件夹
	public static String createFolderChilde(String dir,String name){
		File file=new File(createFolder(dir),name);
		if(!file.exists()){
			file.mkdir();
		}
		return file.getAbsolutePath();
	}

	//获得一个File文件
	public static File getFile(String name){
		String path=createFolder(name);
		File file=new File(path);
		return file;
	}

	//从下载连接中解析出文件名
    public static String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

	
	/**
	 * 文件转化成字符串
	 * @param file
	 * @return
	 */
	public static String fileToString(File file) throws IOException {
		try {
			InputStream inputStream = new FileInputStream(file);
			byte[] fileByte = read(inputStream);
			byte[] encod = Base64.encode(fileByte, Base64.DEFAULT);
			inputStream.close();
			return new String(encod);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 返回字节数组
	 * 
	 * @param in输入的流
	 * @return
	 * @throws IOException 
	 * @throws Exception
	 */

	public static byte[] read(InputStream in) throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		if (in != null) {
			byte[] buffer = new byte[1024];
			int length = 0;
			while ((length = in.read(buffer)) != -1) {
				out.write(buffer, 0, length);
			}
			out.close();
			in.close();
			return out.toByteArray();
		}
		return null;
	}
	public static void appendMethodB(String content) {
		String PATH_LOGCAT = null;
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {// 优先保存到SD卡中
			PATH_LOGCAT =path + File.separator + "abnormal";
		} else{
			return;
		}
		File file = new File(PATH_LOGCAT);
		if (!file.exists()) {
			file.mkdirs();
		}
		File file2 = new File(PATH_LOGCAT + File.separator+"return-infos"+ ".log");
		if (!file2.exists()) {
			try {
				file2.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer = new FileWriter(file2, true);
			writer.write(content);
			writer.write("\r\n");//写入换行符
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
