package com.avicsafety.ShenYangTowerComService.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.avicsafety.ShenYangTowerComService.R;
import com.avicsafety.ShenYangTowerComService.xfd.Rwlb;

import java.util.List;

public class TomorrowAdapter extends BaseAdapter{
    private List<Rwlb.ResponseBean> mList;
    Context mContext;

    public TomorrowAdapter(List<Rwlb.ResponseBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
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
            view= LayoutInflater.from(mContext).inflate(R.layout.n_gd_list_xfd_item,null);
            holder=new MyViewHolder();
            holder.tv_name= (TextView) view.findViewById(R.id.tv_theme1_gd);
            holder.tv_date= (TextView) view.findViewById(R.id.tv_number);
            holder.tv_sign= (TextView) view.findViewById(R.id.tv_sign);
            view.setTag(holder);
        }else {
           view=convertView;
           holder= (MyViewHolder) view.getTag();
        }
        holder.tv_name.setText(mList.get(position).getSitename());
        holder.tv_date.setText(mList.get(position).getExpectedstationtime());
        holder.tv_sign.setText(mList.get(position).getOutagetype());
        return view;
    }
    public class MyViewHolder{
        TextView tv_name;
        TextView tv_date;
        TextView tv_sign;
    }
}
