package com.avicsafety.lib.ListViewManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.avicsafety.lib.R;

public class MultiteChooseAdapter extends BaseAdapter {
	private String[][] selectData;// Fragment要显示的内容集合
	private LayoutInflater inflater;// 布局容器
	private Context context;// 上下文

	public MultiteChooseAdapter(Context _context, String[][] _selectData) {
		context = _context;
		selectData = _selectData;
		inflater = LayoutInflater.from(context);// 获取布局容器
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		ViewHolder view;
		// 判断布局页面 ，如果为空就新建，
		if (convertView == null) {
			// 获取适配器页面
			convertView = inflater.inflate(
					R.layout.com_avicsafety_lib_multite_choose_listview_adapter,
					null);
			// 新建ViewHolder
			view = new ViewHolder();
			view.single_choose_radio = (CheckBox) convertView
					.findViewById(R.id.single_choose_radio);
			view.single_choose_radio
					.setText(selectData[position][1]);

			convertView.setTag(view);
		} else {
			view = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return selectData.length;
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
		public CheckBox single_choose_radio;

	}
}
