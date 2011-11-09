package com.coral.multi.wrappers;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.coral.multi.general.GeneralUsage;
import com.coral.multi.mappedobjects.errorresponse.ErrorResponses;

public class ErrorResponsesWrapperTest {

	private String xml = GeneralUsage.createStringContent("errorResponses.xml"); 
	private XmlToObjectWrapper wrapper = new ErrorResponsesWrapper();
	private ErrorResponses errorResponses = (ErrorResponses)wrapper.buildFromXml(xml);
	
	@Test
	public void testNegativeResponseNotNull(){
		assertNotNull(errorResponses);
	}
	
	@Test
	public void testListNotNull(){
		assertNotNull(errorResponses.getErrorResponses());
	}
	
	@Test
	public void testListSize(){
		assertEquals(1,errorResponses.getErrorResponses().size());
	}
	
	@Test
	public void testResultStatus(){
		assertEquals(0, errorResponses.getErrorResponses().get(0).getResultStatus());
	}
	
	@Test
	public void testResultNum(){
		assertEquals(1, errorResponses.getErrorResponses().get(0).getResultNum());
	}
	
	@Test
	public void testResultMessage(){
		assertEquals("hello hello", errorResponses.getErrorResponses().get(0).getResultMessage());
	}
	
	@Test
	public void testExtraDate(){
		assertEquals("16/08/2011", errorResponses.getErrorResponses().get(0).getExtraDate());
		System.out.println("\n"+wrapper.returnAsXml(errorResponses));
	}
}
