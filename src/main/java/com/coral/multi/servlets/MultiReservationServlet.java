package com.coral.multi.servlets;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coral.multi.general.AppConstants;
import com.coral.multi.general.GeneralUsage;
import com.coral.multi.mappedobjects.errorresponse.ErrorResponse;
import com.coral.multi.mappedobjects.errorresponse.ErrorResponses;
import com.coral.multi.mappedobjects.reservrequest.MultiReservationRequest;
import com.coral.multi.mappedobjects.reservrequest.NewReservationRequest;
import com.coral.multi.mappedobjects.reservresponse.MultiReservationResponse;
import com.coral.multi.mappedobjects.reservresponse.ReservationResponse;
import com.coral.multi.mappedobjects.reservresponse.ResponseDetails;
import com.coral.multi.utils.DbDaoUtils;

/**
 * 
 * @author eli_n
 *
 */
public class MultiReservationServlet extends HttpServlet{
	
	/**
	 * Eli Golin
	 * Version 1.0
	 */
	private static final long serialVersionUID = -1768374871632392953L;
	
	//need to close all db connections on servlet destruction
	@Override
	public void destroy(){
		super.destroy();
		DbDaoUtils.closeConncetion();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		getServletContext().log("\n------Inside Multi resrvation Servlet--------\n");
		MultiReservationRequest multiReservationRequest = (MultiReservationRequest)request.getAttribute(AppConstants.CONVERTED_OBJECT);
		MultiReservationResponse multiReservationResponse = new MultiReservationResponse();
		boolean successFlag = true;
		List<String> storedReservNumbers = new LinkedList<String>();
		successFlag = reservRoomsOneByOne(request,multiReservationResponse,multiReservationRequest,storedReservNumbers);
		if(successFlag){
			addMultiReservationNum(multiReservationResponse, storedReservNumbers);
			request.setAttribute(AppConstants.CONVERTED_OBJECT, multiReservationResponse);
		}
		getServletContext().log("\n------Outside Multi resrvation Servlet--------\n");
	}
		
	private boolean reservRoomsOneByOne(HttpServletRequest request,MultiReservationResponse multiReservationResponse,
			MultiReservationRequest multiReservationRequest, List<String> storedReservNumbers) {
		getServletContext().log("\n\n\n\n--------------Start of reservRoomsOneByOne!!!!!!!!!!!!!\n\n\n\n");
		getServletContext().log("Number of resrevations requests  is : "+multiReservationRequest.getReservDetails().size()+"\n");
		int index = 0;
		boolean successFlag = true;
		List<NewReservationRequest> listOfRequests = multiReservationRequest.getReservDetails();
		List<ReservationResponse> responsesList = multiReservationResponse.getResponses();
		while(successFlag & index < listOfRequests.size()){
			NewReservationRequest currentReservationRequest = listOfRequests.get(index);
			long reservationNum = reserveRoom(currentReservationRequest);
			successFlag = reservationNum != 0 ? true : false;
			if(successFlag){
				ReservationResponse currentReservationResponse = new ReservationResponse();
				makeResponseFromRequest(currentReservationRequest, currentReservationResponse,reservationNum, request);
				storedReservNumbers.add(Long.toString(reservationNum));
				responsesList.add(currentReservationResponse);
			}
			index++;
		}
		if(!successFlag){
			ErrorResponses errorResponses = createErrorResponsesObj(storedReservNumbers, listOfRequests);
			request.setAttribute(AppConstants.CONVERTED_OBJECT, errorResponses);			
			GeneralUsage.setErrorResponseAttribute(request);
			
		}
		getServletContext().log("\n\n\n\n--------------End of reservRoomsOneByOne!!!!!!!!!!!!!\n\n\n\n");
		return successFlag;
	}

	private ErrorResponses createErrorResponsesObj(List<String> storedReservNumbers,List<NewReservationRequest> listOfRequests) {
		ErrorResponses errorResponses = new ErrorResponses();
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setExtraDate(GeneralUsage.getCurrentDateAsString());
		errorResponse.setResultNum(0);
		errorResponse.setResultMessage(AppConstants.ROOM_RESERVATION_FAILED);
		errorResponse.setResultStatus(AppConstants.ROOM_RESERVATION_FAILED_NUM);
		errorResponses.getErrorResponses().add(errorResponse);
		List<String> failedToCancelReservations = cancelReservations(storedReservNumbers, listOfRequests.get(0).getAgentLogin().getId());
		errorResponse = new ErrorResponse();
		for(int i = 0; i < failedToCancelReservations.size(); i++){
			errorResponse.setExtraDate(GeneralUsage.getCurrentDateAsString());
			errorResponse.setResultNum(i+1); // because we already have one ErrorResponse in ErrorResponses
			errorResponse.setResultMessage(AppConstants.CANCELATION_REQUEST_FAILED+failedToCancelReservations.get(i));
			errorResponse.setResultStatus(AppConstants.CANCELATION_REQUEST_FAILED_NUM);
			errorResponses.getErrorResponses().add(errorResponse);
		}
		return errorResponses;
	}
	
	private List<String> cancelReservations(List<String> reservationNembers, long agentId){
		List<String> failedToCancelNumbers = new LinkedList<String>();
		for(String reservNum : reservationNembers){
			String answer = DbDaoUtils.cancelReservation(agentId, Integer.parseInt(reservNum));
			if(answer.equals("0"))
				failedToCancelNumbers.add(reservNum);
		}
		return failedToCancelNumbers;
	}
	
