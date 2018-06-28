package com.avicsafety.safety_examine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.avicsafety.safety_examine.R;
import com.avicsafety.safety_examine.model.WarningBean;

import java.util.List;

public class WarningListAdapter extends BaseAdapter {
    List<WarningBean> datas;
    Context mContext;
    String alarmInformation;

    public WarningListAdapter(List<WarningBean> datas, Context context, String alarmInformation) {
        this.datas = datas;
        mContext = context;
        this.alarmInformation = alarmInformation;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
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
            view=LayoutInflater.from(mContext).inflate(R.layout.item_list_warning,null);
            holder=new MyViewHolder();
            holder.name_warning= (TextView) view.findViewById(R.id.item_list_name_warning);
            holder.type_warning= (TextView) view.findViewById(R.id.item_list_type_warning);
            view.setTag(holder);
        }else {
            view=convertView;
            holder= (MyViewHolder) view.getTag();
        }

        holder.name_warning.setText(datas.get(position).getTheme());
        holder.type_warning.setText(alarmInformation);
        return view;
    }


    public class MyViewHolder {
        TextView name_warning;
        TextView type_warning;
    }
}
