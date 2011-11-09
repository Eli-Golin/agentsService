package com.coral.multi.mappedobjects.availability.responses.legacy;

import java.util.ArrayList;
import java.util.List;

public class Rooms {
	private List<Room> rooms;

	public Rooms(){
		rooms = new ArrayList<Room>();
	}
	
	public List<Room> getRooms() {
		return rooms;
	}
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
}
