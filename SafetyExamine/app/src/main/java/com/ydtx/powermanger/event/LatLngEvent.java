package com.ydtx.powermanger.event;

import com.baidu.location.BDLocation;

public class LatLngEvent {
	private BDLocation location;
	public LatLngEvent(BDLocation location){
		this.location=location;
	};
	
	public BDLocation getLocation(){
		return this.location;
	}
}
