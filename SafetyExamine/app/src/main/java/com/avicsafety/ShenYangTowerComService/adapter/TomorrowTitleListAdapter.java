package com.avicsafety.ShenYangTowerComService.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.avicsafety.ShenYangTowerComService.R;
import com.avicsafety.ShenYangTowerComService.model.TomorrowTitleBean;

import java.util.List;

public class TomorrowTitleListAdapter extends BaseAdapter {

    Context mContext;
    List<TomorrowTitleBean> data;

    public TomorrowTitleListAdapter(Context context, List<TomorrowTitleBean> data) {
        mContext = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=null;
       MyViewHolder holder=null;
        if(convertView==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.item_list_title_tomorrow_activity,null);
            holder= new MyViewHolder();
            holder.tv_week= (TextView) view.findViewById(R.id.list_item_tomorrow_week);
            holder.tv_date= (TextView) view.findViewById(R.id.list_item_tomorrow_date);

            view.setTag(holder);
        }else {
            view=convertView;
            holder= (MyViewHolder) view.getTag();
        }
        holder.tv_week.setText(data.get(position).getWeek());
        holder.tv_date.setText(data.get(position).getData());

        return view;
    }
    public class MyViewHolder{
        TextView tv_week;
        TextView tv_date;

    }
}
