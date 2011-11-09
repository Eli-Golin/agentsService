package com.coral.multi.mappedobjects.cancelation;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cancelReservationResponse")
public class CancelationResponse {
	@XStreamAlias("numberOfReservationsInItinerary")
	private Integer numOfReservationsIten;
	@XStreamAlias("reservationNo")
	private long reservNum;
	private String status;
	private Integer totalPrice;
	private String currency;
	private String comments;
	
	public Integer getNumOfReservationsIten() {
		return numOfReservationsIten;
	}
	public void setNumOfReservationsIten(Integer numOfReservationsIten) {
		this.numOfReservationsIten = numOfReservationsIten;
	}
	public Long getReservNum() {
		return reservNum;
	}
	public void setReservNum(Long reservNum) {
		this.reservNum = reservNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
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
}
