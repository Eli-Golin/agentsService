package com.coral.multi.mappedobjects.reservresponse;

public class ResponseDetails {
	private Integer reservIten;
	private long reservNum; 
	private Integer hotelId;
	private String roomType;
	private String roomDescription;
	private String roomTypeDescription;
	private String arrivalDate;
	private String departureDate;
	private String status;
	private Double price;
	private String currency;

	public Integer getReservIten() {
		return reservIten;
	}
	public void setReservIten(Integer reservIten) {
		this.reservIten = reservIten;
	}
	public long getReservNum() {
		return reservNum;
	}
	public void setReservNum(long reservNum) {
		this.reservNum = reservNum;
	}
	public Integer getHotelId() {
		return hotelId;
	}
	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getRoomDescription() {
		return roomDescription;
	}
	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}
	public String getRoomTypeDescription() {
		return roomTypeDescription;
	}
	public void setRoomTypeDescription(String roomTypeDescription) {
		this.roomTypeDescription = roomTypeDescription;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
