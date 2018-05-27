package com.avicsafety.NewShenYangTowerComService.PowerManager.push;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;


import com.avicsafety.lib.tools.L;
import com.ydtx.powermanger.push.XmppManager;

import java.util.Properties;

/**
 * Created by 刘畅 on 2017/8/18.
 */

public class ServiceManager {
    private static final String LOGTAG = LogUtil
            .makeLogTag(ServiceManager.class);

    private Context context;

    public SharedPreferences sharedPrefs;

    private Properties props;

    private String version = "0.5.0";

    private String apiKey;

    private String xmppHost;

    private String xmppPort;

    private String callbackActivityPackageName;

    private String callbackActivityClassName;


    public ServiceManager(Context context) {
        this.context = context;
        if (context instanceof Activity) {
            Activity callbackActivity = (Activity) context;
            callbackActivityPackageName = callbackActivity.getPackageName();
            callbackActivityClassName = callbackActivity.getClass().getName();
        }

        props = loadProperties();
        apiKey = props.getProperty("apiKey", "");
        xmppHost = props.getProperty("xmppHost", "182.150.37.11");
        xmppPort = props.getProperty("xmppPort", "1240");
        sharedPrefs = context.getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(Constants.API_KEY, apiKey);
        editor.putString(Constants.VERSION, version);
        editor.putString(Constants.XMPP_HOST, xmppHost);
        editor.putInt(Constants.XMPP_PORT, Integer.parseInt(xmppPort));
        editor.putString(Constants.CALLBACK_ACTIVITY_PACKAGE_NAME,
                callbackActivityPackageName);
        editor.putString(Constants.CALLBACK_ACTIVITY_CLASS_NAME,
                callbackActivityClassName);
        editor.commit();

    }

    public void startService() {
        Thread serviceThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = NotificationService.getIntent();
                context.startService(intent);
            }
        });
        serviceThread.start();
        L.v("startService服务Log!");
    }

    public void stopService() {
        Intent intent = NotificationService.getIntent();
        context.stopService(intent);
    }

    boolean isRestart = true;
    public void restartService(){
        stopService();
        isRestart = true;
        final XmppManager xmppManager = Constants.xmppManager;
        new Thread(new Runnable() {

            @Override
            public void run() {
                while(isRestart){
                    try {
                        Thread.sleep(5*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (Constants.xmppManager == null
                            || (xmppManager != null && (xmppManager.getConnection() == null
                            || !xmppManager.getConnection().isConnected()))) {
                        isRestart = false;
                        startService();
                    }
                }
            }
        }).start();
        L.v("推送服务开启成功");
    }


    private Properties loadProperties() {
        Properties props = new Properties();
        try {
            int id = context.getResources().getIdentifier("power", "raw",
                    context.getPackageName());
            props.load(context.getResources().openRawResource(id));
        } catch (Exception e) {
            Log.e(LOGTAG, "Could not find the properties file.", e);
        }
        return props;
    }

    public void setNotificationIcon(int iconId) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(Constants.NOTIFICATION_ICON, iconId);
        editor.commit();
    }
}
