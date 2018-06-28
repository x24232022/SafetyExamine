package com.avicsafety.safety_examine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.avicsafety.safety_examine.R;

import java.util.List;
import java.util.Map;

public class GridViewAdapter extends BaseAdapter implements ListAdapter {
	
	private List<Map<String,Object>> mList;
	private Context mContex;
	
	public GridViewAdapter(Context context, List<Map<String,Object>> list) {
		this.mContex = context;
		this.mList = list;
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
		ViewHolder viewHolder;
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView = View.inflate(mContex, R.layout.item_home, null);
			viewHolder.image = (ImageView) convertView.findViewById(R.id.imageView3);
			viewHolder.title = (TextView) convertView.findViewById(R.id.textView4);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		Map<String,Object> map = mList.get(position);
		viewHolder.image.setImageResource((int) map.get("images"));
		viewHolder.title.setText((String) map.get("titles"));
		return convertView;
	}
	
	class ViewHolder{
		ImageView image;
		TextView title;
	}
}
