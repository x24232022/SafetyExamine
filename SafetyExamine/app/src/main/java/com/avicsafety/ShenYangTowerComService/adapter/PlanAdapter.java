package com.avicsafety.ShenYangTowerComService.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avicsafety.ShenYangTowerComService.R;
import com.avicsafety.ShenYangTowerComService.activity.TowerMainActivity;
import com.avicsafety.ShenYangTowerComService.xfd.Rwlb;

import java.util.List;

/**
 * Created by 刘畅 on 2018/2/9.
 */

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.MyViewHolder> {

    private Context context;
    private List<Rwlb.ResponseBean> datas ;
    private int listType;

    public List<Rwlb.ResponseBean> getDatas(){
        return datas;
    }


    public PlanAdapter(Context context, List<Rwlb.ResponseBean> datas, int listType) {
        this.context = context;
        this.datas = datas;
        this.listType = listType;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.n_gd_list_xfd_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_title.setText(datas.get(position).getSitename());
        holder.tv_number.setText(datas.get(position).getExpectedstationtime());
     //   holder.tv_sign.setBackgroundResource(giveAnAlarmSelect(datas.get(position).getOutagetype(),datas.get(position).getAlarmInformation()));
        holder.tv_sign.setText(datas.get(position).getOutagetype());

        if(listType==3) {
            holder.tv_state.setVisibility(View.VISIBLE);
            holder.tv_state.setText(datas.get(position).getState());
        }
    }



    @Override
    public int getItemCount() {
        return datas.size();
    }



   public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView tv_number;
        TextView tv_title;
       // ImageView tv_sign;
        TextView tv_sign;
        TextView tv_state;
       public LinearLayout ll_item,ll_hidden;
       public MyViewHolder(final View itemView) {
           super(itemView);
           if(context.getClass()!= TowerMainActivity.class){
               ll_item= (LinearLayout) itemView.findViewById(R.id.n_gd_list_xfd_item);
               ll_hidden= (LinearLayout) itemView.findViewById(R.id.ll_hidden);
           }

           if(listType==3) {
               tv_state = (TextView) itemView.findViewById(R.id.tv_state);
           }
           tv_number= (TextView) itemView.findViewById(R.id.tv_number);
           tv_title= (TextView) itemView.findViewById(R.id.tv_theme1_gd);
           tv_sign= (TextView) itemView.findViewById(R.id.tv_sign);


       }
   }


}
