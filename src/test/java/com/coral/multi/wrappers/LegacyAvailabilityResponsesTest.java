package com.coral.multi.wrappers;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.coral.multi.general.GeneralUsage;
import com.coral.multi.mappedobjects.availability.responses.legacy.LegacyAvailabilityAndRatesResponses;
import com.coral.multi.wrappers.LegacyAvailabilityAndRateResponsesWrapper;
import com.coral.multi.wrappers.XmlToObjectWrapper;

public class LegacyAvailabilityResponsesTest {
	private String xmlFromFile;
	private XmlToObjectWrapper wrapper;
	private LegacyAvailabilityAndRatesResponses responseObject;
	
	@Before
	public void setUp() throws Exception {
		xmlFromFile = GeneralUsage.createStringContent("availabilityAndRatesResponses.xml");
		wrapper = new LegacyAvailabilityAndRateResponsesWrapper();
		responseObject = (LegacyAvailabilityAndRatesResponses)wrapper.buildFromXml(xmlFromFile);
	}
	
	@Test
	public void testResponseObjNotNull(){
		assertNotNull(responseObject);
	}
	
	@Test
	public void testRightType(){
		assertTrue(responseObject instanceof LegacyAvailabilityAndRatesResponses);
	}
	
	@Test
	public void testNumOfResrvInItern(){
		assertEquals(1,responseObject.getNumOfResInIten());
	}
	
	@Test
	public void testAvailabilitiResponsesNotNull(){
		assertNotNull(responseObject.getResponses());
	}
	
	@Test
	public void testAvailabilityResponsesListSize(){
		assertEquals(1, responseObject.getResponses().size());
	}
	
	@Test
	public void testResrvInIten(){
		assertEquals(1,responseObject.getResponses().get(0).getReservInIten());
	}
	
	@Test
	public void testHotelAreaCd(){
		assertEquals(1, responseObject.getResponses().get(0).getHotelAreaCd());
	}
	
	@Test
	public void testArrivalDate(){
		assertEquals("15/02/2008", responseObject.getResponses().get(0).getArrivalDate());
	}
	
	@Test
	public void testDeprtureDate(){
		assertEquals("17/02/2008", responseObject.getResponses().get(0).getDepartureDate());
	}
	
	@Test
	public void testHotelsNotNull(){
		assertNotNull(responseObject.getResponses().get(0).getHotels());
	}
	
	@Test
	public void testHotelNotNull(){
		assertNotNull(responseObject.getResponses().get(0).getHotels().getHotels());
	}
	
	@Test
	public void testNumOfHotels(){
		assertEquals(1, responseObject.getResponses().get(0).getHotels().getHotels().size());
	}
	
	@Test
	public void testHotelId(){
		assertEquals(1, responseObject.getResponses().get(0).getHotels().getHotels().get(0).getHotelId());
	}
	
	@Test
	public void testHotelIdDescription(){
		assertEquals("DT", responseObject.getResponses().get(0).getHotels().getHotels().get(0).getHotelIdDescription());
	}
	
	@Test
	public void testHotelPicturesnotNull(){
		assertNotNull(responseObject.getResponses().get(0).getHotels().getHotels().get(0).getPictures());
	}
	
	@Test
	public void testHotelPicturesSize(){
		assertEquals(1, responseObject.getResponses().get(0).getHotels().getHotels().get(0).getPictures().getPictures().size());
	}
	
	@Test
	public void testHotelPictureType(){
		assertEquals(1, responseObject.getResponses().get(0).getHotels().getHotels().get(0).getPictures().getPictures().get(0).getPictureType());
	}
	
	@Test
	public void testHotelPictureUrl(){
		assertEquals("http://www.danhotels.co.il/danSite/eng/images/eng/hotelstoolbar/m1.gif", responseObject.getResponses().get(0).getHotels().getHotels().get(0).getPictures().getPictures().get(0).getPictureUrl());
	}
	
	@Test
	public void testHotelRoomsNotNull(){
		 assertNotNull(responseObject.getResponses().get(0).getHotels().getHotels().get(0).getRooms());
	}
	
	@Test
	public void testHotelRoomsListNotNull(){
		assertNotNull(responseObject.getResponses().get(0).getHotels().getHotels().get(0).getRooms().getRooms());
	}
	
	@Test
	public void testHotelRoomsListSize(){
		assertEquals(5, responseObject.getResponses().get(0).getHotels().getHotels().get(0).getRooms().getRooms().size());
	}
	
	@Test
	public void testFirstRoomType(){
		assertEquals("DLX", responseObject.getResponses().get(0).getHotels().getHotels().get(0).getRooms().getRooms().get(0).getRoomType());
	}
	
	@Test
	public void testFirstRoomTypeDescription(){
		assertEquals("Deluxe", responseObject.getResponses().get(0).getHotels().getHotels().get(0).getRooms().getRooms().get(0).getRoomTypeDescription());
	}
	
	@Test
	public void testFirstRoomDescription(){
		assertEquals("Rooms are large and comfortable with a sitting\n							area and facing the exciting cityof" +
				" Tel Aviv. Double glazed\n							windows.", responseObject.getResponses().get(0).getHotels().getHotels().get(0).getRooms().getRooms().get(0).getRoomDescription());
	}
	
	@Test
	public void testFirstRoomPicturesNotNull(){
		assertNotNull(responseObject.getResponses().get(0).getHotels().getHotels().get(0).getRooms().getRooms().get(0).getPictures());
	}
	
	@Test
	public void testFirstRoomPictureListSize(){
		assertEquals(1, responseObject.getResponses().get(0).getHotels().getHotels().get(0).getRooms().getRooms().get(0).getPictures().getPictures().size());
	}
	
	@Test
	public void testFirstRoomFirstPictureType(){
		assertEquals(1, responseObject.getResponses().get(0).getHotels().getHotels().get(0).getRooms().getRooms().get(0).getPictures().getPictures().get(0).getPictureType());
	}
	
	@Test
	public void testFirstRoomFirstPictureUrl(){
		assertEquals("http://www.danhotels.com/danSite/eng/Images/eng/Dan\n									Tel Aviv/DLX.jpg", responseObject.getResponses().get(0).getHotels().getHotels().get(0).getRooms().getRooms().get(0).getPictures().getPictures().get(0).getPictureUrl());
	}
	
	@Test
	public void testFirstRoomPrice(){
		assertEquals(598, responseObject.getResponses().get(0).getHotels().getHotels().get(0).getRooms().getRooms().get(0).getPrice(),0);
	}
	
	@Test
	public void testFirstRoomAvailability(){
		assertEquals(1, responseObject.getResponses().get(0).getHotels().getHotels().get(0).getRooms().getRooms().get(0).getAvailability());
		System.out.println(wrapper.returnAsXml(responseObject));
	}
}
