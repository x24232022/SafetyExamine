package com.avicsafety.safety_examine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.avicsafety.safety_examine.R;
import com.avicsafety.safety_examine.model.ProspectBean;
import com.avicsafety.safety_examine.xfd.Rwlb;


import org.xutils.common.Callback;

import java.util.List;

/**
 * Created by Administrator on 2018/6/14 0014.
 */

public class ProspectAdapter extends BaseAdapter {
    Context mContext;
    List<ProspectBean.Response> mList;

    public ProspectAdapter(Context context, List<ProspectBean.Response> list) {
        mContext = context;
        mList = list;
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
        View view = null;
        ProspectViewHolder holder = null;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list_prospect, null);
            holder = new ProspectViewHolder();
            holder.tv_name = (TextView) view.findViewById(R.id.item_list_name_prospect);
            holder.tv_type = (TextView) view.findViewById(R.id.item_list_type_prospect);
            holder.tv_time = (TextView) view.findViewById(R.id.item_list_time_prospect);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ProspectViewHolder) view.getTag();
        }
        holder.tv_name.setText(mList.get(position).getTheme());
        holder.tv_type.setText(mList.get(position).getStatus());
        holder.tv_time.setText(mList.get(position).getCreateDate());
        return view;
    }

    class ProspectViewHolder {
        TextView tv_name;
        TextView tv_type;
        TextView tv_time;

    }
}
