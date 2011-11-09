package com.coral.multi.wrappers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coral.multi.mappedobjects.reservrequest.MultiReservationRequest;

public class MultiReservationReqWrapperTest {
	
	@Before
	public void setUp() throws Exception {
		createStringContent();
		wrapper = new MultiReservReqWrapper();
		requestObj = (MultiReservationRequest)wrapper.buildFromXml(xml);
	}
	
	private void createStringContent() throws IOException{
		BufferedReader	bufReader = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream = loader.getResourceAsStream("multiReservationRequest.xml");
		bufReader = new BufferedReader(new InputStreamReader(stream));
		String temp = "";
		do{
			temp = bufReader.readLine();
			if(temp != null && !temp.equals("")){
				xml+=temp+'\n';
			}
			if(null == temp)
				xml = xml.substring(0, xml.length()-1);
		}
		while(temp != null);
		bufReader.close();
	}
	
	@Test
	public void checkRequestObjectNotNull(){
		Assert.assertNotNull(requestObj);
	}
	
	@Test
	public void checkYayaType(){
		Assert.assertTrue(requestObj instanceof MultiReservationRequest);
	}
	
	@Test
	public void checkListNotNull(){
		Assert.assertNotNull(requestObj.getReservDetails());
	}
	
	@Test
	public void checkListLength(){
		Assert.assertEquals(4, requestObj.getReservDetails().size());
	}
	
	@Test
	public void checkLoginNotNull(){
		Assert.assertNotNull(requestObj.getReservDetails().get(0).getAgentLogin());
	}
	
	@Test
	public void checkAgentId(){
		Assert.assertEquals(700000,requestObj.getReservDetails().get(0).getAgentLogin().getId());
	}
	
	@Test
	public void checkAgentPassword(){
		Assert.assertEquals("test",requestObj.getReservDetails().get(0).getAgentLogin().getPassword());
	}
	
	@Test
	public void checkNumReservInItenarary(){
		Assert.assertTrue(requestObj.getReservDetails().get(0).getNumOfReservationsIten() == 1);
	}
	
	@Test
	public void checkNationality(){
		Assert.assertEquals("IL", requestObj.getReservDetails().get(0).getNationality());
	}
	
	@Test
	public void checkGuestsFirstName(){
		Assert.assertEquals("Eli", requestObj.getReservDetails().get(0).getFirstName());
	}
	
	@Test
	public void checkGuestslastName(){
		Assert.assertEquals("Eli", requestObj.getReservDetails().get(0).getLastName());
	}
	@Test
	public void checkLoginNotNull2(){
		Assert.assertNotNull(requestObj.getReservDetails().get(1).getAgentLogin());
	}
	
	@Test
	public void checkEdanNum(){
		Assert.assertTrue(requestObj.getReservDetails().get(0).getDanMemberNum() == 0);
	}
	
	@Test
	public void checkNumOfAdults(){
		Assert.assertTrue(requestObj.getReservDetails().get(0).getNumOfAdults() == 0);
	}
	
	@Test
	public void checkNumOfChildren(){
		Assert.assertTrue(requestObj.getReservDetails().get(0).getNumOfChildren() == 2);
	}
	
	@Test
	public void checkNumOfBabies(){
		Assert.assertTrue(requestObj.getReservDetails().get(0).getNumOfBabies() == 0);
	}
	
	@Test
	public void checkNumOfRooms(){
		Assert.assertTrue(requestObj.getReservDetails().get(0).getNumOfRooms() == 1);
	}
	
	@Test
	public void checkMop(){
		Assert.assertTrue(requestObj.getReservDetails().get(0).getMop() == 1);
	}
	
	@Test
	public void checkTravelAgentRef(){
		Assert.assertEquals("1847872618", requestObj.getReservDetails().get(0).getTravAgentRef());
	}
	
	@Test
	public void checkReservationRequestNotNull(){
		Assert.assertNotNull(requestObj.getReservDetails().get(0).getReservRoomDetails());
	}
	
	@Test
	public void checkReservInItenarary(){
		Assert.assertTrue(requestObj.getReservDetails().get(0).getReservRoomDetails().getReservIten() == 1);
	}
	
