package com.coral.multi.mappedobjects.reservrequest;

public class NewRoomReservationDetails {
	private Integer reservIten;
	private Integer hotelId;
	private String roomType; //ask for all options , maybe enum!
	private String arrivalDate;
	private String departureDate;
	private String boardArrangement;
	private Integer fridayDinnerNum;
	private Integer saturdayLunchesNum;
	private Integer holidayLunchesNum;
	private SpecialRequest specialReq;
	private Double price;
	private String currency;
	private String combinationString;
	private String comments;
	
	public NewRoomReservationDetails(){
		specialReq = new SpecialRequest();
	}
	public Integer getReservIten() {
		return reservIten;
	}
	public void setReservIten(Integer reservIten) {
		this.reservIten = reservIten;
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
	public Integer getFridayDinnerNum() {
		return fridayDinnerNum;
	}
	public void setFridayDinnerNum(Integer fridayDinnerNum) {
		this.fridayDinnerNum = fridayDinnerNum;
	}
	public Integer getSaturdayLunchesNum() {
		return saturdayLunchesNum;
	}
	public void setSaturdayLunchesNum(Integer saturdayLunchesNum) {
		this.saturdayLunchesNum = saturdayLunchesNum;
	}
	public Integer getHolidayLunchesNum() {
		return holidayLunchesNum;
	}
	public void setHolidayLunchesNum(Integer holidayLunchesNum) {
		this.holidayLunchesNum = holidayLunchesNum;
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
	public String getCombinationString() {
		return combinationString;
	}
	public void setCombinationString(String combinationString) {
		this.combinationString = combinationString;
	}
	public SpecialRequest getSpecialReq() {
		return specialReq;
	}
	public void setSpecialReq(SpecialRequest specialReq) {
		this.specialReq = specialReq;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}
