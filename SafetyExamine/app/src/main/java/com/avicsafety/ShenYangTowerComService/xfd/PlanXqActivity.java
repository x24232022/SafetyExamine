package com.avicsafety.ShenYangTowerComService.xfd;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.avicsafety.ShenYangTowerComService.R;
import com.avicsafety.ShenYangTowerComService.Utils.MyProgressDialog;
import com.avicsafety.ShenYangTowerComService.activity.BaseActivity;
import com.avicsafety.ShenYangTowerComService.activity.RouteActivity;
import com.avicsafety.ShenYangTowerComService.activity.TomorrowActivity;
import com.avicsafety.ShenYangTowerComService.model.MUser;
import com.avicsafety.ShenYangTowerComService.model.WorkOrderBean;
import com.avicsafety.lib.tools.L;
import com.baidu.mapapi.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 刘畅 on 2017/12/27.
 * 工单详情界面
 */
@ContentView(R.layout.n_activity_gdxq_xfd)
public class PlanXqActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {


    @ViewInject(R.id.ll_content1)
    private LinearLayout ll_content;
    @ViewInject(R.id.ll_btns1)
    private LinearLayout ll_btns;
    @ViewInject(R.id.btn_redeploy_ll)
    private LinearLayout ll_redeploy;
    @ViewInject(R.id.btn_fd_ll)
    private LinearLayout btn_fd_ll;
    @ViewInject(R.id.btn_abolish_1)
    private Button btn_abolish_1;
    //    @ViewInject(R.id.btn_redeploy)
//    private Button btn_redeploy;
    @ViewInject(R.id.btn_abolish)
    private Button btn_abolish;
    @ViewInject(R.id.btn_abolish_tomorrow_activity)
    private Button btn_abolish_tomorrow;
    @ViewInject(R.id.btn_cf)
    private Button btn_cf;
    @ViewInject(R.id.btn_dzfd)
    private Button btn_dzfd;
    @ViewInject(R.id.btn_fdjs)
    private Button btn_fdjs;
    public MyProgressDialog progressDialog;
    private String id;
    private String activityId;
    private int taskType = 0;
    private String userId = "boot";
    private List<Rwlb.ResponseBean> rwlb;
    private int listtype = 0;
    private String buttonKz;
    private double lat, lng;
    private MUser userAccoutn;
    private String mUrl;
    private String mBlackoutdate;
    private Timer mTimer;
    private RadioButton mClean_rb1;
    private RadioButton mClean_rb2;
    private RadioButton mClean_rb3;
    private RadioButton mClean_rb4;

    private String mClearReason;

    public List<Rwlb.ResponseBean> getM() {
        return rwlb;
    }

    public void setM(List<Rwlb.ResponseBean> mTT) {
        this.rwlb = mTT;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityId = getIntent().getStringExtra("activityId");
        if(activityId.equals("1")){
            ll_btns.setVisibility(View.GONE);
        }
        initData();
    }

    public void initData() {
        // TODO 自动生成的方法存根
        id = getIntent().getStringExtra("id");

        ll_content.removeAllViewsInLayout();

        mBlackoutdate = getIntent().getStringExtra("blackoutdate");
        loadDate(mBlackoutdate);
    }

