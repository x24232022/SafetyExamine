package com.avicsafety.ShenYangTowerComService.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.avicsafety.lib.CustomView.AvicLoadingDialog;
import com.avicsafety.lib.tools.L;
import com.avicsafety.ShenYangTowerComService.R;

import org.xutils.x;

public abstract class BaseActivity extends AppCompatActivity  {
	//public static DbManager db;
	protected Activity oThis;
	protected Toolbar toolbar;
	protected TextView tv_toolbar_title;
	protected AvicLoadingDialog loadingDialog;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
		getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        oThis = this;
        x.view().inject(oThis);
		L.TAG = this.getClass().getName();
		init();
    }

	protected void init() {
	        InitializeActionBar();
	        InitializeComponent();
	        InitializeData();
	        InitializeEvent();
	}

	protected void InitializeActionBar() {
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("");
		setSupportActionBar(toolbar);
		tv_toolbar_title =  (TextView) findViewById(R.id.tv_toolbar_title);
		tv_toolbar_title.setText(R.string.app_name);
		tv_toolbar_title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
		toolbar.setNavigationIcon(R.drawable.ic_common_back);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	toolbarBack();
            }
        });
	}
	
	protected void InitializeComponent() {
		loadingDialog = new AvicLoadingDialog(this);
	}
	protected void InitializeData() {
		// TODO Auto-generated method stub
	}
	
	protected void InitializeEvent() {
		// TODO Auto-generated method stub
	}
	
	public void setTitle(CharSequence text){
		tv_toolbar_title.setText(text);
	}
	
	protected void toolbarBack(){
		finish(); 
	}

	@Override
	public Resources getResources() {
	   Resources res = super.getResources();
	   Configuration config=new Configuration();
	   config.setToDefaults();
	   res.updateConfiguration(config,res.getDisplayMetrics() );
	   return res;
	}

	public void startActivity(Intent intent){
		startActivityForResult(intent,1);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		L.i("onSaveInstanceState");
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onStart() {
		super.onStart();
		L.i("onStart");
	}

	@Override
	protected void onStop() {
		super.onStop();
		L.i("onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		L.i("onDestroy");
	}
}
