package com.avicsafety.NewShenYangTowerComService.xfd;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.avicsafety.NewShenYangTowerComService.PowerManager.push.Utils.Utils;
import com.avicsafety.NewShenYangTowerComService.R;
import com.avicsafety.NewShenYangTowerComService.Utils.MyProgressDialog;
import com.avicsafety.NewShenYangTowerComService.activity.BaseActivity;
import com.avicsafety.lib.tools.L;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 刘畅 on 2017/12/26.
 */
@ContentView(R.layout.n_activity_rwlb)
public class PlanListActivityXinCx extends BaseActivity implements View.OnClickListener{
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
    private String userid = "boot";
    private int listtype = 0;
    private String userAccoutn;
    private String gdid;




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.towermain1, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_refresh:
                initData(nowStart);
                break;
            case R.id.action_inquire:
                Intent intent = new Intent(oThis,InquireActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //初始化数据
    private void initData(int start) {
        nowStart = start;
        nextStart = start + 15;

        listItems.clear();

        progressDialog = new MyProgressDialog(oThis, "获取中..");

        loadDate();

    }

    //获取用户信息
    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
        setTitle("任务列表");
        userAccoutn = Utils.readOAuth(this).getUserName();
        listtype = getIntent().getIntExtra("listtype", 0);

    }
    //获取数据
    public void loadDate() {

        RequestParams params = new RequestParams(Constants.BASE_URL);
        params.setConnectTimeout(60000);
        params.addParameter("userid", userAccoutn);
        params.addParameter("type", 3);
        if(listtype == 0){
            params.addParameter("typeinfo","wjgd");
        }else if(listtype == 1){
            params.addParameter("typeinfo","ycfgd");
        }else if(listtype == 2){
            params.addParameter("typeinfo","ydzgd");
        }else if(listtype == 3){
            params.addParameter("typeinfo","yjsgd");
        }

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
                L.v("PlanListActivityError!!~:",ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    //翻页按钮点击事件
    @Override
    protected void InitializeEvent() {
        super.InitializeEvent();
        btn_next.setOnClickListener(this);
        btn_pro.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        btn_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData(nowStart - 15);
            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData(nextStart);
            }
        });
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

            for (int i = 0; i < mList.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("iv_logo",mList.get(i).getGenerationorder() );
                map.put("tv_theme", mList.get(i).getSitename());
                listItems.add(map);
            }

//            SimpleAdapter mainListSA = new SimpleAdapter(this, listItems,
//                    R.layout.n_gd_list_xfd_item, new String[]{"iv_logo",
//                    "tv_theme"}, new int[]{R.id.iv_logo1,
//                    R.id.tv_theme1});

            //lv_gdlb.setAdapter(mainListSA);

            lv_gdlb.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    gdid = mList.get(position).getTicketid();
                    // TODO 自动生成的方法存根
                    startActivity(new Intent(oThis, PlanXqActivity.class)
                            .putExtra("id",gdid)
                            .putExtra("taskType", position));
                }
            });
        }
    }
    @Override
    protected void onResume() {
        // TODO 自动生成的方法存根
        super.onResume();
        listItems.clear();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        PlanListActivityXinCx.this.finish();
    }
}
