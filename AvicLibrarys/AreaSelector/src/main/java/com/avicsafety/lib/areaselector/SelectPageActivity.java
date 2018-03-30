package com.avicsafety.lib.areaselector;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * 区域选择的主页面
 * 
 * @author Administrator
 * 
 */
public class SelectPageActivity extends FragmentActivity {
	private Activity activity;
	private DbManager dbs;// 数据库db

	private TabPageIndicator indicator;// ViewPager指示器对象
	private ViewPager pager;// ViewPager对象
	private FragmentPagerAdapter adapter;// ViewPager的adapter对象，里面为fragment

	private TextView tv_title;// 标题view对象

	private List<String> titles = new ArrayList<String>();// ViewPager指示器的数据，如；"辽宁省","沈阳市","皇姑区"……
	List<M_Street> list = new ArrayList<M_Street>();// ViewPager中某个fragment显示的内容
	private int posion;// 当前ViewPager指示器，子节点显示内容的位置
	private int count = 0;// 记录当前有几层

	private int end;// 传过来的参数，设置显示多少层用的（可有可无）
	private String headline;// 传过来的参数，用于设置标题内容（可有可无）
	private String className;// 传过来的参数，用于选择完成时，返回时设置的class（必须） 注：这个要全路径的类名称
	private String areaDbpath;// 传过来的参数，用于设置数据库的路径


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 设置没有Bar
		//requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		setContentView(R.layout.com_avicsafety_lib_select_page);
		activity = this;



//		getOverflowMenu(activity);
//
//		ActionBar actionBar = getActionBar();
//		actionBar.setTitle("返回");
//		actionBar.setDisplayHomeAsUpEnabled(true); // 决定左上角图标的右侧是否有向左的小箭头, true
//		actionBar.setDisplayShowHomeEnabled(false);

