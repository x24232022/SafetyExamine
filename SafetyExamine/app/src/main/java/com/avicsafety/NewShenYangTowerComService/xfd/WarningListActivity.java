package com.avicsafety.NewShenYangTowerComService.xfd;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.avicsafety.NewShenYangTowerComService.R;
import com.avicsafety.NewShenYangTowerComService.Utils.MyProgressDialog;
import com.avicsafety.NewShenYangTowerComService.activity.BaseActivity;
import com.avicsafety.NewShenYangTowerComService.adapter.WarningListAdapter;
import com.avicsafety.NewShenYangTowerComService.model.MUser;
import com.avicsafety.NewShenYangTowerComService.model.WarningBean;
import com.avicsafety.lib.tools.L;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

@ContentView(R.layout.activity_warning_list)
public class WarningListActivity extends BaseActivity {
    @ViewInject(R.id.lv_list_warning)
    ListView lv_list_warning;
    public MyProgressDialog progressDialog;
    private MUser userAccoutn;
    private Activity mActivity=this;
    private String tickId;
    private String alarmInformation;
    public static void startWarningActivity(String tickid,Activity activity,String alarmInformation){
        Intent intent=new Intent(activity, WarningListActivity.class);
        intent.putExtra("tickId",tickid);
        intent.putExtra("alarmInformation",alarmInformation);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userAccoutn = com.avicsafety.NewShenYangTowerComService.yd.activity.ydUtil.Constants.getUserInfo(oThis);
        tickId=getIntent().getStringExtra("tickId");
        alarmInformation=getIntent().getStringExtra("    private String alarmInformation");
        loadData();
    }



    public void loadData(){
        RequestParams params = new RequestParams(Constants.TEST_URL);
        params.setConnectTimeout(60000);
        params.addParameter("userid", userAccoutn.getUserName());
        params.addParameter("type", 12);
        params.addParameter("ticketid", tickId);
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

                        List<WarningBean> mList = JSON.parseArray(datas,
                                WarningBean.class);

                         initListView(mList);

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

    private void initListView(List<WarningBean> list) {
        WarningListAdapter adapter=new WarningListAdapter(list,mActivity,alarmInformation);
        lv_list_warning.setAdapter(adapter);
        lv_list_warning.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(mActivity,WarninDetailsActivity.class);
                intent.putExtra("tickId",tickId);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivity.finish();
    }
}
