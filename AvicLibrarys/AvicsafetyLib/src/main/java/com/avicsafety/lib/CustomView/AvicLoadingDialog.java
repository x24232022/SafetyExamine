package com.avicsafety.lib.CustomView;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avicsafety.lib.R;

/**
 * Created by Administrator on 2017/3/28.
 */

public class AvicLoadingDialog {

    private AvicCircularRing mLoadingView;
    private Dialog mLoadingDialog;

    public AvicLoadingDialog(Context context) {
        avicLoadingDialog(context, "");
    }

    public AvicLoadingDialog(Context context,String msg) {
        avicLoadingDialog(context, msg);
    }

    public void  avicLoadingDialog(Context context, String msg) {
        //首先得到整个View
        View view = LayoutInflater.from(context).inflate(R.layout.loading_dialog_view,null);
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.dialog_view);
        //页面中的LoadingView
        mLoadingView = (AvicCircularRing) view.findViewById(R.id.lv_circularring);
        //页面中是否显示文本
        TextView loadingText = (TextView) view.findViewById(R.id.loading_text);
        if (!TextUtils.isEmpty(msg)) {
            loadingText.setVisibility(View.VISIBLE);
            loadingText.setText(msg);
        }
        //创建自定义样式的Dialog
        mLoadingDialog = new Dialog(context,R.style.loading_dialog);
        //设置返回键有效
        mLoadingDialog.setCancelable(true);
        mLoadingDialog.setContentView(layout,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
    }

    public void show(){
        mLoadingDialog.show();
        mLoadingView.startAnim();
    }

    public void close(){
        if (mLoadingDialog!=null) {
            mLoadingView.stopAnim();
            mLoadingDialog.dismiss();
//            mLoadingDialog=null;
        }
    }
}
