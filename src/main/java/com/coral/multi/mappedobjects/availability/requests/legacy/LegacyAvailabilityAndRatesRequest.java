package com.coral.multi.mappedobjects.availability.requests.legacy;

public class LegacyAvailabilityAndRatesRequest {
	private int reservInIten;
	private int hotelAreaCd;
	private int hotelId;
	private String arrivalDate;
	private String departureDate;
	private String boardArrangement;
	private int numOfFridayDinners;
	private int numOfSaturdayLunches;
	private int numOfHolidayLunches;
	
	public int getReservInIten() {
		return reservInIten;
	}
	public void setReservInIten(int reservInIten) {
		this.reservInIten = reservInIten;
	}
	public int getHotelAreaCd() {
		return hotelAreaCd;
	}
	public void setHotelAreaCd(int hotelAreaCd) {
		this.hotelAreaCd = hotelAreaCd;
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
	public void setNumOfSaturdayLunches(int numOfSaturdayLaunches) {
		this.numOfSaturdayLunches = numOfSaturdayLaunches;
	}
	public int getNumOfHolidayLunches() {
		return numOfHolidayLunches;
	}
	public void setNumOfHolidayLunches(int numOfholidayLaunches) {
		this.numOfHolidayLunches = numOfholidayLaunches;
	}
}
