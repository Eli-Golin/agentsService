package com.coral.multi.wrappers;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coral.multi.mappedobjects.reservresponse.ReservationResponse;
import com.coral.multi.wrappers.ReservResWrapper;

public class ReservResWrapperTest {

	private String xml;
	private ReservationResponse responseObj;
	private ReservResWrapper wrapper;

	@Before
	public void setUp() throws Exception {
		createStringContent();
		wrapper = new ReservResWrapper();
		responseObj = (ReservationResponse)wrapper.buildFromXml(xml);
	}

	private void createStringContent() throws IOException{
		BufferedReader bufReader = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream = loader.getResourceAsStream("reservationResponses.xml");
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
	public void checkReservationResponsesNotNull(){
		Assert.assertNotNull(responseObj);
	}
	
	@Test
	public void checkNumOfReservationsIten(){
		Assert.assertTrue(responseObj.getNumOfReservationsIten() == 1);
	}
	
	@Test
	public void checkMultReservNum(){
		Assert.assertTrue(responseObj.getMultReservNum() == 772346);
	}
	
	@Test
	public void checkStatus(){
		Assert.assertEquals("Reservation Action", responseObj.getStatus());
	}
	
	@Test
	public void checkTotalPrice(){
		Assert.assertTrue(responseObj.getTotalPrice() == 408);
	}
	
	@Test
	public void checkCurrency(){
		Assert.assertEquals("$", responseObj.getCurrency());
	}
	
	@Test
	public void checkCommnets(){
		Assert.assertEquals("Resrvation Response", responseObj.getComments());
	}
	
	@Test
	public void checkReservationResponseNotNull(){
		Assert.assertNotNull(responseObj.getResponses());
	}
	
	@Test
	public void checkReservationInItinerary(){
		Assert.assertTrue(responseObj.getResponses().getReservIten() == 1);
	}
	
	@Test
	public void checkReservationNum(){
		Assert.assertTrue(responseObj.getResponses().getReservNum() == 210);
	}
	
	@Test
	public void checkHotelId(){
		Assert.assertTrue(responseObj.getResponses().getHotelId() == 1);
	}
	
	@Test
	public void checkRoomType(){
		Assert.assertEquals("STD", responseObj.getResponses().getRoomType());
	}
	
	@Test
	public void checkRoomTypeDescription(){
		Assert.assertEquals("Standard Room", responseObj.getResponses().getRoomTypeDescription());
	}
	
	@Test
	public void checkArrivalDate(){
		Assert.assertEquals("2007-12-20", responseObj.getResponses().getArrivalDate());
	}
	
	@Test
	public void checkDepartureDate(){
		Assert.assertEquals("2007-12-22", responseObj.getResponses().getDepartureDate());
	}
	
	@Test
	public void checkInnerStatus(){
		Assert.assertEquals("Reserved", responseObj.getResponses().getStatus());
	}
	
	@Test
	public void checkPrice(){
		Assert.assertTrue(responseObj.getResponses().getPrice() == 501);
	}
	
	@Test
	public void checkInnerCurrency(){
		Assert.assertEquals("$", responseObj.getResponses().getCurrency());
		System.out.println(wrapper.returnAsXml(responseObj));
	}
	
	@After
	public void tearDown() throws Exception {
		xml = "";
	}

}
