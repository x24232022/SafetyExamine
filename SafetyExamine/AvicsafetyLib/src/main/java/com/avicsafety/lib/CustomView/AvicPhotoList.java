package com.avicsafety.lib.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.avicsafety.lib.Adapter.CommonAdapter;
import com.avicsafety.lib.Adapter.ViewHolder;
import com.avicsafety.lib.R;
import com.avicsafety.lib.tools.SDCardUtils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shili on 2017/4/1.
 */


public class AvicPhotoList extends LinearLayout {

    private OnClickListener onClickListener;
    private TextView view_title;
    private View line;
    private GridView grid_view;
    private List<String> mData;
    private CommonAdapter<String> mAdapter;
    private Context context;

    public AvicPhotoList(Context _context, @Nullable AttributeSet attrs) throws IOException {
        super(_context, attrs);
        context = _context;
        LayoutInflater.from(context).inflate(R.layout.layout_photo_list, this);



        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AvicPhotoList);
        String lable = a.getString(R.styleable.AvicPhotoList_photo_lable);
        Boolean show_line = a.getBoolean(R.styleable.AvicPhotoList_photo_show_line, true);

        view_title = (TextView) findViewById(R.id.view_title);
        grid_view = (GridView) findViewById(R.id.grid_view);

        String filename = "ic_select_pic.png";
        String path = SDCardUtils.getSDCardPath();
        final File addImageFilePath = new File(path,filename);
        if(!addImageFilePath.exists()) {
            addImageFilePath.createNewFile();
            copyResToSdcard(filename, path);
        }

        line = findViewById(R.id.line);
        if(!show_line){
            line.setVisibility(View.GONE);
        }

        if (lable != null) {
            view_title.setText(lable);
        }
        mData = new ArrayList<String>();
        grid_view.setAdapter(mAdapter = new CommonAdapter<String>(
                context.getApplicationContext(), mData, R.layout.item_com_avicsafety_photolist){
            @Override
            public void convert(ViewHolder helper, String item) {
                if(item.length()==0) {
                    helper.setImageByUrl(R.id.iv_photo_list_item,addImageFilePath.getAbsolutePath());
                }
                else{
                    helper.setImageByUrl(R.id.iv_photo_list_item,item);
                }
            }});
        grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(onClickListener!=null){
                    onClickListener.onClick();
                }
            }
        });
        setData(new ArrayList<String>());
    }

    public void setHeightBasedOnChildren(GridView grid_view) {
        // 获取listview的adapter
        ListAdapter listAdapter = grid_view.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
        int col = 3;// listView.getNumColumns();
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, grid_view);
            listItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
        }

        // 获取listview的布局参数
        ViewGroup.LayoutParams params = grid_view.getLayoutParams();
        // 设置高度
        params.height = totalHeight;
        // 设置margin
        //((MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        // 设置参数
        grid_view.setLayoutParams(params);
    }

    public String getLable(){
        return view_title.getText().toString();
    }

    public void setData(List<String> data){
        mData.clear();
        mData = data;
        mData.add("");
//        if(mData.size()%3!=0){
//            int n = 3-mData.size()%3;
//            for(int i=0;i<n;i++){
//                mData.add("");
//            }
//        }
//        if(mData.size()==0){
//            mData.add("");
//        }
        mAdapter.setmDatas(mData);
        mAdapter.notifyDataSetChanged();
        setHeightBasedOnChildren(grid_view);
    }

    public List<String> getData(){
        List<String> data = new ArrayList<String>();
        for(String s:mData){
            if(!StringUtils.isEmpty(s)){
                data.add(s);
            }
        }
        return data;
    }

    public void setOnClickListener(OnClickListener listener){
        onClickListener = listener;
    }

    public interface OnClickListener{
        void onClick();
    }

    public void copyResToSdcard(String filename, String path){//name为sd卡下制定的路径
        try {
            //InputStream is = getResources().openRawResource(ResId);
            InputStream is = getResources().getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            FileOutputStream fos = new FileOutputStream(new File(path,filename));
            fos.write(buffer);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
