package com.avicsafety.safety_examine.prospect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.avicsafety.lib.tools.L;
import com.avicsafety.safety_examine.PowerManager.push.Utils.Utils;
import com.avicsafety.safety_examine.R;
import com.avicsafety.safety_examine.activity.BaseActivity;
import com.avicsafety.safety_examine.activity.ChangeOneActivity;
import com.avicsafety.safety_examine.model.Location;
import com.avicsafety.safety_examine.model.MUser;
import com.avicsafety.safety_examine.model.ProspectBean;
import com.avicsafety.safety_examine.xfd.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@ContentView(R.layout.activity_prospect)
public class ProspectActivity extends BaseActivity {
    Activity oThis=this;
    @ViewInject(R.id.ll_content_prespect)
    LinearLayout mLayout;
    @ViewInject(R.id.btn_orderReceiving_prospect)
    Button btn_orderReceiving;
    @ViewInject(R.id.btn_dot_prospect)
    Button btn_dot;
    private MUser mUser;
    private String mTickid;
    private List<ProspectBean.Response> mList;
    private String mStatus;
    private Double mLatitude;
    private Double mLongitude;
    private String mTime;

    public static void starActivity(Activity activity, String tickId,String status){
        Intent intent =new Intent(activity,ProspectActivity.class);
        intent.putExtra("tickid",tickId);
        intent.putExtra("status",status);
        activity.startActivity(intent);
        activity.finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

        mUser = com.avicsafety.safety_examine.yd.activity.ydUtil.Constants.getUserInfo(ProspectActivity.this);
        mTickid = getIntent().getStringExtra("tickid");
        mStatus = getIntent().getStringExtra("status");
        mUser = com.avicsafety.safety_examine.yd.activity.ydUtil.Constants.getUserInfo(ProspectActivity.this);

        initData();
        initDate();
        initButton(mStatus);
    }

    private void initDate() {
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        mTime = year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
    }

