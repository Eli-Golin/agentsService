package com.coral.multi.wrappers;

import com.coral.multi.mappedobjects.general.AgentLogin;
import com.coral.multi.mappedobjects.reservrequest.MultiReservationRequest;
import com.coral.multi.mappedobjects.reservrequest.NewReservationRequest;
import com.coral.multi.mappedobjects.reservrequest.NewRoomReservationDetails;
import com.coral.multi.mappedobjects.reservrequest.SpecialRequest;
import com.thoughtworks.xstream.XStream;

public class MultiReservReqWrapper implements XmlToObjectWrapper{
	private XStream xStream;
	private NewReservReqWrapper newReservReqWrapper;

	public MultiReservReqWrapper(){
		newReservReqWrapper =  new NewReservReqWrapper();
		xStream = newReservReqWrapper.getxStream();
		reservRequestConfig();
	}
	
	public Object buildFromXml(String xml){
		return xStream.fromXML(xml);
	}
	
	public String returnAsXml(Object reservRequest){
		return xStream.toXML(reservRequest);
	}
	
	private void reservRequestConfig() {
		xStream.alias("multiBookHeader", MultiReservationRequest.class);
		xStream.aliasField("reservationDetails",MultiReservationRequest.class,"reservDetails");
		xStream.addImplicitCollection(MultiReservationRequest.class, "reservDetails", NewReservationRequest.class);
	}

	private class NewReservReqWrapper implements XmlToObjectWrapper {
		private XStream xStream;
		
		public XStream getxStream() {
			return xStream;
		}

		public NewReservReqWrapper(){
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
			xStream.alias("reservationDetails", NewReservationRequest.class);
			xStream.aliasField("login", NewReservationRequest.class,"agentLogin");
			agentLoginConfig();
			xStream.aliasField("numberOfReservationsInItinerary", NewReservationRequest.class, "numOfReservationsIten");
			xStream.aliasField("geustNationality",NewReservationRequest.class,"nationality");
			xStream.aliasField("geustFirstName", NewReservationRequest.class, "firstName");
			xStream.aliasField("geustLastName", NewReservationRequest.class, "lastName");
			xStream.aliasField("eDanMemberNo", NewReservationRequest.class, "danMemberNum");
			xStream.aliasField("numberOfAdults", NewReservationRequest.class, "numOfAdults");
			xStream.aliasField("numberOfChildren", NewReservationRequest.class, "numOfchildren");
			xStream.aliasField("numberOfBabies", NewReservationRequest.class, "numOfBabies");
			xStream.aliasField("numberOfRooms", NewReservationRequest.class, "numOfRooms");
			// mop field is defined the same as in xml
			xStream.aliasField("travelAgentReference", NewReservationRequest.class, "travAgentRef");
			xStream.aliasField("reservationRequest",  NewReservationRequest.class, "reservRoomDetails");
			reservRoomDetailsConfig();
		}
		
		private void agentLoginConfig(){
			xStream.alias("login", AgentLogin.class);
			xStream.aliasField("agentId", AgentLogin.class, "id");
			// password field is defined the same as in xml
		}
		
		private void reservRoomDetailsConfig(){
			xStream.alias("reservationRequest", NewRoomReservationDetails.class);
			xStream.aliasField("reservationInItinerary", NewRoomReservationDetails.class, "reservIten");
			// hotelId field is defined the same as in xml
			// roomType field is defined the same as in xml
			// arrivalDate field is defined the same as in xml
			// departureDate field is defined the same as in xml
			// boardArrangement field is defined the same as in xml
			xStream.aliasField("numOfFridayNightDinners", NewRoomReservationDetails.class, "fridayDinnerNum");
			xStream.aliasField("numOfSaturdayLunches", NewRoomReservationDetails.class, "saturdayLunchesNum");
			xStream.aliasField("numOfHolidayLunches", NewRoomReservationDetails.class, "holidayLunchesNum");
			xStream.aliasField("specialRequest", NewRoomReservationDetails.class, "specialReq");
			// price field is the same as in xml
			// currency field is the same as in xml
			// comments field is defined the same as in xml
			specialReqConfig();
		}
		
		private void specialReqConfig(){
			xStream.alias("specialRequest", SpecialRequest.class);
			xStream.aliasField("requestNo", SpecialRequest.class, "reqNum");
			xStream.aliasField("requestType", SpecialRequest.class, "reqType");
		}
	}
}