		// top_btn_left = (TextView) findViewById(R.id.top_btn_left);
		tv_title = (TextView) findViewById(R.id.tv_title);
		TextView tv_back = (TextView) this.findViewById(R.id.tv_back);
		tv_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
		// 获取传过来的参数
		Intent intent = getIntent();
		// 获取传过来的参数，用来设置开始位置的区域名称
		String startPoision = intent.getStringExtra("startPoision");
		end = intent.getIntExtra("end", 100);
		headline = intent.getStringExtra("headline");
		//className = intent.getStringExtra("className");
		areaDbpath = intent.getStringExtra("areaDbpath");
		// 设置标题内容
		if (headline != null) {
			tv_title.setText(headline);
		}
		// 初始化数据
		initData(startPoision, end);
		// 初始化ViewPager指示器，ViewPager，ViewPager的adapter（fragment）
		init();

	}

	/**
	 * 这个handler是头部指示器和对应的fragment刷新时用的， activity和fragment之间通信使用
	 */
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// 获取message中的Bundle对象
			Bundle param = msg.getData();
			// 获取Bundle中NewTitle的值，用于刷新ViewPager指示器子节点的内容
			String NewTitle = param.getString("NewTitle");

			// ViewPager指示器添加新的子节点
			if (posion == titles.size() - 1) {
				/*
				 * 更新ViewPager指示器 这里的操作： 1.是修改原有的子节点的名称 2.添加新的子节点
				 * 实现原理：更新titles中的数据，之后会自动刷新页面
				 */
				titles.set(posion, NewTitle);
				count += 1;
				// 获取ViewPaper中显示的数据
				list.clear();
				M_Street ms = (M_Street) param.getSerializable("M_Street");
				try {
					// 获取下一级的要显示的内容
					list = dbs.selector(M_Street.class)
							.where("parentId", "=", ms.getId()).orderBy("id")
							.findAll();
					// 这个判断用于设置层数时用的
					if (count < end) {
						// list.size() > 0表示没有下一级了，已经到了最后一层
						if (list.size() > 0) {
							// 有下一级要在ViewPager指示器新增一个子节点
							titles.add("请选择");
						} else {
							// 没有下一级，选择完成，返回上一级页面
							Intent intent = new Intent();
							intent.putExtra("qyszd", ms.getQyszd());
							intent.putExtra("qyszdId", ms.getQyszdId());
							setResult(RESULT_OK, intent);
							finish();
						}
					} else {
						// 由于设置了显示的层数，所以到了设置的最后一层就，返回上一级页面
						Intent intent = new Intent();
						intent.putExtra("qyszd", ms.getQyszd());
						intent.putExtra("qyszdId", ms.getQyszdId());
						setResult(RESULT_OK, intent);
						finish();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {

			}
			// 刷新页面
			adapter.notifyDataSetChanged();
			indicator.notifyDataSetChanged();
			if (list.size() > 0 && count < end)
				showTitle(posion);
			// 设置当前显示的块页面，设置当前的ViewPager指示器和ViewPaper
			indicator.setCurrentItem(posion + 1);

		}
	};

	private void initData(String startPoision, int end) {
		// 初始化，在ViewPager指示器添加一个子节点
		titles.add("请选择");
		String enname = "area.db";

		try {
			// 判断数据库路径是否存在
			doCheckDbFile(this, areaDbpath, enname);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 初始化db对象

		dbs = x.getDb(DbConfig.getDbConfig(enname,areaDbpath));
		try {
			// startPoision为空表示没有该参数，默认显示全部的区域
			if (null == startPoision) {
				list = dbs.selector(M_Street.class)
						.where("parentId", "=", "210000000000").orderBy("id")
						.findAll();

			} else {
				// startPoision不为空表示设置了开始的位置，就显示startPoision下级的区域
				M_Street ms = dbs.selector(M_Street.class)
						.where("jdmc", "=", startPoision).orderBy("id")
						.findFirst();
				// 判断有没有改区域
				if (ms != null && ms.getId() != null) {
					list = dbs.selector(M_Street.class)
							.where("parentId", "=", ms.getId()).orderBy("id")
							.findAll();
				}
			}
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	private void init() {
		// ViewPager的adapter
		adapter = new TabPageIndicatorAdapter(getSupportFragmentManager());
		// ViewPager
		pager = (ViewPager) findViewById(R.id.pager);
		// ViewPager和ViewPager的adapter关联
		pager.setAdapter(adapter);

		// 实例化TabPageIndicator然后设置ViewPager与之关联
		indicator = (TabPageIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(pager);

		// 如果我们要对ViewPager设置监听，用indicator设置就行了
		indicator.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// 获取当前位置的poision值
				posion = arg0;
				count = arg0;
				// 向左滑动或点击已有的非最后一个ViewPager指示器节点，要隐藏后面的fragment和ViewPager指示器节点
				hideTitle();
				// 刷新页面
				adapter.notifyDataSetChanged();
				indicator.notifyDataSetChanged();
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.select_page_activity_menu, menu);
		// MenuItem save = menu.findItem(R.id.action_save);
		MenuItem action_save = menu
				.findItem(R.id.select_page_activity_menu_save);
		action_save.setVisible(false);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * ViewPager适配器内部类
	 * 
	 */
	class TabPageIndicatorAdapter extends FragmentPagerAdapter {
		public TabPageIndicatorAdapter(FragmentManager fm) {
			super(fm);
		}

		// 新建fragment页面
		@Override
		public Fragment getItem(int position) {
			// 新建一个Fragment来展示ViewPager 各个页面的内容，并传递参数
			List<M_Street> listData = new ArrayList<M_Street>();
			listData.clear();
			listData.addAll(list);
			ContentFragment fragment = new ContentFragment();
			fragment.setData(listData);
			fragment.sethandler(handler);
			Bundle args = new Bundle();
			args.putString("arg", titles.get(position));
			fragment.setArguments(args);
			return fragment;
		}

		// 获取当前的ViewPager指示器节点
		@Override
		public CharSequence getPageTitle(int position) {
			return titles.get(position % titles.size());
		}

		// 获取ViewPager指示器节点的数量
		@Override
		public int getCount() {
			return titles.size();
		}

		// 获取当前的ViewPager中的某一个Fraghment，这里PagerAdapter.POSITION_NONE表示每次要新建fragment
		@Override
		public int getItemPosition(Object object) {
			// TODO Auto-generated method stub
			return PagerAdapter.POSITION_NONE;
		}
	}

	// 隐藏指定的fragment和ViewPager指示器节点
	private void hideTitle() {
		// 以titles为准，依次设置
		for (int i = titles.size() - 1; i >= 0; i--) {
			// 隐藏到当前的位置时，结束
			if (i == posion) {
				break;
			}
			// 获取fragment管理器，这里是用的android.support.v4.app.FragmentManager;
			FragmentManager fm = getSupportFragmentManager();
			// 开始Fragment事务
			FragmentTransaction Fragmenttransaction = fm.beginTransaction();
			// 根据当前获得FragmentManager获取所有的Fragment
			List<Fragment> listfm = fm.getFragments();
			// 隐藏指定的Fragment
			Fragmenttransaction.hide(listfm.get(i));
			// ContentFragment cf = (ContentFragment) listfm.get(i);
			// 设置被隐藏Fragment中的
			// List<String> listData = new ArrayList<String>();
			// listData.addAll(list);
			// cf.setDataList(listData);
			// 提交FragmentManager事务
			Fragmenttransaction.commitAllowingStateLoss();
			// 设置隐藏ViewPager指示器指定子节点
			LinearLayout ll = (LinearLayout) indicator.getChildAt(0);
			ll.getChildAt(i).setVisibility(View.GONE);
			// 删除titles中的关联的隐藏项
			titles.remove(i);
		}

	}

	// 显示指定的fragment和ViewPager指示器节点
	private void showTitle(int i) {
		// 获取fragment管理器，这里是用的android.support.v4.app.FragmentManager;
		FragmentManager fm = getSupportFragmentManager();
		// 开始Fragment事务
		FragmentTransaction Fragmenttransaction = fm.beginTransaction();
		// 根据当前获得FragmentManager获取所有的Fragment
		List<Fragment> listfm = fm.getFragments();

		int count = i + 1;
		// 判断是用新建的Fragment，还是用之前隐藏的Fragment
		if (count <= listfm.size()) {
			// 这里是用之前隐藏的Fragment
			// 显示已制定的Fragment
			Fragmenttransaction.show(listfm.get(count));
			// 提交FragmentManager事务
			Fragmenttransaction.commitAllowingStateLoss();
			// 设置显示ViewPager指示器指定子节点
			LinearLayout ll = (LinearLayout) indicator.getChildAt(0);
			ll.getChildAt(count).setVisibility(View.VISIBLE);
			// 清除ContentFragment中设置的选中项标示
			ContentFragment cf = (ContentFragment) listfm.get(count);
			cf.getSelectItem().clear();
			// 设置Fragment中显示的内容
			List<M_Street> listData = new ArrayList<M_Street>();
			listData.addAll(list);
			cf.setDataList(listData);
		}
		// 刷新页面
		adapter.notifyDataSetChanged();
		indicator.notifyDataSetChanged();

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


	public synchronized void doCheckDbFile(Context context, String DATABASE_PATH, String DATABASE_FILENAME) throws Exception {
		String databaseFullName = DATABASE_PATH + File.separator + DATABASE_FILENAME;

		File dir = new File(DATABASE_PATH);
		if (!dir.exists())
			dir.mkdir();
		File dbFile = new File(databaseFullName);
		if(!dbFile.exists()){
			dbFile.createNewFile();
		}
		copyResToSdcard(R.raw.area,databaseFullName);
	}


	public static long getFileSize(File f) throws Exception {
		long size = 0;
		File flist[] = f.listFiles();
		if (f.exists() && f.isFile()) {
			size = size + f.length();
		}
		return size;
	}


	public void copyResToSdcard(int ResId, String path){//name为sd卡下制定的路径
		try {
			//InputStream is = getResources().openRawResource(ResId);
			InputStream is = getResources().getAssets().open("area.db");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			FileOutputStream fos = new FileOutputStream(new File(path));
			fos.write(buffer);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
