package com.avicsafety.lib.PhotoManager;

import java.util.LinkedList;
import java.util.List;

import com.avicsafety.lib.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class PhotoAdapter extends CommonAdapter {
	   /** 
     * 用户选择的图片，存储为图片的完整路径 
     */  
    private List<String> SelectedImage;  
    private Context context;
    /** 
     * 文件夹路径 
     */  
    private String mDirPath;  
  
    public PhotoAdapter(Context context, List<String> mDatas, List<String> selectedDatas, int itemLayoutId,  String dirPath)  
    {  
        super(context, mDatas, itemLayoutId);  
        this.mDirPath = dirPath;  
        this.context = context;
        SelectedImage = selectedDatas;
    }  

	@Override
	public void convert(ViewHolder helper, Object obj){
		// TODO Auto-generated method stub
		 // 设置no_pic  
		final String item = (String)obj;
		
        helper.setImageResource(R.id.iv_photo_list_item, android.R.drawable.ic_menu_gallery);  
        // 设置no_selected  
        helper.setImageResource(R.id.id_item_select,  R.drawable.picture_unselected);  
        // 设置图片  
        helper.setImageByUrl(R.id.iv_photo_list_item, item);  
        
        Log.v("path", item);
  
        final ImageView mImageView = helper.getView(R.id.iv_photo_list_item);  
        final ImageView mSelect = helper.getView(R.id.id_item_select);  
  
        mImageView.setColorFilter(null);  
        // 设置ImageView的点击事件  
        mImageView.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, PhotoDetailActivity.class);
				intent.putExtra("path", item);
				context.startActivity(intent);
			}});

        mSelect.setOnClickListener(new OnClickListener(){  
            // 选择，则将图片变暗，反之则反之  
            @Override  
            public void onClick(View v)  
            {  
            	Log.v("tag", item);
                // 已经选择过该图片  
                if (contains(SelectedImage,item))
                {  
                	remove(SelectedImage,item);  
                    mSelect.setImageResource(R.drawable.picture_unselected);  
                    mImageView.setColorFilter(null);  
                } else  
                // 未选择该图片  
                {  
                	SelectedImage.add(item);  
                    mSelect.setImageResource(R.drawable.pictures_selected);  
                    mImageView.setColorFilter(Color.parseColor("#77000000"));  
                }  
  
            }  
        });  
  
        /** 
         * 已经选择过的图片，显示出选择过的效果 
         */  
        if (contains(SelectedImage,item))  
        {  
            mSelect.setImageResource(R.drawable.pictures_selected);  
            mImageView.setColorFilter(Color.parseColor("#77000000"));  
        }  
        
        
	}
	
	public void addSelectedImage(String item){
		SelectedImage.add(item);
	}

	public List<String> getSelectedImage() {
		return SelectedImage;
	}
	
	private boolean contains(List<String> list, String item){
		for(int i=0;i<list.size();i++){
			if(list.get(i).contains(item)){
				return true;
			}
		}
		return false;
	}
	
	private void remove(List<String> list, String item){
		for(int i=0;i<list.size();i++){
			if(list.get(i).contains(item)){
				list.remove(i);
			}
		}
	}
	
	
}