	@Test
	public void checkHotelId(){
		Assert.assertTrue(requestObj.getReservDetails().get(0).getReservRoomDetails().getHotelId() == 8);
	}
	
	@Test
	public void checkRoomType(){
		Assert.assertEquals("STD",requestObj.getReservDetails().get(0).getReservRoomDetails().getRoomType());
	}
	
	@Test
	public void checkArrivalDate(){
		Assert.assertEquals("18/09/2011",requestObj.getReservDetails().get(0).getReservRoomDetails().getArrivalDate());
	}
	
	@Test
	public void checkDepartureDate(){
		Assert.assertEquals("21/09/2011",requestObj.getReservDetails().get(0).getReservRoomDetails().getDepartureDate());
	}
	
	@Test
	public void checkBoardArrangement(){
		Assert.assertEquals("BB",requestObj.getReservDetails().get(0).getReservRoomDetails().getBoardArrangement());
	}
	
	@Test
	public void checkFridayNightDinners(){
		Assert.assertTrue(requestObj.getReservDetails().get(0).getReservRoomDetails().getFridayDinnerNum() == 0);
	}
	
	@Test
	public void checkSaturdayLunches(){
		Assert.assertTrue(requestObj.getReservDetails().get(0).getReservRoomDetails().getSaturdayLunchesNum() == 0);
	}
	
	@Test
	public void checkHolidayLunches(){
		Assert.assertTrue(requestObj.getReservDetails().get(0).getReservRoomDetails().getHolidayLunchesNum() == 0);
	}
	
	@Test
	public void checkPrice(){
		Assert.assertTrue(requestObj.getReservDetails().get(0).getReservRoomDetails().getPrice() == 1200);
	}
	
	@Test
	public void checkCurrency(){
		Assert.assertTrue(requestObj.getReservDetails().get(0).getReservRoomDetails().getCurrency().equals("NIS"));
	}
	
	@Test
	public void checkSpecialRequestNoNull(){
		Assert.assertNotNull(requestObj.getReservDetails().get(0).getReservRoomDetails().getSpecialReq());
	}
	
	@Test
	public void checkSpecialReqNum(){
		Assert.assertEquals(requestObj.getReservDetails().get(0).getReservRoomDetails().getSpecialReq().getReqNum(), "");
	}
	
	@Test
	public void checkSpecialReqType(){
		Assert.assertEquals(requestObj.getReservDetails().get(0).getReservRoomDetails().getSpecialReq().getReqType(), "");
	}
	
	@Test
	public void checkComments(){
		Assert.assertEquals("First comment", requestObj.getReservDetails().get(0).getReservRoomDetails().getComments());
		System.out.println(xml);
		System.out.println("\n--------------------------------------------------");
		System.out.println(wrapper.returnAsXml(requestObj));
	}
	private MultiReservationRequest requestObj;
	private MultiReservReqWrapper wrapper;
	private String xml = "";

	
	@Test
	public void checkAgentId2(){
		Assert.assertEquals(700000,requestObj.getReservDetails().get(1).getAgentLogin().getId());
	}
	
	@Test
	public void checkAgentPassword2(){
		Assert.assertEquals("test",requestObj.getReservDetails().get(1).getAgentLogin().getPassword());
	}
	
	@Test
	public void checkNumReservInItenarary2(){
		Assert.assertTrue(requestObj.getReservDetails().get(1).getNumOfReservationsIten() == 1);
	}
	
	@Test
	public void checkNationality2(){
		Assert.assertEquals("IL", requestObj.getReservDetails().get(1).getNationality());
	}
	
	@Test
	public void checkGuestsFirstName2(){
		Assert.assertEquals("Hilmi", requestObj.getReservDetails().get(1).getFirstName());
	}
	
	@Test
	public void checkGuestslastName2(){
		Assert.assertEquals("Hilmi", requestObj.getReservDetails().get(1).getLastName());
	}
	
	@Test
	public void checkEdanNum2(){
		Assert.assertTrue(requestObj.getReservDetails().get(1).getDanMemberNum() == 0);
	}
	
