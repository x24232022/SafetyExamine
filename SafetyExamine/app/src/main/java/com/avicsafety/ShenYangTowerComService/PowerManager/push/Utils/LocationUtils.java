package com.avicsafety.ShenYangTowerComService.PowerManager.push.Utils;


import android.content.Context;

import com.avicsafety.ShenYangTowerComService.R;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.ydtx.powermanger.event.LatLngEvent;

import org.greenrobot.eventbus.EventBus;

public class LocationUtils{
	public float mCurrentX;
	public BitmapDescriptor mIconLocation;
	public LocationMode mLocationMode;
	public Context context;
	public boolean isFirst=true;
	public BaiduMap baiMap;
	public LocationClient mLocationClient;
	public MyOrientationListener myOrientationListener;
	public LocationClientOption option;
	private volatile static LocationUtils uniqueInstance;
	private MyLocationListener listener;
	public void initLocation(final Context context,BaiduMap mBaidumap) {
		this.context=context;
		this.baiMap=mBaidumap;
		if(mBaidumap!=null){
			isFirst=true;
		}
		if(mIconLocation==null||option==null||mLocationClient==null||listener==null){
			init();
		}
	}

	class oriention implements MyOrientationListener.OnOrientationListener {

		@Override
		public void onOrientationChanged(float x) {
			mCurrentX=x;
		}

	}


	private LocationUtils() {}

	public static LocationUtils getInstance() {
		if (uniqueInstance == null) {
			synchronized (LocationUtils.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new LocationUtils();
				}
			}
		}
		return uniqueInstance;
	}

	public void start(){
		isFirst=true;
		if (!mLocationClient.isStarted()){  //判断是否启动
			//开启定位
			mLocationClient.start();
		}
		// 开启方向传感器
		myOrientationListener.start();
	}

	public void init(){
		listener=new MyLocationListener();
		mLocationClient = new LocationClient(context.getApplicationContext());
		mLocationClient.registerLocationListener(listener);
		option=new LocationClientOption();
		//初始化自己位置的图标
		mIconLocation = BitmapDescriptorFactory.fromResource(R.drawable.itself);
		mLocationMode=LocationMode.NORMAL;
		option.setCoorType("bd09ll");  //坐标类型
		option.setIsNeedAddress(true);  //返回当前位置
		option.setOpenGps(true);
		option.setLocationNotify(true);
		option.setIgnoreKillProcess(false);
		option.setIsNeedAddress(true);
		option.setScanSpan(5000);     //多少秒一次请求
		mLocationClient.setLocOption(option);   //加载option
		//初始化方向传感器
		myOrientationListener = new MyOrientationListener(context);
		myOrientationListener.setOnOrientationListener(new oriention());
	}

	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location.getLocType() == BDLocation.TypeOffLineLocationFail
					|| location.getLocType() == BDLocation.TypeOffLineLocationNetworkFail
					|| location.getLocType() == BDLocation.TypeCriteriaException
					|| location.getLocType() == BDLocation.TypeNetWorkException || location.getLocType() == BDLocation.TypeServerError) {
				ToastUtils.showToast(context, "获取位置失败");
			} else {
				// 构造定位数据
				//				currentx=Utils.calculateOrientation(mCurrentX);
				MyLocationData locData = new MyLocationData.Builder()
				.direction(mCurrentX)// 方向
				.accuracy(location.getRadius())//精度
				.latitude(location.getLatitude())//
				.longitude(location.getLongitude())//
				.build();  //build模式
				if(baiMap!=null){
					// 设置定位数据
					baiMap.setMyLocationData(locData);
					// 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
					MyLocationConfiguration config = new MyLocationConfiguration(mLocationMode, true, mIconLocation);
					baiMap.setMyLocationConfiguration(config);
				}
				LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
				if (isFirst){
					if(baiMap!=null){
						EventBus.getDefault().postSticky(new LatLngEvent(location));
						MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
						baiMap.setMapStatus(msu);
					}
					isFirst = false;
				}
			}
		}

		public void onConnectHotSpotMessage(String arg0, int arg1) {

		}
	}

	public void stop(){
		if(mLocationClient.isStarted()){
			mLocationClient.unRegisterLocationListener(listener);
			mLocationClient.stop();
		}
		mIconLocation=null;
		option=null;
		mLocationClient=null;
		listener=null;
	}

}
