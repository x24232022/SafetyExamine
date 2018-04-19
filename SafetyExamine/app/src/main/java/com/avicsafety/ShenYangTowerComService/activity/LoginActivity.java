package com.avicsafety.ShenYangTowerComService.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.avicsafety.ShenYangTowerComService.PowerManager.push.Utils.SharedUtils;
import com.avicsafety.ShenYangTowerComService.PowerManager.push.service.PositionService;
import com.avicsafety.ShenYangTowerComService.R;
import com.avicsafety.ShenYangTowerComService.model.M_LoginInfo;
import com.avicsafety.ShenYangTowerComService.service.LoginManager;
import com.avicsafety.lib.interfaces.OnNetworkAccessToModelListener;
import com.avicsafety.lib.tools.AppInfo;
import com.avicsafety.lib.tools.L;
import com.avicsafety.lib.tools.NetUtils;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 登录页面
 */


@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener {
	private String username;
	private String password;
	//	private String userNamee = "fufanglin";
	private String userPwd = "a123456";
	private TelephonyManager imeid;
	private String imei;

	private LoginManager loginManager;

	protected void init() {
		InitializeData();
		InitializeEvent();
	}


	@Override
	protected void InitializeData() {
		super.InitializeData();
		imeid = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return;
		}
		imei = imeid.getDeviceId();
//		imeid = imei;
		loginManager = new LoginManager();
	}

	@Override
	protected void InitializeEvent() {
		super.InitializeEvent();
		et_login_account.setOnFocusChangeListener(this);
		et_login_pw.setOnFocusChangeListener(this);
		iv_login_clean_account.setOnClickListener(this);
		iv_login_clean_pwd.setOnClickListener(this);
		btn_login_login.setOnClickListener(this);

		SharedPreferences userInfo = getSharedPreferences("uInfo",
				Context.MODE_PRIVATE);
		String a = userInfo.getString("userName", "");
		String b = userInfo.getString("passWord", "");
		et_login_account.setText(a);
		et_login_pw.setText(b);
		if(!b.equals("")){
			cb_password.setChecked(true);
		}

	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.btn_login_login:
				if(NetUtils.isConnected(oThis)){
					if(!StringUtils.isEmpty(LoginManager.getServerIpAddr())){
						username = et_login_account.getText().toString();
						password = et_login_pw.getText().toString();
						if (cb_password.isChecked()) {
							SharedPreferences userInfo = getSharedPreferences("uInfo",
									Context.MODE_PRIVATE);
							userInfo.edit().putString("userName", username).commit();
							userInfo.edit().putString("passWord", password).commit();
						} else {
							SharedPreferences userInfo = getSharedPreferences("uInfo",
									Context.MODE_PRIVATE);
							userInfo.edit().putString("userName", username).commit();
							userInfo.edit().putString("passWord", "").commit();
						}
						if(username.length()>0&&password.length()>0){

							handler.sendEmptyMessage(0);
						}else{
							Toast.makeText(oThis, "请输入用户名和密码!", Toast.LENGTH_SHORT).show();
						}
					}else{
//						Toast.makeText(oThis, "请先配置服务器信息!", Toast.LENGTH_SHORT).show();
//						Intent intent = new Intent(oThis,ServerConfigActivity.class);
//						startActivity(intent);
					}
				}else{
					Toast.makeText(oThis, "当前环境没有网络,请打开网络再试!", Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.iv_login_clean_account:
				et_login_account.setText("");
				break;
			case R.id.iv_login_clean_pwd:
				et_login_pw.setText("");
				break;
		}
	}

	 protected Handler handler = new Handler(){
		 @Override
		 public void handleMessage(Message msg) {
       // TODO Auto-generated method stub
		      super.handleMessage(msg);
		      if(msg.what == 0){
		    	    L.v("STEP 1");
//				    startServer();
				  // 登录
					login(oThis, username,password,imei,new OnNetworkAccessToModelListener<M_LoginInfo>(){
						@Override
						public void onSuccess(M_LoginInfo model) {
							Toast.makeText(oThis, model.getName()+"已经成功登录!", Toast.LENGTH_SHORT).show();
						}

						@Override
						public void onFail(String error) {
//							loadingDialog.close();
							Toast.makeText(oThis, error, Toast.LENGTH_SHORT).show();
						}
					});
//				  userPwd = MD5Encrypt.getInstance().md5(userPwd, 32);
//				  loginManager.loginDD(oThis,username,userPwd);
			  }else if(msg.what == 1){
				  	L.v("STEP 2");
				  	loadingDialog.close();
			    	Bundle bundle = new Bundle();
					bundle.putString("param", "login");
					Intent i = new Intent();
					i.setClass(oThis, MainActivity.class);
					i.putExtras(bundle);
					oThis.startActivity(i);
					oThis.finish();
			  }
		 }
	};

	public void login(final Context context, final String username, final String password, final String imei, final OnNetworkAccessToModelListener listener) {

		RequestParams params = new RequestParams(com.avicsafety.ShenYangTowerComService.conf.Constants.LOGIN);
		params.setConnectTimeout(30000);
		params.addQueryStringParameter("userName", username);
		params.addQueryStringParameter("password", password);
		params.addQueryStringParameter("imei",imei);
		params.addQueryStringParameter("version", String.valueOf(AppInfo.getVersionCode(context)));
		x.http().get(params, new Callback.CommonCallback<String>() {
			@Override
			public void onCancelled(CancelledException arg0) {}
			@Override
			public void onError(Throwable arg0, boolean arg1) {
				arg0.getLocalizedMessage();
				L.v("TTTTTTTTTTTTTTTTT:",arg0.getLocalizedMessage());
				listener.onFail("无法连接到服务器,请稍候再试!");
			}

			@Override
			public void onFinished() {}

			@Override
			public void onSuccess(String result) {

				JSONObject res;
				try {
					res = new JSONObject(result);
					String code = res.getString("Code");
					if (code.equals("200")) {
						String datas = res.get("Response").toString();
						com.avicsafety.ShenYangTowerComService.conf.Constants.setUserInfo(context, datas);
						com.avicsafety.ShenYangTowerComService.conf.Constants.setSubmitUser(username, context);
;
						((LoginActivity)context).goToMain();
						context.startActivity(new Intent(context, ChangeOneActivity.class));
						((LoginActivity)context).startServer();


					}else{
						listener.onFail("用户名或密码不正确,请核对后重新尝试!");
					}
				} catch (JSONException e) {
					e.printStackTrace();
					L.v(e.getMessage());
					listener.onFail("无法连接到服务器,请稍候再试!123");
				}
			}
		});
	}

	public void goToMain() {
		startServer();
		//用户名
		SharedUtils.putShared(oThis, "username",username);
		//密码
		SharedUtils.putShared(oThis, "userpwd", password);
		//是否首次登录
		SharedUtils.putShared(oThis, "isFirst", true);

		finish();
	}

	public void startServer() {
//		//开启推送服务
		XGPushConfig.enableDebug(this, true);
		XGPushManager.registerPush(this,username, new XGIOperateCallback() {
			@Override
			public void onSuccess(Object data, int flag) {
				Log.d("TPush", "注册成功，设备token为：" + data);

			}
			@Override
			public void onFail(Object data, int errCode, String msg) {

				Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);

			}
		});
		//开启人员位置服务
		Intent intent=new Intent(getApplicationContext(), PositionService.class);
		startService(intent);
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.et_login_account:
			iv_login_clean_account.setVisibility(hasFocus?View.VISIBLE:View.GONE);
			break;
		case R.id.et_login_pw:
			iv_login_clean_pwd.setVisibility(hasFocus?View.VISIBLE:View.GONE);
			break;
		}
	}


	//{start} ////////////////////////////////////////////////////////////////////

	@ViewInject(R.id.et_login_account)
	private EditText et_login_account;
	@ViewInject(R.id.et_login_pw)
	private EditText et_login_pw;
	@ViewInject(R.id.iv_login_clean_account)
	private ImageView iv_login_clean_account;
	@ViewInject(R.id.iv_login_clean_pwd)
	private ImageView iv_login_clean_pwd;
	@ViewInject(R.id.btn_login_login)
	private Button btn_login_login;
	@ViewInject(R.id.cb_password)
	private CheckBox cb_password;

}
