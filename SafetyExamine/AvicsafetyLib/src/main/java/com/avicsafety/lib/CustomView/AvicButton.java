package com.avicsafety.lib.CustomView;

import com.avicsafety.lib.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.Button;

public class AvicButton extends Button {
	
	public final static int Green = 10003;
	public final static int Red = 10002;
	public final static int Blue = 10001;
	public final static int White = 10000;

	public AvicButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.setBackgroundResource(R.drawable.btn_lib_blue_selecter);
		this.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
	}
	
	public AvicButton(Context context, AttributeSet  attrs) {
		super(context, attrs);
		this.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
		// TODO Auto-generated constructor stub
		TypedArray a = context.obtainStyledAttributes(attrs,  R.styleable.AvicButton);  
        int ButtonColor = a.getInteger(R.styleable.AvicButton_buttonColor,  White);
        switch(ButtonColor){
	        case Blue:
	        	this.setBackgroundResource(R.drawable.btn_lib_blue_selecter);
	        	break;
	        case Red:
	        	this.setBackgroundResource(R.drawable.btn_lib_red_selecter);
	        	break;
	        case Green:
	        	this.setBackgroundResource(R.drawable.btn_lib_green_selecter);
	        	break;
	        case White:
	        	this.setBackgroundResource(R.drawable.btn_lib_white_selecter);
	        	break;
        }
	}
	
	public void setButtonColor(int ButtonColor){
		switch(ButtonColor){
        case Blue:
        	this.setBackgroundResource(R.drawable.btn_lib_blue_selecter);
        	break;
        case Red:
        	this.setBackgroundResource(R.drawable.btn_lib_red_selecter);
        	break;
        case Green:
        	this.setBackgroundResource(R.drawable.btn_lib_green_selecter);
        	break;
        case White:
        	this.setBackgroundResource(R.drawable.btn_lib_white_selecter);
        	break;
    }
	}
}
