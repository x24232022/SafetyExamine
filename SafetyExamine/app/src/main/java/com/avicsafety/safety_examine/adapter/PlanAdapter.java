package com.avicsafety.safety_examine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avicsafety.safety_examine.R;
import com.avicsafety.safety_examine.activity.TowerMainActivity;
import com.avicsafety.safety_examine.xfd.Rwlb;

import java.util.List;

/**
 * Created by 刘畅 on 2018/2/9.
 */

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.MyViewHolder> {

    private Context context;
    private List<Rwlb.ResponseBean> datas ;
    private int listType;
    private int mImgid=R.drawable.jh_w_wgj;

    private int[] img_plan_unreceived={R.drawable.jh_w_wgj,R.drawable.jh_w_tfgj,R.drawable.jh_w_jlgj,R.drawable.jh_w_qtgj};
    private int[] img_plan_accepted={R.drawable.jh_yj_wgj,R.drawable.jh_yj_tfgj,R.drawable.jh_yj_jlgj,R.drawable.jh_yj_qtgj};
    private int[] img_plan_HavePower={R.drawable.jh_yf_wgj,R.drawable.jh_yf_tfgj,R.drawable.jh_yf_jlgj,R.drawable.jh_yf_qtgj};
    private int[] img_fault_unreceived={R.drawable.gz_w_wgj,R.drawable.gz_w_tfgj,R.drawable.gz_w_jlgj,R.drawable.gz_w_qtgj};
    private int[] img_fault_accepted={R.drawable.gz_yj_wgj,R.drawable.gz_yj_tfgj,R.drawable.gz_yj_jlgj,R.drawable.gz_yj_qtgj};
    private int[] img_fault_HavePower={R.drawable.gz_yf_wgj,R.drawable.gz_yf_tfgj,R.drawable.gz_yf_jlgj,R.drawable.gz_yf_qtgj};
    private int[] img_complete={R.drawable.jh_ywc,R.drawable.gz_ywc};


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
        if(datas.get(position).getAlarmInformation()==null) {
            holder.iv_sign.setBackgroundResource(mImgid);
//        holder.tv_sign.setText(datas.get(position).getOutagetype());
        }else {
            holder.iv_sign.setBackgroundResource(giveAnAlarmSelect(datas.get(position).getOutagetype(), datas.get(position).getAlarmInformation()));
        }
        if(listType==3) {
            holder.tv_state.setVisibility(View.VISIBLE);
            holder.tv_state.setText(datas.get(position).getState());
        }
    }



    @Override
    public int getItemCount() {
        return datas.size();
    }


    private int giveAnAlarmSelect(String outAgeType,String giveAnAlarm ){

        if(outAgeType.equals("计划停电")){
            switch (listType){
                case 0://未接工单
                    switch (giveAnAlarm){
                        case "无告警":
                            mImgid =img_plan_unreceived[0];
                            break;
                        case "站址退服":
                            mImgid =img_plan_unreceived[1];
                            break;
                        case "交流告警":
                            mImgid =img_plan_unreceived[2];
                            break;
                        case "其他告警":
                            mImgid =img_plan_unreceived[3];
                            break;
                        default:
                            mImgid =img_plan_unreceived[0];
                            break;
                    }


//                    if(giveAnAlarm.equals("无告警")||giveAnAlarm.equals(null)){
//                        mImgid=img_plan_unreceived[0];
//                    }else if(giveAnAlarm.equals("退服告警")){
//                        mImgid=img_plan_unreceived[1];
//                    }else if(giveAnAlarm.equals("交流告警")){
//                        mImgid=img_plan_unreceived[2];
//                    }else if(giveAnAlarm.equals("其他告警")) {
//                        mImgid=img_plan_unreceived[3];
//                    }else {
//                        mImgid=img_plan_unreceived[0];
//                    }
                    break;
                case 1://已接工单

                    switch (giveAnAlarm){
                        case "无告警":
                            mImgid =img_plan_accepted[0];
                            break;
                        case "站址退服":
                            mImgid =img_plan_accepted[1];
                            break;
                        case "交流告警":
                            mImgid =img_plan_accepted[2];
                            break;
                        case "其他告警":
                            mImgid =img_plan_accepted[3];
                            break;
                        default:
                            break;
                    }
                    break;
                case 2://发电中工单
                    switch (giveAnAlarm){
                        case "无告警":
                            mImgid =img_plan_HavePower[0];
                            break;
                        case "站址退服":
                            mImgid =img_plan_HavePower[1];
                            break;
                        case "交流告警":
                            mImgid =img_plan_HavePower[2];
                            break;
                        case "其他告警":
                            mImgid =img_plan_HavePower[3];
                            break;
                        default:
                            break;
                    }
                    break;
                case 3://已完成工单
                    mImgid =img_complete[0];
                    break;

                default:
                    mImgid =img_plan_unreceived[0];

                    break;
            }
        }else if(outAgeType.equals("故障停电")){
            switch (listType){
                case 0:
                    switch (giveAnAlarm){
                        case "无告警":
                            mImgid =img_fault_unreceived[0];
                            break;
                        case "站址退服":
                            mImgid =img_fault_unreceived[1];
                            break;
                        case "交流告警":
                            mImgid =img_fault_unreceived[2];
                            break;
                        case "其他告警":
                            mImgid =img_fault_unreceived[3];
                            break;
                        default:
                            break;
                    }
                    break;
                case 1:
                    switch (giveAnAlarm){
                        case "无告警":
                            mImgid =img_fault_accepted[0];
                            break;
                        case "站址退服":
                            mImgid =img_fault_accepted[1];
                            break;
                        case "交流告警":
                            mImgid =img_fault_accepted[2];
                            break;
                        case "其他告警":
                            mImgid =img_fault_accepted[3];
                            break;
                        default:
                            break;
                    }
                    break;
                case 2:
                    switch (giveAnAlarm){
                        case "无告警":
                            mImgid =img_fault_HavePower[0];
                            break;
                        case "站址退服":
                            mImgid =img_fault_HavePower[1];
                            break;
                        case "交流告警":
                            mImgid =img_fault_HavePower[2];
                            break;
                        case "其他告警":
                            mImgid =img_fault_HavePower[3];
                            break;
                        default:
                            break;
                    }
                    break;
                case 3:
                    mImgid =img_complete[1];
                    break;

                default:
                    mImgid =img_fault_unreceived[0];
                    break;
            }
        }
        return mImgid;
    }


   public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView tv_number;
        TextView tv_title;
        ImageView iv_sign;
       // TextView tv_sign;
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
           iv_sign= (ImageView) itemView.findViewById(R.id.iv_sign);


       }
   }


}
