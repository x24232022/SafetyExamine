package com.avicsafety.lib.DateTimePicker;

import java.util.Calendar;

import com.avicsafety.lib.DateTimePicker.YearMonthPicker.OnYearMonthChangedListener;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.format.DateUtils;

public class YearMonthPickerDialog extends AlertDialog implements
		OnClickListener {
	public interface OnYearMonthSetListener {
		void OnYearMonthSet(AlertDialog dialog, long date, String sdate);
	}

	private YearMonthPicker mYearMonthPicker;
	private Calendar mDate = Calendar.getInstance();

	private OnYearMonthSetListener mOnYearMonthSetListener;

	public YearMonthPickerDialog(Context context, long date) {
		super(context);
		mYearMonthPicker = new YearMonthPicker(context);
		setView(mYearMonthPicker);
		mYearMonthPicker
				.setOnYearMonthChangedListener(new OnYearMonthChangedListener() {
					@Override
					public void onYearMonthChanged(YearMonthPicker view,int year, int month) {
						mDate.set(Calendar.YEAR, year);
						mDate.set(Calendar.MONTH, month);
						updateTitle(mDate.getTimeInMillis());
					}
				});
		setButton(BUTTON_POSITIVE, "确定", this);
		setButton(BUTTON_NEGATIVE,"清空" ,this);
		setButton(BUTTON_NEUTRAL,"取消", (OnClickListener) null);
		mDate.setTimeInMillis(date);
		updateTitle(mDate.getTimeInMillis());
	}

	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		if (mOnYearMonthSetListener != null) {
			if(arg1 == BUTTON_POSITIVE){
				mOnYearMonthSetListener.OnYearMonthSet(this, mDate.getTimeInMillis(), mDate.get(Calendar.YEAR)+"-"+(mDate.get(Calendar.MONTH)+1));
			}else{
				mOnYearMonthSetListener.OnYearMonthSet(this, mDate.getTimeInMillis(), "");
			}
		}
	}

	public void setOnYearMonthSetListener(OnYearMonthSetListener callBack) {
		mOnYearMonthSetListener = callBack;
	}

	private void updateTitle(long date) {
		int flag = DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_DATE
				| DateUtils.FORMAT_SHOW_WEEKDAY | DateUtils.FORMAT_SHOW_TIME;
		//setTitle(DateUtils.formatDateTime(this.getContext(), date, flag));
		setTitle("年月");
	}
}