	private void makeResponseFromRequest(NewReservationRequest currentReservationRequest, ReservationResponse currentReservationResponse, long reservationNum, HttpServletRequest request){
			currentReservationResponse.setNumOfReservationsIten(1); //default
			currentReservationResponse.setCurrency(currentReservationRequest.getReservRoomDetails().getCurrency());
			currentReservationResponse.setTotalPrice(currentReservationRequest.getReservRoomDetails().getPrice());
			currentReservationResponse.setComments(currentReservationRequest.getReservRoomDetails().getComments());//TODO: call hilmi's procedure in the future
			if(reservationNum != 0)
				currentReservationResponse.setStatus("Reserved");
			else
				currentReservationResponse.setStatus("Not Reserved");
			ResponseDetails responseDetails = new ResponseDetails();
			responseDetails.setArrivalDate(currentReservationRequest.getReservRoomDetails().getArrivalDate());
			responseDetails.setDepartureDate(currentReservationRequest.getReservRoomDetails().getDepartureDate());
			responseDetails.setCurrency(currentReservationRequest.getReservRoomDetails().getCurrency());
			responseDetails.setHotelId(currentReservationRequest.getReservRoomDetails().getHotelId());
			responseDetails.setReservNum(reservationNum);
			responseDetails.setReservIten(1); //default
			responseDetails.setPrice(currentReservationRequest.getReservRoomDetails().getPrice());
			responseDetails.setRoomType(currentReservationRequest.getReservRoomDetails().getRoomType());
			double langCd = (Boolean)request.getAttribute(AppConstants.IS_USA) ? AppConstants.P_NLS_LANG_CD_ISR : AppConstants.P_NLS_LANG_CD_FOREIGN;
			Integer hotelId = responseDetails.getHotelId();
			String roomType = responseDetails.getRoomType();
			String roomDescription = DbDaoUtils.getRoomDescription(langCd, hotelId, roomType);
			responseDetails.setRoomDescription(roomDescription);
			String roomTypeDescription = DbDaoUtils.getRoomTypeDescription(langCd, hotelId, roomType);
			responseDetails.setRoomTypeDescription(roomTypeDescription);
			if(reservationNum != 0)
				responseDetails.setStatus("Reserved");
			else
				responseDetails.setStatus("Not Reserved");
			currentReservationResponse.setResponses(responseDetails);
	}		
	
	private int reserveRoom(NewReservationRequest reservationRequest){
		long agentId = reservationRequest.getAgentLogin().getId();
		String guestNationality = reservationRequest.getNationality();
		String guestFirstName = reservationRequest.getFirstName();
		String guestLastName = reservationRequest.getLastName();
		Integer numOfAdults = reservationRequest.getNumOfAdults();
		Integer numOfChildren = reservationRequest.getNumOfChildren();
		Integer numOfBabies = reservationRequest.getNumOfBabies();
		Integer numOfRooms = reservationRequest.getNumOfRooms();
		String travelAgentReference = reservationRequest.getTravAgentRef();
		int hotelNum = reservationRequest.getReservRoomDetails().getHotelId();
		String roomType = reservationRequest.getReservRoomDetails().getRoomType();
		String checkInDate = reservationRequest.getReservRoomDetails().getArrivalDate();
		String checkOutDate = reservationRequest.getReservRoomDetails().getDepartureDate();
		String boardArrangement = reservationRequest.getReservRoomDetails().getBoardArrangement();
		double nlsLangCd  = guestNationality.equals("IL") | guestNationality.equals("ISR") ? AppConstants.P_NLS_LANG_CD_ISR : AppConstants.P_NLS_LANG_CD_FOREIGN;
		String combinationString = reservationRequest.getReservRoomDetails().getCombinationString();
		double roomPrice = reservationRequest.getReservRoomDetails().getPrice();
		String comments = reservationRequest.getReservRoomDetails().getComments();
		int reservationNum = DbDaoUtils.makeReservation(agentId, guestNationality, guestFirstName, guestLastName, numOfAdults, numOfChildren, numOfBabies, numOfRooms, travelAgentReference, hotelNum, roomType, checkInDate, checkOutDate, boardArrangement, nlsLangCd, combinationString, roomPrice, comments);
		return reservationNum;
	}
	
	//adding multireservationNum to all responses (the same number)
	private void addMultiReservationNum(MultiReservationResponse multiReservationResponse,List<String> storedReservNumbers){
		String reservNumbers = "";
		for(int i = 0; i < storedReservNumbers.size(); i++){
			String reservNumber = storedReservNumbers.get(i);
			if( i == storedReservNumbers.size() -1){
				reservNumbers+=reservNumber;
				break;
			}
			reservNumbers+=reservNumber+",";
		}
		List<ReservationResponse> allResponses = multiReservationResponse.getResponses();
		long multiReservNum = 0;
		/*
		 * call for util.getMultiReservNum() iff storedReservNumbers.size()>1
		 * else multiResrvNum = 0 ???? 
		 */
		if(storedReservNumbers.size()>1)
			multiReservNum = DbDaoUtils.getMultiReservNum(reservNumbers);
		for(ReservationResponse response : allResponses){
			response.setMultReservNum(multiReservNum);
		}
		getServletContext().log("\n\n\n---------\nReservation Numbers: "+reservNumbers+"\n\n\n----------");
	}
}