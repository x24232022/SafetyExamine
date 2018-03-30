package com.avicsafety.lib.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.avicsafety.lib.R;

public class AvicDivision extends TextView{

    public AvicDivision(Context context, AttributeSet attrs) {
        super(context, attrs);
//        LayoutInflater.from(content).inflate(R.layout.division,this);
    }
}
//    public AvicDivision(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public AvicDivision(Context context) {
//        super(context);
//    }
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        Rect rect = new Rect();
//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setColor(Color.GRAY);
//        paint.setStrokeWidth(3);
//        getLocalVisibleRect(rect);
//        canvas.drawRect(rect, paint);
//    }