    //获取传递的经纬度
    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onEvent(Location location) {
        mLatitude = location.getLatitude();
        mLongitude = location.getLongitude();
    }
    private void initData() {
        GetWorkOrderDetails(mUser.getUserName(),2,mTickid);
    }
    private void initView(List<ProspectBean.Response> list) {
        Field[] field=list.get(0).getClass().getDeclaredFields();
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
                    tv_name.setText("主题");
                    tv_value.setText(list.get(0).getTheme());
                    mLayout.addView(view);
                    break;
                case 4:
                    tv_name.setText("工单号");
                    tv_value.setText(list.get(0).getNo());
                    mLayout.addView(view);
                    break;
                case 5:
                    tv_name.setText("工单状态");
                    tv_value.setText(list.get(0).getStatus());
                    mLayout.addView(view);
                    break;
                case 6:
                    tv_name.setText("地市");
                    tv_value.setText(list.get(0).getLocation());
                    mLayout.addView(view);
                    break;
                case 7:
                    tv_name.setText("项目编号");
                    tv_value.setText(list.get(0).getProjectNo());
                    mLayout.addView(view);
                    break;
                case 8:
                    tv_name.setText("站址编码");
                    tv_value.setText(list.get(0).getSiteNo());
                    mLayout.addView(view);
                    break;
                case 9:
                    tv_name.setText("站址名称");
                    tv_value.setText(list.get(0).getSiteName());
                    mLayout.addView(view);
                    break;
                case 10:
                    tv_name.setText("创建时间");
                    tv_value.setText(list.get(0).getCreateDate());
                    mLayout.addView(view);
                    break;
                case 11:
                    tv_name.setText("接单时间");
                    tv_value.setText(list.get(0).getGetOrderDate());
                    mLayout.addView(view);
                    break;
                case 12:
                    tv_name.setText("回单时间");
                    tv_value.setText(list.get(0).getBackOrderDate());
                    mLayout.addView(view);
                    break;
                case 13:
                    tv_name.setText("上站时间");
                    tv_value.setText(list.get(0).getUpSiteDate());
                    mLayout.addView(view);
                    break;
                case 14:
                    tv_name.setText("接单人");
                    tv_value.setText(list.get(0).getGetOrderName());
                    mLayout.addView(view);
                    break;
                case 15:
                    tv_name.setText("合作单位名称");
                    tv_value.setText(list.get(0).getCooperativeunit());
                    mLayout.addView(view);
                    break;
                case 16:
                    tv_name.setText("进程用户账号");
                    tv_value.setText(list.get(0).getProcessedUsers());
                    mLayout.addView(view);
                    break;
                case 17:
                    tv_name.setText("进程用户姓名");
                    tv_value.setText(list.get(0).getProcessedUserNames());
                    mLayout.addView(view);
                    break;
                case 18:
                    tv_name.setText("当前处理人账号");
                    tv_value.setText(list.get(0).getProcessingUsers());
                    mLayout.addView(view);
                    break;
                case 19:
                    tv_name.setText("当前处理人姓名");
                    tv_value.setText(list.get(0).getProcessingUserNames());
                    mLayout.addView(view);
                    break;
                case 20:
                    tv_name.setText("当前活动");
                    tv_value.setText(list.get(0).getActivityName());
                    mLayout.addView(view);
                    break;
                case 21:
                    tv_name.setText("经度");
                    tv_value.setText(list.get(0).getLongitude());
                    mLayout.addView(view);
                    break;
                case 22:
                    tv_name.setText("纬度");
                    tv_value.setText(list.get(0).getLatitude());
                    mLayout.addView(view);
                    break;
                case 23:
                    tv_name.setText("当前经度");
                    tv_value.setText(String.valueOf(Utils.doubleFormat(mLatitude, 6)));
                    mLayout.addView(view);
                    break;
                case 24:
                    tv_name.setText("当前纬度");
                    tv_value.setText(String.valueOf(Utils.doubleFormat(mLongitude, 6)));
                    mLayout.addView(view);
                    break;
                default:
                    break;

            }


        }
    }
    private void initButton(String status) {
        switch (status){
            case "未接单" :
                btn_orderReceiving.setVisibility(View.VISIBLE);
                btn_dot.setVisibility(View.GONE);
                btn_orderReceiving.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setStatus(mUser.getUserName(),3,mTickid,"已接单",mTime);
                        ChangeOneActivity.startActivity(ProspectActivity.this);
                    }
                });

                break;
            case "新建":
                btn_orderReceiving.setVisibility(View.VISIBLE);
                btn_dot.setVisibility(View.GONE);
                btn_orderReceiving.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setStatus(mUser.getUserName(),3,mTickid,"已接单",mTime);
                        ChangeOneActivity.startActivity(ProspectActivity.this);
                    }
                });

                break;
            case "已接单":
                btn_orderReceiving.setVisibility(View.GONE);
                btn_dot.setVisibility(View.VISIBLE);
                btn_dot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        workOrderDot(mUser.getUserName(),4,mTickid,mLongitude,mLatitude,mTime);
                        ChangeOneActivity.startActivity(ProspectActivity.this);


                    }
                });

                break;
            case "已退回":
                btn_orderReceiving.setVisibility(View.GONE);
                btn_dot.setVisibility(View.VISIBLE);
                btn_dot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        workOrderDot(mUser.getUserName(),4,mTickid,mLongitude,mLatitude,mTime);
                        ChangeOneActivity.startActivity(ProspectActivity.this);
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //-------------------------网络传递数据的方法-----------------------
    public void  GetWorkOrderDetails(String userName,int type,String tickid){
        RequestParams params = new RequestParams(NetWork.BASE_URL);
        params.setConnectTimeout(60000);
        params.addParameter("userid", userName);
        params.addParameter("type", type);
        params.addParameter("ticketid", tickid);

        params.addParameter("s", "ss");
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

                        mList= JSON.parseArray(datas,ProspectBean.Response.class);
                        initView(mList);

                    } else {
                        Toast.makeText(oThis,
                                (CharSequence) res.get("Msg"),
                                Toast.LENGTH_SHORT).show();

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

    public void setStatus(String userid,int type,String tickid,String status,String time){
        RequestParams params = new RequestParams(NetWork.BASE_URL);
        params.setConnectTimeout(60000);
        params.addParameter("userid", userid);
        params.addParameter("type", type);
        params.addParameter("ticketid", tickid);
        params.addParameter("state", status);
        params.addParameter("getOrderDate",time);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {


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

    public void workOrderDot(String userName,int type,String tickId,Double lon,Double lat,String time){

        RequestParams params = new RequestParams(NetWork.BASE_URL);
        params.addParameter("userid", userName);
        params.addParameter("type", type);
        params.addParameter("ticketid", tickId);
        params.addParameter("upSiteDate", time);
        params.addParameter("lon", Utils.doubleFormat(lon, 6));
        params.addParameter("lat", Utils.doubleFormat(lat, 6));
        params.setConnectTimeout(60000);
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

}
