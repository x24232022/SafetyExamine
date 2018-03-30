package com.avicsafety.ShenYangTowerComService.PowerManager.push.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;

import com.avicsafety.ShenYangTowerComService.activity.ChangeOneActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CrashHandler implements UncaughtExceptionHandler {  
    private UncaughtExceptionHandler mDefaultHandler;// 系统默认的UncaughtException处理类
    private static CrashHandler INSTANCE = new CrashHandler();// CrashHandler实例  
    private Context mContext;// 程序的Context对象  
    private Map<String, String> info = new HashMap<String, String>();// 用来存储设备信息和异常信息  
  
    /** 保证只有一个CrashHandler实例 */  
    private CrashHandler() {  
  
    }  
  
    /** 获取CrashHandler实例 ,单例模式 */  
    public static CrashHandler getInstance() {  
        return INSTANCE;  
    }  
  
    /** 
     * 初始化 
     *  
     * @param context 
     */  
    public void init(Context context) {  
        mContext = context;  
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();// 获取系统默认的UncaughtException处理器  
        Thread.setDefaultUncaughtExceptionHandler(this);// 设置该CrashHandler为程序的默认处理器  
    }  
  
    /** 
     * 当UncaughtException发生时会转入该重写的方法来处理 
     */  
    public void uncaughtException(Thread thread, Throwable ex) {  
        if (!handleException(ex) && mDefaultHandler != null) {  
            // 如果自定义的没有处理则让系统默认的异常处理器来处理  
            mDefaultHandler.uncaughtException(thread, ex);  
        } else {  
            android.os.Process.killProcess(android.os.Process.myPid());  
            System.exit(1);  
        }  
    }  
  
    /** 
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成. 
     *  
     * @param ex 
     *            异常信息 
     * @return true 如果处理了该异常信息;否则返回false. 
     */  
    public boolean handleException(Throwable ex) {  
        if (ex == null)  
            return false;  
        new Thread() {  
            public void run() {  
                Looper.prepare();  
                try {
                	//错误日志
                	SharedUtils.putShared(mContext, "error", true);
                	Intent intent=new Intent(mContext,ChangeOneActivity.class);
                	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    mContext.startActivity(intent);
				} catch (Exception e) {
				}
                Looper.loop();  
            }  
        }.start();  
        // 收集设备参数信息  
        collectDeviceInfo(mContext);  
        // 保存日志文件  
        saveCrashInfo2File(ex);  
        return true;  
    }  
  
    /** 
     * 收集设备参数信息 
     *  
     * @param context 
     */  
    public void collectDeviceInfo(Context context) {  
        try {  
            PackageManager pm = context.getPackageManager();// 获得包管理器  
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(),  
                    PackageManager.GET_ACTIVITIES);// 得到该应用的信息，即主Activity  
            if (pi != null) {  
                String versionName = pi.versionName == null ? "null"  
                        : pi.versionName;  
                String versionCode = pi.versionCode + "";  
                info.put("versionName", versionName);  
                info.put("versionCode", versionCode);  
            }  
        } catch (NameNotFoundException e) {  
            e.printStackTrace();  
        }  
  
        Field[] fields = Build.class.getDeclaredFields();// 反射机制  
        for (Field field : fields) {  
            try {  
                field.setAccessible(true);  
                info.put(field.getName(), field.get("").toString());  
            } catch (IllegalArgumentException e) {  
                e.printStackTrace();  
            } catch (IllegalAccessException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    private String saveCrashInfo2File(Throwable ex) {  
        StringBuffer sb = new StringBuffer();  
        for (Map.Entry<String, String> entry : info.entrySet()) {  
            String key = entry.getKey();  
            String value = entry.getValue();  
            sb.append(key + "=" + value + "\r\n");  
        }  
        Writer writer = new StringWriter();  
        PrintWriter pw = new PrintWriter(writer);  
        ex.printStackTrace(pw);  
        Throwable cause = ex.getCause();  
        // 循环着把所有的异常信息写入writer中  
        while (cause != null) {  
            cause.printStackTrace(pw);  
            cause = cause.getCause();  
        }  
        pw.close();// 记得关闭  
        String result = writer.toString();  
        sb.append(result);  
        String time = DateUtils.getDateForString(new Date(),"yyyy-MM-dd-HH-mm-ss");  
        String fileName = time+ ".log";  
        if (Environment.getExternalStorageState().equals(  
                Environment.MEDIA_MOUNTED)) {  
            try {  
            	FileUtils.createFolder("abnormal");
                String dirs=FileUtils.createFolderChilde("abnormal", "log");
                FileOutputStream fos = new FileOutputStream(new File(dirs,fileName));  
                fos.write(sb.toString().getBytes());  
                fos.close();  
                return fileName;  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return null;  
    }  
}  