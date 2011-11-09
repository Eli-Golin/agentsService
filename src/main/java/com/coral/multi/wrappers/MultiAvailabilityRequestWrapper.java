package com.coral.multi.wrappers;

import com.coral.multi.mappedobjects.availability.requests.multi.HotelDetails;
import com.coral.multi.mappedobjects.availability.requests.multi.MultiAvailabilityRequest;
import com.coral.multi.mappedobjects.availability.requests.multi.ReservationHotel;
import com.coral.multi.mappedobjects.availability.requests.multi.ReservationHotels;
import com.coral.multi.mappedobjects.availability.requests.multi.RoomOccupancy;
import com.coral.multi.mappedobjects.general.AgentLogin;
import com.thoughtworks.xstream.XStream;

public class MultiAvailabilityRequestWrapper implements XmlToObjectWrapper{
	private XStream xStream;
	
	public MultiAvailabilityRequestWrapper(){
		xStream = new XStream();
		config();
	}
	
	private void config(){
		xStream.processAnnotations(AgentLogin.class);
		xStream.processAnnotations(HotelDetails.class);
		xStream.processAnnotations(ReservationHotel.class);
		xStream.processAnnotations(ReservationHotels.class);
		xStream.processAnnotations(MultiAvailabilityRequest.class);
		xStream.processAnnotations(RoomOccupancy.class);
	}
	
	public Object buildFromXml(String xml) {
		return xStream.fromXML(xml);
	}

	public String returnAsXml(Object toParse) {
		return xStream.toXML(toParse);
	}
}
