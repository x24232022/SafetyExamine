package com.avicsafety.lib.PhotoManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.avicsafety.lib.R;

public class PhotoDetailActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_com_avicsafety_photo_photomanager_photo_detail);
		
		ImageView iv_photo_detail = (ImageView) this.findViewById(R.id.iv_photo_detail);
		TextView tv_photo_detail_back = (TextView) this.findViewById(R.id.tv_photo_detail_back);
		tv_photo_detail_back.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}});
		
		Intent intent = this.getIntent();
		Bundle bundle = intent .getExtras();
		String path = bundle.getString("path");
		
		ImageLoader.getInstance().loadImage(path, iv_photo_detail);
		
	}
}
