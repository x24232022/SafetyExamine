package com.ydtx.powermanger.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {
	public static OkHttpClient okClient;
	public static Response response;
	public static Call call;
	public static void postHttp(String url,FormBody body,Callback callback){
		if(okClient==null){
			okClient =new OkHttpClient.Builder()  
			.connectTimeout(10, TimeUnit.SECONDS)  
			.readTimeout(10, TimeUnit.SECONDS) 
			.writeTimeout(30, TimeUnit.SECONDS)
			.build();
		}
		Request requst=new Request.Builder()
		.post(body)
		.url(url)
		.build();
		call=okClient.newCall(requst);
		call.enqueue(callback);
	}
}
