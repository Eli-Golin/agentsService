package com.coral.multi.mappedobjects.availability.requests.multi;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("hotels")
public class ReservationHotels {
	
	@XStreamImplicit(itemFieldName = "hotel")
	private List<ReservationHotel> hotels;

	public ReservationHotels(){
		hotels = new ArrayList<ReservationHotel>();
	}
	
	public List<ReservationHotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<ReservationHotel> hotels) {
		this.hotels = hotels;
	}
}
