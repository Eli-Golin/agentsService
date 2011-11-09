package com.coral.multi.wrappers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.coral.multi.general.GeneralUsage;
import com.coral.multi.mappedobjects.availability.requests.multi.MultiAvailabilityRequest;


public class MultiAvailabilityRequestWrapperTest {
	private String xml = GeneralUsage.createStringContent("availabilityRequestNew.xml"); 
	private XmlToObjectWrapper wrapper = new MultiAvailabilityRequestWrapper();
	private MultiAvailabilityRequest availabilityReqObj = (MultiAvailabilityRequest)wrapper.buildFromXml(xml);
	
	@Test
	public void testAvailabilityReqObjNotNull(){
		assertNotNull(availabilityReqObj);
	}
	
	@Test
	public void testHotelsNotNull(){
		assertNotNull(availabilityReqObj.getReservationHotels());
	}
	
	
	@Test
	public void testHotelsListNotNull(){
		assertNotNull(availabilityReqObj.getReservationHotels());
	}
	
	@Test
	public void testListSize(){
		assertEquals(2, availabilityReqObj.getReservationHotels().getHotels().size());
	}
	
	@Test
	public void testLoginNotNull(){
		assertNotNull(availabilityReqObj.getReservationHotels().getHotels().get(0).getAgentLogin());
	}
	
	@Test
	public void testLoginInfo(){
		assertEquals(700000, availabilityReqObj.getReservationHotels().getHotels().get(0).getAgentLogin().getId());
		assertEquals("test", availabilityReqObj.getReservationHotels().getHotels().get(0).getAgentLogin().getPassword());
	}
	
	@Test
	public void testNumOfResrvInItin(){
		assertEquals(1, availabilityReqObj.getReservationHotels().getHotels().get(0).getNumOfReservInIten());
	}
	
	@Test
	public void testNationality(){
		assertEquals("IL", availabilityReqObj.getReservationHotels().getHotels().get(0).getNationality());
	}
	
	@Test
	public void testNumOfAdults(){
		assertEquals(2, availabilityReqObj.getReservationHotels().getHotels().get(0).getRoomsOccupancyList().get(0).getNumOfAdults());
	}
	
	@Test
	public void testNumOfBabies(){
		assertEquals(0, availabilityReqObj.getReservationHotels().getHotels().get(0).getRoomsOccupancyList().get(0).getNumOfBabies());
	}
	
	@Test
	public void testNumOfChildren(){
		assertEquals(0, availabilityReqObj.getReservationHotels().getHotels().get(0).getRoomsOccupancyList().get(0).getNumOfChildren());
	}
	
	@Test
	public void testNumOfRooms(){
		assertEquals(2, availabilityReqObj.getReservationHotels().getHotels().get(0).getNumOfRooms());
	}
	
	@Test
	public void testHotelDetailsNotNull(){
		assertNotNull(availabilityReqObj.getReservationHotels().getHotels().get(0).getHotelDetails());
	}
	
	@Test
	public void testReservInItin(){
		assertEquals(1, availabilityReqObj.getReservationHotels().getHotels().get(0).getHotelDetails().getReservInItinr());
	}
	
	@Test
	public void testHotelAreaCd(){
		assertEquals(0, availabilityReqObj.getReservationHotels().getHotels().get(0).getHotelDetails().getAreaCd());
	}
	
	@Test
	public void testHotelId(){
		assertEquals(8, availabilityReqObj.getReservationHotels().getHotels().get(0).getHotelDetails().getHotelId());
	}
	
	@Test
	public void testArrivalDate(){
		assertEquals("07/08/2011", availabilityReqObj.getReservationHotels().getHotels().get(0).getHotelDetails().getArrivalDate());
	}
	
	@Test
	public void testDepartureDate(){
		assertEquals("10/08/2011", availabilityReqObj.getReservationHotels().getHotels().get(0).getHotelDetails().getDepartureDate());
	}
	
	@Test
	public void testBoardArrangement(){
		assertEquals("BB", availabilityReqObj.getReservationHotels().getHotels().get(0).getHotelDetails().getBoardArrangement());
	}
	
	@Test
	public void testNumFridayDinners(){
		assertEquals(1, availabilityReqObj.getReservationHotels().getHotels().get(0).getHotelDetails().getNumOfFridayDinners());
	}
	
	public void testNumSaturdayLunches(){
		assertEquals(1, availabilityReqObj.getReservationHotels().getHotels().get(0).getHotelDetails().getNumOfSaturdayLunches());
	}
	
	@Test
	public void testNumHolidayLunches(){
		assertEquals(1, availabilityReqObj.getReservationHotels().getHotels().get(0).getHotelDetails().getNumOfHolidayLunches());
	}
	
	@Test
	public void testRoomType(){
		assertEquals("", availabilityReqObj.getReservationHotels().getHotels().get(0).getHotelDetails().getRoomType());
		System.out.println(wrapper.returnAsXml(availabilityReqObj));
	}
}
