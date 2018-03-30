package com.avicsafety.lib.AutoUpdateManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.avicsafety.lib.CustomView.AvicLoadingDialog;
import com.avicsafety.lib.interfaces.OnNetworkAccessToMessageListener;
import com.avicsafety.lib.tools.SDCardUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UpdateManager
{
	private static final int CHECK_OK = 10003;
	private static final int CHECK_ERROR = 10004;
	private static final int CHECK_DONT=10005;
	private static final int DOWNLOAD = 10001;
	private static final int DOWNLOAD_FINISH = 10002;
	private static final int DOWNLOAD_CANCLE = 10006;

	private AvicLoadingDialog loadingDialog;
	private String fileName;
	private String downPath;
	private String mSavePath;
	private int progress;
	private boolean cancelUpdate = false;

	private Context mContext;
	private ProgressBar mProgress;
	private ProgressDialog progressDailog;
	private Dialog mDownloadDialog;
	private String mDownloadPath;
	
	private Handler mHandler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
			case CHECK_OK:
				if(loadingDialog!=null)loadingDialog.close();
				showNoticeDialog();
				break;
			case CHECK_ERROR:
				if(loadingDialog!=null)loadingDialog.close();
				showErrorDialog();
				break;
			case CHECK_DONT:
				if(loadingDialog!=null)loadingDialog.close();
				Toast.makeText(mContext,"该版本为最新版本!", Toast.LENGTH_LONG).show();
				//goLogin();
				break;
			case DOWNLOAD:
				progressDailog.setProgress(progress);
				break;
			case DOWNLOAD_CANCLE:
				if(loadingDialog!=null)loadingDialog.close();
				Toast.makeText(mContext,"更新被取消!", Toast.LENGTH_LONG).show();
				((Activity)mContext).finish();
				break;
			case DOWNLOAD_FINISH:
				if(loadingDialog!=null)loadingDialog.close();
				installApk();
				break;
			default:
				break;
			}
		}
	};
	
	private void showErrorDialog() {
		  String message = "无法连接到更新服务器";
		Toast.makeText(mContext,message, Toast.LENGTH_LONG).show();
	}

	public UpdateManager(Context context)
	{
		this.mContext = context;
	}

	public void checkUpdate()
	{
		if(loadingDialog!=null)
			loadingDialog.show();
		new connection().start();
	}

	private double getVersionCode(Context context)
	{
		double versionCode = 0.0;
		try
		{
			versionCode = Double.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName+"");
		} catch (NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return versionCode;
	}

	private void showNoticeDialog()
	{
		AlertDialog.Builder builder = new Builder(mContext);
		builder.setTitle("提示");
		builder.setMessage("发现新版本,是否更新?");
		builder.setPositiveButton("确认更新", new OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				dialog.dismiss();
				showDownloadDialog();
			}
		});

		builder.setNegativeButton("取消更新", new OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				((Activity)mContext).finish();
			}
		});
		Dialog noticeDialog = builder.create();
		noticeDialog.show();
	}

	private void showDownloadDialog()
	{
		
		    progressDailog = new ProgressDialog(mContext);  
		    //progressDailog.setIcon(R.drawable.ic_launcher);  
		    progressDailog.setTitle("下载中..");
			progressDailog.setCancelable(false);
		    progressDailog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);  
		    progressDailog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					cancelUpdate = true;
					deleteApk();
				}
			});  
		    progressDailog.show(); 
		    new downloadApkThread().start();
	}

    public void setLoadingDialog(AvicLoadingDialog _loadingDialog) {
		loadingDialog = _loadingDialog;
    }

    public void checkNewVersion(final OnNetworkAccessToMessageListener listener) {
        if(checkConnection(mContext)){
            ApplicationInfo appInfo = null;
            try {
                appInfo = mContext.getPackageManager().getApplicationInfo(mContext.getPackageName(),PackageManager.GET_META_DATA);
            } catch (NameNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            String url=appInfo.metaData.getString("AppUpdateUrl");

            RequestParams params = new RequestParams(url);
            params.setConnectTimeout(15000);

            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onCancelled(CancelledException arg0) {}
                @Override
                public void onError(Throwable arg0, boolean arg1) {
                    listener.onFail("无法连接到更新服务器");
                }

                @Override
                public void onFinished() {}

                @Override
                public void onSuccess(String result) {
                    JSONObject res;
                    try {
                        res = new JSONObject(result);
                        String code = res.getString("code");
						String message = res.getString("message");
                        if(code.equals("200")){
                            double versionCode = Double.valueOf(res.getString("versionCode"));
                            downPath = res.getString("url");
                            fileName = versionCode+".apk";
                            if(versionCode>getVersionCode(mContext)){
                                listener.onSuccess("发现新版本,是否更新?");
                            }else{
                                listener.onFail("该版本是最新版本");
                            }
                        }else{
                            listener.onFail("无法连接到更新服务器");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        listener.onFail("无法连接到更新服务器");
                    }
                }
            });
        }else{
            listener.onFail("无法连接到更新服务器");
        }
    }

    public void update() {
        showDownloadDialog();
    }

    private class downloadApkThread extends Thread
	{

		@Override
		public void run()
		{
			try
			{
				if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
				{
					cancelUpdate = false;
					if(mDownloadPath==null){
						mDownloadPath = SDCardUtils.getSDCardPath();
					}
					mSavePath = mDownloadPath;
					URL url = new URL(downPath);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.connect();

					int length = conn.getContentLength();
					InputStream is = conn.getInputStream();
					
					File dir = new File(mSavePath);
					if(!dir.exists()){
						dir.mkdir();
					}

					File apkFile = new File(mSavePath, fileName);
					if (apkFile.exists())
					{
						mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
					}else{
						FileOutputStream fos = new FileOutputStream(apkFile);
						int count = 0;
						byte buf[] = new byte[1024];
						do
						{
							int numread = is.read(buf);
							count += numread;
							progress = (int) (((float) count / length) * 100);
							mHandler.sendEmptyMessage(DOWNLOAD);
							if (numread <= 0)
							{
								mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
								break;
							}
							fos.write(buf, 0, numread);
						} while (!cancelUpdate);
						if(cancelUpdate){
							mHandler.sendEmptyMessage(DOWNLOAD_CANCLE);
						}
						fos.close();
					}
					is.close();
				}
			} catch (MalformedURLException e)
			{
				mHandler.sendEmptyMessage(DOWNLOAD_CANCLE);
				deleteApk();
				e.printStackTrace();
			} catch (IOException e)
			{
				mHandler.sendEmptyMessage(DOWNLOAD_CANCLE);
				deleteApk();
				e.printStackTrace();
			}
			progressDailog.dismiss();
		}
	}

	private void installApk()
	{
		File apkfile = new File(mSavePath,fileName);
		if (!apkfile.exists())
		{
			return;
		}
		Intent i = new Intent(Intent.ACTION_VIEW);
		Uri fileUri;
		if (android.os.Build.VERSION.SDK_INT >= 24) {
			fileUri = FileProvider.getUriForFile(mContext, "com.avicsafety.ShenYangTowerComService", apkfile);
			i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		}else{
			fileUri = Uri.fromFile(apkfile);
		}
		i.setDataAndType(fileUri, "application/vnd.android.package-archive");
		mContext.startActivity(i);
		((Activity)mContext).finish();
	}
	
	private void deleteApk(){
		File apkfile = new File(mSavePath,fileName);
		apkfile.delete();
	}
	
	private class connection extends Thread
	{
		@Override
		public void run()
		{
				if(checkConnection(mContext)){
					
					ApplicationInfo appInfo = null;
					try {
						appInfo = mContext.getPackageManager().getApplicationInfo(mContext.getPackageName(),PackageManager.GET_META_DATA);
					} catch (NameNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String url=appInfo.metaData.getString("AppUpdateUrl");
					
					RequestParams params = new RequestParams(url);
					params.setConnectTimeout(15000);
					
					x.http().get(params, new Callback.CommonCallback<String>() {
						@Override
						public void onCancelled(CancelledException arg0) {}
						@Override
						public void onError(Throwable arg0, boolean arg1) {
							mHandler.sendEmptyMessage(CHECK_ERROR);
						}
							
						@Override
						public void onFinished() {}
							
						@Override
						public void onSuccess(String result) {
							JSONObject res;
							try {
								res = new JSONObject(result);
								String code = res.getString("code");
								if(code.equals("200")){
									double versionCode = Double.valueOf(res.getString("versionCode"));
									downPath = res.getString("url");
									fileName = versionCode+".apk";
									if(versionCode>getVersionCode(mContext)){
										mHandler.sendEmptyMessage(CHECK_OK);
									}else{
										mHandler.sendEmptyMessage(CHECK_DONT);
									}
								}else{

									mHandler.sendEmptyMessage(CHECK_ERROR);
								}
							} catch (JSONException e) {
								e.printStackTrace();
				
								mHandler.sendEmptyMessage(CHECK_ERROR);
							}
						}
				});
			}else{
			
				mHandler.sendEmptyMessage(CHECK_ERROR);
			}
		}
	}
	
	public static Boolean checkConnection(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if(info==null){
			return false;
		}else{
			return info.isAvailable();
		}
	}

	public void setmDownloadPath(String mDownloadPath) {
		this.mDownloadPath = mDownloadPath;
	}
	
	

}
