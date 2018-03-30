package com.avicsafety.lib.CustomView;

public interface I_kv {
	/**
	 * 当value和text不同时需要设置真是的值
	 * 
	 */
	void setValue(String value);
	/**
	 * 当VALUE为空 会自动获取text的值
	 * 
	 */
	String getValue();
	/**
	 * 设置标题
	 * 
	 */
	void setLable(String lable);
	/**
	 * 获取标题
	 * 
	 */
	String getLable();
	/**
	 * 设置要显示的文本
	 * 
	 */
	void setText(String text);
	/**
	 * 得到控件显示的文本
	 * 
	 */
	String getText();
}
