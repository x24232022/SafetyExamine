package com.avicsafety.ShenYangTowerComService.adapter;

import java.util.List;


import com.avicsafety.ShenYangTowerComService.R;
import com.avicsafety.ShenYangTowerComService.model.PowerWork;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;

public class PowerAdapter extends BaseAdapter {
	private Context context;
	private List<PowerWork> list;
	private int type;//区分是发电还是故障
	public PowerAdapter(Context context, List<PowerWork> list,int type) {
		super();
		this.context = context;
		this.list = list;
		this.type=type;
	}
	public PowerAdapter() {}

	public void setList(List<PowerWork> list) {
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	ViewHolder vh;
	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(int position, View v, ViewGroup parent) {
		if(v==null){
			v=LayoutInflater.from(context).inflate(R.layout.work_item, parent,false);
			vh=new ViewHolder();
			vh.tv_power_level = (TextView) v.findViewById(R.id.tv_power_level);
			vh.tv_station_address = (TextView) v.findViewById(R.id.tv_power_address);
			vh.tv_station_name = (TextView) v.findViewById(R.id.tv_power_name);
			vh.tv_send_time = (TextView) v.findViewById(R.id.tv_send_time);
			v.setTag(vh);
		}else{
			vh=(ViewHolder) v.getTag();
		}
		PowerWork pw=list.get(position);
		if(type==1){
			vh.tv_send_time.setText("预计停电时间:"+pw.getPgstarttime());
		}else{
			vh.tv_send_time.setText("故障时间:"+pw.getFaulttime());
		}
		vh.tv_station_name.setText(pw.getSitename());
		vh.tv_station_address.setText(pw.getAddress());
		vh.tv_power_level.setText(pw.getPglevel());
		if(pw.getPglevel().equals("1")){
			vh.tv_power_level.setBackgroundResource(R.drawable.level_select);
		}else if(pw.getPglevel().equals("2")){
			vh.tv_power_level.setBackgroundResource(R.drawable.level2_select);
		}else if(pw.getPglevel().equals("3")){
			vh.tv_power_level.setBackgroundResource(R.drawable.level3_select);
		}else{
			vh.tv_power_level.setBackgroundResource(R.drawable.level4_select);
		}
		return v;
	}
	
	class ViewHolder{
		//基站名称
		TextView tv_station_name;
		//发电等级
		TextView tv_power_level;
		//基站地址
		TextView tv_station_address;
		//派单时间
		TextView tv_send_time;

	}

}
