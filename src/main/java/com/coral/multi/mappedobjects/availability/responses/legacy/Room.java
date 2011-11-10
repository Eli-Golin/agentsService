package com.coral.multi.mappedobjects.availability.responses.legacy;

public class Room {
	private String roomType;
	private String roomTypeDescription;
	private String roomDescription;
	private Pictures pictures;
	private double price;
	private String availability;
	public Room(){
		pictures = new Pictures();
	}
	
	
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getRoomTypeDescription() {
		return roomTypeDescription;
	}
	public void setRoomTypeDescription(String roomTypeDescription) {
		this.roomTypeDescription = roomTypeDescription;
	}
	public String getRoomDescription() {
		return roomDescription;
	}
	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}
	public Pictures getPictures() {
		return pictures;
	}
	public void setPictures(Pictures pictures) {
		this.pictures = pictures;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
}
