package com.coral.multi.wrappers;

import com.coral.multi.mappedobjects.availability.requests.legacy.LegacyAvailabilityAndRatesRequest;
import com.coral.multi.mappedobjects.availability.requests.legacy.LegacyAvailabilityAndRatesRequests;
import com.coral.multi.mappedobjects.general.AgentLogin;
import com.thoughtworks.xstream.XStream;

public class LegacyAvailabilityAndRatesRequestsWrapper implements XmlToObjectWrapper {
	private XStream xStream;
	public LegacyAvailabilityAndRatesRequestsWrapper(){
		xStream = new XStream();
		config();
	}
	
	private void config(){
		configLegAvailabilityAndRatesRequests();
	}
	
	private void configLegAvailabilityAndRatesRequests(){
		xStream.alias("availabilityAndRatesRequests", LegacyAvailabilityAndRatesRequests.class);
		xStream.aliasField("login", LegacyAvailabilityAndRatesRequests.class, "agentLogin");
		configAgentLogin();
		xStream.aliasField("numberOfReservationsInItinerary",  LegacyAvailabilityAndRatesRequests.class, "numOfResInIten");
		xStream.aliasField("geustNationality", LegacyAvailabilityAndRatesRequests.class, "nationality");
		xStream.aliasField("numberOfAdults", LegacyAvailabilityAndRatesRequests.class, "numOfAdults");
		xStream.aliasField("numberOfChildren", LegacyAvailabilityAndRatesRequests.class, "numOfChildren");
		xStream.aliasField("numberOfBabies", LegacyAvailabilityAndRatesRequests.class, "numOfBabies");
		xStream.aliasField("numberOfRooms", LegacyAvailabilityAndRatesRequests.class, "numOfRooms");
		configLegAvailabilityAndRatesRequest();
		xStream.aliasField("availabilityAndRatesRequest", LegacyAvailabilityAndRatesRequests.class, "requestDetails");
	}
	
	private void configAgentLogin(){
		xStream.alias("login", AgentLogin.class);
		xStream.aliasField("agentId", AgentLogin.class, "id");
		// password field defined the same as in XML
	}
	
	private void configLegAvailabilityAndRatesRequest(){
		xStream.alias("availabilityAndRatesRequest", LegacyAvailabilityAndRatesRequest.class);
		xStream.aliasField("reservationInItinerary", LegacyAvailabilityAndRatesRequest.class, "reservInIten");
		// hotelAreaCd field defined the same as in XML
		// hotelId field defined the same as in XML
		// arrivalDate field defined the same as in XML
		// departureDate field defined the same as in XML
		// boardArrangement field defined the same as in XML
		xStream.aliasField("numOfFridayNightDinners", LegacyAvailabilityAndRatesRequest.class, "numOfFridayDinners");
		// numOfSaturdayLaunches field defined the same as in XML
		// numOfholidayLaunches field defined the same as in XML
	}
	
	public Object buildFromXml(String xml) {
			return xStream.fromXML(xml);
	}

	public String returnAsXml(Object toParse) {
		return xStream.toXML(toParse);
	}
}
