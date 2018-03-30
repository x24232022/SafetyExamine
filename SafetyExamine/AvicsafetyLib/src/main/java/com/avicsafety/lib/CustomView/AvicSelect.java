package com.avicsafety.lib.CustomView;

import com.avicsafety.lib.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AvicSelect extends RelativeLayout implements I_kv {

	private TextView view_title;
	private TextView view_value;
	private String value;
	private View line;
	private String shortValue;

	// private int max_count;

	public AvicSelect(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.s_item2, this);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AvicSelect);
		String lable = a.getString(R.styleable.AvicSelect_select_lable);
		String value = a.getString(R.styleable.AvicSelect_select_value);
		String hint = a.getString(R.styleable.AvicSelect_select_hint);

		view_title = (TextView) findViewById(R.id.view_title);
		view_value = (TextView) findViewById(R.id.view_value);
		//line = (View) findViewById(R.id.line);
		
		view_value.setHint(hint);

		if (lable != null)
			view_title.setText(lable);
		if (value != null)
			view_value.setText(value);
//		if (!show_line)
//			line.setVisibility(View.GONE);
//		if (lable_width != null) {
//			float width = 0;
//			if (Validate.isFloat(lable_width.replace("dip", ""))) {
//				width = Float.valueOf(lable_width.replace("dip", ""));
//				RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(tools.dip2px(context, width), LayoutParams.WRAP_CONTENT);
//				lp.addRule(ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
//				lp.addRule(CENTER_VERTICAL, RelativeLayout.TRUE);
//				lp.leftMargin = tools.dip2px(context, 15);
//				view_title.setLayoutParams(lp);
//			}
//		}
	}

	public void setLable(String title) {
		view_title.setText(title);
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLable() {
		return view_title.getText().toString();
	}

	public String getValue() {
		if (value == null || value.length() == 0) {
			return view_value.getText().toString();
		} else {
			return this.value;
		}
	}

	public String getText() {
		 return view_value.getText().toString();
		//return value;
	}

	public void setText(String text) {
		if(text==null||text.equals("")){
			view_value.setText("");
		}
		view_value.setText(text);
	}

	public void setText(Integer text) {
		//value = text.toString();
		view_value.setText(text.toString());
	}
	// public int getMax_count() {
	// return max_count;
	// }
	//
	//
	// public void setMax_count(int max_count) {
	// this.max_count = max_count;
	// }

}
