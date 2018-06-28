package com.avicsafety.safety_examine.PowerManager.push.service;

import java.util.List;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;


import com.avicsafety.safety_examine.PowerManager.push.Utils.Constants;
import com.avicsafety.safety_examine.PowerManager.push.Utils.LocationAddressUtils;
import com.avicsafety.safety_examine.PowerManager.push.Utils.Utils;
import com.avicsafety.safety_examine.model.Location;
import com.avicsafety.safety_examine.model.MUser;
import com.avicsafety.safety_examine.xfd.XinFDMethod;
import com.avicsafety.lib.tools.L;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.model.LatLng;
import com.ydtx.powermanger.Module.BaseModule;
import com.ydtx.powermanger.MyCallback;
import com.ydtx.powermanger.event.PositionEvent;
import com.ydtx.powermanger.presenter.SubPositionPresenter;
import com.ydtx.powermanger.utils.AccessUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PositionService extends Service {


    private LocationManager locationManager;
    private String locationProvider;
    private LocationAddressUtils utils;
    private BaseModule.SubPositon subs;
    //GPS
    private BDLocation location;
    //百度
    //private BDLocation mLocation;

    private String path;
    double bdLongitude;
    double bdLlatitude;
    double latitude ;
    double longtitude ;
    private String userAccount;
    private String userName;

    private MUser mMUser;
    private String userid = "boot";


    private int i = 0;
    private LatLng mLatLng;

    @Override
    public void onCreate() {
        EventBus.getDefault().register(this);
        mMUser = Constants.getUserInfo(this);
        init();
//通过系统的LocationManager获取经纬度
        getLocation();
        super.onCreate();



    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (utils == null || path == null ||  subs == null) {
            init();
        }
        return START_STICKY;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    //注意用户名和用户账号是通过BdLocation传递过来的
    public void onEventMainThread(PositionEvent event) {
        location = event.getLocation();
        userAccount = mMUser.getUserName();
        userName = mMUser.getName();
        bdLongitude = location.getLongitude();
        bdLlatitude = location.getLatitude();
        if(bdLlatitude!=0.0&&bdLongitude!=0.0){
            EventBus.getDefault().post(new Location(bdLongitude,bdLlatitude));
        }else {
            EventBus.getDefault().post(new Location(longtitude,latitude));
        }

        AsyncTask task= new AsyncTask(){
            @Override
            protected Object doInBackground(Object[] objects) {
                if(bdLlatitude!=0.0&&bdLongitude!=0.0) {
                    new XinFDMethod().GetXinFsJwd(userAccount, 0, bdLongitude, bdLlatitude);
                }else {

                    new XinFDMethod().GetXinFsJwd(userAccount, 0, Utils.doubleFormat(longtitude, 6), Utils.doubleFormat(latitude, 6));
                }

                L.v("发送经纬度：              ~");

                try {
                    //设置发送时间间隔
                    Thread.sleep(1000 * 60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        if(task!=null){
            task.execute();
        }
    }



    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);

        super.onDestroy();
    }

    void init() {
        path = AccessUtils.URL_SERVER + AccessUtils.ADD_PERSONNEL_POSITION;

        utils = LocationAddressUtils.getInstance();
        utils.initLocation(getApplicationContext());
        utils.start();
        subs = SubPositionPresenter.getInstance();
    }

    private void  getLocation() {
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //2.获取位置提供器，GPS或是NetWork
        List<String> providers = locationManager.getProviders(true);
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            //如果是GPS定位
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是网络定位
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else if (providers.contains(LocationManager.PASSIVE_PROVIDER)) {
            //如果是PASSIVE定位
            locationProvider = LocationManager.PASSIVE_PROVIDER;
        }
        else {
            Toast.makeText(this, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
            return;
        }

        //3.获取上次的位置，一般第一次运行，此值为null
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return;
        }

            // 监视地理位置变化，第二个和第三个参数分别为更新的最短时间minTime和最短距离minDistace
            locationManager.requestLocationUpdates(locationProvider, 2000, 100, mListener);


    }


    LocationListener mListener = new LocationListener() {
        // 如果位置发生变化，重新显示
        @Override
        public void onLocationChanged(android.location.Location location) {
            showLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }



    };



    private void showLocation(android.location.Location location) {
        longtitude=location.getLongitude();
        latitude=location.getLatitude();
        mLatLng = new LatLng(latitude,longtitude);

        Log.e("经纬度信息：",longtitude+"  "+latitude);
    }



}
