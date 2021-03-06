package com.avicsafety.safety_examine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import com.avicsafety.safety_examine.R;
import com.avicsafety.safety_examine.Utils.CTCSRestClientUsage;
import com.avicsafety.safety_examine.Utils.MyProgressDialog;

import com.avicsafety.safety_examine.model.MUser;
import com.avicsafety.safety_examine.model.MrwslTT;

import org.json.JSONException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 刘畅 on 2017/6/21.
 */
@ContentView(R.layout.view_main)
public class TowerMainActivity extends BaseActivity{
    public MyProgressDialog progressDialog;
    @ViewInject(R.id.tv_tips)
    private TextView tv_tips;
    private MUser mMUser;
    private String userAccoutn;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;
    private List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("运维保障平台");

        mMUser = com.avicsafety.safety_examine.yd.activity.ydUtil.Constants.getUserInfo(TowerMainActivity.this);

        userAccoutn = mMUser.getUserName();
        tv_tips.setText(mMUser.getLocation() + "-" + mMUser.getDepartment() + " " + mMUser.getName() + "您好！");
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.towermain, menu);
        return true;
    }

    public void initGvMain(MrwslTT m) {
        // TODO 自动生成的方法存根

        GridView gv_main = (GridView) findViewById(R.id.gv_main);

        int[] iv_btnImg = { R.drawable.i_m_a, R.drawable.i_m_b,
                R.drawable.i_m_c, R.drawable.i_m_d, R.drawable.i_m_e,
                R.drawable.i_m_f, R.drawable.i_m_g, R.drawable.i_m_h,
                R.drawable.i_m_i };

        String[] tv_btnName = { "运维保障","计划发电"}; //

        for (int i = 0; i < 1; i++) {
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("iv_btnImg", iv_btnImg[i]);
            map.put("tv_btnName", tv_btnName[i]);

            if (i == 0) {
                map.put("tv_count", m.getTotal());
            } else {
                map.put("tv_code",code);

            }
            listItems.add(map);
        }

        SimpleAdapter mainListSA = new SimpleAdapter(this, listItems,
                R.layout.item_gv_main, new String[] { "iv_btnImg",
                "tv_btnName", "tv_count","tv_code"}, new int[] { R.id.iv_btnImg,
                R.id.tv_btnName, R.id.tv_count ,R.id.tv_code});

        gv_main.setAdapter(mainListSA);

        gv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO 自动生成的方法存根

                switch (arg2) {

                    case 0:
                        startActivity(new Intent(oThis,RWList0Activity.class));
                        break;



                    default:
                        break;

                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_refresh:
                initData();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        listItems.removeAll(listItems);

        progressDialog = new MyProgressDialog(oThis,"获取中...");

        try {
            new CTCSRestClientUsage().GetRWSL(oThis, 0);
            new CTCSRestClientUsage().GetDDRWSL(oThis,userAccoutn);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }





}
