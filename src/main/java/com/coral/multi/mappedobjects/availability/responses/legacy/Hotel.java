package com.coral.multi.mappedobjects.availability.responses.legacy;

public class Hotel {
	private int hotelId;
	private String hotelIdDescription;
	private String hotelDescription;
	private Pictures pictures;
	private Rooms rooms;

	public Hotel(){
		rooms = new Rooms();
	}
	
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	
	public String getHotelIdDescription() {
		return hotelIdDescription;
	}

	public void setHotelIdDescription(String hotelIdDescription) {
		this.hotelIdDescription = hotelIdDescription;
	}

	public String getHotelDescription() {
		return hotelDescription;
	}
	public void setHotelDescription(String hotelDescription) {
		this.hotelDescription = hotelDescription;
	}
	public Pictures getPictures() {
		return pictures;
	}
	public void setPictures(Pictures pictures) {
		this.pictures = pictures;
	}
	public Rooms getRooms() {
		return rooms;
	}
	public void setRooms(Rooms rooms) {
		this.rooms = rooms;
	}
}
