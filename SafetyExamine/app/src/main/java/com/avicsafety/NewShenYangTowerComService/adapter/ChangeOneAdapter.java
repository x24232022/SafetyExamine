package com.avicsafety.NewShenYangTowerComService.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avicsafety.NewShenYangTowerComService.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/23.
 */

public class ChangeOneAdapter extends RecyclerView.Adapter<ChangeOneAdapter.MyViewHolder> {
    private OnRecyclerviewItemClickListener listener;
    private List<Map<String, ImageView>> datas;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ChangeOneAdapter(List<Map<String, ImageView>> datas, Context mContext) {
        this.datas = datas;
        this.mContext = mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mLayoutInflater.inflate(R.layout.activity_changeone_item, parent, false));

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        LinkedHashMap<String, ImageView> map = (LinkedHashMap<String, ImageView>) datas.get(position);

        List<String> titlelist = new ArrayList<>();
        List<ImageView> imgList = new ArrayList<>();
        for (ImageView object : map.values()) {
            ImageView img = (ImageView) object;
            imgList.add(img);
        }
        for (Object str : map.keySet()) {
            titlelist.add(str.toString());

        }

        holder.tv_title.setText(titlelist.get(position));
        Glide.with(mContext).load(imgList.get(position)).into(holder.iv_img);


    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        ImageView iv_img;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title__activity_changeOne);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_icon_activity_changeOne);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view, getLayoutPosition());
                }
            });
        }

    }

    public void setOnRecyclerviewItemClickListener(OnRecyclerviewItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnRecyclerviewItemClickListener {
        void onItemClick(View v, int position);
    }


}
