package com.avicsafety.lib.CustomView;

import com.avicsafety.lib.R;
import com.avicsafety.lib.tools.Validate;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;
import org.xutils.common.util.DensityUtil;

public class AvicTextView extends RelativeLayout implements I_kv {

	private TextView view_title;
	private TextView view_value;
	private String value;
	private String text;
	private View line;
	private String shortValue;
	private Integer substring_number;

	// private int max_count;

	public AvicTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AvicView);
		String lable = a.getString(R.styleable.AvicView_lable);
		String value = a.getString(R.styleable.AvicView_value);
		float lable_width = a.getDimension(R.styleable.AvicView_lable_width, 100);
		Boolean show_line = a.getBoolean(R.styleable.AvicView_show_line, true);
		Integer max_line = a.getInteger(R.styleable.AvicView_max_line, 1);
		substring_number = a.getInteger(R.styleable.AvicView_substring_number, 18);

		if(max_line==1) {
			LayoutInflater.from(context).inflate(R.layout.s_item, this);
		}else{
			LayoutInflater.from(context).inflate(R.layout.s_item_mutil, this);
		}

		view_title = (TextView) findViewById(R.id.view_title);
		view_value = (TextView) findViewById(R.id.view_value);
		line = findViewById(R.id.line);

		if (lable != null)
			view_title.setText(lable);
		if (value != null)
			text = value;
			view_value.setText(value);
		if (!show_line)
			line.setVisibility(View.GONE);

		if(max_line==1) {
			if (lable_width != 100) {
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((int) lable_width, LayoutParams.WRAP_CONTENT);
				lp.leftMargin = DensityUtil.dip2px(15);
				lp.gravity = Gravity.CENTER_VERTICAL;
				view_title.setLayoutParams(lp);
			}
		}else{
			view_value.setMaxLines(max_line);
		}

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
			if (StringUtils.isEmpty(text))
				return "";
			return text;
		} else {
			return this.value;
		}
	}

	public String getText() {
		 return text;
	}

	//只设置显示的值， 当执行getVaule时， 如果value 没有值 才从text中取值
	public void setText(String _text) {
		text = _text;
		if (!StringUtils.isEmpty(_text)) {
			//value = text    不允许此方法修改value的值
			int length=getLen(_text);
			if (substring_number!=0&&length > substring_number) {
				shortValue = _text.subSequence(0, substring_number) + "…";
				view_value.setText(shortValue);
			} else {
				view_value.setText(_text);
			}
		}else{
			view_value.setText("");
		}

	}

	/**
	 * 获取一个字符串的总字符宽度
	 *
	 * @param input
	 * @return
	 */
	public static int getLen(String input) {
		float len = 0;
		if (input.length() > 0) {
			char[] chs = input.toCharArray();
			for (int i = 0; i < chs.length; i++) {
				len += getStrWidth(chs[i]);
			}
		}
		return (int) len;
	}

	public static float getStrWidth(char ch) {
		// ◆该字符表示将画笔修改为带有下划线的画笔 ◇表示普通的笔
		if (ch == '◆' || ch == '◇')
			return 0f;

		if ('\u0030' <= ch && ch <= '\u0039') {
			return 0.5f; // 数字
		} else if ('\u0041' <= ch && ch <= '\u005A') {
			return 0.5f; // 大写
		} else if ('\u0061' <= ch && ch <= '\u007A') {
			return 0.5f; // 小写
		} else if (!(('\uFF61' <= ch) && (ch <= '\uFF9F'))
				&& !(('\u0020' <= ch) && (ch <= '\u007E'))) {
			return 1.0F; // 全角
		} else {
			return 0.5F; // 未知的
		}
	}
	public void setText(Integer text) {
		value = text.toString();
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
