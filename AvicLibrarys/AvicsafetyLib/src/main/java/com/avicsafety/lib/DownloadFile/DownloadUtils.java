package com.avicsafety.lib.DownloadFile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.avicsafety.lib.interfaces.OnNetworkAccessToProgressListener;
import com.avicsafety.lib.tools.SDCardUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by shili on 2017/5/31.
 */

public class DownloadUtils {

    private static final int DOWNLOAD = 10001;
    private static final int DOWNLOAD_FINISH = 10002;
    private static final int DOWNLOAD_CANCLE = 10006;
    private static final int DOWNLOAD_ERROR = 10009;

    private int progress;
    private boolean cancelFlag = false;

    private ProgressDialog progressDialog;

    private boolean mShowProgressDialog;
    private String mDownloadPath;
    private String mFileUrl;
    private String mFileName;
    private Context mContext;
    private OnNetworkAccessToProgressListener mListener;


    public void downloadFile(Context context, String url, String filepath, String fileName, boolean showProgressDialog, OnNetworkAccessToProgressListener listener){
        mShowProgressDialog = showProgressDialog;
        mContext = context;
        mFileUrl = url;
        mDownloadPath = filepath;
        mFileName = fileName;
        mListener = listener;
        startDownload();
    }


    private Handler mHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case DOWNLOAD:
                    if(mShowProgressDialog)
                        progressDialog.setProgress(progress);
                    if(mListener!=null)
                        mListener.onProgressChanage(progress);
                    break;
                case DOWNLOAD_CANCLE:
                    if(mListener!=null)
                        mListener.onFail("下载被取消");
                    else
                        Toast.makeText(mContext,"取消下载", Toast.LENGTH_LONG).show();
                    break;
                case DOWNLOAD_ERROR:
                    if(mListener!=null)
                        mListener.onFail("下载出现错误,请检查网络");
                    else
                        Toast.makeText(mContext,"下载出现错误,请检查网络", Toast.LENGTH_LONG).show();
                    break;
                case DOWNLOAD_FINISH:
                    if(mListener!=null)
                        mListener.onProgressChanage(100);
                    else
                        Toast.makeText(mContext,"文件下载完毕!", Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    };

    private void startDownload()
    {
        if(mShowProgressDialog) {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setTitle("下载中..");
            progressDialog.setCancelable(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    cancelFlag = true;
                    deleteFile();
                }
            });
            progressDialog.show();
        }
        new downloadFileThread().start();
    }

    private class downloadFileThread extends Thread
    {
        @Override
        public void run()
        {
            try
            {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
                {
                    cancelFlag = false;
                    if(mDownloadPath==null){
                        mDownloadPath = SDCardUtils.getSDCardPath();
                    }
                    String mSavePath = mDownloadPath;
                    URL url = new URL(mFileUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();

                    int length = conn.getContentLength();
                    InputStream is = conn.getInputStream();

                    File dir = new File(mSavePath);
                    if(!dir.exists()){
                        dir.mkdir();
                    }

                    File apkFile = new File(mSavePath, mFileName);
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
                        } while (!cancelFlag);
                        if(cancelFlag){
                            mHandler.sendEmptyMessage(DOWNLOAD_CANCLE);
                        }
                        fos.close();
                    }
                    is.close();
                }
            } catch (MalformedURLException e)
            {
                mHandler.sendEmptyMessage(DOWNLOAD_ERROR);
                deleteFile();
                e.printStackTrace();
            } catch (IOException e)
            {
                mHandler.sendEmptyMessage(DOWNLOAD_ERROR);
                deleteFile();
                e.printStackTrace();
            }
            if(mShowProgressDialog)
                progressDialog.dismiss();
        }
    }

    private void deleteFile(){
        File file = new File(mDownloadPath,mFileName);
        file.delete();
    }
}
