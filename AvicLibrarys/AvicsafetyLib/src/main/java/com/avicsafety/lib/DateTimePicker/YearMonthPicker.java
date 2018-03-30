package com.avicsafety.lib.DateTimePicker;

import java.util.Calendar;

import com.avicsafety.lib.R;

import android.content.Context;
import android.text.format.DateFormat;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;

public class YearMonthPicker extends FrameLayout {
	public interface OnYearMonthChangedListener {
		void onYearMonthChanged(YearMonthPicker view, int year, int moth);
	}

	private final NumberPicker mYearSpinner;
	private final NumberPicker mMonthSpinner;
	private Calendar mDate;
	private int mYear,mMonth;
	//private String[] mDateDisplayValues = new String[7];

	private OnYearMonthChangedListener mOnYearMonthChangedListener;
	
	private NumberPicker.OnValueChangeListener mOnYearChangedListener = new OnValueChangeListener() {
		@Override
		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
			mDate.set(Calendar.YEAR, mYearSpinner.getValue() );
//			updateDateControl();
			mYear =  mYearSpinner.getValue();
			updateDateControl();
			onYearMonthChanged();
		}
	};
	
	private NumberPicker.OnValueChangeListener mOnMonthChangedListener = new OnValueChangeListener() {
		@Override
		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
			mDate.set(Calendar.MONTH, mMonthSpinner.getValue()-1 );
			mMonth = mMonthSpinner.getValue();
			updateDateControl();
			onYearMonthChanged();
		}
	};

	public YearMonthPicker(Context context) {
		super(context);
		mDate = Calendar.getInstance();

		mYear = mDate.get(Calendar.YEAR);
		mMonth = mDate.get(Calendar.MONTH)+1;

		inflate(context, R.layout.com_avicsafety_lib_datedialog2, this);

		mYearSpinner = (NumberPicker) this.findViewById(R.id.np_year);
		mYearSpinner.setMinValue(1900);
		mYearSpinner.setMaxValue(3000);
		mYearSpinner.setValue(mYear);
		mYearSpinner.setOnValueChangedListener(mOnYearChangedListener);
		
		
		mMonthSpinner = (NumberPicker) this.findViewById(R.id.np_month);
		mMonthSpinner.setMinValue(1);
		mMonthSpinner.setMaxValue(12);
		mMonthSpinner.setValue(mMonth);
		mMonthSpinner.setOnValueChangedListener(mOnMonthChangedListener);
	}

	private void onYearMonthChanged() {
		if (mOnYearMonthChangedListener != null) {
			mOnYearMonthChangedListener.onYearMonthChanged(this,mYear, mMonth-1);
		}
	}

	public void setOnYearMonthChangedListener(OnYearMonthChangedListener callback) {
		mOnYearMonthChangedListener = callback;
	}

	private void updateDateControl() {
		mYearSpinner.setValue(mDate.get(Calendar.YEAR));
		mMonth = mDate.get(Calendar.MONTH)+1;
		mMonthSpinner.setValue(mMonth);
	}
}
