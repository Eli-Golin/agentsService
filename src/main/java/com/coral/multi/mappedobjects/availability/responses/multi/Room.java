package com.coral.multi.mappedobjects.availability.responses.multi;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("room")
public class Room {
	
	@XStreamAlias("numberOfAdults")
	private int numOfAdults;
	
	@XStreamAlias("numberOfChildren")
	private int numOfChildren;
	
	@XStreamAlias("numberOfBabies")
	private int numOfBabies;
	
	
	private String roomType;
	
	private String roomTypeDescription;
	
	private String roomDescription;
	
	private String boardArrangement;
	
	private Pictures pictures;
	
	private Double price;
	
	private String currency;
	
	private int deal;
	
	private String availability;
	
	private String combinationString;
	
	private String comments;

	public Room(){
		pictures = new Pictures();
	}
	
	public int getNumOfAdults() {
		return numOfAdults;
	}

	public void setNumOfAdults(int numOfAdults) {
		this.numOfAdults = numOfAdults;
	}

	public int getNumOfChildren() {
		return numOfChildren;
	}

	public void setNumOfChildren(int numOfChildren) {
		this.numOfChildren = numOfChildren;
	}

	public int getNumOfBabies() {
		return numOfBabies;
	}

	public void setNumOfBabies(int numOfBabies) {
		this.numOfBabies = numOfBabies;
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

	public String getBoardArrangement() {
		return boardArrangement;
	}

	public void setBoardArrangement(String boardArrangement) {
		this.boardArrangement = boardArrangement;
	}

	public Pictures getPictures() {
		return pictures;
	}

	public void setPictures(Pictures pictures) {
		this.pictures = pictures;
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
	
	public int getDeal() {
		return deal;
	}
	
	public void setDeal(int deal) {
		this.deal = deal;
	}


	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getCombinationString() {
		return combinationString;
	}

	public void setCombinationString(String combinationString) {
		this.combinationString = combinationString;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
