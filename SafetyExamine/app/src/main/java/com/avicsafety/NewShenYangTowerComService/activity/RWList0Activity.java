package com.avicsafety.NewShenYangTowerComService.activity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.avicsafety.NewShenYangTowerComService.R;
import com.avicsafety.NewShenYangTowerComService.Utils.CTCSRestClientUsage;
import com.avicsafety.NewShenYangTowerComService.Utils.MyProgressDialog;
import com.avicsafety.NewShenYangTowerComService.model.MrwslTT;

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
@ContentView(R.layout.n_activity_rwlb)
public class RWList0Activity extends BaseActivity{
    public MyProgressDialog progressDialog;
    @ViewInject(R.id.lv_gdlb)
    private ListView lv_gdlb;
    @ViewInject(R.id.ll_btn)
    private LinearLayout ll_btn;
    private List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.towermain, menu);
        return true;
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
            new CTCSRestClientUsage().GetRWSL(oThis, 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
        setTitle("任务数量");
        ll_btn.setVisibility(View.GONE);

    }
    public void initList(MrwslTT mrwsl){
        String[] tv_theme = {"处理中"};
        for(int i = 0;i < 1;i++){

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("iv_logo", R.drawable.f_i_b_b);
            map.put("tv_theme", tv_theme[i]);

            switch (i){
                case 0:
                    map.put("tv_no", mrwsl.getProcessing());
                    break;
            }
            listItems.add(map);
        }

        SimpleAdapter mainListSA = new SimpleAdapter(this, listItems,
                R.layout.n_gd_list_0_item, new String[] { "iv_logo",
                "tv_theme", "tv_no" }, new int[] { R.id.iv_logo,
                R.id.tv_theme, R.id.tv_no });
        lv_gdlb.setAdapter(mainListSA);

        lv_gdlb.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO 自动生成的方法存根
                startActivity(new Intent(oThis, RWList1Activity.class)
                        .putExtra("taskType", position));

            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }
}
