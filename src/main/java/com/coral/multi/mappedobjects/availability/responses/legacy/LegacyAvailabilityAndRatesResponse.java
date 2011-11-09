package com.coral.multi.mappedobjects.availability.responses.legacy;

public class LegacyAvailabilityAndRatesResponse {
	private int reservInIten;
	private int hotelAreaCd;
	private String arrivalDate;
	private String departureDate;
	private Hotels hotels;
	
	public LegacyAvailabilityAndRatesResponse(){
		hotels = new Hotels();
	}
	
	
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
	public Hotels getHotels() {
		return hotels;
	}
	public void setHotels(Hotels hotels) {
		this.hotels = hotels;
	}
}
