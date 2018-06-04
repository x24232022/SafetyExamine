package com.avicsafety.ShenYangTowerComService.activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.alibaba.fastjson.JSON;
import com.avicsafety.ShenYangTowerComService.R;
import com.avicsafety.ShenYangTowerComService.adapter.TomorrowAdapter;
import com.avicsafety.ShenYangTowerComService.model.MUser;
import com.avicsafety.ShenYangTowerComService.xfd.Constants;
import com.avicsafety.ShenYangTowerComService.xfd.PlanXqActivity;
import com.avicsafety.ShenYangTowerComService.xfd.Rwlb;


import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;



/**
 * 未来工单列表预览
 */
@ContentView(R.layout.activity_tomorrow)
public class TomorrowActivity extends BaseActivity{
    @ViewInject(R.id.list_tomorrow)
    private ListView lv_tomorrow;
    private MUser userAccoutn;
    private String mDate;
    private List<Rwlb.ResponseBean> mList;
    private Activity mActivity=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDate = getIntent().getStringExtra("blackoutdate");
        initData();
    }
    //type10
    private void initData() {
        userAccoutn =com.avicsafety.ShenYangTowerComService.yd.activity.ydUtil.Constants.getUserInfo(oThis) ;
        RequestParams params = new RequestParams(Constants.TEST_URL);
        params.addParameter("userid", userAccoutn.getUserName());
        params.addParameter("blackoutdate", mDate);
        params.addParameter("type", 10);

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
                } catch (org.json.JSONException e) {
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
        TomorrowAdapter adapter =new TomorrowAdapter(list,mActivity);
        lv_tomorrow.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        lv_tomorrow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(oThis,
                        PlanXqActivity.class).
                        putExtra("id", list.get(position).getTicketid()).
                        putExtra("taskType",position).
                        putExtra("activityId","1").
                        putExtra("blackoutdate",mDate)
                        );
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivity.finish();
    }
}
