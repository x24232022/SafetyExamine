package com.avicsafety.ShenYangTowerComService.activity;


import android.content.Intent;
import android.os.Bundle;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.avicsafety.ShenYangTowerComService.R;
import com.avicsafety.ShenYangTowerComService.Utils.OnRecyclerItemClickListener;
import com.avicsafety.ShenYangTowerComService.adapter.PlanAdapter;
import com.avicsafety.ShenYangTowerComService.model.MUser;
import com.avicsafety.ShenYangTowerComService.view.DividerItemDecoration;
import com.avicsafety.ShenYangTowerComService.view.SwipeRecyclerView;
import com.avicsafety.ShenYangTowerComService.xfd.Constants;
import com.avicsafety.ShenYangTowerComService.xfd.PlanXqActivity;
import com.avicsafety.ShenYangTowerComService.xfd.Rwlb;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 未来工单列表预览
 */
@ContentView(R.layout.activity_tomorrow)
public class TomorrowActivity extends BaseActivity {

    @ViewInject(R.id.rcv_gdlb_activity_Tomorrow)
    private SwipeRecyclerView rcy_gdlb;
    private int mYear;
    private int mMonth;
    private  int mDay;
    private MUser userAccoutn;
    private List<Rwlb.ResponseBean> mList=new ArrayList<>();
    private String mBlackoutdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDate();
        initData();
    }
    private void initDate() {
        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH) + 1;
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

    }
    private void initData() {
        userAccoutn =com.avicsafety.ShenYangTowerComService.yd.activity.ydUtil.Constants.getUserInfo(oThis) ;
        mBlackoutdate = new String(mYear + "-" + mMonth + "-" + (mDay + 1));
        RequestParams params = new RequestParams(Constants.BASE_URL);
        params.addParameter("userid", userAccoutn.getUserName());
        params.addParameter("blackoutdate", mBlackoutdate);
        params.addParameter("type", 10);
        params.addParameter("typeinfo", "wjgd");
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

                        mList= JSON.parseArray(datas,Rwlb.ResponseBean.class);
                        initRecyclerView(mList);



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

            }

            @Override
            public void onCancelled(Callback.CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }

    //设置RecyclerView初始化集合参数
    private void initRecyclerView(final List<Rwlb.ResponseBean> list) {
        rcy_gdlb.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        rcy_gdlb.setLayoutManager(new LinearLayoutManager(this));
        //设置点击跳转到工单详情页
        rcy_gdlb.addOnItemTouchListener(new OnRecyclerItemClickListener(rcy_gdlb) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                startActivity(new Intent(oThis,
                        PlanXqActivity.class).
                        putExtra("id", list.get(vh.getLayoutPosition()).getTicketid()).
                        putExtra("taskType", vh.getLayoutPosition()).
                        putExtra("activityId","1").
                        putExtra("blackoutdate",mBlackoutdate).
                        putExtra("url",Constants.BASE_URL));
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder vh) {

            }
        });
        PlanAdapter adapter=new PlanAdapter(TomorrowActivity.this,list);
        rcy_gdlb.setAdapter(adapter);
    }


}
