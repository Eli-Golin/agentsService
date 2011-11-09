package com.coral.multi.mappedobjects.availability.responses.legacy;

import java.util.ArrayList;
import java.util.List;

public class Hotels {
	private List<Hotel> hotels;
	
	public Hotels(){
		hotels = new ArrayList<Hotel>();
	}
	
	
	public List<Hotel> getHotels() {
		return hotels;
	}
	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}
}
