package com.avicsafety.lib.CustomView;

import com.avicsafety.lib.R;
import com.avicsafety.lib.tools.KeyBoardUtils;
import com.avicsafety.lib.tools.Validate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AvicInputActivity extends Activity implements OnClickListener{
	
	private TextView tv_com_avicsafety_lib_input_back;
	private TextView tv_com_avicsafety_lib_input_title;
	private Button btn_com_avicsafety_lib_input_confirm;
	private EditText et_com_avicsafety_lib_input_text;
	private TextView et_com_avicsafety_lib_input_help;
	private String min;
	private String max;
	private int type;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.com_avicsafety_lib_activity_input);
		tv_com_avicsafety_lib_input_back = (TextView) this.findViewById(R.id.tv_com_avicsafety_lib_input_back);
		tv_com_avicsafety_lib_input_title = (TextView) this.findViewById(R.id.tv_com_avicsafety_lib_input_title);
		btn_com_avicsafety_lib_input_confirm = (Button) this.findViewById(R.id.btn_com_avicsafety_lib_input_confirm);
		et_com_avicsafety_lib_input_text = (EditText) this.findViewById(R.id.et_com_avicsafety_lib_input_text);
		et_com_avicsafety_lib_input_help = (TextView) this.findViewById(R.id.et_com_avicsafety_lib_input_help);
		
		Intent intent = getIntent();
		
		String title = intent.getStringExtra("title");
		if(title!=null)
			tv_com_avicsafety_lib_input_title.setText(title);
		else
			tv_com_avicsafety_lib_input_title.setText("请输入");
		
		String value = intent.getStringExtra("value");
		if(value!=null)
			et_com_avicsafety_lib_input_text.setText(value);
		
		String help = intent.getStringExtra("help");
		if(help!=null)
			et_com_avicsafety_lib_input_help.setText(help);
		
	
		type = intent.getIntExtra("type", TYPE.STRING);
		
		min = intent.getStringExtra("min");
		max = intent.getStringExtra("max");
		
		btn_com_avicsafety_lib_input_confirm.setOnClickListener(this);
		tv_com_avicsafety_lib_input_back.setOnClickListener(this);
		
		et_com_avicsafety_lib_input_text.requestFocus();
		et_com_avicsafety_lib_input_text.setFocusable(true);
		
		
		
		if(type==TYPE.DOUBLE||type==TYPE.FLOAT){
			et_com_avicsafety_lib_input_text.setInputType(InputType.TYPE_CLASS_PHONE);
			et_com_avicsafety_lib_input_text.setSelectAllOnFocus(true);
			et_com_avicsafety_lib_input_text.selectAll();
		}
		if(type==TYPE.INTEGER||type==TYPE.MOBILE){
			et_com_avicsafety_lib_input_text.setInputType(InputType.TYPE_CLASS_NUMBER);
			et_com_avicsafety_lib_input_text.setSelectAllOnFocus(true);
			et_com_avicsafety_lib_input_text.selectAll();
		}
		
		KeyBoardUtils.openKeybord(et_com_avicsafety_lib_input_text,this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
			if(v.getId()==R.id.btn_com_avicsafety_lib_input_confirm){
				String value = et_com_avicsafety_lib_input_text.getText().toString();
				
				if(type==TYPE.STRING){
					if(min!=null&&!min.equals("0")&&value.length()<Integer.valueOf(min)){
						Toast.makeText(this,"最少输入"+ min +"个字符",Toast.LENGTH_LONG).show();
					}else{
						if(max!=null&&!max.equals("0")&&value.length()>Integer.valueOf(max)){
							Toast.makeText(this,"最多输入"+ max +"个字符",Toast.LENGTH_LONG).show();
						}else{
							setResult(value);
						}
					}
				}else if(type==TYPE.INTEGER){
					if(!Validate.isInteger(value)){
						Toast.makeText(this,"必须输入整数才可以",Toast.LENGTH_LONG).show();
					}else if(min!=null&&!min.equals("0")&&Integer.valueOf(value)<Integer.valueOf(min)){
						Toast.makeText(this,"不可低于最小值"+ min,Toast.LENGTH_LONG).show();
					}else{
						if(max!=null&&!max.equals("0")&&Integer.valueOf(value)>Integer.valueOf(max)){
							Toast.makeText(this,"不可超过最大值",Toast.LENGTH_LONG).show();
						}else{
							setResult(value);
						}
					}
				}else if(type==TYPE.FLOAT){
					if(!Validate.isFloat(value)){
						Toast.makeText(this,"必须输入小数才可以",Toast.LENGTH_LONG).show();
					}else if(min!=null&&!min.equals("0")&&Float.valueOf(value)<Float.valueOf(min)){
						Toast.makeText(this,"不可低于最小值"+min,Toast.LENGTH_LONG).show();
					}else{
						if(max!=null&&!max.equals("0")&&Float.valueOf(value)>Float.valueOf(max)){
							Toast.makeText(this,"不可超过最大值"+max,Toast.LENGTH_LONG).show();
						}else{
							setResult(value);
						}
					}
				}else if(type==TYPE.DOUBLE){
					if(!Validate.isFloat(value)){
						Toast.makeText(this,"必须输入小数才可以",Toast.LENGTH_LONG).show();
					}else if(min!=null&&!min.equals("0")&&Double.valueOf(value)<Double.valueOf(min)){
						Toast.makeText(this,"不可低于最小值"+min,Toast.LENGTH_LONG).show();
					}else{
						if(max!=null&&!max.equals("0")&&Double.valueOf(value)>Double.valueOf(max)){
							Toast.makeText(this,"不可超过最大值"+max,Toast.LENGTH_LONG).show();
						}else{
							setResult(value);
						}
					}
				}else if(type==TYPE.MOBILE){
					if(!Validate.isMobile(value)){
						Toast.makeText(this,"必须输入手机号码",Toast.LENGTH_LONG).show();
					}else{
						setResult(value);
					}
				}else if(type==TYPE.TEL){
					if(!Validate.isTel(value)){
						Toast.makeText(this,"必须输入座机号码",Toast.LENGTH_LONG).show();
					}else{
						setResult(value);
					}
				}else if(type==TYPE.EMAIL){
					if(!Validate.isEmail(value)){
						Toast.makeText(this,"必须输入邮箱地址",Toast.LENGTH_LONG).show();
					}else{
						setResult(value);
					}
				}else if(type==TYPE.IDCARD){
					if(!Validate.isIdCardNo(value)){
						Toast.makeText(this,"必须输入身份证号",Toast.LENGTH_LONG).show();
					}else{
						setResult(value);
					}
				}
			}else if(v.getId()==R.id.tv_com_avicsafety_lib_input_back){
				KeyBoardUtils.closeKeybord(et_com_avicsafety_lib_input_text,this);
				finish();
			}
		}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			KeyBoardUtils.closeKeybord(et_com_avicsafety_lib_input_text,this);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void setResult(String value){
		Intent intent = new Intent();
		intent.putExtra("value", value);
		setResult(RESULT_OK, intent);
		KeyBoardUtils.closeKeybord(et_com_avicsafety_lib_input_text,this);
		finish();
	}
	
	public static class TYPE{
		public static final int STRING = 990001;
		public static final int INTEGER = 990002;
		public static final int FLOAT = 990003;
		public static final int DOUBLE = 990004;
		public static final int MOBILE = 990005;
		public static final int TEL = 990006;
		public static final int EMAIL = 990007;
		public static final int IDCARD = 990008;
	}
}
