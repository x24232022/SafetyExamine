package com.avicsafety.ShenYangTowerComService.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.avicsafety.ShenYangTowerComService.R;
import com.avicsafety.ShenYangTowerComService.adapter.TomorrowTitleListAdapter;
import com.avicsafety.ShenYangTowerComService.model.TomorrowTitleBean;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ContentView(R.layout.activity_tomorrow_title_list)
public class TomorrowTitleListActivity extends BaseActivity {
    @ViewInject(R.id.list_title_tomorrow)
    private ListView lv_title_tomorrow;
    private int mYear;
    private int mMonth;
    private int mDay;
    private Activity mActivity=this;
    List<TomorrowTitleBean> mList=new ArrayList<>();
    private TomorrowTitleListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDate();
        initData();
        initView();
    }
    //数据挂载视图
    private void initView() {

        mAdapter = new TomorrowTitleListAdapter(mActivity,mList);

        lv_title_tomorrow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(TomorrowTitleListActivity.this,TomorrowActivity.class);
                intent.putExtra("blackoutdate",mList.get(position).getData());
                startActivity(intent);

            }
        });
        lv_title_tomorrow.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
    //初始化数据
    private void initData() {
        mList.add(new TomorrowTitleBean((mYear+"-"+mMonth+"-"+(mDay+1)),getWeek(getNextDay(new Date(),1))));
        mList.add(new TomorrowTitleBean((mYear+"-"+mMonth+"-"+(mDay+2)),getWeek(getNextDay(new Date(),2))));
        mList.add(new TomorrowTitleBean((mYear+"-"+mMonth+"-"+(mDay+3)),getWeek(getNextDay(new Date(),3))));
        mList.add(new TomorrowTitleBean((mYear+"-"+mMonth+"-"+(mDay+4)),getWeek(getNextDay(new Date(),4))));
        mList.add(new TomorrowTitleBean((mYear+"-"+mMonth+"-"+(mDay+5)),getWeek(getNextDay(new Date(),5))));
        mList.add(new TomorrowTitleBean((mYear+"-"+mMonth+"-"+(mDay+6)),getWeek(getNextDay(new Date(),6))));
        mList.add(new TomorrowTitleBean((mYear+"-"+mMonth+"-"+(mDay+7)),getWeek(getNextDay(new Date(),7))));
    }
    //初始化时间
    public void initDate(){
        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH) + 1;
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static Date getNextDay(Date date,int number) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +number);//+1今天的时间加一天
        date = calendar.getTime();
        return date;
    }
    public static String getWeek(Date date){
        String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(week_index<0){
            week_index = 0;
        }
        return weeks[week_index];
    }

}
