package com.avicsafety.safety_examine.Utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.TextView;

import com.avicsafety.safety_examine.R;


public class MyProgressDialog extends ProgressDialog {

	private Dialog progressDialog;

	private TextView msg;

	private String msgText = "加载中...";

	private boolean cancelable = true;

	/**
	 * 创建默认框，不可取消，
	 * 
	 * @param context
	 */
	public MyProgressDialog(Context context) {
		super(context);
		// TODO 自动生成的构造函数存根

		progressDialog = new Dialog(context, R.style.progress_dialog);
		progressDialog.setContentView(R.layout.wait_dialog);
		progressDialog.setCancelable(false);
		progressDialog.getWindow().setBackgroundDrawableResource(
				android.R.color.transparent);
		msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);

		setMsgText(msgText);
		setCancelable(cancelable);

		progressDialog.show();

	}

	/**
	 * 创建是否可取消进度框
	 * 
	 * @param context
	 * @param// Cancelable
	 */
	public MyProgressDialog(Context context, boolean cancelable) {
		super(context);
		// TODO 自动生成的构造函数存根

		progressDialog = new Dialog(context, R.style.progress_dialog);
		progressDialog.setContentView(R.layout.wait_dialog);
        progressDialog.setCancelable(false);
		progressDialog.getWindow().setBackgroundDrawableResource(
				android.R.color.transparent);
		msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);

		setMsgText(msgText);
		setCancelable(true);

		progressDialog.show();

	}

	/**
	 * 创建带文本、不可取消的
	 * 
	 * @param context
	 * @param msgText
	 */

	public MyProgressDialog(Context context, String msgText) {
		// TODO 自动生成的构造函数存根
		super(context);

		progressDialog = new Dialog(context, R.style.progress_dialog);
		progressDialog.setContentView(R.layout.wait_dialog);
        progressDialog.setCancelable(false);
		progressDialog.getWindow().setBackgroundDrawableResource(
				android.R.color.transparent);
		msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);

		setMsgText(msgText);
		setCancelable(true);

		progressDialog.show();

	}

	/**
	 * 创建带文本、是否可取消的
	 * 
	 * @param context
	 * @param msgText
	 */

	public MyProgressDialog(Context context, String msgText, boolean cancelable) {
		// TODO 自动生成的构造函数存根
		super(context);

		progressDialog = new Dialog(context, R.style.progress_dialog);
		progressDialog.setContentView(R.layout.wait_dialog);
        progressDialog.setCancelable(false);
		progressDialog.getWindow().setBackgroundDrawableResource(
				android.R.color.transparent);
		msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);

		setMsgText(msgText);
		setCancelable(true);

		progressDialog.show();
	}

	@Override
	public void dismiss() {
		// TODO 自动生成的方法存根

		progressDialog.dismiss();
	}

	public String getMsgText() {
		return msgText;
	}

	public boolean isCancelable() {
		return cancelable;
	}

//	@Override
//	public void setCancelable(boolean cancelable) {
//		this.cancelable = cancelable;
//		progressDialog.setCancelable(true);
//	}

	public void setMsgText(String msgText) {
		this.msgText = msgText;
		msg.setText(msgText);
	}

}