    //获取工单详情信息 type1
    public void loadDate(String blackoutdate) {

        RequestParams params = new RequestParams(Constants.TEST_URL);
        params.setConnectTimeout(60000);
        params.addParameter("userid", userAccoutn.getUserName());
        params.addParameter("type", 1);
        params.addParameter("ticketid", id);
        params.addParameter("blackoutdate", blackoutdate);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                JSONObject res;
                try {
                    res = new JSONObject(result);
                    String code = res.getString("Code");

                    if (code.equals("200")) {
                        String datas = res.getString("Response").toString();
                        // 登录成功保存用户信息

                        List<Rwlb.ResponseBean> mList = JSON.parseArray(datas,
                                Rwlb.ResponseBean.class);

                        ShowGDInfo(mList);

                        progressDialog.dismiss();

                    } else {
                        Toast.makeText(oThis, (CharSequence) res.get("Msg"),
                                Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                L.v("PlanXqActivity !!!!!!!!!!!~:", ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    //显示工单详情
    public void ShowGDInfo(List<Rwlb.ResponseBean> mTT) {
        // TODO 自动生成的方法存根

        this.setM(mTT);

        Field[] field = mTT.get(0).getClass().getDeclaredFields();
        buttonKz = mTT.get(0).getStatepowerstation();
        for (int i = 0; i < field.length; i++) {

            View view = LayoutInflater.from(oThis).inflate(
                    R.layout.n_gdxq_item, null);

            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            TextView tv_value = (TextView) view.findViewById(R.id.tv_value);

            switch (i) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    tv_name.setText("地市");
                    tv_value.setText(mTT.get(0).getLocation());
                    ll_content.addView(view);
                    break;
                case 4:
                    tv_name.setText("所属行政区");
                    tv_value.setText(mTT.get(0).getAdministrative());
                    ll_content.addView(view);
                    break;
                case 5:
                    tv_name.setText("所属乡镇");
                    tv_value.setText(mTT.get(0).getTownship());
                    ll_content.addView(view);
                    break;
                case 6:
                    tv_name.setText("站址编码");
                    tv_value.setText(mTT.get(0).getLocationencod());
                    ll_content.addView(view);
                    break;
                case 7:
                    tv_name.setText("站址名称");
                    tv_value.setText(mTT.get(0).getSitename());
                    ll_content.addView(view);
                    break;
                case 8:
                    tv_name.setText("经度");
                    tv_value.setText(mTT.get(0).getLongitude());
                    ll_content.addView(view);
                    break;
                case 9:
                    tv_name.setText("纬度");
                    tv_value.setText(mTT.get(0).getLatitude());
                    ll_content.addView(view);
                    break;
                case 10:
                    tv_name.setText("供电类型");
                    tv_value.setText(mTT.get(0).getPowersupply());
                    ll_content.addView(view);
                    break;
                case 11:
                    tv_name.setText("归属线路");
                    tv_value.setText(mTT.get(0).getHomeline());
                    ll_content.addView(view);
                    break;
                case 12:
                    tv_name.setText("原产权方");
                    tv_value.setText(mTT.get(0).getRightorigin());
                    ll_content.addView(view);
                    break;
                case 13:
                    tv_name.setText("站址共享关系");
                    tv_value.setText(mTT.get(0).getSharingsite());
                    ll_content.addView(view);
                    break;
                case 14:
                    tv_name.setText("所属维护单位");
                    tv_value.setText(mTT.get(0).getMaintenancunit());
                    ll_content.addView(view);
                    break;
                case 15:
                    tv_name.setText("是否自留站");
                    tv_value.setText(mTT.get(0).getRetentionstation());
                    ll_content.addView(view);
                    break;
                case 16:
                    tv_name.setText("站址发电保障级别");
                    tv_value.setText(mTT.get(0).getLevelpower());
                    ll_content.addView(view);
                    break;
                case 17:
                    tv_name.setText("当前蓄电池备电时长（小时）");
                    tv_value.setText(mTT.get(0).getBatteryhour());
                    ll_content.addView(view);
                    break;
                case 18:
                    tv_name.setText("是否安装倒顺开关");
                    tv_value.setText(mTT.get(0).getDownswitch());
                    ll_content.addView(view);
                    break;
                case 19:
                    tv_name.setText("停电类型");
                    tv_value.setText(mTT.get(0).getOutagetype());
                    ll_content.addView(view);
                    break;
                case 20:
                    tv_name.setText("停电日期");
                    tv_value.setText(mTT.get(0).getBlackoutdate());
                    ll_content.addView(view);
                    break;
                case 21:
                    tv_name.setText("停电时间区间");
                    tv_value.setText(mTT.get(0).getBlackouttimeint());
                    ll_content.addView(view);
                    break;
                case 22:
                    tv_name.setText("基站保障对策");
                    tv_value.setText(mTT.get(0).getBcountermeasures());
                    ll_content.addView(view);
                    break;
                case 23:
                    tv_name.setText("油机功率（KW）");
                    tv_value.setText(mTT.get(0).getOilenginepowe());
                    ll_content.addView(view);
                    break;
                case 24:
                    tv_name.setText("发电电缆长度(米)");
                    tv_value.setText(mTT.get(0).getCablemeter());
                    ll_content.addView(view);
                    break;
                case 25:
                    tv_name.setText("单站发电操作时间（分钟）");
                    tv_value.setText(mTT.get(0).getOperationtime());
                    ll_content.addView(view);
                    break;
                case 26:
                    tv_name.setText("发电保障小组负责人");
                    tv_value.setText(mTT.get(0).getSecuritygroup());
                    ll_content.addView(view);
                    break;
                case 27:
                    tv_name.setText("发电保障人联系电话");
                    tv_value.setText(mTT.get(0).getSecuritygrouptel());
                    ll_content.addView(view);
                    break;
                case 28:
                    tv_name.setText("发电开始时间");
                    tv_value.setText(mTT.get(0).getDrawtime());
                    ll_content.addView(view);
                    break;
                case 29:
                    tv_name.setText("发电结束时间");
                    tv_value.setText(mTT.get(0).getInetialtime());
                    ll_content.addView(view);
                    break;

                default:
                    break;

            }
            initBtns();
        }
    }

    // 显示按钮的控制状态
    private void initBtns() {
        // TODO 自动生成的方法存根

        reSetAllBtns();// 重置按钮状态
        if (buttonKz.equals("未接单")||buttonKz.equals("未接收")) {
            ll_redeploy.setVisibility(View.VISIBLE);
            btn_cf.setVisibility(View.VISIBLE);
            //btn_redeploy.setVisibility(View.VISIBLE);
            btn_abolish.setVisibility(View.VISIBLE);

        }
        if (buttonKz.equals("已接单")||buttonKz.equals("已出发")) {
            btn_fd_ll.setVisibility(View.VISIBLE);
            btn_dzfd.setVisibility(View.VISIBLE);
            btn_abolish_1.setVisibility(View.VISIBLE);
        }
        if (buttonKz.equals("发电中")||buttonKz.equals("已发电")) {
            btn_fdjs.setVisibility(View.VISIBLE);
        }
        if(buttonKz.equals("已完成")){
            ll_btns.setVisibility(View.GONE);
        }


    }

    //隐藏按钮
    private void reSetAllBtns() {
        // TODO 自动生成的方法存根


        ll_redeploy.setVisibility(View.GONE);
        btn_fd_ll.setVisibility(View.GONE);
        btn_fdjs.setVisibility(View.GONE);

    }

    //创建菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (activityId.equals("0")) {
            getMenuInflater().inflate(R.menu.towermain, menu);

            return true;
        }
        return false;
    }

    //设置菜单按钮点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                initData();
                break;
            case R.id.action_daohang:
                Intent intent = new Intent(oThis, RouteActivity.class);
                lat = Double.valueOf(rwlb.get(0).getLatitude());
                lng = Double.valueOf(rwlb.get(0).getLongitude());
                LatLng latlng = new LatLng(lat, lng);
                Bundle bund = new Bundle();
                bund.putParcelable("latlng", latlng);
                intent.putExtra("bund", bund);
                startActivity(intent);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //按钮状态点击事件
    @Override
    protected void InitializeEvent() {
        super.InitializeEvent();
        //   btn_redeploy.setOnClickListener(this);
        btn_abolish.setOnClickListener(this);
        btn_cf.setOnClickListener(this);
        btn_dzfd.setOnClickListener(this);
        btn_fdjs.setOnClickListener(this);
        btn_abolish_tomorrow.setOnClickListener(this);
        btn_abolish_1.setOnClickListener(this);

    }

    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
        setTitle("工单详情");
        id = getIntent().getStringExtra("id");
        taskType = getIntent().getIntExtra("taskType", 0);
        userAccoutn = com.avicsafety.ShenYangTowerComService.yd.activity.ydUtil.Constants.getUserInfo(oThis);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cf://出发状态
                progressDialog = new MyProgressDialog(oThis, "提交中..");
                GetXinFdPlanRwslCfData(oThis, userAccoutn.getUserName(), 4, id);
                startActivity(new Intent(oThis,PlanActivityXin.class));
                break;
            //转派状态
//            case R.id.btn_redeploy:
//                Intent intentPerson=new Intent(oThis, PersonActivity.class);
//                intentPerson.putExtra("ticketid", id);
//                startActivity(intentPerson);
//                break;
            //消除状态
            case R.id.btn_abolish:
                Intent intent1 = new Intent(this, PlanActivityXin.class);
                showClearReasonDialog(intent1);
                break;
            //发电前工单消除按钮
            case R.id.btn_abolish_1:
                Intent intent2 = new Intent(this, PlanActivityXin.class);
                showClearReasonDialog(intent2);
                break;
            //工单预览界面消除按钮
            case R.id.btn_abolish_tomorrow_activity:
                Intent intent3 = new Intent(this, TomorrowActivity.class);
                showClearReasonDialog(intent3);
                break;
            case R.id.btn_dzfd://发电状态
                Intent intent = new Intent(this, PhotoActivityDzXin.class)
                        .putExtra("ticketid", id);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_fdjs://结束状态
                progressDialog = new MyProgressDialog(oThis, "提交中..");
                GetXinFdPlanRwslFdjsData(oThis, userAccoutn.getUserName(), 6, id);

                break;
        }
    }

