package com.coral.multi.mappedobjects.availability.responses.multi;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("rooms")
public class Rooms {
	
	@XStreamImplicit(itemFieldName = "roomsPerDate")
	private List<RoomsPerDate> roomsPerDate;

	public Rooms(){
		roomsPerDate = new ArrayList<RoomsPerDate>();
	}

	public List<RoomsPerDate> getRoomsArrangement() {
		return roomsPerDate;
	}

	public void setRoomsArrangement(List<RoomsPerDate> roomsPerDate) {
		this.roomsPerDate = roomsPerDate;
	}
	
}
