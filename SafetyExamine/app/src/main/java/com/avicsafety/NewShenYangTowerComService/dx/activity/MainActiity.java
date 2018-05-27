package com.avicsafety.NewShenYangTowerComService.dx.activity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.avicsafety.NewShenYangTowerComService.R;
import com.avicsafety.NewShenYangTowerComService.Utils.MyProgressDialog;
import com.avicsafety.NewShenYangTowerComService.activity.BaseActivity;
import com.avicsafety.NewShenYangTowerComService.dx.activity.dxUtil.CTCSRestClientUsage;
import com.avicsafety.NewShenYangTowerComService.dx.activity.dxUtil.Constants;
import com.avicsafety.NewShenYangTowerComService.model.MUser;
import com.avicsafety.NewShenYangTowerComService.model.Mrwsl;

import org.json.JSONException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 刘畅 on 2017/7/19.
 */
@ContentView(R.layout.view_main)
public class MainActiity extends BaseActivity{
    public MyProgressDialog progressDialog;
    @ViewInject(R.id.tv_tips)
    private TextView tv_tips;
    private List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
    private MUser mUser;

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

    public void initGvMain(Mrwsl m) {
        // TODO 自动生成的方法存根

        GridView gv_main = (GridView) findViewById(R.id.gv_main);

        int[] iv_btnImg = { R.drawable.i_m_a, R.drawable.i_m_b,
                R.drawable.i_m_c, R.drawable.i_m_d, R.drawable.i_m_e,
                R.drawable.i_m_f, R.drawable.i_m_g, R.drawable.i_m_h,
                R.drawable.i_m_i };

        String[] tv_btnName = { "运维保障", "我处理过的工单" };

        for (int i = 0; i < 2; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("iv_btnImg", iv_btnImg[i]);
            map.put("tv_btnName", tv_btnName[i]);

            if (i == 0) {
                map.put("tv_count", m.getTotal());
            } else {
                map.put("tv_count", "");
            }
            listItems.add(map);
        }

        SimpleAdapter mainListSA = new SimpleAdapter(this, listItems,
                R.layout.item_gv_main, new String[] { "iv_btnImg",
                "tv_btnName", "tv_count" }, new int[] { R.id.iv_btnImg,
                R.id.tv_btnName, R.id.tv_count });

        gv_main.setAdapter(mainListSA);

        gv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO 自动生成的方法存根

                switch (arg2) {

                    case 0:
                        startActivity(new Intent(oThis, RWList0Activity.class));
                        break;

                    case 1:
                        startActivity(new Intent(oThis, ProcessedActivity.class));
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
            new CTCSRestClientUsage().GetRWSL(oThis,0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
        setTitle("运维保障平台");
        mUser = Constants.getUserInfo(oThis);
    }

    @Override
    protected void InitializeData() {
        super.InitializeData();
        tv_tips.setText(mUser.getLocation() + "-" + mUser.getDepartment() + " " + mUser.getName() + "您好！");
    }

    @Override
    protected void InitializeEvent() {
        super.InitializeEvent();

    }
}
