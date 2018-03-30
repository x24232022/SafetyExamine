package com.ydtx.powermanger;

public interface MyCallback{
	//初始化
	void onMyStart();
	//成功
	void onSucces(String response);
	//失败
	void onFailed(String error);
}
