package com.coral.multi.wrappers;

import com.coral.multi.mappedobjects.errorresponse.ErrorResponse;
import com.coral.multi.mappedobjects.errorresponse.ErrorResponses;
import com.thoughtworks.xstream.XStream;

public class ErrorResponsesWrapper implements XmlToObjectWrapper {
	
	private XStream xStream;
	
	public ErrorResponsesWrapper(){
		xStream = new XStream();
		config();
	}

	private void config(){
		xStream.processAnnotations(ErrorResponses.class);
		xStream.processAnnotations(ErrorResponse.class);
	}
	
	public Object buildFromXml(String xml) {
		return xStream.fromXML(xml); 
	}

	public String returnAsXml(Object toParse) {
		return xStream.toXML(toParse);
	}

}
