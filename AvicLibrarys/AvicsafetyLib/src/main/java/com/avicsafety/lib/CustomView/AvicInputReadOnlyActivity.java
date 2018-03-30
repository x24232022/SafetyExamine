package com.avicsafety.lib.CustomView;

import com.avicsafety.lib.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class AvicInputReadOnlyActivity extends Activity{
	
	private TextView tv_com_avicsafety_lib_input_back;
	private TextView tv_com_avicsafety_lib_input_title;
	private TextView tv_com_avicsafety_lib_input_text;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.com_avicsafety_lib_activity_input_readonly);
		tv_com_avicsafety_lib_input_back = (TextView) this.findViewById(R.id.tv_com_avicsafety_lib_input_back);
		tv_com_avicsafety_lib_input_title = (TextView) this.findViewById(R.id.tv_com_avicsafety_lib_input_title);
		tv_com_avicsafety_lib_input_text = (TextView) this.findViewById(R.id.tv_com_avicsafety_lib_input_text);
		
		Intent intent = getIntent();
		
		String title = intent.getStringExtra("title");
		if(title!=null){
			if(title.length()>8){
				title = title.substring(0, 8);
			}
			tv_com_avicsafety_lib_input_title.setText(title);
		}
		else{
			tv_com_avicsafety_lib_input_title.setText("查看");
		}
		
		String value = intent.getStringExtra("value");
		if(value!=null)
			tv_com_avicsafety_lib_input_text.setText(value);
		
		tv_com_avicsafety_lib_input_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
