package com.coral.multi.wrappers;

import com.coral.multi.mappedobjects.availability.responses.legacy.Hotel;
import com.coral.multi.mappedobjects.availability.responses.legacy.Hotels;
import com.coral.multi.mappedobjects.availability.responses.legacy.LegacyAvailabilityAndRatesResponse;
import com.coral.multi.mappedobjects.availability.responses.legacy.LegacyAvailabilityAndRatesResponses;
import com.coral.multi.mappedobjects.availability.responses.legacy.Picture;
import com.coral.multi.mappedobjects.availability.responses.legacy.Pictures;
import com.coral.multi.mappedobjects.availability.responses.legacy.Room;
import com.coral.multi.mappedobjects.availability.responses.legacy.Rooms;
import com.thoughtworks.xstream.XStream;

public class LegacyAvailabilityAndRateResponsesWrapper implements XmlToObjectWrapper {
	private XStream xStream;
	
	public LegacyAvailabilityAndRateResponsesWrapper(){
		xStream = new XStream();
		config();
	}
	
	private void config(){
		configAvailAndRatesResponses();
	}
	
	private void configAvailAndRatesResponses(){
		xStream.alias("availabilityAndRatesResponses", LegacyAvailabilityAndRatesResponses.class);
		xStream.aliasField("numberOfReservationsInItinerary", LegacyAvailabilityAndRatesResponses.class, "numOfResInIten");
		configAvailAndRatesResponse();
		xStream.addImplicitCollection(LegacyAvailabilityAndRatesResponses.class, "responses", LegacyAvailabilityAndRatesResponse.class);
		xStream.aliasField("availabilityAndRatesResponse", LegacyAvailabilityAndRatesResponses.class, "responses");
	}
	
	private void configAvailAndRatesResponse(){
		xStream.alias("availabilityAndRatesResponse", LegacyAvailabilityAndRatesResponse.class);
		xStream.aliasField("reservationInItinerary", LegacyAvailabilityAndRatesResponse.class, "reservInIten");
		// hotelAreaCd field is defined the same as in XML
		// arrivalDate field is defined the same as in XML
		// departureDate field is defined the same as in XML
		configHotels();
		// hotels field is defined the same as in XML
	}
	
	private void configHotels(){
		xStream.alias("hotels", Hotels.class);
		xStream.addImplicitCollection(Hotels.class, "hotels", Hotel.class);
		xStream .aliasField("hotel", Hotels.class, "hotels");
		configHotel();
	}
	
	private void configHotel(){
		xStream.alias("hotel",Hotel.class);
		// hotelId field is defined the same as in XML
		// hotelIdDescription field is defined the same as in XML
		// hotelDescription field is defined the same as in XML
		// pictures field is defined the same as in XML
			configPictures();
		// rooms field is defined the same as in XML
			configRooms();
	}
	
	private void configPictures(){
		xStream.alias("pictures", Pictures.class);
		xStream.addImplicitCollection(Pictures.class, "pictures", Picture.class);
		xStream.aliasField("picture", Pictures.class, "pictures");
		configPicture();
	}
	
	private void configPicture(){
		xStream.alias("picture", Picture.class);
		// pictureType field is defined the same as in XML
		//pictureUrl field is defined the same as in XML
	}
	
	private void configRooms(){
		xStream.alias("rooms", Rooms.class);
		xStream.addImplicitCollection(Rooms.class, "rooms", Room.class);
		xStream.aliasField("room", Rooms.class, "rooms");
		configRoom();
	}
	
	private void configRoom(){
		xStream.alias("room", Room.class);
		// roomType field is defined the same as in XML
		// roomTypeDescription field is defined the same as in XML
		// roomDescription field is defined the same as in XML
		// pictures field is defined the same as in XML
		// price field is defined the same as in XML
		// availability is defined the same as in XML
	}
	
	public Object buildFromXml(String xml) {
		return xStream.fromXML(xml);
	}

	public String returnAsXml(Object toParse) {
		return xStream.toXML(toParse);
	}
}
