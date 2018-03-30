package com.avicsafety.lib.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import com.avicsafety.lib.MultilLevelTreeList.Node;
import com.avicsafety.lib.MultilLevelTreeList.TreeRecyclerAdapter;
import com.avicsafety.lib.R;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class AvicTreeRecyclerAdapter extends TreeRecyclerAdapter {

    private boolean isShowCheckBox = true;

    public void setShowCheckBox(boolean showCheckBox) {
        isShowCheckBox = showCheckBox;
    }

    public AvicTreeRecyclerAdapter(RecyclerView mTree, Context context, List<Node> datas, int defaultExpandLevel, int iconExpand, int iconNoExpand) {

        super(mTree, context, datas, defaultExpandLevel, iconExpand, iconNoExpand);

    }



    public AvicTreeRecyclerAdapter(RecyclerView mTree, Context context, List<Node> datas, int defaultExpandLevel) {

        super(mTree, context, datas, defaultExpandLevel);

    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyHoder(View.inflate(mContext, R.layout.list_item,null));

    }



    @Override
    public void onBindViewHolder(final Node node, RecyclerView.ViewHolder holder, int position) {

        final MyHoder viewHolder = (MyHoder) holder;

        if(isShowCheckBox){
            viewHolder.cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setChecked(node,viewHolder.cb.isChecked());
                }
            });
            if (node.isChecked()){
                viewHolder.cb.setChecked(true);
            }else {
                viewHolder.cb.setChecked(false);
            }
        }else{
            viewHolder.cb.setVisibility(View.GONE);
        }

        if (node.getIcon() == -1) {
            viewHolder.icon.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.icon.setVisibility(View.VISIBLE);
            viewHolder.icon.setImageResource(node.getIcon());
        }

        viewHolder.label.setText(node.getName());


    }

    class MyHoder extends RecyclerView.ViewHolder{

        public CheckBox cb;

        public TextView label;

        public ImageView icon;
        public MyHoder(View itemView) {
            super(itemView);

            cb = (CheckBox) itemView
                    .findViewById(R.id.cb_select_tree);
            label = (TextView) itemView
                    .findViewById(R.id.id_treenode_label);
            icon = (ImageView) itemView.findViewById(R.id.icon);

        }

    }
}