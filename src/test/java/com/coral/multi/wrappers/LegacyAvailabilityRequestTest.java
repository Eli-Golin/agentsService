package com.coral.multi.wrappers;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.coral.multi.general.GeneralUsage;
import com.coral.multi.mappedobjects.availability.requests.legacy.LegacyAvailabilityAndRatesRequests;
import com.coral.multi.wrappers.LegacyAvailabilityAndRatesRequestsWrapper;
import com.coral.multi.wrappers.XmlToObjectWrapper;

public class LegacyAvailabilityRequestTest {
	private String xmlFromFile;
	private XmlToObjectWrapper wrapper;
	private LegacyAvailabilityAndRatesRequests availabilityRequests;
	
	@Before
	public void setUp() throws Exception {
		xmlFromFile = GeneralUsage.createStringContent("availabilityAndRatesRequests.xml");
		wrapper = new LegacyAvailabilityAndRatesRequestsWrapper();
		availabilityRequests = (LegacyAvailabilityAndRatesRequests)wrapper.buildFromXml(xmlFromFile);
	}
	
	@Test
	public void testAvailabilityrequestsNotNull(){
		assertNotNull(availabilityRequests);
	}
	
	@Test
	public void testRightInstance(){
		assertTrue(availabilityRequests instanceof LegacyAvailabilityAndRatesRequests);
	}
	
	@Test
	public void testAgentLoginNotNull(){
		assertNotNull(availabilityRequests.getAgentLogin());
	}
	
	@Test
	public void testAgentLoginId(){
		assertEquals(123456, availabilityRequests.getAgentLogin().getId());
	}
	
	@Test
	public void testAgentLoginPassword(){
		assertEquals("password", availabilityRequests.getAgentLogin().getPassword());
	}
	
	@Test
	public void testNumOfReservInIten(){
		assertEquals(1, availabilityRequests.getNumOfResInIten());
	}
	
	@Test
	public void testNationality(){
		assertEquals("USA", availabilityRequests.getNationality());
	}
	
	@Test
	public void testNumOfAdults(){
		assertEquals(2, availabilityRequests.getNumOfAdults());
	}
	
	@Test
	public void testNumOfChildren(){
		assertEquals(1, availabilityRequests.getNumOfChildren());
	}
	
	@Test
	public void testNumOfBabies(){
		assertEquals(1, availabilityRequests.getNumOfBabies());
	}
	
	@Test
	public void testNumOfRooms(){
		assertEquals(2, availabilityRequests.getNumOfRooms());
	}
	
	@Test
	public void testRequestDetailsNotNull(){
		assertNotNull(availabilityRequests.getRequestDetails());
	}
	
	@Test
	public void testReservInItern(){
		assertEquals(1, availabilityRequests.getRequestDetails().getReservInIten());
	}
	
	@Test
	public void testHotelAreaCd(){
		assertEquals(1, availabilityRequests.getRequestDetails().getHotelAreaCd());
	}
	
	@Test
	public void testHotelId(){
		assertEquals(1, availabilityRequests.getRequestDetails().getHotelId());
	}
	
	@Test
	public void testArrivalDate(){
		assertEquals("15/02/2008", availabilityRequests.getRequestDetails().getArrivalDate());
	}
	
	@Test
	public void testDepartureDate(){
		assertEquals("17/02/2008", availabilityRequests.getRequestDetails().getDepartureDate());
	}
	
	@Test
	public void testFridayDinners(){
		assertEquals(1, availabilityRequests.getRequestDetails().getNumOfFridayDinners());
	}
	
	@Test
	public void testSaturdayLunches(){
		assertEquals(1, availabilityRequests.getRequestDetails().getNumOfSaturdayLunches());
	}
	
	@Test
	public void testHolidayLunches(){
		assertEquals(1, availabilityRequests.getRequestDetails().getNumOfHolidayLunches());
		System.out.println(wrapper.returnAsXml(availabilityRequests));
	}
}
