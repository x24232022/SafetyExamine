package com.avicsafety.ShenYangTowerComService.PowerManager.push.service;

import java.util.HashMap;
import java.util.Map;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

import com.avicsafety.ShenYangTowerComService.PowerManager.push.Utils.Constants;
import com.avicsafety.ShenYangTowerComService.PowerManager.push.Utils.LocationAddressUtils;
import com.avicsafety.ShenYangTowerComService.model.MUser;
import com.avicsafety.ShenYangTowerComService.xfd.XinFDMethod;
import com.avicsafety.lib.tools.L;
import com.baidu.location.BDLocation;
import com.ydtx.powermanger.Module.BaseModule;
import com.ydtx.powermanger.MyCallback;
import com.ydtx.powermanger.event.PositionEvent;
import com.ydtx.powermanger.presenter.SubPositionPresenter;
import com.ydtx.powermanger.utils.AccessUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PositionService extends Service implements MyCallback {

    private LocationAddressUtils utils;
    private BaseModule.SubPositon subs;
    //GPS
    private BDLocation location;
    //百度
    private BDLocation mLocation;
    private Map<String, String> params;
    private String path;
    private double longitude;
    private double latitude;
    private String userAccount;
    private String userName;
    private double blongitude;
    private double blatitude;
    private MUser mMUser;
    private String userid = "boot";
    //	private PowerApplication application;
//	private PositionBeanDao dao;
    private int i = 0;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        EventBus.getDefault().register(this);
        mMUser = Constants.getUserInfo(this);
        init();
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (utils == null || path == null || params == null || subs == null) {
            init();
        }
        return START_STICKY;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    //注意用户名和用户账号是通过BdLocation传递过来的
    public void onEventMainThread(PositionEvent event) {
        location = event.getLocation();
        mLocation = event.getmBDLocation();
        userAccount = mMUser.getUserName();
        userName = mMUser.getName();
        longitude = location.getLongitude();
        latitude = location.getLatitude();
        blatitude = mLocation.getLatitude();
        blongitude = mLocation.getLongitude();




       AsyncTask task= new AsyncTask(){

           @Override
           protected Object doInBackground(Object[] objects) {
               new XinFDMethod().GetXinFsJwd(userAccount, 0, longitude, latitude);
               L.v("发送经纬度：              ~");

               try {
                   //设置发送时间间隔
                   Thread.sleep(1000 * 60*5);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               return null;
           }
       };
        task.execute();


//        Date d = new Date();
//        int n = d.getMinutes() * 60;
//        int n1 = d.getSeconds();
//        if ((n + n1) % 60 == 0) {
//
//        }
    }

    @Override
    public void onMyStart() {
    }

    @Override
    public void onSucces(String response) {
    }

    @Override
    public void onFailed(String error) {
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    void init() {
        path = AccessUtils.URL_SERVER + AccessUtils.ADD_PERSONNEL_POSITION;
        params = new HashMap<String, String>();
        utils = LocationAddressUtils.getInstance();
        utils.initLocation(getApplicationContext());
        utils.start();
        subs = SubPositionPresenter.getInstance();
    }

}
