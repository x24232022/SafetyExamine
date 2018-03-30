package com.avicsafety.lib.areaselector;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContentFragment extends Fragment {
	private Activity content;
	private List<M_Street> dataList;// Fragment要显示的内容集合
	private ContentFragment_adapter cf_adapter;// Fragment内容中的适配器
	private Handler handler;// 接受从activity中传过来的Handler，用来发送消息，与activity通信
	private List<Integer> selectItem = new ArrayList<Integer>();// 选中的哪项的标示集合（以备以后做多选，所以这里用集合）



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 获取Fragment页面
		View contextView = inflater.inflate(
				R.layout.com_avicsafety_lib_treemanager_fragment_item,
				container, false);
		// 获取Fragment中的ListView
		content = getActivity();
		ListView tree_list_view = (ListView) contextView
				.findViewById(R.id.tree_list_view);
		// 制作ListView内容的适配器
		cf_adapter = new ContentFragment_adapter(dataList, content, selectItem);
		tree_list_view.setAdapter(cf_adapter);
		// 设置ListView中，数据项的点击事件
		tree_list_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 清除之前保存选中项，设置新的选中项
				selectItem.clear();
				selectItem.add(position);
				// 获取选中检查项的内容
				// 注：这里暂时获取内容，根据业务获取更多数据
				// LinearLayout vp = (LinearLayout) parent.getChildAt(position);
				LinearLayout vp = (LinearLayout) view;
				LinearLayout vp1 = (LinearLayout) vp.getChildAt(0);
				TextView tv = (TextView) vp1.getChildAt(2);
				String param = tv.getText().toString();
				// 发送消息
				Message message = new Message();
				Bundle data = new Bundle();
				data.putString("NewTitle", param);
				data.putSerializable("M_Street", dataList.get(position));
				message.setData(data);
				handler.sendMessage(message);
			}
		});
		return contextView;
	}

	public List<M_Street> getDataList() {
		return dataList;
	}

	public void setDataList(List<M_Street> dataList) {
		this.dataList = dataList;
	}

	public List<Integer> getSelectItem() {
		return selectItem;
	}

	public void setSelectItem(List<Integer> selectItem) {
		this.selectItem = selectItem;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	public void setData(List<M_Street> _dataList) {
		dataList = _dataList;
	}

	public void sethandler(Handler _handler) {
		handler = _handler;
	}
}
