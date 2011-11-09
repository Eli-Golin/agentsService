package com.coral.multi.wrappers;

import com.coral.multi.mappedobjects.general.AgentLogin;
import com.coral.multi.mappedobjects.reservrequest.OldReservationRequest;
import com.coral.multi.mappedobjects.reservrequest.OldRoomReservationDetails;
import com.coral.multi.mappedobjects.reservrequest.SpecialRequest;
import com.thoughtworks.xstream.XStream;

public class LegacyReservReqWrapper implements XmlToObjectWrapper {
	private XStream xStream;
	
	public XStream getxStream() {
		return xStream;
	}

	public LegacyReservReqWrapper(){
		xStream = new XStream();
		reservRequestConfig();
	}
	
	public Object buildFromXml(String xml) {
		return xStream.fromXML(xml);
	}

	public String returnAsXml(Object reservRequest) {
		return xStream.toXML(reservRequest);
	}
	
	private void reservRequestConfig() {
		xStream.alias("reservationRequests", OldReservationRequest.class);	
		xStream.aliasField("login", OldReservationRequest.class,"agentLogin");
		agentLoginConfig();
		xStream.aliasField("numberOfReservationsInItinerary", OldReservationRequest.class, "numOfReservationsIten");
		xStream.aliasField("geustNationality",OldReservationRequest.class,"nationality");
		xStream.aliasField("geustFirstName", OldReservationRequest.class, "firstName");
		xStream.aliasField("geustLastName", OldReservationRequest.class, "lastName");
		xStream.aliasField("eDanMemberNo", OldReservationRequest.class, "danMemberNum");
		xStream.aliasField("numberOfAdults", OldReservationRequest.class, "numOfAdults");
		xStream.aliasField("numberOfChildren", OldReservationRequest.class, "numOfChildren");
		xStream.aliasField("numberOfBabies", OldReservationRequest.class, "numOfBabies");
		xStream.aliasField("numberOfRooms", OldReservationRequest.class, "numOfRooms");
		xStream.aliasField("travelAgentReference", OldReservationRequest.class, "travAgentRef");
		// mop field is defined the same as in xml
		xStream.aliasField("reservationRequest",  OldReservationRequest.class, "reservRoomDetails");
		reservDetailsConfig();
	}
	
	private void agentLoginConfig(){
		xStream.alias("login", AgentLogin.class);
		xStream.aliasField("agentId", AgentLogin.class, "id");
		// password field is defined the same as in xml
	}
	
	private void reservDetailsConfig(){
		xStream.alias("reservationRequest", OldRoomReservationDetails.class);
		xStream.aliasField("reservationInItinerary", OldRoomReservationDetails.class, "reservIten");
		// hotelId field is defined the same as in xml
		// roomType field is defined the same as in xml
		// arrivalDate field is defined the same as in xml
		// departureDate field is defined the same as in xml
		// boardArrangement field is defined the same as in xml
		xStream.aliasField("numOfFridayNightDinners", OldRoomReservationDetails.class, "fridayDinnerNum");
		xStream.aliasField("numOfSaturdayLunches", OldRoomReservationDetails.class, "saturdayLunchesNum");
		xStream.aliasField("numOfHolidayLunches", OldRoomReservationDetails.class, "holidayLunchesNum");
		xStream.aliasField("specialRequest", OldRoomReservationDetails.class, "specialReq");
		specialReqConfig();
	}
	
	private void specialReqConfig(){
		xStream.alias("specialRequest", SpecialRequest.class);
		xStream.aliasField("requestNo", SpecialRequest.class, "reqNum");
		xStream.aliasField("requestType", SpecialRequest.class, "reqType");
	}
}
