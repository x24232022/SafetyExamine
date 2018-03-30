package com.ydtx.powermanger.Module;

import java.util.Map;

import android.app.Activity;

import com.ydtx.powermanger.MyCallback;


public interface BaseModule {
	//公共访问服务器接口
	public interface SubAddress{
		void doSub(Map<String, String> params, String path, MyCallback call, Activity activity);
	}
	//时时发送位置接口
	public interface SubPositon{
		void doSub(Map<String, String> params, String path, MyCallback call);
	}
}