	@Test
	public void checkNumOfAdults2(){
		Assert.assertTrue(requestObj.getReservDetails().get(1).getNumOfAdults() == 1);
	}
	
	@Test
	public void checkNumOfChildren2(){
		Assert.assertTrue(requestObj.getReservDetails().get(1).getNumOfChildren() == 2);
	}
	
	@Test
	public void checkNumOfBabies2(){
		Assert.assertTrue(requestObj.getReservDetails().get(1).getNumOfBabies() == 0);
	}
	
	@Test
	public void checkNumOfRooms2(){
		Assert.assertTrue(requestObj.getReservDetails().get(1).getNumOfRooms() == 1);
	}
	
	@Test
	public void checkMop2(){
		Assert.assertTrue(requestObj.getReservDetails().get(1).getMop() == 1);
	}
	
	@Test
	public void checkTravelAgentRef2(){
		Assert.assertEquals("1847872618", requestObj.getReservDetails().get(1).getTravAgentRef());
	}
	
	@Test
	public void checkReservationRequestNotNull2(){
		Assert.assertNotNull(requestObj.getReservDetails().get(1).getReservRoomDetails());
	}
	
	@Test
	public void checkReservInItenarary2(){
		Assert.assertTrue(requestObj.getReservDetails().get(1).getReservRoomDetails().getReservIten() == 1);
	}
	
	@Test
	public void checkHotelId2(){
		Assert.assertTrue(requestObj.getReservDetails().get(1).getReservRoomDetails().getHotelId() == 8);
	}
	
	@Test
	public void checkRoomType2(){
		Assert.assertEquals("STD",requestObj.getReservDetails().get(1).getReservRoomDetails().getRoomType());
	}
	
	@Test
	public void checkArrivalDate2(){
		Assert.assertEquals("18/09/2011",requestObj.getReservDetails().get(1).getReservRoomDetails().getArrivalDate());
	}
	
	@Test
	public void checkDepartureDate2(){
		Assert.assertEquals("21/09/2011",requestObj.getReservDetails().get(1).getReservRoomDetails().getDepartureDate());
	}
	
	@Test
	public void checkBoardArrangement2(){
		Assert.assertEquals("BB",requestObj.getReservDetails().get(1).getReservRoomDetails().getBoardArrangement());
	}
	
	@Test
	public void checkFridayNightDinners2(){
		Assert.assertTrue(requestObj.getReservDetails().get(1).getReservRoomDetails().getFridayDinnerNum() == 0);
	}
	
	@Test
	public void checkSaturdayLunches2(){
		Assert.assertTrue(requestObj.getReservDetails().get(1).getReservRoomDetails().getSaturdayLunchesNum() == 0);
	}
	
	@Test
	public void checkHolidayLunches2(){
		Assert.assertTrue(requestObj.getReservDetails().get(1).getReservRoomDetails().getHolidayLunchesNum() == 0);
	}
	
	@Test
	public void checkPrice2(){
		Assert.assertTrue(requestObj.getReservDetails().get(1).getReservRoomDetails().getPrice() == 820);
	}
	
	@Test
	public void checkCurrency2(){
		Assert.assertTrue(requestObj.getReservDetails().get(1).getReservRoomDetails().getCurrency().equals("$"));
	}
	
	@Test
	public void checkSpecialRequestNoNull2(){
		Assert.assertNotNull(requestObj.getReservDetails().get(1).getReservRoomDetails().getSpecialReq());
	}
	
	@Test
	public void checkSpecialReqNum2(){
		Assert.assertEquals(requestObj.getReservDetails().get(1).getReservRoomDetails().getSpecialReq().getReqNum(), "");
	}
	
	@Test
	public void checkSpecialReqType2(){
		Assert.assertEquals(requestObj.getReservDetails().get(1).getReservRoomDetails().getSpecialReq().getReqType(), "");
	}
	
	@Test
	public void checkComments2(){
		Assert.assertEquals("Second comment", requestObj.getReservDetails().get(1).getReservRoomDetails().getComments());
		System.out.println(xml);
		System.out.println("\n--------------------------------------------------");
		System.out.println(wrapper.returnAsXml(requestObj));
	}
	
	@After
	public void tearDown(){
		xml = "";
	}
}
