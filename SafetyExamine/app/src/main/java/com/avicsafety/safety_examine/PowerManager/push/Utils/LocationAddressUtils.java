package com.avicsafety.safety_examine.PowerManager.push.Utils;


import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.ydtx.powermanger.event.LatLngEvent;
import com.ydtx.powermanger.event.PositionEvent;

import org.greenrobot.eventbus.EventBus;

public class LocationAddressUtils{
    public MyLocationConfiguration.LocationMode mLocationMode;
    public Context context;
    public LocationClient mLocationClient;
    public LocationClientOption option;
    private volatile static LocationAddressUtils uniqueInstance;
    private MyLocationListener listener;
    private BDLocation mLocation;//Gps经纬度
    private boolean isFirst=true;
    private String userName = "付方林",userAccount = "fufanglin";
    public void initLocation(Context context) {
        this.context=context;
        isFirst=true;
        if(option==null||mLocationClient==null||listener==null){
            init();
        }
    }

    private LocationAddressUtils() {}

    public static LocationAddressUtils getInstance() {
        if (uniqueInstance == null) {
            synchronized (LocationAddressUtils.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new LocationAddressUtils();
                }
            }
        }
        return uniqueInstance;
    }

    public void start(){
        isFirst=true;
        //判断是否启动
        if (!mLocationClient.isStarted()){
            //开启定位
            mLocationClient.start();
        }

    }

    public void init(){
        listener=new MyLocationListener();
        mLocationClient = new LocationClient(context.getApplicationContext());
        mLocationClient.registerLocationListener(listener);
        option=new LocationClientOption();
        //初始化自己位置的图标
        mLocationMode= MyLocationConfiguration.LocationMode.NORMAL;
        option.setCoorType("bd09ll");  //坐标类型
        option.setIsNeedAddress(true);  //返回当前位置
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIgnoreKillProcess(false);
        option.setIsNeedAddress(true);
        option.setScanSpan(100000);     //多少秒一次请求
        mLocationClient.setLocOption(option);   //加载option
    }
    public final static double[] X = new double[660 * 450];
    public final static double[] Y = new double[660 * 450];

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location.getLocType() == BDLocation.TypeOffLineLocationFail
                    || location.getLocType() == BDLocation.TypeOffLineLocationNetworkFail
                    || location.getLocType() == BDLocation.TypeCriteriaException
                    || location.getLocType() == BDLocation.TypeNetWorkException || location.getLocType() == BDLocation.TypeServerError) {
                ToastUtils.showToast(context, "获取位置失败,请开启位置信息");
            } else {
                mLocation=new BDLocation();
                PointDouble pIn = new PointDouble(

                        Double.valueOf(location.getLongitude()),
                        Double.valueOf(location.getLatitude()));
                PointDouble pOut =Utils.c2s(pIn,X,Y);
                mLocation.setLongitude(Utils.doubleFormat(pOut.x,
                        6));
                mLocation.setLatitude(Utils.doubleFormat(pOut.y,
                        6));
                mLocation.setCoorType(userAccount);
                mLocation.setBuildingName(userName);
                mLocation.setAddr(location.getAddress());
                mLocation.setTime(location.getTime());
            }
            if(isFirst){
                EventBus.getDefault().postSticky(new LatLngEvent(mLocation));
                isFirst=false;
            }
            EventBus.getDefault().post(new PositionEvent(mLocation));
        }

        public void onConnectHotSpotMessage(String arg0, int arg1) {

        }
    }


    public void stop(){
        if(mLocationClient.isStarted()){
            mLocationClient.unRegisterLocationListener(listener);
            mLocationClient.stop();
        }
    }

}
