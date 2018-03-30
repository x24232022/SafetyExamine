package com.ydtx.powermanger.event;

import com.baidu.location.BDLocation;

public class PositionEvent {
	private BDLocation bdLocation;
	private BDLocation mBDLocation;
	public BDLocation getmBDLocation() {
		return mBDLocation;
	}
	public void setmBDLocation(BDLocation mBDLocation) {
		this.mBDLocation = mBDLocation;
	}
	public PositionEvent(BDLocation bdLocation,BDLocation bdLocation2){
		this.bdLocation=bdLocation;
		this.mBDLocation=bdLocation2;
	}
	public BDLocation getLocation(){
		return this.bdLocation;
	}
}
