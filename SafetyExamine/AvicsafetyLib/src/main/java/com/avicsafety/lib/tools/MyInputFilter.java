package com.avicsafety.lib.tools;

import android.text.InputFilter;
import android.text.Spanned;

public class MyInputFilter implements InputFilter{
	private int DECIMAL_DIGITS = 1; 
	
	public MyInputFilter(int n){
		
		DECIMAL_DIGITS=n;
	}
	@Override
	public CharSequence filter(CharSequence source, int start, int end,
			Spanned dest, int dstart, int dend) {
		if ("".equals(source.toString())) {   
            return null;   
        }   
        String dValue = dest.toString();   
        String[] splitArray = dValue.split("//.");   
        if (splitArray.length > 1) {   
            String dotValue = splitArray[1];   
            int diff = dotValue.length() + 1 - DECIMAL_DIGITS;   
            if (diff > 0) {   
                return source.subSequence(start, end - diff);   
            }   
        }   
        return null;   
	}

}
