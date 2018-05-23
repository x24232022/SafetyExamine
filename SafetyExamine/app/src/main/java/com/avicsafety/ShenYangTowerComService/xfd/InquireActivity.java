package com.avicsafety.ShenYangTowerComService.xfd;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.avicsafety.ShenYangTowerComService.PowerManager.push.Utils.Utils;
import com.avicsafety.ShenYangTowerComService.R;
import com.avicsafety.ShenYangTowerComService.Utils.MyProgressDialog;
import com.avicsafety.ShenYangTowerComService.activity.BaseActivity;
import com.avicsafety.ShenYangTowerComService.datedialog.DateTimePickerDialog;
import com.avicsafety.ShenYangTowerComService.model.MUser;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 刘畅 on 2018/1/14.
 */
@ContentView(R.layout.n_activity_rwlb)
public class InquireActivity extends BaseActivity {

    @ViewInject(R.id.lv_gdlb)
    private ListView lv_gdlb;
    @ViewInject(R.id.btn_page)
    private Button btn_page;
    @ViewInject(R.id.btn_pro)
    private TextView btn_pro;
    @ViewInject(R.id.btn_next)
    private TextView btn_next;

    public MyProgressDialog progressDialog;
    private List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
    private int taskType;
    private int nowStart = 0, nextStart;
    private int totalC = 0;// 总条数
    private int listtype = 0;
    private MUser userAccoutn;
    private String gdid;
    private String date;


    public static String getStringDate(Long date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);

        return dateString;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inquire, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_xzrq:
                DateTimePickerDialog date = new DateTimePickerDialog(oThis,System.currentTimeMillis());
                        date.setOnDateTimeSetListener(new DateTimePickerDialog.OnDateTimeSetListener() {
                            @Override
                            public void OnDateTimeSet(AlertDialog dialog, long date) {
                                progressDialog = new MyProgressDialog(oThis, "获取中..");
                                loadData(getStringDate(date));

                            }
                        });
                date.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void initList2(final List<Rwlb.ResponseBean> mList, int count) {

        // TODO 自动生成的方法存根

        totalC = count;

        if (totalC >= 0) {
            btn_page.setVisibility(View.VISIBLE);


            if (nowStart <= 0) {
                btn_pro.setVisibility(View.INVISIBLE);
            } else {
                btn_pro.setVisibility(View.VISIBLE);
            }


            Log.d("totalC", totalC + "");

            if ((totalC) % 15 == 0) {
                btn_page.setText((nowStart / 15 + 1) + "/" + totalC / 15);

                if ((nowStart / 15 + 1) == totalC / 15) {
                    btn_next.setVisibility(View.INVISIBLE);
                } else {
                    btn_next.setVisibility(View.VISIBLE);
                }

            } else {
                btn_page.setText((nowStart / 15 + 1) + "/" + (totalC / 15 + 1));

                if ((nowStart / 15 + 1) == (totalC / 15 + 1)) {
                    btn_next.setVisibility(View.INVISIBLE);
                } else {
                    btn_next.setVisibility(View.VISIBLE);
                }
            }

            listItems.clear();

            for (int i = 0; i < mList.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("iv_logo", R.drawable.f_i_b_b);
                map.put("tv_theme", mList.get(i).getSitename());
                listItems.add(map);
            }

//            SimpleAdapter mainListSA = new SimpleAdapter(this, listItems,
//                    R.layout.n_gd_list_xfd_item, new String[]{"iv_logo",
//                    "tv_theme"}, new int[]{R.id.iv_logo1,
//                    R.id.tv_theme1});

         //   lv_gdlb.setAdapter(mainListSA);

            lv_gdlb.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    gdid = mList.get(position).getTicketid();
                    // TODO 自动生成的方法存根
                    startActivity(new Intent(oThis, PlanXqActivity.class)
                            .putExtra("id",gdid)
                            .putExtra("taskType", position)

                    );
                }
            });
        }
    }
    //type8
    private void loadData(String date){
        RequestParams params = new RequestParams(Constants.TEST_URL);
        params.setConnectTimeout(60000);
        params.addParameter("userid",userAccoutn.getUserName());
        params.addParameter("type",8);
        params.addParameter("blackoutdate",date);
        x.http().get(params, new Callback.CommonCallback<String>() {
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

                        initList2(mList, res.getInt("total"));

                        progressDialog.dismiss();

                    } else {
                        Toast.makeText(oThis,
                                (CharSequence) res.get("Msg"),
                                Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();
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

    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
        userAccoutn = com.avicsafety.ShenYangTowerComService.yd.activity.ydUtil.Constants.getUserInfo(oThis);
    }
}
