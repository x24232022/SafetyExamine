package com.avicsafety.NewShenYangTowerComService.PowerManager.push.Utils;
import android.content.Context;  
import android.hardware.Sensor;  
import android.hardware.SensorEvent;  
import android.hardware.SensorEventListener;  
import android.hardware.SensorManager;  

public class MyOrientationListener implements SensorEventListener {  
	private SensorManager mSensorManager;  
	private Context mContext;  
	private Sensor mSensor;  
	private float lastX;  

	public MyOrientationListener(Context context) {  
		this.mContext = context;  
	}  

	public void start()   //开始监听  
	{  
		mSensorManager = (SensorManager) mContext  
				.getSystemService(Context.SENSOR_SERVICE);  
		if (mSensorManager != null) {  
			// 获得方向传感器  
			mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);  
		}  
		//注册方向传感器广播  
		if (mSensor != null) {  
			mSensorManager.registerListener(this, mSensor,  
					SensorManager.SENSOR_DELAY_UI);  
		}  
	}  

	public void stop()  //结束监听  
	{  
		mSensorManager.unregisterListener(this);  
	}  

	@Override  
	public void onAccuracyChanged(Sensor arg0, int arg1) {  

	}  

	@SuppressWarnings(  
			{"deprecation"})  
	//方向发生改变  
	@Override  
	public void onSensorChanged(SensorEvent event) {  
		if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {  
			float x = event.values[SensorManager.DATA_X];  

			if (Math.abs(x - lastX) > 1.0)  //角度大于1度  
			{  
				if (mOnOrientationListener != null) {  
					mOnOrientationListener.onOrientationChanged(x);  
				}  
			}  
			lastX = x;  

		}  
	}  

	private OnOrientationListener mOnOrientationListener;  

	public void setOnOrientationListener(  
			OnOrientationListener mOnOrientationListener) {  
		this.mOnOrientationListener = mOnOrientationListener;  
	}  
	//跑出接口  
	public interface OnOrientationListener {  
		void onOrientationChanged(float x);  
	}  

}