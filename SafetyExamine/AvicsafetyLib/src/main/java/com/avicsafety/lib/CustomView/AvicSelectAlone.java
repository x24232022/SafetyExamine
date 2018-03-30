package com.avicsafety.lib.CustomView;

import com.avicsafety.lib.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

public class AvicSelectAlone extends TextView implements I_kv{
	
	private String value;
	
	public AvicSelectAlone(Context context) {
		super(context);
	}
	
	public AvicSelectAlone(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AvicSelectAlone);
		String text = a.getString(R.styleable.AvicSelectAlone_alone_text);
		value = a.getString(R.styleable.AvicSelectAlone_alone_value);
		String hint = a.getString(R.styleable.AvicSelectAlone_alone_hint);
		this.setText(text);
		this.setHint(hint);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public void setLable(String lable) {
	}

	@Override
	public String getLable() {
		return "";
	}

	@Override
	public void setText(String text) {
		super.setText(text);
	}
	
	@Override
	public String getText() {
		return super.getText().toString();
	}

}
