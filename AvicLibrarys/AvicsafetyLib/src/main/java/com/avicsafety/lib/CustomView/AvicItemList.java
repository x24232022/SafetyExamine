package com.avicsafety.lib.CustomView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.avicsafety.lib.Adapter.CommonAdapter;
import com.avicsafety.lib.Adapter.ViewHolder;
import com.avicsafety.lib.PhotoManager.PhotoDetailActivity;
import com.avicsafety.lib.R;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shili on 2017/4/1.
 */


public class AvicItemList extends LinearLayout {

    private TextView view_title;
    private View line;
    private ListView list_view;
    private List<I_kv> mData;
    private CommonAdapter<I_kv> mAdapter;
    private Context context;
    private onAddButtonClickListener onAddButtonClickListener;
    private AlertDialog delete_confirm_dialog;
    private String AddButtonText = "添加";
    private Button btn_item_list_add;

    public void setAddButtonText(String addButtonText) {
        AddButtonText = addButtonText;
    }

    public AvicItemList(Context _context, @Nullable AttributeSet attrs) {
        super(_context, attrs);
        context = _context;
        LayoutInflater.from(context).inflate(R.layout.layout_item_list, this);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AvicItemList);
        String lable = a.getString(R.styleable.AvicItemList_item_lable);
        Boolean show_line = a.getBoolean(R.styleable.AvicItemList_item_show_line, true);

        btn_item_list_add = (Button)findViewById(R.id.btn_item_list_add);
        view_title = (TextView) findViewById(R.id.view_title);
        list_view = (ListView) findViewById(R.id.list_view);
        line = findViewById(R.id.line);

        btn_item_list_add.setText(this.AddButtonText);

        if(!show_line){
            line.setVisibility(View.GONE);
        }

        if (lable != null) {
            view_title.setText(lable);
        }

        btn_item_list_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onAddButtonClickListener!=null){
                    onAddButtonClickListener.onClick();
                }
            }
        });

        mData = new ArrayList<I_kv>();
        list_view.setAdapter(mAdapter = new CommonAdapter<I_kv>(
                context.getApplicationContext(), mData, R.layout.item_com_avicsafety_itemlist){
            @Override
            public void convert(ViewHolder helper, I_kv item) {
                helper.setText(R.id.tv_com_avicsafety_lib_item_text,item.getText());
                //helper.setImageResource(R.id.iv_com_avicsafety_lib_item_image, R.drawable.ic_item_del);
            }});

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if(delete_confirm_dialog==null){
                    delete_confirm_dialog = new AlertDialog.Builder(context)
                            .setMessage("是否要删除")
                            .setTitle("提示")
                            .setPositiveButton("确认", new Dialog.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mData.remove(position);
                                    refresh();
                                }
                            })
                            .setNegativeButton("取消", null)
                            .create();
                }
                delete_confirm_dialog.show();
            }
        });
        refresh();
    }

    public void setHeightBasedOnChildren(ListView list_view) {
        // 获取listview的adapter
        ListAdapter listAdapter = list_view.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
        //int col = 3;// listView.getNumColumns();
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i++) {
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, list_view);
            listItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
        }

        // 获取listview的布局参数
        ViewGroup.LayoutParams params = list_view.getLayoutParams();
        // 设置高度
        params.height = totalHeight;
        // 设置margin
        //((MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        // 设置参数
        list_view.setLayoutParams(params);
    }

    public String getLable(){
        return view_title.getText().toString();
    }

    private void refresh(){
        mAdapter.setmDatas(mData);
        mAdapter.notifyDataSetChanged();
        setHeightBasedOnChildren(list_view);
    }

    public List<I_kv> getData(){
        return mData;
    }

    public void setOnAddButtonClickListener(onAddButtonClickListener listener){
        onAddButtonClickListener = listener;
    }

    public void addData(I_kv kv) {
        mData.add(kv);  //插入项
        refresh();
    }

    public interface onAddButtonClickListener{
        void onClick();
    }

}
