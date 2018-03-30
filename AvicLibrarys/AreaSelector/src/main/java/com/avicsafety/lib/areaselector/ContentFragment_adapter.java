package com.avicsafety.lib.areaselector;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContentFragment_adapter extends BaseAdapter {
	private List<M_Street> dataList;// Fragment要显示的内容集合
	private LayoutInflater inflater;// 布局容器
	private Context context;// 上下文
	private List<Integer> selectItem;// 选中的哪项的标示集合（以备以后做多选，所以这里用集合）

	public ContentFragment_adapter(List<M_Street> _dataList, Context _context,
			List<Integer> _selectItem) {
		context = _context;
		dataList = _dataList;
		selectItem = _selectItem;
		inflater = LayoutInflater.from(context);// 获取布局容器
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		ViewHolder view;
		// 判断布局页面 ，如果为空就新建，
		// if (convertView == null) {
		// 获取适配器页面
		convertView = inflater.inflate(R.layout.com_avicsafety_lib_treemanager_listview_adapter, null);
		// 新建ViewHolder
		view = new ViewHolder();
		view.name = (TextView) convertView.findViewById(R.id.TextView);
		view.name.setText(dataList.get(position).getJdmc());
		view.ic_icon = (ImageView) convertView.findViewById(R.id.ImageView1);

		// 选中的时候，设置不同的UI
		for (int i = 0; i < selectItem.size(); i++) {

			if (selectItem.get(i) == position) {
				// 设置选中时为红色，并显示选中图标
				view.name.setTextColor(Color.RED);
				view.ic_icon.setVisibility(View.VISIBLE);
			}
		}
		convertView.setTag(view);
		// } else {
		// view = (ViewHolder) convertView.getTag();
		// }

		return convertView;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dataList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	class ViewHolder {
		public ImageView ic_icon;
		public TextView name;

	}
}
