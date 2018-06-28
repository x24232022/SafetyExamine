package com.ydtx.powermanger.event;

import com.baidu.location.BDLocation;

public class PositionEvent {
	private BDLocation bdLocation;
	public void setBdLocation(BDLocation bdLocation) {
		this.bdLocation = bdLocation;
	}




	public PositionEvent(BDLocation bdLocation){
		this.bdLocation=bdLocation;

	}
	public BDLocation getLocation(){
		return this.bdLocation;
	}
}
