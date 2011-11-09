package com.coral.multi.mappedobjects.availability.requests.legacy;

import com.coral.multi.mappedobjects.general.AgentLogin;

public class LegacyAvailabilityAndRatesRequests {
	private AgentLogin agentLogin;
	private int numOfResInIten;
	private String nationality;
	private int numOfAdults;
	private int numOfChildren;
	private int numOfBabies;
	private int numOfRooms;
	private LegacyAvailabilityAndRatesRequest requestDetails;
	
	public AgentLogin getAgentLogin() {
		return agentLogin;
	}
	public void setAgentLogin(AgentLogin agentLogin) {
		this.agentLogin = agentLogin;
	}
	public int getNumOfResInIten() {
		return numOfResInIten;
	}
	public void setNumOfResInIten(int numOfResInIten) {
		this.numOfResInIten = numOfResInIten;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
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
	public int getNumOfRooms() {
		return numOfRooms;
	}
	public void setNumOfRooms(int numOfRooms) {
		this.numOfRooms = numOfRooms;
	}
	public LegacyAvailabilityAndRatesRequest getRequestDetails() {
		return requestDetails;
	}
	public void setRequestDetails(LegacyAvailabilityAndRatesRequest requestDetails) {
		this.requestDetails = requestDetails;
	}
}
