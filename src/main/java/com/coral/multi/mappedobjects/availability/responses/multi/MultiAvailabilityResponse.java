package com.coral.multi.mappedobjects.availability.responses.multi;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("availabilityAndRatesResponses")
public class MultiAvailabilityResponse {
	private Hotels hotels;
	
	public Hotels getHotels() {
		return hotels;
	}

	public void setHotels(Hotels hotels) {
		this.hotels = hotels;
	}

	public MultiAvailabilityResponse(){
		hotels = new Hotels();
	}
}