    //显示取消原因
    public void showClearReasonDialog(final Intent intent) {

        AlertDialog.Builder builder = new AlertDialog.Builder(oThis);
        View view = View.inflate(oThis, R.layout.cleanreason_dialog_planxq_activity, null);
        mClean_rb1 = (RadioButton) view.findViewById(R.id.rb_clean_plabxq_1);
        mClean_rb2 = (RadioButton) view.findViewById(R.id.rb_clean_plabxq_2);
        mClean_rb3 = (RadioButton) view.findViewById(R.id.rb_clean_plabxq_3);
        mClean_rb4 = (RadioButton) view.findViewById(R.id.rb_clean_plabxq_4);

        mClean_rb1.setOnCheckedChangeListener(this);
        mClean_rb2.setOnCheckedChangeListener(this);
        mClean_rb3.setOnCheckedChangeListener(this);
        mClean_rb4.setOnCheckedChangeListener(this);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                abolishWorkOrder(userAccoutn.getUserName(), "11", id, mClearReason);
                dialog.dismiss();
                if(mClearReason!=null) {
                    startActivity(intent);
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mClearReason = null;
                dialog.dismiss();
            }
        });
        builder.setTitle("选择取消原因");
        builder.setView(view);
        builder.show();

    }

    //发送取消工单请求type11
    private void abolishWorkOrder(String userid, String type, String ticketid, String clearReason) {
        RequestParams params = new RequestParams(Constants.TEST_URL );
        params.setConnectTimeout(60000);
        params.addParameter("userid", userid);
        params.addParameter("type", type);
        params.addParameter("ticketid", ticketid);
        params.addParameter("clearReason", clearReason);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    //type6
    public void GetXinFdPlanRwslFdjsData(final Context context, final String userid, final int type, final String ticketid) {
        RequestParams params = new RequestParams(Constants.TEST_URL);
        params.setConnectTimeout(60000);
        params.addParameter("userid", userid);
        params.addParameter("type", type);
        params.addParameter("ticketid", ticketid);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                JSONObject res;
                String jMsg = null;
                try {
                    res = new JSONObject(result);
                    if (res.get("Code").equals(200)) {
                        if (res.get("Msg").equals("Success")) {
                            // GDShowAndDealWithNoFildActivity
//                            Toast.makeText(context, "操作成功", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            Intent intent = new Intent(oThis, PhotoActivityXin.class);
                            intent.putExtra("ticketid", id);
                            intent.putExtra("status", "成功");
                            context.startActivity(intent);
                            finish();
                            //MyApplication.getInstance().exit();
                        } else {
                            progressDialog.dismiss();
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setIcon(R.drawable.warning);
                            builder.setTitle("异常状态");
                            builder.setMessage("工单状态异常,请选择发电状态");
                            builder.setPositiveButton("异常发电", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(oThis, PhotoActivityXin.class);
                                    intent.putExtra("ticketid", id);
                                    intent.putExtra("status", "异常");
                                    context.startActivity(intent);

                                    inputData(userid, ticketid, type);
                                    if (mTimer == null) {
                                        timer();
                                    }
                                    ((PlanXqActivity) context).progressDialog
                                            .dismiss();
                                }
                            });
                            builder.setNegativeButton("发电失败", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(oThis, PhotoActivityXin.class);
                                    intent.putExtra("ticketid", id);
                                    intent.putExtra("status", "失败");
                                    context.startActivity(intent);
                                }
                            });
                            builder.show();

                        }
                    } else {
                        Toast.makeText(context, "服务器异常", Toast.LENGTH_SHORT).show();
                        ((PlanXqActivity) context).progressDialog
                                .dismiss();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                L.v("发电结束失败log     : ", ex.getMessage().toString());
                //  inputDataSQL(userid,ticketid,type);

                inputData(userid, ticketid, type);
                if (mTimer == null) {
                    timer();
                }


            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    //向数据库写入数据
    private void inputData(String userid, String ticketid, int type) {
        WorkOrderBean bean = new WorkOrderBean();
        bean.setUserid(userid);
        bean.setTicketid(ticketid);
        bean.setType(type);
        if (bean.save()) {
            Toast.makeText(oThis, "存储成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(oThis, "存储失败", Toast.LENGTH_SHORT).show();
        }
    }
    //type4
    public void GetXinFdPlanRwslCfData(final Context context, final String userid, int type, final String ticketid) {
        RequestParams params = new RequestParams(Constants.TEST_URL);
        params.setConnectTimeout(60000);
        params.addParameter("userid", userid);
        params.addParameter("type", type);
        params.addParameter("ticketid", ticketid);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                JSONObject res;
                String jMsg = null;
                try {
                    res = new JSONObject(result);
                    if (res.get("Code").equals(200)) {
                        if (res.get("Msg").equals("Success")) {
                            // GDShowAndDealWithNoFildActivity
                            Toast.makeText(context, "操作成功", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                            //MyApplication.getInstance().exit();
                        }
                    } else {
                        Toast.makeText(context, "操作失败", Toast.LENGTH_SHORT).show();
                        ((PlanXqActivity) context).progressDialog
                                .dismiss();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    private void GetXinFdPlanRwslSbData(final Context context, String userid, int type, String ticketid, String status) {
        RequestParams params = new RequestParams(Constants.BASE_URL);
        params.setConnectTimeout(60000);
        params.addParameter("userid", userid);
        params.addParameter("type", type);
        params.addParameter("ticketid", ticketid);
        params.addParameter("status", status);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                JSONObject res;
                try {
                    res = new JSONObject(result);
                    if (res.get("Code").equals(200)) {
                        progressDialog.dismiss();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    //定时器开启网络重连
    private void timer() {

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                final WorkOrderBean bean = DataSupport.findFirst(WorkOrderBean.class);
                RequestParams params = new RequestParams(Constants.BASE_URL);
                params.setConnectTimeout(60000);
                params.addParameter("userid", bean.getUserid());
                params.addParameter("type", bean.getType());
                params.addParameter("ticketid", bean.getTicketid());
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        JSONObject res;
                        try {
                            res = new JSONObject(result);
                            if (res.get("Code").equals(200)) {
                                if (bean.isSaved()) {
                                    bean.delete();
                                }
                                if (mTimer != null) {
                                    mTimer.cancel();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });

            }
        }, 0, 1000);


    }


    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onBackPressed() {

        PlanXqActivity.this.finish();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.rb_clean_plabxq_1:
                mClearReason = (String) mClean_rb1.getText();
                break;
            case R.id.rb_clean_plabxq_2:
                mClearReason = (String) mClean_rb2.getText();
                break;
            case R.id.rb_clean_plabxq_3:
                mClearReason = (String) mClean_rb3.getText();
                break;
            case R.id.rb_clean_plabxq_4:
                mClearReason = (String) mClean_rb4.getText();
                break;
        }
    }
}
