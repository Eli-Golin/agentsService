package com.coral.multi.wrappers;

import com.coral.multi.mappedobjects.cancelation.CancelationRequest;
import com.coral.multi.mappedobjects.general.AgentLogin;
import com.thoughtworks.xstream.XStream;

public class CancelRequestWrapper implements XmlToObjectWrapper {

	private XStream xStream;
	
	public CancelRequestWrapper(){
		xStream = new XStream();
		config();
	}
	
	private void config(){
		xStream.processAnnotations(CancelationRequest.class);
		xStream.processAnnotations(AgentLogin.class);
	}
		
	public Object buildFromXml(String xml) {
		return xStream.fromXML(xml);
	}

	public String returnAsXml(Object reservRequest) {
		return xStream.toXML(reservRequest);
	}

}
