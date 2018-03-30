package com.avicsafety.lib.CustomView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avicsafety.lib.R;
import com.avicsafety.lib.tools.DialogCreator;
import com.avicsafety.lib.tools.L;

/**
 * Created by Administrator on 2017/4/6.
 */

public class AvicSelectButton extends RelativeLayout {

    private ImageView imageView;
    private TextView textView;
    private onClickIntercept intercept;
    private String[] texts;
    private String[] values;
    private Dialog dialogItem;
    private OnItemClickListener onItemClickListener;
    private Context context;

    public interface onClickIntercept{
        void onClick();
    }

    public AvicSelectButton(Context context) {
        this(context,null);
    }

    public AvicSelectButton(String[] _texts, Context context) {
        this(context,null);
        texts = _texts;
        init();
    }

    public AvicSelectButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AvicSelectButton(Context _context, AttributeSet attrs, int defStyleAttr) {
        super(_context, attrs, defStyleAttr);
        context = _context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.com_avicsafety_lib_selectbutton,this);
        imageView = (ImageView) findViewById(R.id.iamgeview01);
        textView = (TextView) findViewById(R.id.text01);
    }

    private void init() {
        if(texts!=null) {
            dialogItem = DialogCreator.singleChoiceDailog(context, texts, new DialogCreator.OnSelectionChangedListener() {
                @Override
                public void onChanged(String key, String value, int index) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onClick(key, value, index);
                    }
                }
            }, values);

            dialogItem.setOnDismissListener(new Dialog.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    textView.setTextColor(Color.parseColor("#696969"));
                    imageView.setImageResource(R.drawable.btn_icon_unpress);
                }
            });

            this.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setTextColor(Color.parseColor("#06a0e9"));
                    imageView.setImageResource(R.drawable.btn_icon_press);
                    dialogItem.show();
                }
            });
        }else{
            L.v("items must init a value");
        }
    }

    /**
     *  设置图片资源
     * @param resId
     */
    public void setImageResource(int resId) {
        imageView.setImageResource(resId);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTextView() {
        return textView;
    }

    /**
     * 设置文字
     * @param text
     */
    public void setTextView(String text) {
        textView.setText(text);
    }

    public void setTextColors(int i) {
        textView.setTextColor(i);
    }

    public void setTexts(String[] texts) {
        this.texts = texts;
        init();
    }

    public String[] getTexts() {
        return texts;
    }

    public void setValues(String[] values) {
        this.values = values;
        init();
    }

    public String[] getValues() {
        return values;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public interface OnItemClickListener{
        /**
         *
         * @param key jian
         * @param value zhi
         * @param index xuhao
         */
        void onClick(String key, String value, int index);
    }
}
