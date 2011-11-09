package com.coral.multi.wrappers;

public interface XmlToObjectWrapper {
	public Object buildFromXml(String xml);
	public String returnAsXml(Object toParse);
}
