package com.coral.multi.wrappers;


import com.coral.multi.mappedobjects.availability.responses.multi.Hotels;
import com.coral.multi.mappedobjects.availability.responses.multi.MultiAvailabilityResponse;
import com.coral.multi.mappedobjects.availability.responses.multi.Picture;
import com.coral.multi.mappedobjects.availability.responses.multi.Pictures;
import com.coral.multi.mappedobjects.availability.responses.multi.ResponseHotel;
import com.coral.multi.mappedobjects.availability.responses.multi.Room;
import com.coral.multi.mappedobjects.availability.responses.multi.Rooms;
import com.coral.multi.mappedobjects.availability.responses.multi.RoomsPerDate;
import com.thoughtworks.xstream.XStream;

public class MultiAvailabilityResponseWrapper implements XmlToObjectWrapper{
	private XStream xStream;
	public MultiAvailabilityResponseWrapper(){
		xStream = new XStream();
		config();
	}
	
	private void config(){
		xStream.processAnnotations(MultiAvailabilityResponse.class);
		xStream.processAnnotations(Hotels.class);
		xStream.processAnnotations(ResponseHotel.class);
		xStream.processAnnotations(Pictures.class);
		xStream.processAnnotations(Picture.class);
		xStream.processAnnotations(Rooms.class);
		xStream.processAnnotations(Room.class);
		xStream.processAnnotations(RoomsPerDate.class);
	}
	
	public Object buildFromXml(String xml) {
		return xStream.fromXML(xml);
	}

	public String returnAsXml(Object toParse) {
		return xStream.toXML(toParse);
	}
}
