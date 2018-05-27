package com.avicsafety.NewShenYangTowerComService.model;

import android.view.Gravity;

import java.util.ArrayList;
import java.util.List;

/**
 * 树节点
 * 
 * @author LongShao
 * 
 */
public class NodeN {
	private NodeN parent;// 父节点
	private List<NodeN> children = new ArrayList<NodeN>();// 子节点
	// private List<MUsers> user = new ArrayList<MUsers>();
	private String text;// 节点显示的文字
	private String value;// 节点的值
	private String mobile;
	private int icon = -1;// 是否显示小图标,-1表示隐藏图标
	private boolean isChecked = false;// 是否处于选中状态
	private boolean isExpanded = true;// 是否处于展开状态
	private boolean hasCheckBox = true;// 是否拥有复选框
	private int textGravity = Gravity.CENTER_VERTICAL;// 节点显示的对齐方式 Gravity.LEFT

	public NodeN(String text, String value) {
		this.text = text;
		this.value = value;
	}

	/**
	 * Node构造函数
	 * 
	 * @param text
	 *            节点显示的文字
	 * @param value
	 *            节点的值
	 * @param //string
	 */
	public NodeN(String text, String value, String mobile) {
		this.text = text;
		this.value = value;
		this.setMobile(mobile);
	}

	/**
	 * 添加子节点
	 * 
	 * @param node
	 */
	public void add(NodeN node) {
		if (!children.contains(node)) {
			children.add(node);
		}
	}

	/**
	 * 清除所有子节点
	 */
	public void clear() {
		children.clear();
	}

	/**
	 * 获得子节点
	 * 
	 * @return
	 */
	public List<NodeN> getChildren() {
		return this.children;
	}

	/**
	 * 获得图标文件
	 * 
	 * @return
	 */
	public int getIcon() {
		return icon;
	}

	/**
	 * 获得节点的级数,根节点为0
	 * 
	 * @return
	 */
	public int getLevel() {
		return parent == null ? 0 : parent.getLevel() + 1;
	}

	public String getMobile() {
		return mobile;
	}

	/**
	 * 获得父节点
	 * 
	 * @return
	 */
	public NodeN getParent() {
		return this.parent;
	}

	/**
	 * 获得节点文本
	 * 
	 * @return
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * @return 获得文字对齐方式
	 */
	public int getTextGravity() {
		return textGravity;
	}

	/**
	 * 获得节点值
	 * 
	 * @return
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * 是否拥有复选框
	 * 
	 * @return
	 */
	public boolean hasCheckBox() {
		return hasCheckBox;
	}

	/**
	 * 获得节点选中状态
	 * 
	 * @return
	 */
	public boolean isChecked() {
		return isChecked;
	}

	/**
	 * 当前节点是否处于展开状态
	 * 
	 * @return
	 */
	public boolean isExpanded() {
		return isExpanded;
	}

	/**
	 * 是否叶节点,即没有子节点的节点
	 * 
	 * @return
	 */
	public boolean isLeaf() {
		return children.size() < 1 ? true : false;
	}

	/**
	 * 递归判断所给的节点是否当前节点的父节点
	 * 
	 * @param node
	 *            所给节点
	 * @return
	 */
	public boolean isParent(Node node) {
		if (parent == null)
			return false;
		if (node.equals(parent))
			return true;
		return parent.isParent(node);
	}

	/**
	 * 递归判断父节点是否处于折叠状态,有一个父节点折叠则认为是折叠状态
	 * 
	 * @return
	 */
	public boolean isParentCollapsed() {
		if (parent == null)
			return !isExpanded;
		if (!parent.isExpanded())
			return true;
		return parent.isParentCollapsed();
	}

	/**
	 * 是否根节点
	 * 
	 * @return
	 */
	public boolean isRoot() {
		return parent == null ? true : false;
	}

	/**
	 * 删除指定位置的子节点
	 * 
	 * @param location
	 */
	public void remove(int location) {
		children.remove(location);
	}

	/**
	 * 删除一个子节点
	 * 
	 * @param node
	 */
	public void remove(Node node) {
		if (!children.contains(node)) {
			children.remove(node);
		}
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
	 * 设置节点选中状态
	 * 
	 * @param isChecked
	 */
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	/**
	 * 设置节点展开状态
	 * 
	 * @return
	 */
	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
	}

	/**
	 * 设置节点图标文件
	 * 
	 * @param icon
	 */
	public void setIcon(int icon) {
		this.icon = icon;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 设置父节点
	 * 
	 * @param node
	 */
	public void setParent(NodeN node) {
		this.parent = node;
	}

	/**
	 * 设置节点文本
	 * 
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @param// 设置文字对齐方式
	 */
	public void setTextGravity(int textGravity) {
		this.textGravity = textGravity;
	}

	/**
	 * 设置节点值
	 * 
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
