package com.avicsafety.lib.ListViewManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.avicsafety.lib.R;


/**
 * 单层数据单选页面
 * 
 * @author Administrator
 * 
 */
public class SingleChoosePageActivity extends Activity {

	protected Activity activity;
	protected ListView listViewManager_listview;// listView对象
	protected TextView textView;// 标题view对象
	protected String[][] content;// 传过来的参数，该数据用来显示页面（必须有）
	// protected String className;// 传过来的参数，用于选择完成时，返回时设置的class（必须） 注：这个要全路径的类名称
	protected String titleName;// 传过来的参数，用于设置标题内容（必须有）
	protected BaseAdapter selectAdapter;// listView适配器
	private OnRemoteDataLoadFinishListener remoteDataLoadFinishListener;
	private ProgressDialog progressDialog;
	private String text_key = "text";
	private String value_key = "value";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO onCreate方法
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		setContentView(R.layout.com_avicsafety_lib_listviewmanager_base_select_page);
		activity = this;
		// 初始化页面控件
		initUI();
		// 初始化数据
		initData();
		// 初始化控件的事件
		initEvent();
	}

	protected void initEvent() {
		// TODO initEvent方法
		// listView设置item点击事件
		listViewManager_listview
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// 获取RadioButton控件
						LinearLayout ll = (LinearLayout) view;
						RadioButton rb = (RadioButton) ll.getChildAt(0);
						// 选中的RadioButton设置选中属性
						if (rb.isChecked()) {
							rb.setChecked(false);
						} else {
							rb.setChecked(true);
						}
						// 因是单选，所以点击Item后，选择完成，返回上级页面
						M_TwoStringArray mts = new M_TwoStringArray();
						List<String[]> stringArrayList = new ArrayList<String[]>();

						stringArrayList.add(content[position]);

						mts.setStringArrayList(stringArrayList);

						Intent intent = new Intent();
						intent.putExtra("value", stringArrayList.get(0)[0]);
						intent.putExtra("text", stringArrayList.get(0)[1]);
						intent.putExtra("resultData", mts);
						setResult(RESULT_OK, intent);
						finish();
					}
				});
	}

	protected void initData() {
		// TODO initData方法
		// 获取传过来的参数
		Intent intent = getIntent();
		titleName = intent.getStringExtra("titleName");
		String DataType = intent.getStringExtra("DataType");
		if(DataType!=null&&DataType.equals("Remote")){
			progressDialog.show();
			String DataUrl = intent.getStringExtra("DataUrl");
			if(intent.getStringExtra("text_key")!=null){
				text_key = intent.getStringExtra("text_key");
			}
			if(intent.getStringExtra("value_key")!=null){
				value_key = intent.getStringExtra("value_key");
			}
			this.setOnRemoteDataLoadFinishListener(new OnRemoteDataLoadFinishListener(){
				@Override
				public void success() {
					selectAdapter = new SingleChooseAdapter(activity, content);
					listViewManager_listview.setAdapter(selectAdapter);
					progressDialog.dismiss();
				}
				@Override
				public void failure() {
					Toast.makeText(activity,"连接服务器出错,请稍后再试!",Toast.LENGTH_LONG).show();
					progressDialog.dismiss();
				}
			});
			getRemoteData(DataUrl);
		}else{
			M_TwoStringArray mts = (M_TwoStringArray) intent.getSerializableExtra("param");
			if(mts.getTwoStringArray()!=null)
				content = mts.getTwoStringArray();
			if(mts.getStringArrayList()!=null){
				List<String[]> list = mts.getStringArrayList();
				content = new String[list.size()][2];
				for(int i=0;i<list.size();i++){
					content[i]=list.get(i);
				}
			}
			selectAdapter = new SingleChooseAdapter(activity, content);
			// listView设置item适配器
			listViewManager_listview.setAdapter(selectAdapter);
		}
		// 设置标题内容
		if (titleName != null) {
			textView.setText(titleName);
		}
	}

	

	protected void initUI() {
		// TODO initData方法
		// 设置actionBar控件

		getOverflowMenu(activity);
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(R.string.back);
		actionBar.setDisplayHomeAsUpEnabled(true); // 决定左上角图标的右侧是否有向左的小箭头,
		actionBar.setDisplayShowHomeEnabled(false);
		// 初始化控件
		textView = (TextView) findViewById(R.id.textView);
		listViewManager_listview = (ListView) findViewById(R.id.listViewManager_listview);
		
		progressDialog = new ProgressDialog(this);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setTitle("提示");
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(true);
		progressDialog.setMessage("正在与服务器交互...");
		//progressDialog.create();
		
	}

	protected Handler handler = new Handler() {
		// TODO Handler
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO onCreateOptionsMenu方法
		getMenuInflater().inflate(R.menu.select_page_activity_menu, menu);
		MenuItem action_save = menu
				.findItem(R.id.select_page_activity_menu_save);
		action_save.setVisible(false);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO onOptionsItemSelected方法
		switch (item.getItemId()) {
		case android.R.id.home:
			// 左上角返回按钮的点击事件
			finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void getRemoteData(String dataUrl) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams(dataUrl);
		params.setConnectTimeout(45000);
		x.http().get(params, new Callback.CommonCallback<String>() {
			@Override
			public void onCancelled(CancelledException arg0) {}
			@Override
			public void onError(Throwable arg0, boolean arg1) {
				if(remoteDataLoadFinishListener!=null)
					remoteDataLoadFinishListener.failure();
			}
				
			@Override
			public void onFinished() {}

			@Override
			public void onSuccess(String result) {
				JSONObject res;
				try {
					res = new JSONObject(result);
					String code = res.getString("Code");
					if(code.equals("200")){
						JSONArray datas = res.getJSONArray("Response");
						if(datas==null||datas.length()==0){
							if(remoteDataLoadFinishListener!=null){
								remoteDataLoadFinishListener.failure();
							}
		    			}else{
		    				content = new String[datas.length()][2];
		    				for(int i=0; i<datas.length(); i++){
		    					
		    					JSONObject data = datas.getJSONObject(i);
		    					String text = data.getString(text_key);
		    					String value = data.getString(value_key);
		    					content[i][0]=value;
		    					content[i][1]=text;
		    				}
		    				if(remoteDataLoadFinishListener!=null)
		    					remoteDataLoadFinishListener.success();
		    			}
					}else{
						if(remoteDataLoadFinishListener!=null)
							remoteDataLoadFinishListener.failure();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					if(remoteDataLoadFinishListener!=null)
						remoteDataLoadFinishListener.failure();
				}
			}
		});
	}

	private void setOnRemoteDataLoadFinishListener(
			OnRemoteDataLoadFinishListener _onRemoteDataLoadFinish) {
		// TODO Auto-generated method stub
		remoteDataLoadFinishListener = _onRemoteDataLoadFinish;
	}
	
	public interface OnRemoteDataLoadFinishListener {
		void success();
		void failure();
	}

	public static void getOverflowMenu(Activity oThis) {
		ViewConfiguration viewConfig = ViewConfiguration.get(oThis);

		try {
			Field overflowMenuField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			if (null != overflowMenuField) {
				overflowMenuField.setAccessible(true);
				overflowMenuField.setBoolean(viewConfig, false);
			}
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
