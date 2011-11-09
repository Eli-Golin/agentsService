package com.coral.multi.mappedobjects.availability.requests.multi;

import java.util.ArrayList;
import java.util.List;

import com.coral.multi.mappedobjects.general.AgentLogin;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("hotel")
public class ReservationHotel {
	
	@XStreamAlias("login")
	private AgentLogin agentLogin;
	
	
	@XStreamAlias("numberOfReservationsInItinerary")
	private int numOfReservInIten;
	
	@XStreamAlias("geustNationality")
	private String nationality;
	
	@XStreamAlias("numberOfRooms")
	private int numOfRooms;
	
	@XStreamAlias("roomsOccupancy")
	private List<RoomOccupancy> roomsOccupancyList;
	
	private HotelDetails hotelDetails;
	
	public ReservationHotel(){
		agentLogin = new AgentLogin();
		hotelDetails = new HotelDetails();
		roomsOccupancyList =  new ArrayList<RoomOccupancy>();
	}
	
	@Override
	public boolean equals(Object hotel) {
		if(! (hotel instanceof ReservationHotel))
			return false;
		ReservationHotel other = (ReservationHotel)hotel;
		if(this.hotelDetails.getHotelId() ==other.hotelDetails.getHotelId() & this.hotelDetails.getArrivalDate().equals(other.hotelDetails.getArrivalDate()) 
				& this.hotelDetails.getDepartureDate().equals(other.hotelDetails.getDepartureDate())) 
			return true;
		else
			return false;
	}
	
	
	@Override
	public int hashCode() {
		return getHotelDetails().getHotelId()+getHotelDetails().getArrivalDate().hashCode()+getHotelDetails().getDepartureDate().hashCode();
	}



	public AgentLogin getAgentLogin() {
		return agentLogin;
	}
	public void setAgentLogin(AgentLogin agentLogin) {
		this.agentLogin = agentLogin;
	}
	
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public int getNumOfReservInIten() {
		return numOfReservInIten;
	}
	public void setNumOfReservInIten(int numOfReservInIten) {
		this.numOfReservInIten = numOfReservInIten;
	}

	public int getNumOfRooms() {
		return numOfRooms;
	}
	public void setNumOfRooms(int numOfRooms) {
		this.numOfRooms = numOfRooms;
	}
	
	public List<RoomOccupancy> getRoomsOccupancyList() {
		return roomsOccupancyList;
	}
	
	public void setRoomsOccupancyList(List<RoomOccupancy> roomsOccupancyList) {
		this.roomsOccupancyList = roomsOccupancyList;
	}
	
	public HotelDetails getHotelDetails() {
		return hotelDetails;
	}
	
	public void setHotelDetails(HotelDetails hotelDetails) {
		this.hotelDetails = hotelDetails;
	}
}
