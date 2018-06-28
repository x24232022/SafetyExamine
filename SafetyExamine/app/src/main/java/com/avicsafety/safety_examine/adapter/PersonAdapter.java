package com.avicsafety.safety_examine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.avicsafety.safety_examine.R;
import com.avicsafety.safety_examine.model.UserMsgBean;

import java.util.List;

public class PersonAdapter extends BaseAdapter {
    List<UserMsgBean> data;
    public Context mContext;

    public int getPosition() {
        return position;
    }

    public int position;
    public PersonAdapter(List<UserMsgBean> data, Context context) {
        this.data = data;
        mContext = context;
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
        PersonViewHolder holder=null;
        View view =null;
        if(convertView==null){
            this.position=position;
            view= LayoutInflater.from(mContext).inflate(R.layout.item_personactivity,null);
            holder=new PersonViewHolder();
            holder.tv_item_person= (TextView) view.findViewById(R.id.item_person_tv);
            view.setTag(holder);
        }else {
            view=convertView;
            holder= (PersonViewHolder) view.getTag();

        }

        holder.tv_item_person.setText(data.get(position).getName());
        return view;
    }

    class PersonViewHolder {
        TextView tv_item_person;

    }
}
