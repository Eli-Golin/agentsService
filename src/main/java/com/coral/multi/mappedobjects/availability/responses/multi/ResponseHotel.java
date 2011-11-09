package com.coral.multi.mappedobjects.availability.responses.multi;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("hotel")
public class ResponseHotel {
	
	@XStreamAlias("hotelAreaCd")
	private int areaCd;
	
	@XStreamAlias("reservationInItinerary")
	private int reservInItiner;
	
	private int hotelId;
	
	private String hotelIdDescription;
	
	private String hotelDescription;
	
	private Pictures pictures;
	
	private String comments;
	
	private Rooms rooms;
	
	
	public ResponseHotel(){
		rooms = new Rooms();
		pictures = new Pictures();
	}

	public int getAreaCd() {
		return areaCd;
	}

	public void setAreaCd(int areaCd) {
		this.areaCd = areaCd;
	}

	public int getReservInItiner() {
		return reservInItiner;
	}

	public void setReservInItiner(int reservInItiner) {
		this.reservInItiner = reservInItiner;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Rooms getRooms() {
		return rooms;
	}

	public void setRooms(Rooms rooms) {
		this.rooms = rooms;
	}
}
