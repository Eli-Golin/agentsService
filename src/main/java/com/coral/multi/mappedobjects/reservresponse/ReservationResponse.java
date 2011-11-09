package com.coral.multi.mappedobjects.reservresponse;


public class ReservationResponse {
	private Integer numOfReservationsIten;
	private long multReservNum; // How long can the number be? ask hilmi
	private String status;
	private Double totalPrice;
	private String currency;
	private String comments;
	private ResponseDetails response;
	
	public ReservationResponse(){
		response = new ResponseDetails();
	}
	
	public Integer getNumOfReservationsIten() {
		return numOfReservationsIten;
	}
	public void setNumOfReservationsIten(Integer numOfReservationsIten) {
		this.numOfReservationsIten = numOfReservationsIten;
	}
	public long getMultReservNum() {
		return multReservNum;
	}
	public void setMultReservNum(long multReservNum) {
		this.multReservNum = multReservNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public ResponseDetails getResponses() {
		return response;
	}
	public void setResponses(ResponseDetails response) {
		this.response = response;
	}
}
