package com.coral.multi.wrappers;

import com.coral.multi.mappedobjects.cancelation.CancelationResponse;
import com.thoughtworks.xstream.XStream;

public class CancelResponseWrapper implements XmlToObjectWrapper {

	private XStream xStream;
	public CancelResponseWrapper(){
		xStream = new XStream();
		config();
	}
	
	private void config(){
		xStream.processAnnotations(CancelationResponse.class);
	}
	
	public Object buildFromXml(String xml) {
		return xStream.fromXML(xml);
	}

	public String returnAsXml(Object reservRequest) {
		return xStream.toXML(reservRequest);
	}

}
