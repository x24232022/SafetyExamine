package com.avicsafety.NewShenYangTowerComService.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.avicsafety.NewShenYangTowerComService.PowerManager.push.Utils.FileUtils;
import com.avicsafety.NewShenYangTowerComService.PowerManager.push.Utils.LocationUtils;
import com.avicsafety.NewShenYangTowerComService.PowerManager.push.Utils.ToastUtils;
import com.avicsafety.NewShenYangTowerComService.PowerManager.push.Utils.Utils;
import com.avicsafety.NewShenYangTowerComService.PowerManager.push.navigation.BNEventHandler;
import com.avicsafety.NewShenYangTowerComService.PowerManager.push.overlayutil.DrivingRouteOverlay;
import com.avicsafety.NewShenYangTowerComService.PowerManager.push.overlayutil.OverlayManager;
import com.avicsafety.NewShenYangTowerComService.R;
import com.avicsafety.lib.tools.L;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.navisdk.adapter.BNCommonSettingParam;
import com.baidu.navisdk.adapter.BNRoutePlanNode;
import com.baidu.navisdk.adapter.BNaviSettingManager;
import com.baidu.navisdk.adapter.BaiduNaviManager;
import com.ydtx.powermanger.event.LatLngEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 刘畅 on 2017/9/6.
 */
@ContentView(R.layout.activity_mapview)
public class RouteActivity extends BaseActivity{

    @ViewInject(R.id.bmapView)
    TextureMapView mapView;
    @ViewInject(R.id.daohang)
    Button mButton;
    BaiduMap mBaiduMap;
    LocationUtils utils;
    private static final String APP_FOLDER_NAME = "power";
    public static final String ROUTE_PLAN_NODE = "routePlanNode";
    public static List<Activity> activityList = new LinkedList<Activity>();
    public static String mSDCardPath;
    RoutePlanSearch mSearch = null;    // 搜索模块，也可去掉地图模块独立使用
    DrivingRouteLine route = null;
    OverlayManager routeOverlay = null;
    boolean useDefaultIcon = false;
    private LatLng latlng=null;//基站地址
    private BDLocation location;//定位地址
    private boolean iserror=false;

