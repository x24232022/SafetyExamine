package com.avicsafety.safety_examine.yd.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.avicsafety.safety_examine.R;
import com.avicsafety.safety_examine.yd.activity.ydUtil.Response;

import java.util.List;

/**
 * Created by User on 2016/4/21.
 */
public class ResponeAdapter extends BaseAdapter {

    public class ViewHolder {
        ImageView iv_logo;
        TextView tv_theme;
        TextView tv_no;
    }

    private Context context;
    private List<Response> datas;

    public ResponeAdapter(Context context, List<Response> datas) {
        this.context = context;
        this.datas = datas;
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
        ViewHolder viewHolder = null;

        Response dt = datas.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.n_gd_list_1_item, null);

            viewHolder = new ViewHolder();

            viewHolder.iv_logo = (ImageView) convertView
                    .findViewById(R.id.iv_logo);
            viewHolder.tv_theme = (TextView) convertView
                    .findViewById(R.id.tv_theme);
            viewHolder.tv_no = (TextView) convertView
                    .findViewById(R.id.tv_no);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_no.setText(dt.getNo());
        viewHolder.tv_theme.setText(dt.getTheme());
        return convertView;
    }
}
