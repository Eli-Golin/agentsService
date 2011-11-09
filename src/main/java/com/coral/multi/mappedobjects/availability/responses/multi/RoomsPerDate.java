package com.coral.multi.mappedobjects.availability.responses.multi;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("roomsPerDate")
public class RoomsPerDate {
	private String arrivalDate;
	private String departureDate;
	
	@XStreamImplicit(itemFieldName = "room")
	private List<Room> rooms;
	
	public RoomsPerDate(){
		rooms = new ArrayList<Room>();
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
}
