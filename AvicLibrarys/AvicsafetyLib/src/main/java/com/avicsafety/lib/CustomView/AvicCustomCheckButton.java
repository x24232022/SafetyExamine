package com.avicsafety.lib.CustomView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.avicsafety.lib.R;
import com.avicsafety.lib.tools.ToastUtil;

/**
 * Created by Administrator on 2017/3/31.
 */

public class AvicCustomCheckButton extends LinearLayout {

    public static String[][] items;
//    private AvicSelectButton asb_btn01;
//    private AvicSelectButton asb_btn02;
//    private AvicSelectButton asb_btn03;
//    private OnClickIntercept intercept;
//
//    private boolean flag = false;
//    private boolean flag1 = false;
//    private boolean flag2 = false;
//
//    public interface OnClickIntercept{
//        void onClickLeft(AvicSelectButton button);
//        void onClickCenter(AvicSelectButton button);
//        void onClickRight(AvicSelectButton button);
//    }
//    public void setIntercept(OnClickIntercept intercept) {
//        this.intercept = intercept;
//    }
//
//    public static void setTitleText(String []text) {
//        items[0] = text[0];
//        items[1] = text[1];
//        items[2] = text[2];
//    }


    public AvicCustomCheckButton(Context context) {
        this(context,null);
    }

    public AvicCustomCheckButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AvicCustomCheckButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.com_avicsafety_lib_custombutton,this);
//        asb_btn01 = (AvicSelectButton) findViewById(R.id.asb_btn);
//        asb_btn02 = (AvicSelectButton) findViewById(R.id.asb_btn1);
//        asb_btn03 = (AvicSelectButton) findViewById(R.id.asb_btn2);
//        asb_btn01.setTextView(items[0]);
//        asb_btn02.setTextView(items[1]);
//        asb_btn03.setTextView(items[2]);

//        asb_btn01.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                asb_btn02.setImageResource(R.drawable.btn_icon_unpress);
//                asb_btn02.setTextColors(getResources().getColor(R.color.tv_grey));
//                asb_btn03.setImageResource(R.drawable.btn_icon_unpress);
//                asb_btn03.setTextColors(getResources().getColor(R.color.tv_grey));
//                flag1 = false;
//                flag2 = false;
//                flag = !flag;
//                if (flag) {
//                    asb_btn01.setTextColors(getResources().getColor(R.color.bgColor));
//                    asb_btn01.setImageResource(R.drawable.btn_icon_press);
//                } else {
//                    asb_btn01.setTextColors(getResources().getColor(R.color.tv_grey));
//                    asb_btn01.setImageResource(R.drawable.btn_icon_unpress);
//                }
//                intercept.onClickLeft(asb_btn01);
//            }
//        });
//
//        asb_btn02.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                asb_btn01.setImageResource(R.drawable.btn_icon_unpress);
//                asb_btn01.setTextColors(getResources().getColor(R.color.tv_grey));
//                asb_btn03.setImageResource(R.drawable.btn_icon_unpress);
//                asb_btn03.setTextColors(getResources().getColor(R.color.tv_grey));
//                flag = false;
//                flag2 = false;
//                flag1 = !flag1;
//                if (flag1) {
//                    asb_btn02.setTextColors(getResources().getColor(R.color.bgColor));
//                    asb_btn02.setImageResource(R.drawable.btn_icon_press);
//                } else {
//                    asb_btn02.setTextColors(getResources().getColor(R.color.tv_grey));
//                    asb_btn02.setImageResource(R.drawable.btn_icon_unpress);
//                }
//                intercept.onClickCenter(asb_btn02);
//            }
//        });
//
//        asb_btn03.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                asb_btn01.setImageResource(R.drawable.btn_icon_unpress);
//                asb_btn01.setTextColors(getResources().getColor(R.color.tv_grey));
//                asb_btn02.setImageResource(R.drawable.btn_icon_unpress);
//                asb_btn02.setTextColors(getResources().getColor(R.color.tv_grey));
//                flag = false;
//                flag1 = false;
//                flag2 = !flag2;
//                if (flag2) {
//                    asb_btn03.setTextColors(getResources().getColor(R.color.bgColor));
//                    asb_btn03.setImageResource(R.drawable.btn_icon_press);
//                } else {
//                    asb_btn03.setTextColors(getResources().getColor(R.color.tv_grey));
//                    asb_btn03.setImageResource(R.drawable.btn_icon_unpress);
//                }
//                intercept.onClickRight(asb_btn03);
//            }
//        });

    }

    public static void setItems(String[][] items) {
        AvicCustomCheckButton.items = items;
    }
}
