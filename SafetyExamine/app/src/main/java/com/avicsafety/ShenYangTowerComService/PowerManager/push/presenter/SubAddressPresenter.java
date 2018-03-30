package com.avicsafety.ShenYangTowerComService.PowerManager.push.presenter;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.FormBody.Builder;
import okhttp3.Response;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.ydtx.powermanger.MyCallback;
import com.ydtx.powermanger.Module.BaseModule.SubAddress;
import com.ydtx.powermanger.utils.OkHttpUtils;

public class SubAddressPresenter implements SubAddress,Callback {
	private MyCallback call;
	private Activity activity;
	// 为什么要使用volatile关键字？
	private volatile static SubAddressPresenter uniqueInstance;
	

	private SubAddressPresenter() {}

	public static SubAddressPresenter getInstance() {
		if (uniqueInstance == null) {
			synchronized (SubAddressPresenter.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new SubAddressPresenter();
				}
			}
		}
		return uniqueInstance;
	}

	@Override
	public void onFailure(Call arg0, IOException arg1) {
		Message msg=handler.obtainMessage(500,"连接服务器出错!");
		handler.sendMessage(msg);
	}

	@Override
	public void onResponse(Call arg0, Response arg1) throws IOException {
		if(arg1.isSuccessful()){
			String response=arg1.body().string();
			if(!TextUtils.isEmpty(response)){
				Message msg=handler.obtainMessage(200, response);
				handler.sendMessage(msg);
			}else{
				Message msg=handler.obtainMessage(500, "有一个未知错误!");
				handler.sendMessage(msg);
			}
		}else{
			Message msg=handler.obtainMessage(500, "服务器出错！");
			handler.sendMessage(msg);
		}
	}

	@Override
	public void doSub(Map<String, String> params,String path, MyCallback call,Activity activity) {
		this.call=call;
		this.activity=activity;
		call.onMyStart();
		Builder body=new Builder();
		for (String key:params.keySet()) {
			body.add(key, params.get(key)+"");
		}
		FormBody fb=body.build();
		if(!activity.isFinishing()){
			OkHttpUtils.postHttp(path, fb,this);
		}
	}

	@SuppressLint("HandlerLeak")
	Handler handler=new Handler(){
		public void handleMessage(Message msg) {
			//如果Activity已经被销毁了,就不在发送消息
			if(!activity.isFinishing()){
				if(msg.obj!=null){
					String response=msg.obj.toString();
					if(msg.what==200){
						call.onSucces(response);
					}else if(msg.what==500){
						call.onFailed(response);
					}
				}else{
					call.onFailed("发生未知错误!");
				}
			}
		};
	};

}
