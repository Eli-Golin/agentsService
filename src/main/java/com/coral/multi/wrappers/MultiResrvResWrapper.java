package com.coral.multi.wrappers;

import com.coral.multi.mappedobjects.reservresponse.MultiReservationResponse;
import com.coral.multi.mappedobjects.reservresponse.ReservationResponse;
import com.thoughtworks.xstream.XStream;

public class MultiResrvResWrapper implements XmlToObjectWrapper{
	private XStream xStream;
	
	public MultiResrvResWrapper(){
		xStream = new ReservResWrapper().getxStream();
		yayaResConfig();
	}
	
	private void yayaResConfig(){
		xStream.alias("multiResponses", MultiReservationResponse.class);
		xStream.addImplicitCollection(MultiReservationResponse.class, "responses", ReservationResponse.class);
	}
	
	public Object buildFromXml(String xml) {
		return xStream.fromXML(xml);
	}

	public String returnAsXml(Object reservRequest) {
		return xStream.toXML(reservRequest);
	}
	
}
