package com.avicsafety.NewShenYangTowerComService.xfd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.avicsafety.NewShenYangTowerComService.R;

import com.avicsafety.NewShenYangTowerComService.activity.BaseActivity;
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

import java.lang.reflect.Field;
import java.util.List;

@ContentView(R.layout.activity_warnin_details)
public class WarninDetailsActivity extends BaseActivity {

    private MUser userAccoutn;
    @ViewInject(R.id.ll_content1_warning)
    private LinearLayout ll_content;
    private String tickId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userAccoutn = com.avicsafety.NewShenYangTowerComService.yd.activity.ydUtil.Constants.getUserInfo(oThis);
        tickId=getIntent().getStringExtra("tickId");
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

                        ShowGDInfo(mList);



                    } else {
                        Toast.makeText(oThis, (CharSequence) res.get("Msg"),
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
    //显示详情列表
    public void ShowGDInfo(List<WarningBean> list) {
        // TODO 自动生成的方法存根



        Field[] field = list.get(0).getClass().getDeclaredFields();

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
                    ll_content.addView(view);
                    break;
                case 4:
                    tv_name.setText("告警发生时间");
                    tv_value.setText(list.get(0).getAlarmtime());
                    ll_content.addView(view);
                    break;
                case 5:
                    tv_name.setText("告警恢复时间");
                    tv_value.setText(list.get(0).getFaultautorectime());
                    ll_content.addView(view);
                    break;
                case 6:
                    tv_name.setText("告警原因");
                    tv_value.setText(list.get(0).getAlarmreason());
                    ll_content.addView(view);
                    break;
                case 7:
                    tv_name.setText("原因分类");
                    tv_value.setText(list.get(0).getTeleAlarmreaanalysis());
                    ll_content.addView(view);
                    break;
                case 8:
                    tv_name.setText("故障原因");
                    tv_value.setText(list.get(0).getAlarmrealist());
                    ll_content.addView(view);
                    break;
                case 9:
                    tv_name.setText("处理措施");
                    tv_value.setText(list.get(0).getResprocess());
                    ll_content.addView(view);
                    break;
                case 10:
                    tv_name.setText("原因细分");
                    tv_value.setText(list.get(0).getTeleSubAlarmreaanalysis());
                    ll_content.addView(view);
                    break;
                case 11:
                    tv_name.setText("运营商工单号");
                    tv_value.setText(list.get(0).getSiteNo());
                    ll_content.addView(view);
                    break;
                case 12:
                    tv_name.setText("所属运营商");
                    tv_value.setText(list.get(0).getBelongs());
                    ll_content.addView(view);
                    break;
                case 13:
                    tv_name.setText("处理专业【联通回单结果】");
                    tv_value.setText(list.get(0).getHanprofessional());
                    ll_content.addView(view);
                    break;
                case 14:
                    tv_name.setText("告警设备名称");
                    tv_value.setText(list.get(0).getBak1());
                    ll_content.addView(view);
                    break;

                default:
                    break;

            }

        }
    }
}
