package com.coral.multi.filters;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.coral.multi.general.AppConstants;
import com.coral.multi.mappedobjects.general.AgentLogin;
import com.coral.multi.mappedobjects.reservrequest.MultiReservationRequest;
import com.coral.multi.mappedobjects.reservrequest.NewReservationRequest;
import com.coral.multi.mappedobjects.reservrequest.NewRoomReservationDetails;
import com.coral.multi.utils.DbDaoUtils;

public class PriceValidationFilter implements Filter{

	private FilterConfig filterConfig;
	

	public void destroy() {
		DbDaoUtils.closeConncetion();
	}


	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
		filterConfig.getServletContext().log("\n-------Inside Price Validation Filter---------\n");
		MultiReservationRequest reservations = (MultiReservationRequest)req.getAttribute(AppConstants.CONVERTED_OBJECT);
		List<NewReservationRequest> listOfRequests = reservations.getReservDetails();
		boolean isLocalAgent = (Boolean)req.getAttribute(AppConstants.IS_USA);
		boolean validPrice = true;
		if(isLocalAgent)
			validPrice = checkPriceIntegrity(listOfRequests);
		if(!validPrice){
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/priceNotValid");
			requestDispatcher.include(req,res);
		}
		else
			filterChain.doFilter(req, res);
		filterConfig.getServletContext().log("\n--------Outside Price Validation Filter--------\n");
	}
	
	private boolean checkPriceIntegrity(List<NewReservationRequest> listOfRequests){
		for(NewReservationRequest currentRequest: listOfRequests){
			AgentLogin agentLogin = currentRequest.getAgentLogin();
			long agentId = agentLogin.getId();
			String nationality = currentRequest.getNationality();
			Integer numOfAdults = currentRequest.getNumOfAdults();
			Integer numOfChildren = currentRequest.getNumOfChildren();
			Integer numOfRooms = currentRequest.getNumOfRooms();
			NewRoomReservationDetails reservRoomDetails = currentRequest.getReservRoomDetails();
			String arrivalDate = reservRoomDetails.getArrivalDate();
			String departureDate = reservRoomDetails.getDepartureDate();
			String boardArrangement = reservRoomDetails.getBoardArrangement();
			Double price = reservRoomDetails.getPrice();
			Integer hotelId = reservRoomDetails.getHotelId();
			String roomType = reservRoomDetails.getRoomType();
			boolean validationSucceeded = DbDaoUtils.validatePrice(hotelId, roomType, arrivalDate, departureDate, numOfAdults, numOfChildren, numOfRooms, nationality, boardArrangement, agentId, price);
			if(!validationSucceeded)
				return false;
		}
		return true;
	}
	
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
}
