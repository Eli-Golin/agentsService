package com.coral.multi.mappedobjects.availability.responses.multi;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("hotels")
public class Hotels {
	
	@XStreamAlias("numberOfReservationsInItinerary")
	private int numOfReservInItiner;
	
	@XStreamImplicit(itemFieldName = "hotel")
	private List<ResponseHotel> responseHotels;
	
	public Hotels(){
		responseHotels = new ArrayList<ResponseHotel>();
	}

	public int getNumOfReservInItiner() {
		return numOfReservInItiner;
	}

	public void setNumOfReservInItiner(int numOfReservInItiner) {
		this.numOfReservInItiner = numOfReservInItiner;
	}

	public List<ResponseHotel> getHotels() {
		return responseHotels;
	}

	public void setHotels(List<ResponseHotel> responseHotels) {
		this.responseHotels = responseHotels;
	}
	
	
}
