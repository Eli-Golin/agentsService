package com.coral.multi.mappedobjects.availability.requests.multi;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("roomOccupancy")
public class RoomOccupancy {
	@XStreamAlias("numberOfAdults")
	private int numOfAdults;
	
	@XStreamAlias("numberOfChildren")
	private int numOfChildren;
	
	@XStreamAlias("numberOfBabies")
	private int numOfBabies;

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
}
