package com.avicsafety.lib.ListViewManager;

import java.io.Serializable;
import java.util.List;

import com.avicsafety.lib.CustomView.I_kv;

public class M_TwoStringArray implements Serializable {
	//向选择页面传递数据使用
	private String[][] twoStringArray;
	//从选择页面返回数据使用
	private List<String[]> stringArrayList;

	public String[][] getTwoStringArray() {
		return twoStringArray;
	}

	public void setTwoStringArray(String[][] twoStringArray) {
		this.twoStringArray = twoStringArray;
	}

	public List<String[]> getStringArrayList() {
		return stringArrayList;
	}

	public void setStringArrayList(List<String[]> stringArrayList) {
		this.stringArrayList = stringArrayList;
	}

}
