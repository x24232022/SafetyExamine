package com.avicsafety.lib.ExpandTree;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avicsafety.lib.CustomView.AvicLoadingDialog;
import com.avicsafety.lib.R;
import com.avicsafety.lib.tools.L;

import org.xutils.common.util.DensityUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExpandTree {
	private List<ITreeCollection> selectedItem;
	private Context context;
	private LinearLayout.LayoutParams lp_checkbox;
	private LayoutParams lp_textview;
	private LayoutParams lp_icon;
	private ITreeDao dao;
	public boolean isAuto = false;
	private int chooseMode;
	private CompoundButton current;
	private AvicLoadingDialog loadingDialog;
	private LinearLayout currentLinearLayout;
	private String leaf = "1";

	public ExpandTree(Context _context, ITreeDao _dao) {
		context = _context;
		dao = _dao;
		selectedItem = new ArrayList<ITreeCollection>();

		lp_checkbox = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);

		lp_textview = new LayoutParams(LayoutParams.MATCH_PARENT,DensityUtil.dip2px(45));
		lp_icon = new LayoutParams(DensityUtil.dip2px(30f), DensityUtil.dip2px(30f));
	}

	public void b(LinearLayout renderto, String _rootParent, String _rootLeaf,
			String _rootParam, String text) {
		insertSubView(renderto, _rootParent, _rootLeaf, _rootParam, text);
	}


	private void loadData(final String parent, final String leaf, final String param,
										   final String text) {
		handler.sendEmptyMessage(1);
		new Thread(new Runnable() {
			@Override
			public void run() {
				List<ITreeCollection> data = dao.getList(parent, leaf, param, text);
				Message msg = new Message();
				Bundle bundle = new Bundle();
				bundle.putSerializable("data", (Serializable) data);
				msg.setData(bundle);
				msg.what = 0;
				handler.sendMessage(msg);
			}
		}).start();
	}

	/**
	 * 遍历所有父节点下的数据
	 */
	List<ITreeCollection> forList = new ArrayList<>();
	public List<ITreeCollection> forList(ITreeCollection node) {
		List<ITreeCollection> list = dao.getList(node.getTreeValue(),node.getTreeLeaf(),node.getTreeParam(),node.getTreeText());
		for (ITreeCollection nodes : list){
			if (!node.getTreeLeaf().equals(leaf)) {
				forList(nodes);
			} else {
				forList.addAll(list);
				break;
			}
		}

		return forList;
	}

	private LinearLayout setContent(LinearLayout ll, List<ITreeCollection> mData) {
		for (final ITreeCollection node : mData) {
			v_Tree view = new v_Tree(context);
			final LinearLayout main_container = view.getMain_container();
			final LinearLayout sub_container = view.getSub_container();

			if (this.chooseMode == ChooseMode.WG_SINGLE) {
				View views = LayoutInflater.from(context).inflate(R.layout.expandtree_item,null);
				ImageView iv = (ImageView) views.findViewById(R.id.iv_expand_tree);
				RadioButton iv_check = (RadioButton) views.findViewById(R.id.iv_expand_tree_check);

				final TextView tv = (TextView) views.findViewById(R.id.tv_expand_tree);

				if (node.getTreeLeaf().equals(leaf)) {
					iv.setVisibility(View.GONE);
				}

				tv.setText(node.getTreeText());

				if (!node.getTreeLeaf().equals(leaf)) {
					tv.setOnClickListener(new TreeExpandClickListener(sub_container, node, iv));
				}
				iv.setOnClickListener(new  TreeExpandClickListener(sub_container, node, iv));

				iv_check.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						if(isChecked){
//							if (node.getTreeLeaf().equals(leaf)) {
								if (selectedItem.size() == 0)
									selectedItem.add(node);
								else {
									current.setChecked(false);
									selectedItem.set(0, node);
								}
//							} else {
//								if (selectedItem.size() == 0) {
//									forList.clear();
//									List<ITreeCollection> list = forList(node.getTreeValue());
//									selectedItem.addAll(list);
//								} else {
//									current.setChecked(false);
//									selectedItem.clear();
//									forList.clear();
//									List<ITreeCollection> list = forList(node.getTreeValue());
//									selectedItem.addAll(list);
//								}
//							}
							current = buttonView;
						}
					}
				});

				main_container.addView(views);


			} else {
				if (node.getTreeLeaf().equals(leaf)) {
					if(this.chooseMode==ChooseMode.SINGLE){
						RadioButton rb = new RadioButton(context);
						rb.setLayoutParams(lp_checkbox);
						rb.setText(node.getTreeText());
						//rb.setMinHeight(DensityUtils.dp2px(context,45));
						rb.setTextSize(16);
						rb.setOnCheckedChangeListener(new OnCheckedChangeListener(){

							@Override
							public void onCheckedChanged(CompoundButton v,
														 boolean checked) {
								// TODO Auto-generated method stub
								if(checked){
									if(selectedItem.size()==0)
										selectedItem.add(node);
									else{
										current.setChecked(false);
										selectedItem.set(0, node);
									}
									current = v;
								}
							}});
						main_container.addView(rb);
						rb.setLayoutParams(lp_checkbox);
					}else{
						CheckBox cb = new CheckBox(context);
						cb.setText(node.getTreeText());
						cb.setLayoutParams(lp_checkbox);
						//cb.setMinHeight(DensityUtils.dp2px(context,45));
						cb.setTextSize(16);
						cb.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								CheckBox cBox = (CheckBox) v;
								if (cBox.isChecked()) {
									selectedItem.add(node);
								} else {
									selectedItem.remove(node);
								}
							}
						});
						main_container.addView(cb);
						cb.setLayoutParams(lp_checkbox);
					}
				} else {
					final ImageView icon = new ImageView(context);

					icon.setId(node.getTreeId());
					icon.setLayoutParams(lp_icon);
					icon.setBackgroundResource(R.drawable.ic_arrow_right);

					main_container.addView(icon);

					TextView tv = new TextView(context);
					tv.setText(node.getTreeText());
					tv.setLayoutParams(lp_textview);
					tv.setPadding(10, 3, 3, 0);
					tv.setTextSize(16);
					tv.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);

					tv.setOnClickListener(new TreeExpandClickListener(sub_container, node, icon));
					icon.setOnClickListener(new  TreeExpandClickListener(sub_container, node, icon));

					main_container.addView(tv);
				}
			}
			ll.addView(view);
		}
		return ll;
	}

	private void insertSubView(LinearLayout sub_container, String parent,
			String leaf, String param, String text) {
		// TODO Auto-generated method stub
		currentLinearLayout = sub_container;
		loadData(parent, leaf, param, text);
	}

	public List<ITreeCollection> getSelectedItem() {
		return selectedItem;
	}
	
	public int getChooseMode() {
		return chooseMode;
	}

	public void setChooseMode(int _chooseMode) {
		chooseMode = _chooseMode;
	}

	public void setLoadingDialog(AvicLoadingDialog loadingDialog) {
		this.loadingDialog = loadingDialog;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	public static class ChooseMode{
		public static final int SINGLE = 00001;
		public static final int MULTIPLE = 00002;
		public static final int SINGLE_ANY = 00003;
		public static final int WG_SINGLE = 00004;
	}
	
	public class TreeExpandClickListener implements OnClickListener {

		LinearLayout sub_container;
		ITreeCollection node;
		ImageView icon;
		
		TreeExpandClickListener(LinearLayout _sub_container, ITreeCollection _node, ImageView _icon){
			this.sub_container = _sub_container;
			this.node = _node;
			this.icon = _icon;
		}	
	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub\
			
				if (sub_container.getChildCount() == 0) {
					insertSubView(sub_container, node.getTreeValue(),
							node.getTreeLeaf(), node.getTreeParam(),
							node.getTreeText());
				}
			
			if (sub_container.getVisibility() == View.GONE) {
				sub_container.setVisibility(View.VISIBLE);
				icon.setBackgroundResource(R.drawable.ic_arrow_down);
			} else {
				sub_container.setVisibility(View.GONE);
				icon.setBackgroundResource(R.drawable.ic_arrow_right);
			}
		}

	}

	protected Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what == 0){
				L.v("STEP 1");
				List<ITreeCollection> mData = (List<ITreeCollection>)msg.getData().getSerializable("data");
				setContent(currentLinearLayout, mData);
				if(loadingDialog!=null){
					loadingDialog.close();
				}
			}else if(msg.what==1){
				if(loadingDialog!=null){
					loadingDialog.show();
				}
			}
		}
	};

}
