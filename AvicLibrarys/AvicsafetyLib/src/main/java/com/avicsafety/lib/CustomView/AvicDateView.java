package com.avicsafety.lib.CustomView;

import java.util.Date;

import com.avicsafety.lib.R;
import com.avicsafety.lib.tools.DateTime;
import com.avicsafety.lib.tools.DateTime.Format;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AvicDateView extends RelativeLayout implements I_kv{

	private TextView view_title;
	private TextView view_value;
	private View line;
	private DatePickerDialog dateDialog;
//	private int max_count;
	
	
	public AvicDateView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.s_item, this);  
		
		TypedArray a = context.obtainStyledAttributes(attrs,  R.styleable.AvicView);  
        String lable = a.getString(R.styleable.AvicView_lable);
        String value = a.getString(R.styleable.AvicView_value);
        Boolean show_line =  a.getBoolean(R.styleable.AvicView_show_line, true);
        
        view_title = (TextView)findViewById(R.id.view_title);  
        view_value = (TextView)findViewById(R.id.view_value);
        line = findViewById(R.id.line);
		
        if(lable!=null)
        	view_title.setText(lable);
        if(value!=null)
        	view_value.setText(value);
        if(!show_line)
        	line.setVisibility(View.GONE);
        
        dateDialog = new DatePickerDialog(context, null,
				DateTime.getYear(), DateTime.getMonth() - 1, DateTime.getDay());
		dateDialog.setCancelable(true);
		dateDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"清空", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				view_value.setText("");
			}
		});
		dateDialog.setButton(AlertDialog.BUTTON_POSITIVE,"确认", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				int year = dateDialog.getDatePicker().getYear();
				int monthOfYear = dateDialog.getDatePicker().getMonth();
				int dayOfMonth = dateDialog.getDatePicker().getDayOfMonth();

				String date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
				Date da = DateTime.getDate(Format.yyyymd, date);
				date = DateTime.getStrDate(Format.full, da);
				setText(date);
			}
		});

		this.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dateDialog.show();
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

	public String getText() {
		return view_value.getText().toString();
	}

	public void setText(String text) {
		view_value.setText(text);
	}
	
//	public int getMax_count() {
//		return max_count;
//	}
//
//
//	public void setMax_count(int max_count) {
//		this.max_count = max_count;
//	}
	
//	DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
//		@Override
//		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//			String date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
//			Date da = DateTime.getDate(Format.yyyymd, date);
//			date = DateTime.getStrDate(Format.full, da);
//			setText(date);
//		}
//	};
	
	
}
