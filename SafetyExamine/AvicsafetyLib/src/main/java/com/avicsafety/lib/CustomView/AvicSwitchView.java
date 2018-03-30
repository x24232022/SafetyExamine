package com.avicsafety.lib.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avicsafety.lib.R;

public class AvicSwitchView extends RelativeLayout implements I_kv{

	private TextView view_lable;
	private CheckBox view_value;
	private View line;
	private float before_x;
	private float now_x;
	private OnMyCheckedChangeListener checkedChangeListener;
	
	public AvicSwitchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.s_switch, this);  
		
		TypedArray a = context.obtainStyledAttributes(attrs,  R.styleable.AvicView);  
        String lable = a.getString(R.styleable.AvicView_lable);
        String value = a.getString(R.styleable.AvicView_value);
        Boolean show_line =  a.getBoolean(R.styleable.AvicView_show_line, true);
        
        view_lable = (TextView)findViewById(R.id.view_title);  
        view_value = (CheckBox)findViewById(R.id.view_cb);
        line = findViewById(R.id.line);
        setLable(lable);
        setValue(value);
        if(!show_line)
        	line.setVisibility(View.GONE);
        
        view_value.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
				// TODO Auto-generated method stub
				if(checkedChangeListener!=null){
					checkedChangeListener.onCheckedChanged(isChecked);
				}
			}});
        
//        view_value.setOnTouchListener(new CheckBox.OnTouchListener(){  
//			
//
//			@Override
//			public boolean onTouch(View arg0, MotionEvent arg1) {
//				switch(arg1.getAction()){
//
//				case MotionEvent.ACTION_DOWN:{
//					before_x = arg1.getX();
//					break;
//				}
//				case MotionEvent.ACTION_MOVE:{
////					evenX = arg1.getX();
//					break;
//				}
//				case MotionEvent.ACTION_UP:{
//					now_x = arg1.getX();
//					if(now_x>before_x){
//						view_value.setChecked(true);
//					}else if(now_x<before_x){
//						view_value.setChecked(false);
//					}else{
//						view_value.setChecked(!view_value.isChecked());
//					}
//					break;
//				}
//				}
//				//刷新界面
//				//invalidate();
//				return true;
//			}});
	}
	
	public void setLable(String title){
		view_lable.setText(title);
	}

	public String getLable(){
		return view_lable.getText().toString();
	}
	
	public String getValue(){
		if(view_value.isChecked()){
			return "1";
		}else{
			return "0";
		}
	}

	@Override
	public void setValue(String value) {
		// TODO Auto-generated method stub
		if(value.equals("1")){
			view_value.setChecked(true);
		}else{
			view_value.setChecked(false);
		}
	}
	
	public void setValue(int value) {
		// TODO Auto-generated method stub
		if(value==1){
			view_value.setChecked(true);
		}else{
			view_value.setChecked(false);
		}
	}
	
	public void setValue(boolean value) {
		// TODO Auto-generated method stub
		view_value.setChecked(value);
	}
	
	public void setText(String value) {
		// TODO Auto-generated method stub
		if(value.equals("1")){
			view_value.setChecked(true);
		}else{
			view_value.setChecked(false);
		}
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		if(view_value.isChecked()){
			return "1";
		}else{
			return "0";
		}
	}

	public void setOnCheckedChangeListener(
			OnMyCheckedChangeListener checkedChangeListener) {
		this.checkedChangeListener = checkedChangeListener;
	}


	
	
	
//	public int getMax_count() {
//		return max_count;
//	}
//
//
//	public void setMax_count(int max_count) {
//		this.max_count = max_count;
//	}
	
	
	
}
