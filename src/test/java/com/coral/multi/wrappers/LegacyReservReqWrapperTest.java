package com.coral.multi.wrappers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coral.multi.mappedobjects.reservrequest.OldReservationRequest;
import com.coral.multi.wrappers.LegacyReservReqWrapper;

public class LegacyReservReqWrapperTest{
	private BufferedReader bufReader;
	private String fileContent;
	LegacyReservReqWrapper wrapper;
	OldReservationRequest oldRequest;

	@Before
	public void setUp() throws Exception {
		wrapper = new LegacyReservReqWrapper();
		createStringContent();
		oldRequest = (OldReservationRequest)wrapper.buildFromXml(fileContent);
	}

	private void createStringContent() throws IOException{
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream = loader.getResourceAsStream("oldReservationRequests.xml");
		bufReader = new BufferedReader(new InputStreamReader(stream));
		String temp = "";
		fileContent = "";
		do{
			if(!temp.equals(""))
				fileContent+=temp+'\n';
			temp = bufReader.readLine();
			if(null == temp)
				fileContent = fileContent.substring(0, fileContent.length()-1);
		}
		while(temp != null);
	}
	
	@Test
	public void checkCreatedNotNUll(){
		Assert.assertNotNull(oldRequest);
	}
	
	@Test
	public void checkCreatedOfRightType(){
		Assert.assertTrue(oldRequest instanceof OldReservationRequest);
	}

	@Test
	public void checkLoginDetails(){
		Assert.assertEquals(700000, oldRequest.getAgentLogin().getId());
		Assert.assertEquals("test", oldRequest.getAgentLogin().getPassword());
	}
	
	@Test
	public void checkReservationsInItinerary(){
		Assert.assertTrue(oldRequest.getNumOfReservationsIten() == 1);
	}

	@Test
	public void checkGeustNationality(){
		Assert.assertEquals("USA", oldRequest.getNationality());
	}
	
	@Test
	public void checkFirstName(){
		Assert.assertEquals("Bill", oldRequest.getFirstName());
	}
	
	public void checkLastName(){
		Assert.assertEquals("Gate", oldRequest.getLastName());
	}
	
	@Test
	public void checkEdanMemberNo(){
		Assert.assertTrue(oldRequest.getDanMemberNum() == 123);
	}
	
	@Test
	public void checkNumberOfAdults(){
		Assert.assertTrue(oldRequest.getNumOfAdults() == 2);
	}
	
	@Test
	public void checkNumberOfChildren(){
		Assert.assertTrue(oldRequest.getNumOfChildren() == 3);
	}
	
	@Test
	public void checkNumOfBabieas(){
		Assert.assertTrue(oldRequest.getNumOfBabies() == 1);
	}
	
	@Test
	public void checkNumOfRooms(){
		Assert.assertTrue(oldRequest.getNumOfRooms() == 1);
	}
	
	@Test
	public void checkMop(){
		Assert.assertTrue(oldRequest.getMop() == 12);
	}
	
	@Test
	public void checkCreatedReservationDetailsNotNull(){
		Assert.assertNotNull(oldRequest.getReservRoomDetails());
	}
	
	@Test
	public void checkReservInItinerary(){
		Assert.assertTrue(oldRequest.getReservRoomDetails().getReservIten() == 1);
	}
	
	@Test
	public void checkHotelId(){
		Assert.assertTrue(oldRequest.getReservRoomDetails().getHotelId() == 1);
	}
	
	@Test 
	public void checkRoomType(){
		Assert.assertEquals("STD", oldRequest.getReservRoomDetails().getRoomType());
	}
	
	@Test
	public void checkArrivalDate(){
		Assert.assertEquals("20/12/2007", oldRequest.getReservRoomDetails().getArrivalDate());
	}
	
	@Test
	public void checkDeprtureDate(){
		Assert.assertEquals("22/12/2007", oldRequest.getReservRoomDetails().getDepartureDate());
	}
	
	@Test
	public void checkBoardArrangement(){
		Assert.assertEquals("BB", oldRequest.getReservRoomDetails().getBoardArrangement());
	}
	
	@Test
	public void checkNumOfFridayNightDinners(){
		Assert.assertTrue(oldRequest.getReservRoomDetails().getFridayDinnerNum() == 1);
	}
	
	@Test
	public void checkNumOfSaturdayLunches(){
		Assert.assertTrue(oldRequest.getReservRoomDetails().getSaturdayLunchesNum() == 2);
	}
	
	@Test
	public void checkNumOfHolidayLunches(){
		Assert.assertTrue(oldRequest.getReservRoomDetails().getHolidayLunchesNum() == 1);
	}
	
	@Test
	public void checkSpecialRequestNotNull(){
		Assert.assertNotNull(oldRequest.getReservRoomDetails().getSpecialReq());
	}
	
	@Test
	public void checkRequestNo(){
		Assert.assertEquals(oldRequest.getReservRoomDetails().getSpecialReq().getReqNum(),"1");
	}
	
	@Test
	public void checkRequestType(){
		Assert.assertEquals(oldRequest.getReservRoomDetails().getSpecialReq().getReqType(), "2");
	}
	
	@Test
	public void checkXmlRepresentation() throws IOException{
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream = loader.getResourceAsStream("oldReservationRequests.xml");
		BufferedReader buf = new BufferedReader(new InputStreamReader(stream));
		String temp = "";
		String content = "";
		do{
			if(!temp.equals(""))
				content+=temp+'\n';
			temp = buf.readLine();
			if(null == temp)
				content = content.substring(0, content.length()-1);
		}
		while(temp != null);
		System.out.println(content);
		Assert.assertEquals(content,wrapper.returnAsXml(oldRequest));
	}
	
	@After
	public void tearDown(){
		try {
			bufReader.close();
		} catch (IOException e) {
			System.out.println("Could not close the bufReader");
			e.printStackTrace();
		}
	}
}
