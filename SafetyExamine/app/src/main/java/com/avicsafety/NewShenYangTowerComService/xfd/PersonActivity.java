package com.avicsafety.NewShenYangTowerComService.xfd;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.avicsafety.NewShenYangTowerComService.R;
import com.avicsafety.NewShenYangTowerComService.activity.BaseActivity;
import com.avicsafety.NewShenYangTowerComService.activity.ChangeOneActivity;
import com.avicsafety.NewShenYangTowerComService.adapter.PersonAdapter;
import com.avicsafety.NewShenYangTowerComService.model.MUser;
import com.avicsafety.NewShenYangTowerComService.model.UserMsgBean;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_person)


public class PersonActivity extends BaseActivity{
    @ViewInject(R.id.xdp_lv_persion)
    ListView lv_person;
    Activity mActivity=this;
    List<UserMsgBean> namedata=new ArrayList<>();

    MUser userAccoutn;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getStringExtra("id");
        userAccoutn = com.avicsafety.NewShenYangTowerComService.yd.activity.ydUtil.Constants.getUserInfo(mActivity);

        initData(userAccoutn.getName(),"10");
        final PersonAdapter adapter=new PersonAdapter(namedata,mActivity);
        lv_person.setAdapter(adapter);
        lv_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(mActivity);
                builder.setMessage("是否转派给此人");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        sendUseranme(userAccoutn.getName(),"10",namedata.get(adapter.getPosition()).getUsername(),id);
                        dialog.dismiss();

                        Intent intent=new Intent(mActivity,PlanListActivityXin.class);
                        intent.putExtra("listtype",0);
                        startActivity(intent);
                        mActivity.finish();

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();

            }
        });
    }
    //发送转派人员信息和工单信息
    private void sendUseranme(String userid,String type,String username,String id) {
        RequestParams params = new RequestParams(Constants.BASE_URL);
        params.addParameter("userid", userid);
        params.addParameter("type", type);
        params.addParameter("ticketid",id);
        params.addParameter("username",username);
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

    //获取转派人员名单
    private void initData(String userid,String type) {
        RequestParams params = new RequestParams(Constants.BASE_URL);
        params.addParameter("userid", userid);
        params.setConnectTimeout(60000);
        params.addParameter("type", type);
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

                        namedata=JSON.parseArray(datas,UserMsgBean.class);



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
