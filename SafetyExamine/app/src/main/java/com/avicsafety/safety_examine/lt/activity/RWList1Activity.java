package com.avicsafety.safety_examine.lt.activity;

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

import com.avicsafety.safety_examine.R;
import com.avicsafety.safety_examine.Utils.MyProgressDialog;
import com.avicsafety.safety_examine.activity.BaseActivity;
import com.avicsafety.safety_examine.lt.activity.ltUtil.CTCSRestClientUsage;
import com.avicsafety.safety_examine.model.Mrwlb;

import org.json.JSONException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 刘畅 on 2017/7/26.
 */
@ContentView(R.layout.n_activity_rwlb)
public class RWList1Activity extends BaseActivity implements View.OnClickListener{
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.towermain, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_refresh:
                initData(nowStart);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initData(int start) {
        nowStart = start;
        nextStart = start + 15;

        listItems.clear();

        progressDialog = new MyProgressDialog(oThis, "获取中..");

        try {
            new CTCSRestClientUsage().GetRWLB(oThis, taskType, start, 15 );

        } catch (JSONException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
        setTitle("任务列表");
        taskType = getIntent().getIntExtra("taskType", 0);
    }

    public void initList(final List<Mrwlb> mList) {
        for (int i = 0; i < mList.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("iv_logo", R.drawable.f_i_b_b);
            map.put("tv_theme", mList.get(i).getTheme());
            map.put("tv_no", mList.get(i).getNo());
            listItems.add(map);
        }

        SimpleAdapter mainListSA = new SimpleAdapter(this, listItems,
                R.layout.n_gd_list_1_item, new String[] { "iv_logo",
                "tv_theme", "tv_no" }, new int[] { R.id.iv_logo,
                R.id.tv_theme, R.id.tv_no });

        lv_gdlb.setAdapter(mainListSA);

        lv_gdlb.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO 自动生成的方法存根
                startActivity(new Intent(oThis,
                        GDShowAndDealWithNoFildActivity.class).
                        putExtra("id", mList.get(position).getId()).
                        putExtra("taskType", taskType));
            }
        });
    }


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
    public void initList2(final List<Mrwlb> mList, int count) {
        // TODO 自动生成的方法存根

        totalC = count;

        if (totalC > 0) {
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
                map.put("iv_logo", R.drawable.f_i_b_b);
                map.put("tv_theme", mList.get(i).getTheme());
                map.put("tv_no", mList.get(i).getNo());
                listItems.add(map);
            }

            SimpleAdapter mainListSA = new SimpleAdapter(this, listItems,
                    R.layout.n_gd_list_1_item, new String[]{"iv_logo",
                    "tv_theme", "tv_no"}, new int[]{R.id.iv_logo,
                    R.id.tv_theme, R.id.tv_no});

            lv_gdlb.setAdapter(mainListSA);

            lv_gdlb.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // TODO 自动生成的方法存根
                    startActivity(new Intent(oThis,
                            GDShowAndDealWithNoFildActivity.class).putExtra("id",
                            mList.get(position).getId()).putExtra("taskType",
                            taskType));
                }
            });
        }
    }
    @Override
    protected void onResume() {
        // TODO 自动生成的方法存根

        super.onResume();
        initData(nowStart);
    }


}
