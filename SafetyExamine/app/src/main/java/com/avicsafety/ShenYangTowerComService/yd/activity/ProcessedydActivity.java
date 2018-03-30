package com.avicsafety.ShenYangTowerComService.yd.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.avicsafety.ShenYangTowerComService.R;
import com.avicsafety.ShenYangTowerComService.Utils.MyProgressDialog;
import com.avicsafety.ShenYangTowerComService.activity.BaseActivity;
import com.avicsafety.ShenYangTowerComService.yd.activity.ydUtil.CTCSRestClientUsage;
import com.avicsafety.ShenYangTowerComService.yd.activity.ydUtil.Response;

import org.json.JSONException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by 刘畅 on 2017/7/24.
 */
@ContentView(R.layout.processed)
public class ProcessedydActivity extends BaseActivity {

    //当前，下一页 起始条数
    private int nowStart = 0, nextStart;

    private int totalC = 0;// 总条数
    public MyProgressDialog progressDialog;

    @ViewInject(R.id.btn_page)
    private Button btn_page;
    @ViewInject(R.id.btn_pro)
    private TextView btn_pro;
    @ViewInject(R.id.btn_next)
    private TextView btn_next;
    @ViewInject(R.id.lv_list)
    private ListView lv_list;

    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
        setTitle("处理过的工单");
    }

    @Override
    protected void InitializeEvent() {
        super.InitializeEvent();
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

    @Override
    protected void onResume() {
        // TODO 自动生成的方法存根
        super.onResume();
        initData(nowStart);
    }

    private void initData(int start) {
        // TODO 自动生成的方法存根

        nowStart = start;
        nextStart = start + 15;

        progressDialog = new MyProgressDialog(oThis, "获取中..");

        try {
            new CTCSRestClientUsage().GetProcessedyd(oThis, start, 15);

//            Toast.makeText(oThis, "成功", Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }

    }

    public void initList2(final List<Response> mList2, int count) {

        totalC = count;

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

        lv_list.setAdapter(new ResponeAdapter(oThis, mList2));

        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO 自动生成的方法存根
                startActivity(new Intent(oThis,
                        GDShowAndDealWithNoFildActivity.class)
                        .putExtra("id", mList2.get(position).getId())
                        .putExtra("taskType", 9));
            }
        });

    }

}
