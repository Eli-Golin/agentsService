package com.coral.multi.mappedobjects.reservrequest;

import com.coral.multi.mappedobjects.general.AgentLogin;

public class OldReservationRequest {
	private AgentLogin agentLogin;
	private Integer numOfReservationsIten;
	private String nationality;
	private String firstName;
	private String lastName;
	private Integer danMemberNum;
	private Integer numOfAdults;
	private Integer numOfChildren;
	private Integer numOfBabies;
	private Integer numOfRooms;
	private Integer mop;
	private String travAgentRef;
	private OldRoomReservationDetails reservRoomDetails;
	
	public AgentLogin getAgentLogin() {
		return agentLogin;
	}
	public void setAgentLogin(AgentLogin agentLogin) {
		this.agentLogin = agentLogin;
	}
	public Integer getNumOfReservationsIten() {
		return numOfReservationsIten;
	}
	public void setNumOfReservationsIten(Integer numOfReservationsIten) {
		this.numOfReservationsIten = numOfReservationsIten;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getDanMemberNum() {
		return danMemberNum;
	}
	public void setDanMemberNum(Integer danMemberNum) {
		this.danMemberNum = danMemberNum;
	}
	public Integer getNumOfAdults() {
		return numOfAdults;
	}
	public void setNumOfAdults(Integer numOfAdults) {
		this.numOfAdults = numOfAdults;
	}
	
	public Integer getNumOfChildren() {
		return numOfChildren;
	}
	public void setNumOfChildren(Integer numOfChildren) {
		this.numOfChildren = numOfChildren;
	}
	
	public Integer getNumOfBabies() {
		return numOfBabies;
	}
	public void setNumOfBabies(Integer numOfBabies) {
		this.numOfBabies = numOfBabies;
	}
	public Integer getNumOfRooms() {
		return numOfRooms;
	}
	public void setNumOfRooms(Integer numOfRooms) {
		this.numOfRooms = numOfRooms;
	}
	public Integer getMop() {
		return mop;
	}
	public void setMop(Integer mop) {
		this.mop = mop;
	}
	public String getTravAgentRef() {
		return travAgentRef;
	}
	public void setTravAgentRef(String travAgentRef) {
		this.travAgentRef = travAgentRef;
	}
	public OldRoomReservationDetails getReservRoomDetails() {
		return reservRoomDetails;
	}
	public void setReservRoomDetails(OldRoomReservationDetails reservDetails) {
		this.reservRoomDetails = reservDetails;
	}
}
