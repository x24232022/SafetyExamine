package com.avicsafety.lib.DateTimePicker;

import java.util.Calendar;

import com.avicsafety.lib.R;

import android.content.Context;
import android.text.format.DateFormat;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;

public class DateTimePicker extends FrameLayout {
	public interface OnDateTimeChangedListener {
		void onDateTimeChanged(DateTimePicker view, int year, int month,
				int day, int hour, int minute);
	}

	private final NumberPicker mDateSpinner;
	private final NumberPicker mHourSpinner;
	private final NumberPicker mMinuteSpinner;
	private final NumberPicker mYearSpinner;
	private final NumberPicker mMonthSpinner;
	private Calendar mDate;
	private int mYear,mMonth, mHour, mMinute;
	private String[] mDateDisplayValues = new String[7];

	private OnDateTimeChangedListener mOnDateTimeChangedListener;
	
	private NumberPicker.OnValueChangeListener mOnYearChangedListener = new OnValueChangeListener() {
		@Override
		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
			mDate.set(Calendar.YEAR, mYearSpinner.getValue() );
//			updateDateControl();
			mYear =  mYearSpinner.getValue();
			updateDateControl();
			onDateTimeChanged();
		}
	};
	
	private NumberPicker.OnValueChangeListener mOnMonthChangedListener = new OnValueChangeListener() {
		@Override
		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
			mDate.set(Calendar.MONTH, mMonthSpinner.getValue()-1 );
			mMonth = mMonthSpinner.getValue();
			updateDateControl();
			onDateTimeChanged();
		}
	};

	private NumberPicker.OnValueChangeListener mOnDateChangedListener = new OnValueChangeListener() {
		@Override
		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
			mDate.add(Calendar.DAY_OF_MONTH, newVal - oldVal);
			
			updateDateControl();
			onDateTimeChanged();
		}
	};

	private NumberPicker.OnValueChangeListener mOnHourChangedListener = new OnValueChangeListener() {
		@Override
		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
			mHour = mHourSpinner.getValue();
			onDateTimeChanged();
		}
	};

	private NumberPicker.OnValueChangeListener mOnMinuteChangedListener = new OnValueChangeListener() {
		@Override
		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
			mMinute = mMinuteSpinner.getValue();
			onDateTimeChanged();
		}
	};

	public DateTimePicker(Context context) {
		super(context);
		mDate = Calendar.getInstance();

		mHour = mDate.get(Calendar.HOUR_OF_DAY);
		mMinute = mDate.get(Calendar.MINUTE);
		mYear = mDate.get(Calendar.YEAR);
		mMonth = mDate.get(Calendar.MONTH)+1;

		inflate(context, R.layout.com_avicsafety_lib_datedialog, this);

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
		
		mDateSpinner = (NumberPicker) this.findViewById(R.id.np_date);
		mDateSpinner.setMinValue(0);
		mDateSpinner.setMaxValue(6);
		updateDateControl();
		mDateSpinner.setOnValueChangedListener(mOnDateChangedListener);

		mHourSpinner = (NumberPicker) this.findViewById(R.id.np_hour);
		mHourSpinner.setMaxValue(23);
		mHourSpinner.setMinValue(0);
		mHourSpinner.setValue(mHour);
		mHourSpinner.setOnValueChangedListener(mOnHourChangedListener);

		mMinuteSpinner = (NumberPicker) this.findViewById(R.id.np_minute);
		mMinuteSpinner.setMaxValue(59);
		mMinuteSpinner.setMinValue(0);
		mMinuteSpinner.setValue(mMinute);
		mMinuteSpinner.setOnValueChangedListener(mOnMinuteChangedListener);
	}

	private void onDateTimeChanged() {
		if (mOnDateTimeChangedListener != null) {
			mOnDateTimeChangedListener.onDateTimeChanged(this,
					mYear, mMonth-1,
					mDate.get(Calendar.DAY_OF_MONTH), mHour, mMinute);
		}
	}

	public void setOnDateTimeChangedListener(OnDateTimeChangedListener callback) {
		mOnDateTimeChangedListener = callback;
	}

	private void updateDateControl() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(mDate.getTimeInMillis());
		cal.add(Calendar.DAY_OF_YEAR, -7 / 2 - 1);
		mDateSpinner.setDisplayedValues(null);
		for (int i = 0; i < 7; ++i) {
			cal.add(Calendar.DAY_OF_YEAR, 1);
			mDateDisplayValues[i] = (String) DateFormat.format("dd EEEE",
					cal);
		}
		mDateSpinner.setDisplayedValues(mDateDisplayValues);
		mDateSpinner.setValue(7 / 2);
		mYearSpinner.setValue(mDate.get(Calendar.YEAR));
		mMonth = mDate.get(Calendar.MONTH)+1;
		mMonthSpinner.setValue(mMonth);
		mDateSpinner.invalidate();
	}
}
