package com.coral.multi.wrappers;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coral.multi.general.GeneralUsage;
import com.coral.multi.mappedobjects.availability.responses.multi.MultiAvailabilityResponse;


public class MultiAvailabilityResponseWrapperTest {
	private String xml = GeneralUsage.createStringContent("availabilityResponsesNew.xml");
	private XmlToObjectWrapper wrapper = new MultiAvailabilityResponseWrapper();
	private MultiAvailabilityResponse availabilityResObj = (MultiAvailabilityResponse)wrapper.buildFromXml(xml);
	
	@Test
	public void testAvailabilityResObjNotNull(){
		assertNotNull(availabilityResObj);
	}
	
	@Test
	public void testHotelsNotNull(){
		assertNotNull(availabilityResObj.getHotels());
	}
	
	@Test
	public void testHotelsListSize(){
		assertEquals(1, availabilityResObj.getHotels().getHotels().size());
	}
	
	@Test
	public void testNumOfReservInItiner(){
		assertEquals(1, availabilityResObj.getHotels().getNumOfReservInItiner());
	}
	
	@Test 
	public void testFirstHotelAreaCd(){
		assertEquals(1, availabilityResObj.getHotels().getHotels().get(0).getAreaCd());
	}
	
	@Test
	public void testFirstHotelReserInItiner(){
		assertEquals(1, availabilityResObj.getHotels().getHotels().get(0).getReservInItiner());
	}
	
	@Test
	public void testFirstHotelId(){
		assertEquals(1, availabilityResObj.getHotels().getHotels().get(0).getHotelId());
	}
	
	@Test
	public void testFirstHotelIdDescription(){
		assertEquals("DT", availabilityResObj.getHotels().getHotels().get(0).getHotelIdDescription());
	}
	
	@Test
	public void testFirstHotelDescription(){
		assertEquals("This is Hotel Description", availabilityResObj.getHotels().getHotels().get(0).getHotelDescription());
	}
	
	@Test
	public void testFirstHotelPicturesNotNull(){
		assertNotNull(availabilityResObj.getHotels().getHotels().get(0).getPictures());
	}
	
	@Test
	public void testFirstHotelPicturesListSize(){
		assertEquals(1, availabilityResObj.getHotels().getHotels().get(0).getPictures().getPictures().size());
	}
	
	@Test
	public void testFirstHotelPictureType(){
		assertEquals("1", availabilityResObj.getHotels().getHotels().get(0).getPictures().getPictures().get(0).getPictureType());
	}
	
	@Test
	public void testFirstHotelPictureUrl(){
		assertEquals("http://www.danhotels.co.il/danSite/eng/images/eng/hotelstoolbar/m1.gif",  availabilityResObj.getHotels().getHotels().get(0).getPictures().getPictures().get(0).getPictureUrl());
	}
	
	@Test
	public void testFirstHotelComments(){
		assertEquals("Hotel Level Commnets", availabilityResObj.getHotels().getHotels().get(0).getComments());
	}
	
	
	@Test
	public void testFirstHotelRoomsNotNull(){
		assertNotNull(availabilityResObj.getHotels().getHotels().get(0).getRooms());
	}
	
	@Test
	public void testFirstHotelFirstRoomArrangementArrivalDate(){
		assertEquals("15/08/2011", availabilityResObj.getHotels().getHotels().get(0).getRooms().getRoomsArrangement().get(0).getArrivalDate());
	}
	
	@Test
	public void testFirstHotelFirstRoomArrangementDepartureDate(){
		assertEquals("17/08/2011", availabilityResObj.getHotels().getHotels().get(0).getRooms().getRoomsArrangement().get(0).getDepartureDate());
	}
	
	@Test
	public void testFirstHotelRoomsListSize(){
		assertEquals(2, availabilityResObj.getHotels().getHotels().get(0).getRooms().getRoomsArrangement().get(0).getRooms().size());
	}
	
