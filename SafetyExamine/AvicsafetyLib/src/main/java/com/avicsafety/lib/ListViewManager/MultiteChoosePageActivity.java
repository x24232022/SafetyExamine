package com.avicsafety.lib.ListViewManager;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.avicsafety.lib.R;

/**
 * 单层数据多选页面
 * 
 * @author Administrator
 * 
 */
public class MultiteChoosePageActivity extends SingleChoosePageActivity {
	private M_TwoStringArray mts;
	private List<String[]> stringArrayList;
	private boolean[] selectFlag;

	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		super.initUI();

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		super.initData();
		mts = new M_TwoStringArray();
		stringArrayList = new ArrayList<String[]>();
		selectAdapter = new MultiteChooseAdapter(activity, content);
		selectFlag = new boolean[content.length];

		for (int i = 0; i < selectFlag.length; i++) {
			selectFlag[i] = false;
		}
	}

	@Override
	protected void initEvent() {
		// TODO Auto-generated method stub
		// listView设置item适配器
		listViewManager_listview.setAdapter(selectAdapter);
		// listView设置item点击事件
		listViewManager_listview
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// 获取CheckBox控件
						LinearLayout ll = (LinearLayout) view;
						CheckBox cb = (CheckBox) ll.getChildAt(0);
						// 选中的CheckBox设置选中属性
						if (cb.isChecked()) {
							cb.setChecked(false);
							selectFlag[position] = false;
						} else {
							cb.setChecked(true);
							selectFlag[position] = true;

						}
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO onCreateOptionsMenu方法
		getMenuInflater().inflate(R.menu.select_page_activity_menu, menu);
		MenuItem action_save = menu
				.findItem(R.id.select_page_activity_menu_save);
		action_save.setVisible(true);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO onOptionsItemSelected方法

		if (item.getItemId() == android.R.id.home) {
			finish();
		} else if (item.getItemId() == R.id.select_page_activity_menu_save) {
			// 右上角保存按钮的点击事件
			// 在stringArrayList中添加已经选择的数据
			for (int i = 0; i < selectFlag.length; i++) {
				if (selectFlag[i])
					stringArrayList.add(content[i]);

			}

			mts.setStringArrayList(stringArrayList);
			Intent intent = new Intent();
			intent.putExtra("resultData", mts);
			setResult(RESULT_OK, intent);
			finish();
		}

		return super.onOptionsItemSelected(item);
	}
}
