package com.avicsafety.safety_examine.dx.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


import com.avicsafety.safety_examine.R;
import com.avicsafety.safety_examine.model.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 树数据源构造器
 * 
 * @author LongShao
 * 
 */
public class TreeAdapter extends BaseAdapter {

	/**
	 * 
	 * 列表项控件集合
	 * 
	 */
	public class ViewHolder {
		CheckBox chbSelect;// 选中与否
		ImageView ivIcon;// 图标
		TextView tvText;// 文本〉〉〉
		ImageView ivExEc;// 展开或折叠标记">"或"v"
	}

	private Context con;
	private LayoutInflater lif;
	private List<Node> allsCache = new ArrayList<Node>();
	private List<Node> alls = new ArrayList<Node>();
	private TreeAdapter oThis = this;
	private boolean hasCheckBox = true;// 是否拥有复选框
	private boolean indentIF = true;// 是否缩进

	private int expandedIcon = -1;
	private int collapsedIcon = -1;

	public TreeAdapter(Context context, List<Node> nodes) {
		this.con = context;
		this.lif = (LayoutInflater) con
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		for (int i = 0; i < nodes.size(); i++) {
			addNode(nodes.get(i));
		}
	}

	/**
	 * TreeAdapter构造函数
	 * 
	 * @param context
	 * @param rootNode
	 *            根节点
	 */
	public TreeAdapter(Context context, Node rootNode) {
		this.con = context;
		this.lif = (LayoutInflater) con
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		addNode(rootNode);
	}

	private void addNode(Node node) {
		alls.add(node);
		allsCache.add(node);
		if (node.isLeaf())
			return;
		for (int i = 0; i < node.getChildren().size(); i++) {
			addNode(node.getChildren().get(i));
		}
	}

	// 复选框联动
	private void checkNode(Node node, boolean isChecked) {
		node.setChecked(isChecked);
		for (int i = 0; i < node.getChildren().size(); i++) {
			checkNode(node.getChildren().get(i), isChecked);
		}
	}

	/**
	 * 控制节点的展开和收缩
	 * 
	 * @param position
	 */
	public void ExpandOrCollapse(int position) {
		Node n = alls.get(position);
		if (n != null) {
			if (!n.isLeaf()) {
				n.setExpanded(!n.isExpanded());
				filterNode();
				this.notifyDataSetChanged();
			}
		}
	}

	// 控制节点的展开和折叠
	private void filterNode() {
		alls.clear();
		for (int i = 0; i < allsCache.size(); i++) {
			Node n = allsCache.get(i);
			if (!n.isParentCollapsed() || n.isRoot()) {
				alls.add(n);
			}
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return alls.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return alls.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/**
	 * 获得选中节点
	 * 
	 * @return
	 */
	public List<Node> getSeletedNodes() {
		List<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i < allsCache.size(); i++) {
			Node n = allsCache.get(i);
			if (n.isChecked()) {
				nodes.add(n);
			}
		}
		return nodes;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub

		ViewHolder holder = null;
		if (view == null) {
			view = this.lif.inflate(R.layout.listview_item_tree, null);
			holder = new ViewHolder();
			holder.chbSelect = (CheckBox) view.findViewById(R.id.chbSelect);

			// 复选框单击事件
			holder.chbSelect.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Node n = (Node) v.getTag();
					checkNode(n, ((CheckBox) v).isChecked());
					oThis.notifyDataSetChanged();
				}

			});
			holder.ivIcon = (ImageView) view.findViewById(R.id.ivIcon);
			holder.tvText = (TextView) view.findViewById(R.id.tvText);
			holder.ivExEc = (ImageView) view.findViewById(R.id.ivExEc);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		// 得到当前节点
		Node n = alls.get(position);

		if (n != null) {
			holder.chbSelect.setTag(n);
			holder.chbSelect.setChecked(n.isChecked());

			// 是否显示复选框
			if (n.hasCheckBox() && hasCheckBox) {
				holder.chbSelect.setVisibility(View.VISIBLE);
			} else {
				holder.chbSelect.setVisibility(View.GONE);
			}

			// 是否显示图标
			if (n.getIcon() == -1) {
				holder.ivIcon.setVisibility(View.GONE);
			} else {
				holder.ivIcon.setVisibility(View.VISIBLE);
				holder.ivIcon.setImageResource(n.getIcon());
			}

			// 显示文本
			holder.tvText.setText(n.getText());
			holder.tvText.setGravity(n.getTextGravity());

			if (n.isLeaf()) {
				// 是叶节点 不显示展开和折叠状态图标
				holder.ivExEc.setVisibility(View.GONE);
			} else {
				// 单击时控制子节点展开和折叠,状态图标改变
				holder.ivExEc.setVisibility(View.VISIBLE);
				if (n.isExpanded()) {
					if (expandedIcon != -1)
						holder.ivExEc.setImageResource(expandedIcon);
				} else {
					if (collapsedIcon != -1)
						holder.ivExEc.setImageResource(collapsedIcon);
				}
			}

			if (hasCheckBox) {
				// 控制缩进
				view.setPadding(25 * n.getLevel(), 3, 3, 3);
			} else {
				view.setPadding(-3, 3, 3, 3);
			}

		}

		return view;
	}

	/**
	 * 是否缩进
	 * 
	 * @return indentIF
	 */
	public boolean isIndentIF() {
		return indentIF;
	}

	/**
	 * 设置是否拥有复选框
	 * 
	 * @param hasCheckBox
	 */
	public void setCheckBox(boolean hasCheckBox) {
		this.hasCheckBox = hasCheckBox;
	}

	/**
	 * 设置展开和折叠状态图标
	 * 
	 * @param expandedIcon
	 *            展开时图标
	 * @param collapsedIcon
	 *            折叠时图标
	 */
	public void setExpandedCollapsedIcon(int expandedIcon, int collapsedIcon) {
		this.expandedIcon = expandedIcon;
		this.collapsedIcon = collapsedIcon;
	}

	/**
	 * 设置展开级别
	 * 
	 * @param level
	 */
	public void setExpandLevel(int level) {
		alls.clear();
		for (int i = 0; i < allsCache.size(); i++) {
			Node n = allsCache.get(i);
			if (n.getLevel() <= level) {
				if (n.getLevel() < level) {// 上层都设置展开状态
					n.setExpanded(true);
				} else {// 最后一层都设置折叠状态
					n.setExpanded(false);
				}
				alls.add(n);
			}
		}
		this.notifyDataSetChanged();
	}

	/**
	 * * 是否缩进
	 * 
	 * @param indentIF
	 *            要设置的 indentIF
	 */
	public void setIndentIF(boolean indentIF) {
		this.indentIF = indentIF;
	}

}
