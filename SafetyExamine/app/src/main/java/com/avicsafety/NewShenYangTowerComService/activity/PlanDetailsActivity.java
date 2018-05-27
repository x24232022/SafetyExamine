//package com.avicsafety.ShenYangTowerComService.activity;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.text.TextUtils;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.avicsafety.ShenYangTowerComService.PowerManager.push.Utils.Constants;
//import com.avicsafety.ShenYangTowerComService.PowerManager.push.Utils.DateUtils;
//import com.avicsafety.ShenYangTowerComService.PowerManager.push.Utils.LocationAddressUtils;
//import com.avicsafety.ShenYangTowerComService.PowerManager.push.Utils.SharedUtils;
//import com.avicsafety.ShenYangTowerComService.PowerManager.push.Utils.ToastUtils;
//import com.avicsafety.ShenYangTowerComService.PowerManager.push.Utils.Utils;
//import com.avicsafety.ShenYangTowerComService.PowerManager.push.presenter.SubAddressPresenter;
//import com.avicsafety.ShenYangTowerComService.R;
//import com.avicsafety.ShenYangTowerComService.Utils.MyProgressDialog;
//import com.avicsafety.ShenYangTowerComService.model.MUser;
//import com.avicsafety.ShenYangTowerComService.model.PowerWork;
//import com.avicsafety.lib.tools.L;
//import com.avicsafety.lib.tools.ToastUtil;
//import com.baidu.location.BDLocation;
//import com.baidu.mapapi.SDKInitializer;
//import com.baidu.mapapi.model.LatLng;
//import com.baidu.mapapi.search.core.SearchResult;
//import com.baidu.mapapi.search.route.BikingRouteResult;
//import com.baidu.mapapi.search.route.DrivingRouteLine;
//import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
//import com.baidu.mapapi.search.route.DrivingRouteResult;
//import com.baidu.mapapi.search.route.IndoorRouteResult;
//import com.baidu.mapapi.search.route.MassTransitRouteResult;
//import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
//import com.baidu.mapapi.search.route.PlanNode;
//import com.baidu.mapapi.search.route.RoutePlanSearch;
//import com.baidu.mapapi.search.route.TransitRouteResult;
//import com.baidu.mapapi.search.route.WalkingRouteResult;
//import com.ydtx.powermanger.Module.BaseModule;
//import com.ydtx.powermanger.event.LatLngEvent;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;
//import org.xutils.common.Callback;
//import org.xutils.http.RequestParams;
//import org.xutils.view.annotation.ContentView;
//import org.xutils.view.annotation.ViewInject;
//import org.xutils.x;
//
//import java.math.RoundingMode;
//import java.text.DecimalFormat;
//import java.util.Timer;
//import java.util.TimerTask;
//
///**
// * Created by 刘畅 on 2017/8/28.
// */
//@ContentView(R.layout.work_details)
//public class PlanDetailsActivity extends BaseActivity implements View.OnClickListener {
//    public MyProgressDialog progressDialog;
//    private MUser mMUser;
//    private String userAccount;//账号
//
////    public PowerWork getBean() {
////        return bean;
////    }
////
////    public void setBean(PowerWork bean) {
////        this.bean = bean;
////    }
//
//    public PowerWork getBean() {
//        return bean;
//    }
//
//    public void setBean(PowerWork bean) {
//        this.bean = bean;
//    }
//
//    private PowerWork bean;
//    private long time;//计算后的毫秒数
//    private long oldtime;//本身的毫秒数
//    private long pgtime;//发电时间的毫秒数
//    private long date;//当前时间的毫秒数
//    private long lastTime;//上次保存的时间
//    private BaseModule.SubAddress subs;//访问服务其
//    private LocationAddressUtils utils;//定位
//    private Timer timer;
//    private TimerTask task;
//    private RoutePlanSearch mSearch = null;    // 搜索模块，也可去掉地图模块独立使用
//    private DrivingRouteLine route = null;
//    private BDLocation location;//定位地址
//    private String path="";//接口地址
//
//    @Override
//    protected void InitializeEvent() {
//        super.InitializeEvent();
//        initList();
//        subs= SubAddressPresenter.getInstance();
//        utils= LocationAddressUtils.getInstance();
//        tv_orders.setOnClickListener(this);
//        tv_turn_down.setOnClickListener(this);
//        btn_start_power.setOnClickListener(this);
//        btn_end_power.setOnClickListener(this);
//        tv_station_address.setOnClickListener(this);
//    }
//    @Override
//    protected void InitializeComponent() {
//        super.InitializeComponent();
//        setTitle("工单详情");
//
//    }
//
//    public void initList(){
//        bean = (PowerWork)  getIntent().getSerializableExtra("pb");
////        this.setBean(bean);
//        EventBus.getDefault().register(this);
//        userAccount = Utils.readOAuth(this).getAccount();
//        tv_station_name.setText(bean.getSitename());
//        tv_station_address.setText(bean.getAddress());
//        tv_station_startTime.setText(bean.getPgstarttime());
//        tv_station_endTime.setText(bean.getPgendtime());
//        tv_station_level.setText(bean.getPgperson());
//        tv_responsible_unit.setText(bean.getPglevel());
//        tv_principal.setText(bean.getFzdepartment());
//        tv_life_time.setText(bean.getDcxhtime()+"");
//        tv_duration.setText("计算中...");
//        tv_machine.setText(bean.getJyequipoli());
//        tv_cable_length.setText(bean.getJycanlelen());
//        tv_order_time.setText(bean.getJoinordertime());
//        tv_generators.setText(bean.getFapgperson());
//        tv_contact.setText(bean.getTel());
//        tv_generator_address.setText(bean.getPgaddress());
//        tv_generation_time.setText(bean.getPgtime());
//        tv_end_time.setText(bean.getEndtime());
//        tv_station_ssoperta.setText(bean.getSsoperta());
//        String state=bean.getStatus();
//        String pgdate=bean.getPgtime();
//        //新增判断
//        if (userAccount.equals(bean.getPgperson())) {
//            tv_turn_down.setVisibility(View.GONE);
//        }
//        if(TextUtils.isEmpty(pgdate)){
//            pgtime=0;
//        }else{
//            pgtime= DateUtils.getDateForLong(bean.getPgtime(),"yyyy-MM-dd HH:mm:ss");
//        }
//        String fault=bean.getFaulttime();
//        if(!TextUtils.isEmpty(fault)){
//            state=bean.getStatus();
//            tv_fault_time.setText(bean.getFaulttime());
//            tv_fault_area.setText(bean.getArea());
//            tv_fault_btsType.setText(bean.getBtsType());
//            tv_fault_creator.setText(bean.getCreator());
//            tv_fault_faultNo.setText(bean.getFaultNo());
//            tv_fault_locationStr.setText(bean.getLocationStr());
//            tv_fault_theme.setText(bean.getTheme());
//            tv_fault_troubleNo.setText(bean.getTroubleNo());
//            tv_fault_vtype.setText(bean.getVtype());
//            ll_fult_work.setVisibility(View.VISIBLE);
//            ll_startPower.setVisibility(View.GONE);
//            ll_endPower.setVisibility(View.GONE);
//            date=System.currentTimeMillis();
//            oldtime=DateUtils.getDateForLong(bean.getFaulttime(),"yyyy-MM-dd HH:mm:ss");
//            time=DateUtils.getDateForString(bean.getFaulttime(), bean.getDcxhtime());
//            lastTime= SharedUtils.getLongShared(oThis,bean.getId()+"lastTime");
//            if(lastTime==0){
//                lastTime=DateUtils.getLongForString(bean.getDcxhtime());
//            }
//            countDown();
//        }else{
//            ll_fult_work.setVisibility(View.GONE);
//            ll_fault_area.setVisibility(View.GONE);
//            ll_fault_btsType.setVisibility(View.GONE);
//            ll_fault_creator.setVisibility(View.GONE);
//            ll_fault_faultNo.setVisibility(View.GONE);
//            ll_fault_locationStr.setVisibility(View.GONE);
//            ll_fault_theme.setVisibility(View.GONE);
//            ll_fault_troubleNo.setVisibility(View.GONE);
//            ll_fault_vtype.setVisibility(View.GONE);
//            view_fault_area.setVisibility(View.GONE);
//            view_fault_btsType.setVisibility(View.GONE);
//            view_fault_creator.setVisibility(View.GONE);
//            view_fault_faultNo.setVisibility(View.GONE);
//            view_fault_locationStr.setVisibility(View.GONE);
//            view_fault_theme.setVisibility(View.GONE);
//            view_fault_troubleNo.setVisibility(View.GONE);
//            view_fault_vtype.setVisibility(View.GONE);
//            ll_startPower.setVisibility(View.VISIBLE);
//            ll_endPower.setVisibility(View.VISIBLE);
//            time=DateUtils.getDateForString(bean.getPgstarttime(), bean.getDcxhtime());
//            date=System.currentTimeMillis();
//            oldtime=DateUtils.getDateForLong(bean.getPgendtime(),"yyyy-MM-dd HH:mm:ss");
//            lastTime=SharedUtils.getLongShared(oThis,bean.getId()+"lastTime");
//            if(lastTime==0){
//                lastTime=DateUtils.getLongForString(bean.getDcxhtime());
//            }
//            countDown();
//        }
//        //Utils.readOAuth(context).getAccount()
//        if(Utils.readOAuth(oThis).getIsPower().equals("是")){
//            if(state.equals("已派单")){
//                ll_operating.setVisibility(View.VISIBLE);
////					if(TextUtils.isEmpty(fault)){
////						tv_transfer.setVisibility(View.VISIBLE);
////					}else{
////						tv_transfer.setVisibility(View.GONE);
////					}
//
//                btn_start_power.setVisibility(View.GONE);
//
//                //tv_transfer.setVisibility(View.VISIBLE);
//                btn_end_power.setVisibility(View.GONE);
//                ll_received.setVisibility(View.GONE);
//                ll_btn.setVisibility(View.GONE);
//            }else if(state.equals("已接单")){
//                ll_operating.setVisibility(View.GONE);
//                btn_start_power.setVisibility(View.VISIBLE);
//                btn_end_power.setVisibility(View.GONE);
//                ll_received.setVisibility(View.VISIBLE);
//                ll_btn.setVisibility(View.VISIBLE);
//            }else if(state.equals("正在发电")){
//                ll_operating.setVisibility(View.GONE);
//                btn_start_power.setVisibility(View.GONE);
//                btn_end_power.setVisibility(View.VISIBLE);
//                ll_received.setVisibility(View.VISIBLE);
//                ll_btn.setVisibility(View.VISIBLE);
//            }else{
//                ll_operating.setVisibility(View.GONE);
//                btn_start_power.setVisibility(View.GONE);
//                btn_end_power.setVisibility(View.GONE);
//                ll_received.setVisibility(View.VISIBLE);
//                ll_btn.setVisibility(View.GONE);
//            }
//        }else{
//            ll_operating.setVisibility(View.GONE);
//            btn_start_power.setVisibility(View.GONE);
//            btn_end_power.setVisibility(View.GONE);
//            ll_received.setVisibility(View.GONE);
//            ll_btn.setVisibility(View.GONE);
//        }
//    }
//
//
//    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
//    public void onEventMainThread(LatLngEvent event){
//        L.v("从LocationAddressUtils传来的经纬度:" + event.getLocation());
//        location=event.getLocation();
//            startRoute();
//    }
//
//
//    @SuppressLint("HandlerLeak")
//    Handler hd=new Handler(){
//        public void handleMessage(Message msg) {
//            if(msg.what==250){
//                timer.cancel();
//                paseDown();
//            }else if(msg.what==350){
//                long time=(Long) msg.obj;
//                String date=DateUtils.getDateForString(time, "HH:mm:ss");
//                tv_life_time.setText(date);
//            }else if(msg.what==450){
//                String type=msg.obj.toString();
//                tv_life_time.setText(type);
//                timer.cancel();
//                if(type.equals("已超时")){
//                    tv_life_time.setTextColor(Color.parseColor("#f54336"));
//                }else{
//                    tv_life_time.setTextColor(Color.parseColor("#00FF7F"));
//                }
//            }
//        };
//    };
//    private void paseDown(){
//        timer=new Timer();
//        task=new TimerTask() {
//
//            @Override
//            public void run() {
//                lastTime-=1000;
//                Message message=hd.obtainMessage(350, lastTime);
//                hd.sendMessage(message);
//            }
//        };
//        timer.schedule(task, 0,1000);
//    }
//
//    private void countDown(){
//        timer=new Timer();
//        task=new TimerTask() {
//            @Override
//            public void run() {
//                //证明还没有开始发电
//                if(pgtime==0){
//                    if(!TextUtils.isEmpty(bean.getFaulttime())){
//                        if(date>time){
//                            Message message=hd.obtainMessage(450,"已超过电池续航时间");
//                            hd.sendMessage(message);
//
//                        }else if(oldtime>date){
//                        }else if(lastTime>0){
//                            Message message=hd.obtainMessage(250);
//                            hd.sendMessage(message);
//                        }
//                    }else{
//                        //已经到了计算电池续航时间
//                        if(oldtime==date){
//                            Message message=hd.obtainMessage(250);
//                            hd.sendMessage(message);
//                        }
//                        //已经超过电池续航时间(当前时间>总续航时间)
//                        if(time<date){
//                            Message message=hd.obtainMessage(450,"已超过电池续航时间");
//                            hd.sendMessage(message);
//                        }
//                    }
//                }else{
//                    //证明已经开始发电
//                    Message message=hd.obtainMessage(450,"已发电");
//                    hd.sendMessage(message);
//                }
//            }
//        };
//        timer.schedule(task, 0,1000);
//    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        utils.initLocation(oThis);
//        mSearch = RoutePlanSearch.newInstance();
//        mSearch.setOnGetRoutePlanResultListener(new OnResultListener());
//        utils.start();
////        ToastUtil.showToast(oThis,mSearch.toString());
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
////        if(bean.getStatus().equals("已派单")){
////         if(TextUtils.isEmpty(bean.getFaulttime())){
////
////         }else {
//             getMenuInflater().inflate(R.menu.zhuanpai, menu);
////         }
////        }
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.action_zhuanpai:
//                Intent trasfre=new Intent(oThis,PowerWorkerTransfer.class);
//                Bundle bud=new Bundle();
//                bud.putSerializable("pw", bean);
//                trasfre.putExtra("bund", bud);
//                startActivityForResult(trasfre,100);
//                finish();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    public void onClick(View v){
//        int id=v.getId();
//        String time=bean.getFaulttime();
//        switch (id) {
//            case R.id.tv_orders://接单
//                if (!TextUtils.isEmpty(time)) {
//                    path = Constants.POWER_FAULT_WORK;
//                } else {
//                    path = Constants.POWER_ORDERS_WORK;
//                }
//                RequestParams params = new RequestParams(path);
//                params.addParameter("id", bean.getId() + "");
//                params.addParameter("orderid", bean.getOrderid());
//                params.addParameter("account", Utils.readOAuth(oThis).getAccount());
//                params.addParameter("name", Utils.readOAuth(oThis).getName());
//                x.http().post(params, new Callback.CommonCallback<String>() {
//                    @Override
//                    public void onSuccess(String result) {
//                        if (result.contains("success")) {
//                            ToastUtils.showToast(oThis, "操作成功!");
//                        } else {
//                            ToastUtils.showToast(oThis, "操作失败!");
//                        }
//                        finish();
//                        Utils.hideProgress(oThis);
//                    }
//
//                    @Override
//                    public void onError(Throwable ex, boolean isOnCallback) {
//                        L.v("接单OnClick!", ex.getMessage());
//                    }
//
//                    @Override
//                    public void onCancelled(CancelledException cex) {
//
//                    }
//
//                    @Override
//                    public void onFinished() {
//
//                    }
//                });
//                break;
//            case R.id.tv_turn_down://驳回
//                path = Constants.POWER_TURN_DOWN;
//                if (!TextUtils.isEmpty(time)) {
//                    path = Constants.FAULT_TURN_DOWN;
//                }
//                RequestParams params1 = new RequestParams(path);
//                params1.addParameter("orderid", bean.getOrderid());
//                params1.addParameter("account", Utils.readOAuth(oThis).getAccount());
//                params1.addParameter("name", Utils.readOAuth(oThis).getName());
//                params1.addParameter("pgperson", bean.getPgperson());
//                params1.addParameter("id", bean.getId() + "");
//                x.http().post(params1, new Callback.CommonCallback<String>() {
//                    @Override
//                    public void onSuccess(String result) {
//                        if (result.contains("success")) {
//                            ToastUtils.showToast(oThis, "操作成功!");
//                        } else {
//                            ToastUtils.showToast(oThis, "操作失败!");
//                        }
//                        finish();
//                        Utils.hideProgress(oThis);
//                    }
//
//                    @Override
//                    public void onError(Throwable ex, boolean isOnCallback) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(CancelledException cex) {
//
//                    }
//
//                    @Override
//                    public void onFinished() {
//
//                    }
//                });
//                break;
//            case R.id.btn_start_power://开始发电
//                path = Constants.POWER_START_GENERATION;
//                if (!TextUtils.isEmpty(time)) {
//                    path = Constants.POWER_FAULT_START_WORK;
//                }
//                if (location == null) {
//                    ToastUtils.showToast(oThis, "正在获取位置信息请稍后!");
//                    return;
//                }
//                RequestParams params2 = new RequestParams(path);
//                params2.addParameter("perlongitude", location.getLongitude() + "");
//                params2.addParameter("perlatitude", location.getLatitude() + "");
//                params2.addParameter("pgaddress", location.getAddrStr());
//                params2.addParameter("orderid", bean.getOrderid());
//                params2.addParameter("account", Utils.readOAuth(oThis).getAccount());
//                params2.addParameter("name", Utils.readOAuth(oThis).getName());
//                params2.addParameter("id", bean.getId() + "");
//                x.http().post(params2, new Callback.CommonCallback<String>() {
//                    @Override
//                    public void onSuccess(String result) {
//                        if (result.contains("success")) {
//                            ToastUtils.showToast(oThis, "操作成功!");
//                        } else {
//                            ToastUtils.showToast(oThis, "操作失败!");
//                        }
//                        finish();
//                        Utils.hideProgress(oThis);
//                    }
//
//                    @Override
//                    public void onError(Throwable ex, boolean isOnCallback) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(CancelledException cex) {
//
//                    }
//
//                    @Override
//                    public void onFinished() {
//
//                    }
//                });
//                break;
//            case R.id.btn_end_power://结束发电
//                path = Constants.POWER_END_GENERATION;
//                if (!TextUtils.isEmpty(time)) {
//                    path = Constants.POWER_FAULT_END_WORK;
//                }
//                RequestParams params3 = new RequestParams(path);
//                params3.addParameter("id", bean.getId() + "");
//                params3.addParameter("orderid", bean.getOrderid());
//                params3.addParameter("account", Utils.readOAuth(oThis).getAccount());
//                params3.addParameter("name", Utils.readOAuth(oThis).getName());
//                x.http().post(params3, new Callback.CommonCallback<String>() {
//                    @Override
//                    public void onSuccess(String result) {
//                        if (result.contains("success")) {
//                            ToastUtils.showToast(oThis, "操作成功!");
//                        } else {
//                            ToastUtils.showToast(oThis, "操作失败!");
//                        }
//                        finish();
//                        Utils.hideProgress(oThis);
//                    }
//
//                    @Override
//                    public void onError(Throwable ex, boolean isOnCallback) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(CancelledException cex) {
//
//                    }
//
//                    @Override
//                    public void onFinished() {
//
//                    }
//                });
//                break;
//            case R.id.tv_station_address://路径规划导航
//                Intent intent=new Intent(oThis, RouteActivity.class);
//                LatLng latlng=new LatLng(bean.getLatitude(),bean.getLongitude());
//                Bundle bund=new Bundle();
//                bund.putParcelable("latlng", latlng);
//                intent.putExtra("bund", bund);
//                startActivity(intent);
//                break;
//        }
//    }
//
//    class OnResultListener implements OnGetRoutePlanResultListener {
//
//        @Override
//        public void onGetBikingRouteResult(BikingRouteResult arg0) {}
//
//        @Override
//        public void onGetDrivingRouteResult(DrivingRouteResult result) {
//            try {
//                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
//                    tv_duration.setText("抱歉未查找到结果!");
//                }
//                if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
//                    // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
//                    //				 result.getSuggestAddrInfo();
//                    return;
//                }
//                if (result.error == SearchResult.ERRORNO.NO_ERROR) {
//                    route = result.getRouteLines().get(0);
//                    String hh=format(route.getDuration(),3600);
//                    tv_duration.setText(hh+"小时");
//                }
//            } catch (Exception e) {
//                L.e(e.getMessage());
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {
//
//        }
//
//        @Override
//        public void onGetMassTransitRouteResult(MassTransitRouteResult arg0) {}
//
//        @Override
//        public void onGetTransitRouteResult(TransitRouteResult arg0) {}
//
//        @Override
//        public void onGetWalkingRouteResult(WalkingRouteResult arg0) {}
//
//    }
//
//    private String format(float s, float d) {
//        float num = (float) s / d;
//        DecimalFormat df = new DecimalFormat("0.00");// 格式化小数
//        df.setRoundingMode(RoundingMode.FLOOR);// 取消四舍五入
//        return df.format(num);
//    }
//
//    private void startRoute(){
//        PlanNode stNode = PlanNode.withLocation(new LatLng(location.getLatitude(),location.getLongitude()));
//        PlanNode enNode = PlanNode.withLocation(new LatLng( bean.getLatitude(),bean.getLongitude()));
//        mSearch.drivingSearch((new DrivingRoutePlanOption())
//                .from(stNode).to(enNode));
//    }
//
//
//    @Override
//    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
//        if(arg1==200){
//            this.finish();
//        }
//    }
//    @Override
//    protected void onDestroy() {
//        mSearch.destroy();
//        SharedUtils.putShared(oThis, bean.getId()+"lastTime", System.currentTimeMillis());
//        EventBus.getDefault().unregister(this);
//        super.onDestroy();
//    }
//
//
//
//
//
//
//
//
//
//    @ViewInject(R.id.tv_station_name)
//    TextView tv_station_name;
//    @ViewInject(R.id.tv_station_address) TextView tv_station_address;
//    @ViewInject(R.id.tv_station_startTime) TextView tv_station_startTime;
//    @ViewInject(R.id.tv_station_endTime) TextView tv_station_endTime;
//    @ViewInject(R.id.tv_responsible_unit) TextView tv_responsible_unit;
//    @ViewInject(R.id.tv_principal) TextView tv_principal;
//    @ViewInject(R.id.tv_station_level) TextView tv_station_level;
//    @ViewInject(R.id.tv_life_time) TextView tv_life_time;
//    @ViewInject(R.id.tv_duration) TextView tv_duration;
//    @ViewInject(R.id.tv_machine) TextView tv_machine;
//    @ViewInject(R.id.tv_cable_length) TextView tv_cable_length;
//    @ViewInject(R.id.tv_orders) TextView tv_orders;//接单
//    @ViewInject(R.id.ll_operating)
//    LinearLayout ll_operating;//操作
//    @ViewInject(R.id.ll_received) LinearLayout ll_received;//已经接单的
//    @ViewInject(R.id.tv_turn_down) TextView tv_turn_down;//驳回
//    @ViewInject(R.id.btn_start_power)
//    Button btn_start_power;//开始发电
//    @ViewInject(R.id.btn_end_power) Button btn_end_power;//结束发电
//    @ViewInject(R.id.tv_order_time) TextView  tv_order_time;
//    @ViewInject(R.id.tv_generators) TextView tv_generators;
//    @ViewInject(R.id.tv_contact) TextView tv_contact;
//    @ViewInject(R.id.tv_generator_address) TextView tv_generator_address;
//    @ViewInject(R.id.tv_generation_time) TextView tv_generation_time;
//    @ViewInject(R.id.tv_end_time) TextView tv_end_time;
//    @ViewInject(R.id.tv_fault_time) TextView tv_fault_time;
//    @ViewInject(R.id.ll_btn) LinearLayout ll_btn;
//    @ViewInject(R.id.ll_startPower) LinearLayout ll_startPower;
//    @ViewInject(R.id.ll_fult_work) LinearLayout ll_fult_work;
//    @ViewInject(R.id.ll_endPower) LinearLayout ll_endPower;
//    @ViewInject(R.id.tv_station_ssoperta) TextView tv_station_ssoperta;
//    @ViewInject(R.id.tv_fault_area) TextView tv_fault_area;
//    @ViewInject(R.id.tv_fault_btsType) TextView tv_fault_btsType;
//    @ViewInject(R.id.tv_fault_creator) TextView tv_fault_creator;
//    @ViewInject(R.id.tv_fault_faultNo) TextView tv_fault_faultNo;
//    @ViewInject(R.id.tv_fault_locationStr) TextView tv_fault_locationStr;
//    @ViewInject(R.id.tv_fault_theme) TextView tv_fault_theme;
//    @ViewInject(R.id.tv_fault_troubleNo) TextView tv_fault_troubleNo;
//    @ViewInject(R.id.tv_fault_vtype) TextView tv_fault_vtype;
//    @ViewInject(R.id.ll_fault_area) LinearLayout ll_fault_area;
//    @ViewInject(R.id.ll_fault_btsType) LinearLayout ll_fault_btsType;
//    @ViewInject(R.id.ll_fault_creator) LinearLayout ll_fault_creator;
//    @ViewInject(R.id.ll_fault_faultNo) LinearLayout ll_fault_faultNo;
//    @ViewInject(R.id.ll_fault_locationStr) LinearLayout ll_fault_locationStr;
//    @ViewInject(R.id.ll_fault_theme) LinearLayout ll_fault_theme;
//    @ViewInject(R.id.ll_fault_troubleNo) LinearLayout ll_fault_troubleNo;
//    @ViewInject(R.id.ll_fault_vtype) LinearLayout ll_fault_vtype;
//    @ViewInject(R.id.view_fault_area) View view_fault_area;
//    @ViewInject(R.id.view_fault_btsType) View view_fault_btsType;
//    @ViewInject(R.id.view_fault_creator) View view_fault_creator;
//    @ViewInject(R.id.view_fault_faultNo) View view_fault_faultNo;
//    @ViewInject(R.id.view_fault_locationStr) View view_fault_locationStr;
//    @ViewInject(R.id.view_fault_theme) View view_fault_theme;
//    @ViewInject(R.id.view_fault_troubleNo) View view_fault_troubleNo;
//    @ViewInject(R.id.view_fault_vtype) View view_fault_vtype;
//
//}
