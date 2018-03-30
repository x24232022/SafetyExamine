package com.avicsafety.lib.tools;

import com.avicsafety.lib.CustomView.AvicSelect;
import com.avicsafety.lib.CustomView.I_kv;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.ListView;

public class DialogCreator {

	public static ProgressDialog getProgressDialog(Context context, String message) {
		ProgressDialog progressDialog = new ProgressDialog(context);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setTitle("提示");
		progressDialog.setMessage(message);
		progressDialog.setIndeterminate(false);
		progressDialog.setCancelable(true);
		return progressDialog;
	}

	public static AlertDialog getAlertDialog(Context context, String message) {
		AlertDialog alertDialog = new ProgressDialog(context);
		alertDialog.setTitle("提示");
		alertDialog.setMessage(message);
		alertDialog.setCancelable(true);
		return alertDialog;
	}

	public static AlertDialog multipleChoiceDailog(Context context, final String[] key, final AvicSelect et) {
		return multipleChoiceDailog(context, key, et, null);
	}

	/**
	 * 多选AlertDialog (逗号分隔的文本输出)
	 * 
	 * @param context
	 * @param
	 * @param et
	 * @return
	 */
	public static AlertDialog multipleChoiceDailog(Context context, final String[] key, final AvicSelect et, final String[] value) {
		final AlertDialog adialog = new AlertDialog.Builder(context).setTitle("请选择").setMultiChoiceItems(key, null, null)
				.setPositiveButton("确定", new Dialog.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						ListView lv = ((AlertDialog) dialog).getListView();
						StringBuffer sb = new StringBuffer();
						StringBuffer sb2 = new StringBuffer();
						for (int i = 0; i < key.length; i++) {
							if (lv.getCheckedItemPositions().get(i)) {
								if (value != null) {
									sb2.append(value[i]);
									sb2.append(",");
								}
								sb.append(key[i]);
								sb.append(",");
							}
						}
						if (sb.length() > 0) {
							String str = sb.subSequence(0, sb.length() - 1).toString();
							et.setText(str);
						} else {
							et.setText("");
						}

						if (sb2.length() > 0) {
							String str = sb2.subSequence(0, sb2.length() - 1).toString();
							et.setValue(str);
						} else {
							et.setValue("");
						}

					}
				}).setNeutralButton("取消", null).create();
		return adialog;
	}

	public static AlertDialog singleChoiceDailog(Context context, final String[] key, final I_kv et) {
		return singleChoiceDailog(context, key, et, null);
	}

	/**
	 * 单选AlertDialog (逗号分隔的文本输出)
	 * 
	 * @param context
	 * @param
	 * @param et
	 * @return
	 */
	public static AlertDialog singleChoiceDailog(Context context, final String[] key, final I_kv et, final String[] value) {

		final AlertDialog adialog = new AlertDialog.Builder(context).setTitle("请选择").setItems(key, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int index) {
				et.setText(key[index]);
				if (value != null) {
					et.setValue(value[index]);
				}
			}
		}).create();
		return adialog;
	}

	public static AlertDialog singleChoiceDailog(Context context, final String[] key, final OnSelectionChangedListener listener) {
		return singleChoiceDailog(context, key, listener, null);
	}

	/**
	 * 单选AlertDialog (逗号分隔的文本输出)
	 * 
	 * @param context
	 * @param
	 * @param
	 * @return
	 */
	public static AlertDialog singleChoiceDailog(Context context, final String[] key, final OnSelectionChangedListener listener, final String[] value) {
		final AlertDialog adialog = new AlertDialog.Builder(context).setTitle("请选择").setItems(key, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int index) {
				if (value != null) {
					listener.onChanged(key[index], value[index], index);
				} else {
					listener.onChanged(key[index], key[index], index);
				}

			}
		}).create();
		return adialog;
	}

	public interface OnSelectionChangedListener {
		void onChanged(String key, String value, int index);
	}
}
