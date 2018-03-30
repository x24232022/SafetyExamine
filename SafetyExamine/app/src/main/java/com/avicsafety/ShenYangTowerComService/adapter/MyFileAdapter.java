package com.avicsafety.ShenYangTowerComService.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avicsafety.ShenYangTowerComService.R;
import com.avicsafety.ShenYangTowerComService.activity.FileScActivity;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by 刘畅 on 2017/11/22.
 */

public class MyFileAdapter extends BaseAdapter {

    public MyFileAdapter(Context context, List<String> data,List<String> list, int resource) {
        this.data = data;
        this.list = list;
        this.context = context;
        this.resource = resource;
    }
    private List<String> list;
    private List<String> data;
    private Context context;
    private int resource;

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
    public View getView(int position, View converView, ViewGroup parent) {

        Util util = null;
        View view = null;

        if (converView == null) {
            util = new Util();

            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.activity_file_sc_item, null);
//            util.imageView = (ImageView) view.findViewById(R.id.file_list_item_img);
            util.tv_title = (TextView) view.findViewById(R.id.file_list_item_tx1);
            util.tv_dizhi = (TextView) view.findViewById(R.id.file_list_item_tx2);
            view.setTag(util);
        } else {
            view = converView;
            util = (Util) view.getTag();
        }

        util.tv_title.setText(data.get(position).toString());
        util.tv_dizhi.setText(list.get(position).toString());
//        if (data.get(position).isDirectory()){
//            util.imageView.setImageResource(R.drawable.listimgview);
//        }



        return view;
    }
}

class Util {
//    ImageView imageView;
    TextView tv_title,tv_dizhi;

}
