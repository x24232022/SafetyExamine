package com.avicsafety.lib.CustomView;

import com.avicsafety.lib.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AvicDoubleView extends RelativeLayout implements I_kv{

	private Context context;
	private TextView view_title;
	private TextView view_value;
	private View line;
	private AlertDialog.Builder dialog_item;
	private Button btn_com_avicsafety_lib_number_increase;
	private Button btn_com_avicsafety_lib_number_decrease;
//	private int max_count;
	
	
	public AvicDoubleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.s_item_number, this);  
		this.context = context;
		
		TypedArray a = context.obtainStyledAttributes(attrs,  R.styleable.AvicView);  
        String lable = a.getString(R.styleable.AvicView_lable);
        String value = a.getString(R.styleable.AvicView_value);
        Boolean show_line =  a.getBoolean(R.styleable.AvicView_show_line, true);
        
        view_title = (TextView)findViewById(R.id.view_title);  
        view_value = (TextView)findViewById(R.id.view_value);
        btn_com_avicsafety_lib_number_increase = (Button)findViewById(R.id.btn_com_avicsafety_lib_number_increase);
        btn_com_avicsafety_lib_number_decrease = (Button)findViewById(R.id.btn_com_avicsafety_lib_number_decrease);
        line = findViewById(R.id.line);
        
        
		
        view_title.setText(lable);
        view_value.setText(value);
        if(!show_line)
        	line.setVisibility(View.GONE);
        
        btn_com_avicsafety_lib_number_increase.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(view_value.getText().toString().length()==0){
					view_value.setText("1");
				}else{
					view_value.setText(Double.valueOf(view_value.getText().toString())+1+"");
				}
			}});
        
        btn_com_avicsafety_lib_number_decrease.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if(view_value.getText().toString().length()==0){
					view_value.setText("-1");
				}else{
					view_value.setText(Double.valueOf(view_value.getText().toString())-1+"");
				}
			}});
	}
	
	public void setLable(String title){
		view_title.setText(title);
	}

	public void setValue(String value){
		view_value.setText(value);
	}

	public String getLable(){
		return view_title.getText().toString();
	}
	
	public String getValue(){
		return view_value.getText().toString();
	}
//	public int getMax_count() {
//		return max_count;
//	}
//
//
//	public void setMax_count(int max_count) {
//		this.max_count = max_count;
//	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		view_value.setText(text);
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return view_value.getText().toString();
	}
	
	
	
}
