package com.avicsafety.NewShenYangTowerComService.PowerManager.push;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.avicsafety.NewShenYangTowerComService.R;

/** 
 * This class is to notify the user of messages with NotificationManager.
 *
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class Notifier {

	private static final String LOGTAG = LogUtil.makeLogTag(Notifier.class);

	private Context context;

	private SharedPreferences sharedPrefs;

	private NotificationManager notificationManager;


	public Notifier(Context context) {
		this.context = context;
		this.sharedPrefs = context.getSharedPreferences(
				Constants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
		this.notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
	}

	@SuppressLint("NewApi")
	public void notify(String notificationId, String apiKey, String title,
			String message, String custMessage, String uri,String from,String packetId) {
		//notificationId 通知ID
		// title通知标题
		// message通知的内容
		//  custMessage额外信息 (json数据)
		//uri 跳转activity的包名
		if (isNotificationEnabled()) { 
			if (isNotificationToastEnabled()) {
				Toast.makeText(context, message, Toast.LENGTH_LONG).show();
			}
			Intent intent = new Intent();
			intent.setClassName(context, uri);
			intent.putExtra(Constants.NOTIFICATION_ID, notificationId);
			intent.putExtra(Constants.NOTIFICATION_API_KEY, apiKey);
			intent.putExtra(Constants.NOTIFICATION_TITLE, title);
			intent.putExtra(Constants.NOTIFICATION_MESSAGE, message);
			intent.putExtra(Constants.NOTIFICATION_CUSTMESSAGE, custMessage);
			intent.putExtra(Constants.NOTIFICATION_URI, uri);	// 标识
			intent.putExtra(Constants.NOTIFICATION_FROM, from);
			intent.putExtra(Constants.PACKET_ID, packetId);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
			intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			PendingIntent contentIntent = PendingIntent.getActivity(context,0,
					intent, PendingIntent.FLAG_UPDATE_CURRENT);
			//
			//			Notification notification = new Notification
			//					.Builder(context).build();
			//			notification.icon = R.drawable.paush;
			//			notification.defaults = Notification.DEFAULT_LIGHTS;
			//			if (isNotificationSoundEnabled()) {
			//				notification.defaults |= Notification.DEFAULT_SOUND;
			//			}
			//			if (isNotificationVibrateEnabled()) {
			//				notification.defaults |= Notification.DEFAULT_VIBRATE;
			//			}
			//			notification.flags |= Notification.FLAG_AUTO_CANCEL;
			//			notification.when = System.currentTimeMillis();
			//			notification.tickerText = message;
			Notification notification=new Notification.Builder(context)
			.setSmallIcon(R.drawable.paush)
			.setContentTitle(message)
			.setDefaults(Notification.DEFAULT_SOUND)
			.setWhen(System.currentTimeMillis())
			.setContentIntent(contentIntent)
			.build();
			if (isNotificationSoundEnabled()) {
				notification.defaults |= Notification.DEFAULT_SOUND;
			}
			if (isNotificationVibrateEnabled()) {
				notification.defaults |= Notification.DEFAULT_VIBRATE;
			}
			notification.flags |= Notification.FLAG_AUTO_CANCEL;
			//			notification.setLatestEventInfo(context, title, message,
			//					contentIntent);
			notificationManager.notify(1, notification);

		} else {
			Log.w(LOGTAG, "Notificaitons disabled.");
		}
	}

	private boolean isNotificationEnabled() {
		return sharedPrefs.getBoolean(Constants.SETTINGS_NOTIFICATION_ENABLED,
				true);
	}

	private boolean isNotificationSoundEnabled() {
		return sharedPrefs.getBoolean(Constants.SETTINGS_SOUND_ENABLED, true);
	}

	private boolean isNotificationVibrateEnabled() {
		return sharedPrefs.getBoolean(Constants.SETTINGS_VIBRATE_ENABLED, true);
	}

	private boolean isNotificationToastEnabled() { 
		return sharedPrefs.getBoolean(Constants.SETTINGS_TOAST_ENABLED, false);
	}

}