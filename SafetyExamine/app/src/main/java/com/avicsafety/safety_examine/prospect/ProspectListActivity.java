package com.avicsafety.safety_examine.prospect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.alibaba.fastjson.JSON;
import com.avicsafety.safety_examine.R;
import com.avicsafety.safety_examine.activity.BaseActivity;
import com.avicsafety.safety_examine.activity.ChangeOneActivity;
import com.avicsafety.safety_examine.adapter.ProspectAdapter;
import com.avicsafety.safety_examine.model.MUser;
import com.avicsafety.safety_examine.model.ProspectBean;
import com.avicsafety.safety_examine.xfd.Constants;
import com.avicsafety.safety_examine.xfd.Rwlb;
import com.google.gson.Gson;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 勘察任务列表
 */
@ContentView(R.layout.activity_prospect_list)
public class ProspectListActivity extends BaseActivity {
    @ViewInject(R.id.list_prospect_activity)
    private ListView list_prospect_activity;
    private MUser mUser;

    public List<ProspectBean.Response> mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUser = com.avicsafety.safety_examine.yd.activity.ydUtil.Constants.getUserInfo(ProspectListActivity.this);

        initData();

    }

    private void initData() {
        GetWorkOrderAll(mUser.getUserName(), 1);


    }

    private void initView(List<ProspectBean.Response> list) {
        ProspectAdapter adapter = new ProspectAdapter(this, mList);
        list_prospect_activity.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        list_prospect_activity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProspectActivity.starActivity(
                        ProspectListActivity.this,
                        mList.get(position).getId(),
                        mList.get(position).getStatus()
                );

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    //-------------------------------网络数据获取的方法----------------------------------------------------------
    public void GetWorkOrderAll(String userName, int type) {
        RequestParams params = new RequestParams(NetWork.BASE_URL);
        params.addParameter("userid", userName);
        params.addParameter("type", type);
        params.addParameter("s", "ss");
        params.setConnectTimeout(60000);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
//                try {
//                    JSONObject obj=new JSONObject(result);
//                    JSONArray arr=obj.getJSONArray("Response");
//                    for(int i=0;i<arr.length();i++){
//                        JSONObject object=arr.getJSONObject(i);
//                        ProspectBean.Response bean= (ProspectBean.Response) JSON.toJSON(ProspectBean.Response.class);
//                        mList.add(bean);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                JSONObject res;
                try {
                    res = new JSONObject(result);
                    String code = res.getString("Code");

                    if (code.equals("200")) {

                        // 登录成功保存用户信息
                        Gson gson = new Gson();
                        ProspectBean bean = gson.fromJson(result, ProspectBean.class);
                        mList = bean.getResponse();


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
