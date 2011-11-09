package com.coral.multi.wrappers;

import com.coral.multi.mappedobjects.reservresponse.ReservationResponse;
import com.coral.multi.mappedobjects.reservresponse.ResponseDetails;
import com.thoughtworks.xstream.XStream;

public class ReservResWrapper implements XmlToObjectWrapper {
	private XStream xStream;

	public ReservResWrapper(){
		xStream = new XStream();
		reservResConfig();
	}
	
	private void reservResConfig(){
		xStream.alias("reservationResponses", ReservationResponse.class);
		xStream.aliasField("numberOfReservationsInItinerary", ReservationResponse.class, "numOfReservationsIten");
		xStream.aliasField("multiReservationNo", ReservationResponse.class, "multReservNum");
		// status field is defined the same as in xml 
		// price field is defined the same as in xml
		// currency field is defined the same as in xml
		// comments field is defined the same as in xml
		xStream.aliasField("reservationResponse", ReservationResponse.class,"response");
		resDetailsConfig();
	}
	
	private void resDetailsConfig(){
		xStream.alias("reservationResponse", ResponseDetails.class);
		xStream.aliasField("reservationInItinerary", ResponseDetails.class, "reservIten");
		xStream.aliasField("reservationNo", ResponseDetails.class, "reservNum");
		xStream.aliasField("roomTypeDescription", ResponseDetails.class, "roomDescription");
		//	hotelId field is defined the same as in xml 
		//	roomType field is defined the same as in xml
		//	arrivalDate field is defined the same as in xml 
		//	departureDate field is defined the same as in xml 
		//	price field is defined the same as in xml 
		//	currency field is defined the same as in xml 
	}
	
	public Object buildFromXml(String xml) {
		return xStream.fromXML(xml);
	}

	public String returnAsXml(Object reservRequest) {
		return xStream.toXML(reservRequest); 
	}

	public XStream getxStream() {
		return xStream;
	}
}
