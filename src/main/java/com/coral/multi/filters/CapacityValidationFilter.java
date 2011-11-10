package com.coral.multi.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.coral.multi.general.AppConstants;
import com.coral.multi.mappedobjects.availability.requests.multi.MultiAvailabilityRequest;
import com.coral.multi.mappedobjects.availability.requests.multi.ReservationHotel;
import com.coral.multi.mappedobjects.availability.requests.multi.RoomOccupancy;
import com.coral.multi.mappedobjects.reservrequest.MultiReservationRequest;
import com.coral.multi.mappedobjects.reservrequest.NewReservationRequest;

public class CapacityValidationFilter implements Filter {

	private FilterConfig filterConfig;
	
	public void destroy() {
		// Nothing todo
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filterChain) throws IOException, ServletException {
		filterConfig.getServletContext().log("\n---Inside CapacityValidationFilter---\n");
		boolean validOccupation = true;
		if((Boolean)req.getAttribute(AppConstants.IS_AVAILABILITY_REQUEST)) { // availability 
			validOccupation = checkAvailabilityCapacity(req);
			if(validOccupation){
				RequestDispatcher dispatcher = req.getRequestDispatcher("/availability");
				dispatcher.include(req, res);
			}
		}
		else {	//reservation
			validOccupation = checkRequestCapacity(req);
			if(validOccupation){
				filterChain.doFilter(req, res);	//continue to reservations servlet
			}
		}
		if(!validOccupation){
			RequestDispatcher dispatcher = req.getRequestDispatcher("/occupationError");
			dispatcher.include(req, res);
		}
		filterConfig.getServletContext().log("\n---Outside CapacityValidationFilter---\n");
	}

	private boolean checkAvailabilityCapacity(ServletRequest request){
		MultiAvailabilityRequest availabilityRequest = (MultiAvailabilityRequest)request.getAttribute(AppConstants.CONVERTED_OBJECT);
		List<ReservationHotel> listOfHotels = availabilityRequest.getReservationHotels().getHotels();
		boolean validOccupancy = false;
		for(ReservationHotel hotel : listOfHotels){
			validOccupancy = false;
			List<RoomOccupancy> roomsOccupancyList = hotel.getRoomsOccupancyList();
			for(RoomOccupancy roomOccupancy : roomsOccupancyList){
				if(roomOccupancy.getNumOfAdults() > 0)
					validOccupancy = true;
			}
			if(!validOccupancy)
				break;
		}
		return validOccupancy;
	}
	
	private boolean checkRequestCapacity(ServletRequest request){
		MultiReservationRequest multiRequest = (MultiReservationRequest)request.getAttribute(AppConstants.CONVERTED_OBJECT);
		List<NewReservationRequest> reservDetailsList = multiRequest.getReservDetails();
		Map<String, List<NewReservationRequest>> idToHotelMap = new HashMap<String, List<NewReservationRequest>>();
		for(NewReservationRequest reservRequest : reservDetailsList){
			List<NewReservationRequest> listOfReservationRequests = idToHotelMap.get(reservRequest.getReservRoomDetails().getArrivalDate()+reservRequest.getReservRoomDetails().getDepartureDate()+reservRequest.getReservRoomDetails().getHotelId());
			if(listOfReservationRequests != null)
				listOfReservationRequests.add(reservRequest);
			else {
				 listOfReservationRequests = new ArrayList<NewReservationRequest>();
				 listOfReservationRequests.add(reservRequest);
				 idToHotelMap.put(reservRequest.getReservRoomDetails().getArrivalDate()+reservRequest.getReservRoomDetails().getDepartureDate()+reservRequest.getReservRoomDetails().getHotelId(),listOfReservationRequests);
			}
		}
		boolean validCapacity  = true;
		Set<String> hotelKeys = idToHotelMap.keySet();
		for(String currentHotelsArrivalDateDepartureDateAndId : hotelKeys){
			List<NewReservationRequest> listOfReservationRequests = idToHotelMap.get(currentHotelsArrivalDateDepartureDateAndId);
			boolean hasAdults  = false;
			for(NewReservationRequest reservRequest : listOfReservationRequests){
				if(reservRequest.getNumOfAdults() > 0)
					hasAdults = true;
			}
			if(!hasAdults){				
				validCapacity = false;
				break;
			}
		}
		return validCapacity;
	}
	
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}
