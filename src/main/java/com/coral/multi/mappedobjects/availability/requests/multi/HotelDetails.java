package com.coral.multi.mappedobjects.availability.requests.multi;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("hotelDetails")
public class HotelDetails {
	
	@XStreamAlias("reservationInItinerary")
	private int reservInItinr;
	
	@XStreamAlias("hotelAreaCd")
	private int areaCd;
	
	private int hotelId;
	
	private String arrivalDate;
	
	private String departureDate;
	
	private String boardArrangement;
	
	@XStreamAlias("numOfFridayNightDinners")
	private int numOfFridayDinners;
	
	private int numOfSaturdayLunches;
	
	private int numOfHolidayLunches;
	
	private String roomType;
	
	public int getReservInItinr() {
		return reservInItinr;
	}
	public void setReservInItinr(int reservInItinr) {
		this.reservInItinr = reservInItinr;
	}
	public int getAreaCd() {
		return areaCd;
	}
	public void setAreaCd(int areaCd) {
		this.areaCd = areaCd;
	}
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
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
	public String getBoardArrangement() {
		return boardArrangement;
	}
	public void setBoardArrangement(String boardArrangement) {
		this.boardArrangement = boardArrangement;
	}
	public int getNumOfFridayDinners() {
		return numOfFridayDinners;
	}
	public void setNumOfFridayDinners(int numOfFridayDinners) {
		this.numOfFridayDinners = numOfFridayDinners;
	}
	public int getNumOfSaturdayLunches() {
		return numOfSaturdayLunches;
	}
	public void setNumOfSaturdayLunches(int numOfSutardayLunches) {
		this.numOfSaturdayLunches = numOfSutardayLunches;
	}
	public int getNumOfHolidayLunches() {
		return numOfHolidayLunches;
	}
	public void setNumOfHolidayLunches(int numOfHolidayLunches) {
		this.numOfHolidayLunches = numOfHolidayLunches;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
}