    @Override
    protected void InitializeActionBar() {

    }

    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
        //setTitle("路径规划");
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(new OnResultListener());
    }

    @Override
    protected void InitializeEvent() {
        super.InitializeEvent();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BaiduNaviManager.isNaviInited()) {
                    if(!iserror){
                        Utils.showProgress("正在加载导航信息....", false,oThis);
                        routeplanToNavi(BNRoutePlanNode.CoordinateType.BD09LL);
                    }else{
                        ToastUtils.showToast(oThis, "算路失败,不能导航!");
                    }
                }else{
                    ToastUtils.showToast(oThis, "导航引擎未初始化!");
                }

            }
        });
    }

    @Override
    protected void InitializeData() {
        super.InitializeData();
        EventBus.getDefault().register(this);
        activityList.clear();
        activityList.add(this);
        mBaiduMap=mapView.getMap();
        utils=LocationUtils.getInstance();
        utils.initLocation(getApplicationContext(), mBaiduMap);
        Utils.readOAuth(this);
        if(getIntent()!=null){
            Bundle bund=getIntent().getBundleExtra("bund");
            latlng=bund.getParcelable("latlng");
        }
    }
    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void onEventMainThread(LatLngEvent event){
        location=event.getLocation();
        if (initDirs()) {
            initNavi();
        }
        test();
    }

    private void test() {
        PlanNode stNode = PlanNode.withLocation(new LatLng(location.getLatitude(),location.getLongitude()));
        PlanNode enNode = PlanNode.withLocation(latlng);
        mSearch.drivingSearch((new DrivingRoutePlanOption())
                .from(stNode).to(enNode));
    }

    private boolean initDirs() {
        try {
            mSDCardPath= FileUtils.createFolder(APP_FOLDER_NAME);
            return true;
        } catch (Exception e) {
            L.v("Catch~!!!!!!!!!!!!!!!!!!!1:",e.getMessage());
            return false;
        }
    }

    private void initSetting() {
        BNaviSettingManager
                .setShowTotalRoadConditionBar(BNaviSettingManager.PreViewRoadCondition.ROAD_CONDITION_BAR_SHOW_ON);
        BNaviSettingManager.setVoiceMode(BNaviSettingManager.VoiceMode.Veteran);
        BNaviSettingManager.setRealRoadCondition(BNaviSettingManager.RealRoadCondition.NAVI_ITS_ON);
        BNaviSettingManager.setIsAutoQuitWhenArrived(true);
        Bundle bundle = new Bundle();
        // 必须设置APPID，否则会静音
        bundle.putString(BNCommonSettingParam.TTS_APP_ID, "9354030");
        BNaviSettingManager.setNaviSdkParam(bundle);
    }

    private void initNavi() {
        BaiduNaviManager.getInstance().init(this, mSDCardPath, APP_FOLDER_NAME, new BaiduNaviManager.NaviInitListener() {
            @Override
            public void onAuthResult(int status, String msg) {
                if (status!=0) {
                    ToastUtils.showToast(oThis,  "key校验失败");
                }
            }
            @Override
            public void initSuccess() {
                ToastUtils.showToast(oThis, "百度导航引擎初始化成功");
                initSetting();
            }
            @Override
            public void initStart() {
                ToastUtils.showToast(oThis, "百度导航引擎初始化开始");
            }
            @Override
            public void initFailed() {
                L.v("百度导航引擎初始化失败信息:");
                ToastUtils.showToast(oThis, "百度导航引擎初始化失败");
            }

        }, null, ttsHandler, ttsPlayStateListener);

    }

    /**
     * 内部TTS播报状态回调接口
     */
    private BaiduNaviManager.TTSPlayStateListener ttsPlayStateListener = new BaiduNaviManager.TTSPlayStateListener() {

        @Override
        public void playEnd() {
        }

        @Override
        public void playStart() {
        }
    };

    @Override
    protected void onStart() {
        if(!mBaiduMap.isMyLocationEnabled()){
            mBaiduMap.setMyLocationEnabled(true);  //开启定位图层
        }
        utils.start();
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @SuppressLint("HandlerLeak")
    private Handler ttsHandler = new Handler() {
        public void handleMessage(Message msg) {
            int type = msg.what;
            switch (type) {
                case BaiduNaviManager.TTSPlayMsgType.PLAY_START_MSG: {
                    break;
                }
                case BaiduNaviManager.TTSPlayMsgType.PLAY_END_MSG: {
                    break;
                }
                default:
                    break;
            }
        }
    };

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.navigation, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.action_navigation:
//                if (BaiduNaviManager.isNaviInited()) {
//                    if(!iserror){
//                        Utils.showProgress("正在加载导航信息....", false,this);
//                        routeplanToNavi(BNRoutePlanNode.CoordinateType.BD09LL);
//                    }else{
//                        ToastUtils.showToast(oThis, "算路失败,不能导航!");
//                    }
//                }else{
//                    ToastUtils.showToast(oThis, "导航引擎未初始化!");
//                }
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private void routeplanToNavi(BNRoutePlanNode.CoordinateType coType) {
        BNRoutePlanNode sNode = null;
        BNRoutePlanNode eNode = null;
        sNode = new BNRoutePlanNode(location.getLongitude(),location.getLatitude(),null, null, coType);
        eNode = new BNRoutePlanNode(latlng.longitude,latlng.latitude,null, null, coType);
        if (sNode != null && eNode != null) {
            List<BNRoutePlanNode> list = new ArrayList<BNRoutePlanNode>();
            list.add(sNode);
            list.add(eNode);

            // 开发者可以使用旧的算路接口，也可以使用新的算路接口,可以接收诱导信息等
            // BaiduNaviManager.getInstance().launchNavigator(this, list, 1, true, new DemoRoutePlanListener(sNode));
            BaiduNaviManager.getInstance().launchNavigator(this, list, 1, true, new DemoRoutePlanListener(sNode),
                    eventListerner);
        }
    }

    BaiduNaviManager.NavEventListener eventListerner = new BaiduNaviManager.NavEventListener() {

        @Override
        public void onCommonEventCall(int what, int arg1, int arg2, Bundle bundle) {
            BNEventHandler.getInstance().handleNaviEvent(what, arg1, arg2, bundle);
        }
    };

    public class DemoRoutePlanListener implements BaiduNaviManager.RoutePlanListener {

        private BNRoutePlanNode mBNRoutePlanNode = null;

        public DemoRoutePlanListener(BNRoutePlanNode node) {
            mBNRoutePlanNode = node;
        }

        @Override
        public void onJumpToNavigator() {
            for (Activity ac : activityList) {
                if (ac.getClass().getName().endsWith("BNDemoGuideActivity")) {
                    return;
                }
            }
            Intent intent = new Intent(RouteActivity.this, BNDemoGuideActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(ROUTE_PLAN_NODE, (BNRoutePlanNode) mBNRoutePlanNode);
            intent.putExtras(bundle);
            startActivity(intent);
            Utils.hideProgress(RouteActivity.this);
        }
        @Override
        public void onRoutePlanFailed() {
            iserror=true;
            ToastUtils.showToast(oThis, "算路失败!");
        }
    }

    class OnResultListener implements OnGetRoutePlanResultListener {

        @Override
        public void onGetBikingRouteResult(BikingRouteResult arg0) {}

        @Override
        public void onGetDrivingRouteResult(DrivingRouteResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                ToastUtils.showToast(oThis, "抱歉，未找到结果");
            }
            if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                //				 result.getSuggestAddrInfo();
                return;
            }
            if (result.error == SearchResult.ERRORNO.NO_ERROR) {
                route = result.getRouteLines().get(0);
                Log.d("###",route.getDuration()+"route");
                DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaiduMap);
                routeOverlay = overlay;
                mBaiduMap.setOnMarkerClickListener(overlay);
                overlay.setData(route);
                overlay.addToMap();
            }
        }

        @Override
        public void onGetIndoorRouteResult(IndoorRouteResult arg0) {}

        @Override
        public void onGetMassTransitRouteResult(MassTransitRouteResult arg0) {}

        @Override
        public void onGetTransitRouteResult(TransitRouteResult arg0) {}

        @Override
        public void onGetWalkingRouteResult(WalkingRouteResult arg0) {}

    }

    private class MyDrivingRouteOverlay extends DrivingRouteOverlay {

        public MyDrivingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_en);
            }
            return null;
        }
    }

    @Override
    protected void onDestroy() {
        utils.stop();
        utils=null;
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

}
