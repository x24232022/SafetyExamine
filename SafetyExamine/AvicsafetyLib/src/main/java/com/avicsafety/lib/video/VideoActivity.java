package com.avicsafety.lib.video;

import com.avicsafety.lib.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

public class VideoActivity extends Activity{
	
	private FrameLayout frameLayout = null;
	private WebView webView = null;
	private WebChromeClient chromeClient = null;
	private View myView = null;
	private WebChromeClient.CustomViewCallback myCallBack = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_lib_video);
		
		//获取视频地址
		String src = getIntent().getStringExtra("src");
		
		frameLayout = (FrameLayout)findViewById(R.id.framelayout);
		webView = (WebView)findViewById(R.id.webview);
		
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		
		
		webView.setWebViewClient(new MyWebviewCient());
		
		chromeClient = new MyChromeClient();
		
		webView.setWebChromeClient(chromeClient);
		webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

		webView.setHorizontalScrollBarEnabled(false);
		webView.setVerticalScrollBarEnabled(false);	
		
		final String USER_AGENT_STRING = webView.getSettings().getUserAgentString() + " Rong/2.0";
		webView.getSettings().setUserAgentString( USER_AGENT_STRING );
		webView.getSettings().setSupportZoom(false);
		webView.getSettings().setPluginState(WebSettings.PluginState.ON);
		webView.getSettings().setLoadWithOverviewMode(true);
//		tools.getAssetsHtml(this, );
//		StringBuffer sb = new StringBuffer();
//		sb.append("<!DOCTYPE html><html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta charset=\"utf-8\"><title>视频播放</title></head><body>");
//		sb.append("<video src=" +src+" height=\"100%\" width=\"100%\" autoplay=\"autoplay\"></video></body></html>");
//		webView.loadData(sb.toString(), "text/html", null);
		webView.loadUrl(src);
		if(savedInstanceState != null){
			webView.restoreState(savedInstanceState);
		}
		
	}
	
	@Override
	public void onBackPressed() {
		if(myView == null){
			super.onBackPressed();
		}
		else{
			chromeClient.onHideCustomView();
		}
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		webView.saveState(outState);
	}
	
	public void addJavaScriptMap(Object obj, String objName){
		webView.addJavascriptInterface(obj, objName);
	}
	
	public class MyWebviewCient extends WebViewClient{
		@Override
		public WebResourceResponse shouldInterceptRequest(WebView view,
				String url) {
			WebResourceResponse response = null;
			response = super.shouldInterceptRequest(view, url);
			return response;
		}
	}
	
	public class MyChromeClient extends WebChromeClient{
		
		@Override
		public void onShowCustomView(View view, CustomViewCallback callback) {
			if(myView != null){
				callback.onCustomViewHidden();
				return;
			}
			frameLayout.removeView(webView);
			frameLayout.addView(view);
			myView = view;
			myCallBack = callback;
		}
		
		@Override
		public void onHideCustomView() {
			if(myView == null){
				return;
			}
			frameLayout.removeView(myView);
			myView = null;
			frameLayout.addView(webView);
			myCallBack.onCustomViewHidden();
		}
		
		@Override
		public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
			// TODO Auto-generated method stub
			Log.d("ZR", consoleMessage.message()+" at "+consoleMessage.sourceId()+":"+consoleMessage.lineNumber());
			return super.onConsoleMessage(consoleMessage);
		}
	}
	
}
