package com.avicsafety.lib.CustomView;

import com.avicsafety.lib.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class AvicSearchButton extends LinearLayout{

	public AvicSearchButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.search_button, this);  
	}

	public AvicSearchButton(Context context) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.search_button, this);  
	}

}
