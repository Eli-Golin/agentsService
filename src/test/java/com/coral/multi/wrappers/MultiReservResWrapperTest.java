package com.coral.multi.wrappers;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coral.multi.mappedobjects.reservresponse.MultiReservationResponse;
import com.coral.multi.wrappers.MultiResrvResWrapper;

public class MultiReservResWrapperTest {

	private String xml;
	private MultiReservationResponse responseObj;
	private MultiResrvResWrapper wrapper;
	
	@Before
	public void setUp() throws Exception {
		wrapper = new MultiResrvResWrapper();
		createStringContent();
		responseObj = (MultiReservationResponse)wrapper.buildFromXml(xml);
	}

	private void createStringContent() throws IOException{
		BufferedReader bufReader = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream = loader.getResourceAsStream("multiReservationResponse.xml");
		bufReader = new BufferedReader(new InputStreamReader(stream));
		String temp = "";
		xml = "";
		do{
			if(!temp.equals(""))
				xml+=temp+'\n';
			temp = bufReader.readLine();
			if(null == temp)
				xml = xml.substring(0, xml.length()-1);
		}
		while(temp != null);
		bufReader.close();
	}
	
	@Test
	public void checkYayaReservResponseNotNull(){
		Assert.assertNotNull(responseObj);
	}
	
	@Test
	public void checkResponsesNotNull(){
		Assert.assertNotNull(responseObj.getResponses());
	}
	
	@Test
	public void checkResponseLength(){
		Assert.assertEquals(2, responseObj.getResponses().size());
	}
	
	@Test
	public void checkResrvInItenarary1(){
		Assert.assertTrue(responseObj.getResponses().get(0).getNumOfReservationsIten() == 1);
	}
	
	@Test
	public void checkMultReservNum(){
		Assert.assertTrue(responseObj.getResponses().get(0).getMultReservNum() == 1);
	}
	
	@Test
	public void checkStatus1(){
		Assert.assertEquals("Reservation Action",responseObj.getResponses().get(0).getStatus());
	}
	
	@Test
	public void checkTotalPrice(){
		Assert.assertTrue(responseObj.getResponses().get(0).getTotalPrice() == 408);
	}
	
	@Test
	public void checkCurrency1(){
		Assert.assertEquals("$",responseObj.getResponses().get(0).getCurrency());
	}
	
	@Test
	public void checkComments(){
		Assert.assertEquals("Resrvation Response",responseObj.getResponses().get(0).getComments());
	}
	
	@Test
	public void checkReservationResponseNotNull(){
		Assert.assertNotNull(responseObj.getResponses().get(0).getResponses());
	}
	
	@Test
	public void checkResrvInItenarary2(){
		Assert.assertTrue(responseObj.getResponses().get(0).getResponses().getReservIten() == 1);
	}
	
	@Test
	public void checkReservNum(){
		Assert.assertTrue(responseObj.getResponses().get(0).getResponses().getReservNum() == 210);
	}
	
	@Test
	public void checkHotelId(){
		Assert.assertTrue(responseObj.getResponses().get(0).getResponses().getHotelId() == 1);
	}
	
	@Test
	public void checkRoomType(){
		Assert.assertEquals("STD",responseObj.getResponses().get(0).getResponses().getRoomType());
	}
	
	@Test
	public void checkRoomDescription(){
		Assert.assertEquals("Description",responseObj.getResponses().get(0).getResponses().getRoomDescription());
	}
	
	@Test
	public void checkArrivalDate(){
		Assert.assertEquals("2007-12-20",responseObj.getResponses().get(0).getResponses().getArrivalDate());
	}
	
	@Test
	public void checkDepartureDate(){
		Assert.assertEquals("2007-12-22",responseObj.getResponses().get(0).getResponses().getDepartureDate());
	}
	
	@Test
	public void checkStatus2(){
		Assert.assertEquals("Reserved",responseObj.getResponses().get(0).getResponses().getStatus());
	}
	
	@Test
	public void checkPrice(){
		Assert.assertTrue(responseObj.getResponses().get(0).getResponses().getPrice() == 612);
	}
	
	@Test
	public void checkCurrency2(){
		Assert.assertEquals("$", responseObj.getResponses().get(0).getResponses().getCurrency());
		System.out.println(wrapper.returnAsXml(responseObj));
	}
	
	@After
	public void tearDown() throws Exception {
		xml = "";
	}
}