	@Test
	public void testFirstHotelFirstRoomType(){
		assertEquals("DLX", availabilityResObj.getHotels().getHotels().get(0).getRooms().getRoomsArrangement().get(0).getRooms().get(0).getRoomType());
	}
	
	@Test
	public void testFirstHotelFirstRoomTypeDescription(){
		assertEquals("Deluxe", availabilityResObj.getHotels().getHotels().get(0).getRooms().getRoomsArrangement().get(0).getRooms().get(0).getRoomTypeDescription());
	}
	
	@Test
	public void testFirstHotelFirstRoomDescription(){
		assertEquals("Rooms are large and comfortable with a sitting\n" +
				"							area and facing the exciting cityof Tel Aviv. Double glazed\n" +
				"							windows.", availabilityResObj.getHotels().getHotels().get(0).getRooms().getRoomsArrangement().get(0).getRooms().get(0).getRoomDescription());
	}
	
	@Test
	public void testFirstHotelFirstRoomBoardArrangement(){
		assertEquals("BB", availabilityResObj.getHotels().getHotels().get(0).getRooms().getRoomsArrangement().get(0).getRooms().get(0).getBoardArrangement());
	}
	
	@Test
	public void testFirstHotelFirstRoomPicturesNotNull(){
		assertNotNull(availabilityResObj.getHotels().getHotels().get(0).getRooms().getRoomsArrangement().get(0).getRooms().get(0).getPictures().getPictures());
	}
	
	@Test
	public void testFirstHotelFirstRoomPicturesListSize(){
		assertEquals(1, availabilityResObj.getHotels().getHotels().get(0).getRooms().getRoomsArrangement().get(0).getRooms().get(0).getPictures().getPictures().size());
	}
	
	@Test
	public void testFirstHotelFirstRoomFirstPictureType(){
		assertEquals("1", availabilityResObj.getHotels().getHotels().get(0).getRooms().getRoomsArrangement().get(0).getRooms().get(0).getPictures().getPictures().get(0).getPictureType());
	}
	
	@Test
	public void testFirstHotelFirstRoomFirstPictureUrl(){
		assertEquals("http://www.danhotels.com/danSite/eng/Images/eng/Dan\n" +
"									Tel Aviv/DLX.jpg", availabilityResObj.getHotels().getHotels().get(0).getRooms().getRoomsArrangement().get(0).getRooms().get(0).getPictures().getPictures().get(0).getPictureUrl());
	}
	
	@Test
	public void testFirstHotelFirstRoomPrice(){
		assertTrue(availabilityResObj.getHotels().getHotels().get(0).getRooms().getRoomsArrangement().get(0).getRooms().get(0).getPrice() == 598);
	}
	
	@Test
	public void testFirstHotelFirstRoomCurrency(){
		assertEquals("$", availabilityResObj.getHotels().getHotels().get(0).getRooms().getRoomsArrangement().get(0).getRooms().get(0).getCurrency());
	}
	
	@Test
	public void testFirstHotelFirstRoomDeal(){
		assertEquals(1, availabilityResObj.getHotels().getHotels().get(0).getRooms().getRoomsArrangement().get(0).getRooms().get(0).getDeal());
	}
	
	@Test
	public void testFirstHotelFirstRoomAvailability(){
		assertEquals("1", availabilityResObj.getHotels().getHotels().get(0).getRooms().getRoomsArrangement().get(0).getRooms().get(0).getAvailability());
	}
	
	@Test 
	public void testFirstHotelFirstRoomCombinationString(){
		assertEquals("aaaa", availabilityResObj.getHotels().getHotels().get(0).getRooms().getRoomsArrangement().get(0).getRooms().get(0).getCombinationString());
	}
	@Test
	public void testFirstHotelFirstRoomComments(){
		assertEquals("Room Level Comments", availabilityResObj.getHotels().getHotels().get(0).getRooms().getRoomsArrangement().get(0).getRooms().get(0).getComments());
		System.out.println(wrapper.returnAsXml(availabilityResObj));
	}
}
