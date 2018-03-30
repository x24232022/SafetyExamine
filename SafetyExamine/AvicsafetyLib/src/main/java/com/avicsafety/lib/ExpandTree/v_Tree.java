package com.avicsafety.lib.ExpandTree;


import com.avicsafety.lib.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class v_Tree extends LinearLayout {

	private LinearLayout main_container;  
    private LinearLayout sub_container;  
	
	public v_Tree(Context context) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.v_tree, this);  
		main_container = (LinearLayout) findViewById(R.id.main_container);  
		sub_container = (LinearLayout) findViewById(R.id.sub_container);  
		sub_container.setVisibility(View.GONE);
	}

	public LinearLayout getMain_container() {
		return main_container;
	}

	public void setMain_container(LinearLayout main_container) {
		this.main_container = main_container;
	}

	public LinearLayout getSub_container() {
		return sub_container;
	}

	public void setSub_container(LinearLayout sub_container) {
		this.sub_container = sub_container;
	}
	
	
	

}
