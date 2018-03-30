package com.avicsafety.lib.CustomView;

import com.avicsafety.lib.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AvicEditText extends RelativeLayout implements I_kv {

	private TextView view_title;
	private EditText view_value;
	private String value;

	public AvicEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.s_item3, this);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AvicEditText);
		String lable = a.getString(R.styleable.AvicEditText_edit_lable);
		String value = a.getString(R.styleable.AvicEditText_edit_value);
		String hint = a.getString(R.styleable.AvicEditText_edit_hint);

		view_title = (TextView) findViewById(R.id.view_title);
		view_value = (EditText) findViewById(R.id.view_value);
	
		if (lable != null)
			view_title.setText(lable);
		if (value != null)
			view_value.setText(value);
		if(hint!=null){
			view_value.setHint(hint);
		}
	}
	
	public String getLable() {
		return view_title.getText().toString();
	}

	public void setLable(String title) {
		view_title.setText(title);
	}
	
	public String getValue() {
		if (value == null || value.length() == 0) {
			return view_value.getText().toString();
		} else {
			return this.value;
		}
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return value;
	}

	public void setText(String text) {
		if (text == null|| text.equals("")) {
			view_value.setText("");
		}
		view_value.setText(text);
	}

	public void setText(Integer text) {
		value = text.toString();
		view_value.setText(text.toString());
	}
}
