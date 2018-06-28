package com.avicsafety.safety_examine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avicsafety.safety_examine.R;
import com.avicsafety.safety_examine.model.ProspectBean;
import com.avicsafety.safety_examine.prospect.ProspectListActivity;
import com.avicsafety.safety_examine.xfd.Rwlb;

import java.util.List;

/**
 * Created by Administrator on 2018/3/23.
 */

public class PreviewAdapter extends RecyclerView.Adapter<PreviewAdapter.MyViewHolder>  {

    private Context context;
    private List<Rwlb.ResponseBean> datas ;




    public List<Rwlb.ResponseBean> getDatas(){
        return datas;
    }



    public PreviewAdapter(Context context,List<Rwlb.ResponseBean> datas){
        this.context = context;
        this.datas = datas;
    }


    @Override
    public PreviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.n_gd_list_xfd_item,parent,false);

        return new PreviewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_title.setText(datas.get(position).getSitename());
        holder.tv_number.setText(datas.get(position).getExpectedstationtime());
    }



    @Override
    public int getItemCount() {
        return datas.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_number;
        TextView tv_title;
        public MyViewHolder(final View itemView) {
            super(itemView);
            tv_number= (TextView) itemView.findViewById(R.id.tv_number);
            tv_title= (TextView) itemView.findViewById(R.id.tv_theme1_gd);



        }
    }






//    private List<Rwlb.ResponseBean> mList;
//    private Context mContext;
//
//
//    public PreviewAdapter(List<Rwlb.ResponseBean> list, Context context) {
//        mList = list;
//        mContext = context;
//
//    }
//
//    @Override
//    public int getCount() {
//
//        return mList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return mList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View view = null;
//        MyViewHolder myViewHolder = null;
//        if (convertView == null) {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.n_gd_list_xfd_item,parent,false);
//            myViewHolder = new MyViewHolder();
//            myViewHolder.tv_number = (TextView) view.findViewById(R.id.tv_name);
//            myViewHolder.tv_title = (TextView) view.findViewById(R.id.tv_theme1_gd);
//            view.setTag(myViewHolder);
//        } else {
//            view = convertView;
//            myViewHolder = (MyViewHolder) view.getTag();
//        }
//
//        myViewHolder.tv_title.setText(mList.get(position).getSitename());
////        myViewHolder.tv_number.setText(mList.get(position).getExpectedstationtime());
//
//
//        return view;
//    }
//
//
//    public class MyViewHolder {
//        TextView tv_number;
//        TextView tv_title;
//
//    }



}
