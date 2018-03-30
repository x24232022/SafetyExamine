package com.avicsafety.lib.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avicsafety.lib.R;
import com.avicsafety.lib.tools.DensityUtils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by shili on 2017/4/6.
 */

public class AvicDivider extends TextView {

    public AvicDivider(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        this.setTextColor(Color.parseColor("#666666"));
        this.setGravity(Gravity.CENTER_VERTICAL);
        this.setBackgroundColor(Color.parseColor("#eeeeee"));
        this.setPadding(DensityUtils.dp2px(context, 15),0,0,0);
        if(StringUtils.isEmpty(this.getText())) {
            this.setHeight(DensityUtils.dp2px(context, 20));
        }else{
            this.setHeight(DensityUtils.dp2px(context, 30));
        }
    }
}
