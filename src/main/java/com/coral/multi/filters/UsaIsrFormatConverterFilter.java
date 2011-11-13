package com.coral.multi.filters;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.coral.multi.general.AppConstants;
import com.coral.multi.mappedobjects.availability.requests.legacy.LegacyAvailabilityAndRatesRequests;
import com.coral.multi.mappedobjects.availability.requests.multi.HotelDetails;
import com.coral.multi.mappedobjects.availability.requests.multi.MultiAvailabilityRequest;
import com.coral.multi.mappedobjects.availability.requests.multi.ReservationHotel;
import com.coral.multi.mappedobjects.availability.requests.multi.ReservationHotels;
import com.coral.multi.mappedobjects.availability.requests.multi.RoomOccupancy;
import com.coral.multi.mappedobjects.availability.responses.legacy.Hotel;
import com.coral.multi.mappedobjects.availability.responses.legacy.Hotels;
import com.coral.multi.mappedobjects.availability.responses.legacy.LegacyAvailabilityAndRatesResponse;
import com.coral.multi.mappedobjects.availability.responses.legacy.LegacyAvailabilityAndRatesResponses;
import com.coral.multi.mappedobjects.availability.responses.legacy.Picture;
import com.coral.multi.mappedobjects.availability.responses.legacy.Room;
import com.coral.multi.mappedobjects.availability.responses.legacy.Rooms;
import com.coral.multi.mappedobjects.availability.responses.multi.MultiAvailabilityResponse;
import com.coral.multi.mappedobjects.availability.responses.multi.Pictures;
import com.coral.multi.mappedobjects.availability.responses.multi.ResponseHotel;
import com.coral.multi.mappedobjects.availability.responses.multi.RoomsPerDate;
import com.coral.multi.mappedobjects.general.AgentLogin;
import com.coral.multi.mappedobjects.reservrequest.MultiReservationRequest;
import com.coral.multi.mappedobjects.reservrequest.NewReservationRequest;
import com.coral.multi.mappedobjects.reservrequest.NewRoomReservationDetails;
import com.coral.multi.mappedobjects.reservrequest.OldReservationRequest;
import com.coral.multi.mappedobjects.reservrequest.SpecialRequest;
import com.coral.multi.mappedobjects.reservresponse.MultiReservationResponse;
import com.coral.multi.mappedobjects.reservresponse.ReservationResponse;
import com.coral.multi.mappedobjects.reservresponse.ResponseDetails;
import com.coral.multi.utils.DbDaoUtils;

public class UsaIsrFormatConverterFilter implements Filter {

	private FilterConfig filterConfig;

	public void destroy() {
		// TODO: do nothing
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
			ServletException {
		filterConfig.getServletContext().log("\n------------ Inside UsaIsrFormatConverterFilter -------------\n");
		Boolean isUsaRequest = (Boolean) request.getAttribute(AppConstants.IS_USA);
		if (isUsaRequest) {
			if ((Boolean) request.getAttribute(AppConstants.IS_AVAILABILITY_REQUEST)) {
				LegacyAvailabilityAndRatesRequests legacyAvailabilityRequests = (LegacyAvailabilityAndRatesRequests) request
						.getAttribute(AppConstants.CONVERTED_OBJECT);
				MultiAvailabilityRequest multiAvailabilityrequest = convertLegacyAvailabilityToMultiAvailability(legacyAvailabilityRequests);
				request.setAttribute(AppConstants.CONVERTED_OBJECT, multiAvailabilityrequest);
				filterChain.doFilter(request, response);
				if ((Boolean) request.getAttribute(AppConstants.IS_AVAILABILITY_REQUEST)) {
					MultiAvailabilityResponse multiAvailabilityResponse = (MultiAvailabilityResponse) request
							.getAttribute(AppConstants.CONVERTED_OBJECT);
					LegacyAvailabilityAndRatesResponses oldAvailabilityResponse = convertMultiAvailabilityRespToLegacyAvailabilityResp(multiAvailabilityResponse);
					request.setAttribute(AppConstants.CONVERTED_OBJECT, oldAvailabilityResponse);
				}
			} else if ((Boolean) request.getAttribute(AppConstants.IS_RESERVATION_REQUEST)) {
				OldReservationRequest oldReservRequest = (OldReservationRequest) request
						.getAttribute(AppConstants.CONVERTED_OBJECT);
				MultiReservationRequest multiReservationRequest = convertLegacyReservationToMultiReservation(oldReservRequest);
				request.setAttribute(AppConstants.CONVERTED_OBJECT, multiReservationRequest);
				filterChain.doFilter(request, response);
				if ((Boolean) request.getAttribute(AppConstants.IS_RESERVATION_REQUEST)) {
					MultiReservationResponse multiReservationResponse = (MultiReservationResponse) request
							.getAttribute(AppConstants.CONVERTED_OBJECT);
					ReservationResponse oldResrvationResponse = convertMultiReservResponseToOldReservResponse(multiReservationResponse);
					request.setAttribute(AppConstants.CONVERTED_OBJECT, oldResrvationResponse);
				}
			}

		} else
			filterChain.doFilter(request, response);
		filterConfig.getServletContext().log("\n------------ Outside UsaIsrFormatConverterFilter -------------\n");
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	private MultiAvailabilityRequest convertLegacyAvailabilityToMultiAvailability(
			LegacyAvailabilityAndRatesRequests legacyAvailability) {
		MultiAvailabilityRequest multiAvailabilityRequest = new MultiAvailabilityRequest();
		ReservationHotels reservationHotels = new ReservationHotels();
		ReservationHotel reservationHotel = new ReservationHotel();
		AgentLogin agentlogin = new AgentLogin();
		agentlogin.setId(legacyAvailability.getAgentLogin().getId());
		agentlogin.setPassword(legacyAvailability.getAgentLogin().getPassword());
		reservationHotel.setAgentLogin(agentlogin);
		HotelDetails hotelDetails = new HotelDetails();
		hotelDetails.setAreaCd(legacyAvailability.getRequestDetails().getHotelAreaCd());
		hotelDetails.setArrivalDate(legacyAvailability.getRequestDetails().getArrivalDate());
		hotelDetails.setDepartureDate(legacyAvailability.getRequestDetails().getDepartureDate());
		hotelDetails.setBoardArrangement(legacyAvailability.getRequestDetails().getBoardArrangement());
		hotelDetails.setHotelId(legacyAvailability.getRequestDetails().getHotelId());
		hotelDetails.setNumOfFridayDinners(legacyAvailability.getRequestDetails().getNumOfFridayDinners());
		hotelDetails.setNumOfHolidayLunches(legacyAvailability.getRequestDetails().getNumOfHolidayLunches());
		hotelDetails.setNumOfSaturdayLunches(legacyAvailability.getRequestDetails().getNumOfSaturdayLunches());
		hotelDetails.setReservInItinr(legacyAvailability.getRequestDetails().getReservInIten());
		hotelDetails.setRoomType(""); // setting empty string enables all room
										// types
		reservationHotel.setHotelDetails(hotelDetails);
		reservationHotel.setNationality(legacyAvailability.getNationality());
		reservationHotel.setNumOfReservInIten(legacyAvailability.getNumOfResInIten());
		reservationHotel.setNumOfRooms(legacyAvailability.getNumOfRooms());
		RoomOccupancy roomOccupancy = new RoomOccupancy();
		roomOccupancy.setNumOfAdults(legacyAvailability.getNumOfAdults());
		roomOccupancy.setNumOfBabies(legacyAvailability.getNumOfBabies());
		roomOccupancy.setNumOfChildren(legacyAvailability.getNumOfChildren());
		List<RoomOccupancy> occupancyList = new LinkedList<RoomOccupancy>();
		occupancyList.add(roomOccupancy);
		reservationHotel.setRoomsOccupancyList(occupancyList);
		List<ReservationHotel> reservationHotelList = new LinkedList<ReservationHotel>();
		reservationHotelList.add(reservationHotel);
		reservationHotels.setHotels(reservationHotelList);
		multiAvailabilityRequest.setReservationHotels(reservationHotels);
		return multiAvailabilityRequest;
	}

	private MultiReservationRequest convertLegacyReservationToMultiReservation(OldReservationRequest oldReservRequest) {
		MultiReservationRequest multiReservationRequest = new MultiReservationRequest();
		NewReservationRequest newRequest = new NewReservationRequest();
		AgentLogin agentLogin = new AgentLogin();
		agentLogin.setId(oldReservRequest.getAgentLogin().getId());
		agentLogin.setPassword(oldReservRequest.getAgentLogin().getPassword());
		newRequest.setAgentLogin(agentLogin);
		newRequest.setDanMemberNum(oldReservRequest.getDanMemberNum());
		newRequest.setFirstName(oldReservRequest.getFirstName());
		newRequest.setLastName(oldReservRequest.getLastName());
		newRequest.setMop(oldReservRequest.getMop());
		newRequest.setNationality(oldReservRequest.getNationality());
		newRequest.setNumOfAdults(oldReservRequest.getNumOfAdults());
		newRequest.setNumOfBabies(oldReservRequest.getNumOfBabies());
		newRequest.setNumOfChildren(oldReservRequest.getNumOfChildren());
		newRequest.setNumOfReservationsIten(oldReservRequest.getNumOfReservationsIten());
		newRequest.setNumOfRooms(oldReservRequest.getNumOfRooms());
		List<NewReservationRequest> roomReservDetailsList = new LinkedList<NewReservationRequest>();
		roomReservDetailsList.add(newRequest);
		multiReservationRequest.setReservDetails(roomReservDetailsList);
		NewRoomReservationDetails roomReservationDetails = new NewRoomReservationDetails();
		roomReservationDetails.setArrivalDate(oldReservRequest.getReservRoomDetails().getArrivalDate());
		roomReservationDetails.setDepartureDate(oldReservRequest.getReservRoomDetails().getDepartureDate());
		roomReservationDetails.setBoardArrangement(oldReservRequest.getReservRoomDetails().getBoardArrangement());
		roomReservationDetails.setComments("");
		if (newRequest.getNationality().equals("IL") | newRequest.getNationality().equals("ISR"))
			roomReservationDetails.setCurrency("NIS");
		else
			roomReservationDetails.setCurrency("$");
		roomReservationDetails.setFridayDinnerNum(oldReservRequest.getReservRoomDetails().getFridayDinnerNum());
		roomReservationDetails.setHolidayLunchesNum(oldReservRequest.getReservRoomDetails().getHolidayLunchesNum());
		roomReservationDetails.setHotelId(oldReservRequest.getReservRoomDetails().getHotelId());
		roomReservationDetails.setRoomType(oldReservRequest.getReservRoomDetails().getRoomType());
		Double price = DbDaoUtils.checkForRoomPrice(roomReservationDetails.getHotelId(), roomReservationDetails
				.getRoomType(), roomReservationDetails.getArrivalDate(), roomReservationDetails.getDepartureDate(),
				newRequest.getNumOfAdults(), newRequest.getNumOfChildren(), newRequest.getNationality(),
				roomReservationDetails.getBoardArrangement(), newRequest.getAgentLogin().getId());
		roomReservationDetails.setPrice(price); 
		roomReservationDetails.setReservIten(oldReservRequest.getReservRoomDetails().getReservIten());
		roomReservationDetails.setSaturdayLunchesNum(oldReservRequest.getReservRoomDetails().getSaturdayLunchesNum());
		SpecialRequest specialRequest = new SpecialRequest();
		specialRequest.setReqNum(oldReservRequest.getReservRoomDetails().getSpecialReq().getReqNum());
		specialRequest.setReqType(oldReservRequest.getReservRoomDetails().getSpecialReq().getReqType());
		roomReservationDetails.setSpecialReq(specialRequest);
		newRequest.setReservRoomDetails(roomReservationDetails);
		newRequest.setTravAgentRef(oldReservRequest.getTravAgentRef());
		return multiReservationRequest;
	}

	private ReservationResponse convertMultiReservResponseToOldReservResponse(
			MultiReservationResponse multiReservResponse) {
		ReservationResponse reservationResponse = new ReservationResponse();
		ReservationResponse newSingleResponse = multiReservResponse.getResponses().get(0);
		reservationResponse.setComments(newSingleResponse.getComments());
		reservationResponse.setCurrency(newSingleResponse.getCurrency());
		reservationResponse.setMultReservNum(newSingleResponse.getMultReservNum());
		reservationResponse.setNumOfReservationsIten(newSingleResponse.getNumOfReservationsIten());
		ResponseDetails responseDetails = new ResponseDetails();
		responseDetails.setArrivalDate(newSingleResponse.getResponses().getArrivalDate());
		responseDetails.setDepartureDate(newSingleResponse.getResponses().getDepartureDate());
		responseDetails.setCurrency(newSingleResponse.getResponses().getCurrency());
		responseDetails.setHotelId(newSingleResponse.getResponses().getHotelId());
		responseDetails.setPrice(newSingleResponse.getResponses().getPrice());
		responseDetails.setReservIten(newSingleResponse.getResponses().getReservIten());
		responseDetails.setReservNum(newSingleResponse.getResponses().getReservNum());
		responseDetails.setRoomDescription(newSingleResponse.getResponses().getRoomDescription());
		responseDetails.setRoomType(newSingleResponse.getResponses().getRoomType());
		responseDetails.setStatus(newSingleResponse.getResponses().getStatus());
		reservationResponse.setResponses(responseDetails);
		reservationResponse.setStatus(newSingleResponse.getStatus());
		reservationResponse.setTotalPrice(newSingleResponse.getTotalPrice());
		return reservationResponse;
	}

	private LegacyAvailabilityAndRatesResponses convertMultiAvailabilityRespToLegacyAvailabilityResp(
			MultiAvailabilityResponse multiAvailabilityResponse) {
		LegacyAvailabilityAndRatesResponses availabilityResponses = new LegacyAvailabilityAndRatesResponses();
		LegacyAvailabilityAndRatesResponse legacyResponseForSingleHotel = new LegacyAvailabilityAndRatesResponse();
		com.coral.multi.mappedobjects.availability.responses.multi.Hotels hotelsContainer = multiAvailabilityResponse
				.getHotels();
		List<ResponseHotel> multiResponseHotelsList = hotelsContainer.getHotels();
		ResponseHotel multiResponseHotel = multiResponseHotelsList.get(0);
		legacyResponseForSingleHotel.setHotelAreaCd(multiResponseHotel.getAreaCd());
		legacyResponseForSingleHotel.setReservInIten(1);
		RoomsPerDate allRoomsForSingleDate = multiResponseHotel.getRooms().getRoomsArrangement().get(0);
		legacyResponseForSingleHotel.setArrivalDate(allRoomsForSingleDate.getArrivalDate());
		legacyResponseForSingleHotel.setDepartureDate(allRoomsForSingleDate.getDepartureDate());

		// dealing with hotel pictures - start
		Pictures multiHotelPictures = multiResponseHotel.getPictures();
		com.coral.multi.mappedobjects.availability.responses.legacy.Pictures legacyHotelPictures = new com.coral.multi.mappedobjects.availability.responses.legacy.Pictures();
		Picture legacyHotelPicture = new Picture();
		List<Picture> legacyHotelPictureList = new LinkedList<Picture>();
		legacyHotelPictureList.add(legacyHotelPicture);
		legacyHotelPictures.setPictures(legacyHotelPictureList);
		com.coral.multi.mappedobjects.availability.responses.multi.Picture multiHotelPicture = multiHotelPictures
				.getPictures().get(0);
		legacyHotelPicture.setPictureType(multiHotelPicture.getPictureType());
		legacyHotelPicture.setPictureUrl(multiHotelPicture.getPictureUrl());
		// dealing with hotel pictures - end
		Hotels legacyHotels = new Hotels();
		Hotel legacyHotel = new Hotel();
		legacyHotel.setPictures(legacyHotelPictures);
		legacyHotel.setHotelIdDescription(multiResponseHotel.getHotelIdDescription());
		legacyHotel.setHotelDescription(multiResponseHotel.getHotelDescription());
		legacyHotel.setHotelId(multiResponseHotel.getHotelId());
		List<Hotel> legacyHotelList = new LinkedList<Hotel>();
		legacyHotelList.add(legacyHotel);
		legacyHotels.setHotels(legacyHotelList);
		copyRoomsData(allRoomsForSingleDate, legacyHotel);
		legacyResponseForSingleHotel.setHotels(legacyHotels);
		List<LegacyAvailabilityAndRatesResponse> legacyResponseForSingleHotelList = new LinkedList<LegacyAvailabilityAndRatesResponse>();
		legacyResponseForSingleHotelList.add(legacyResponseForSingleHotel);
		availabilityResponses.setResponses(legacyResponseForSingleHotelList);
		return availabilityResponses;
	}

	private void copyRoomsData(RoomsPerDate multiRoomsforSingleData, Hotel legacyHotel) {
		Rooms legacyRooms = new Rooms();
		List<Room> listOfLegacyRoom = new LinkedList<Room>();
		legacyRooms.setRooms(listOfLegacyRoom);
		for (com.coral.multi.mappedobjects.availability.responses.multi.Room multiResponseRoom : multiRoomsforSingleData
				.getRooms()) {
			Room legacyRoom = new Room();
			legacyRoom.setAvailability(multiResponseRoom.getAvailability());
			legacyRoom.setPrice(multiResponseRoom.getPrice());
			legacyRoom.setRoomDescription(multiResponseRoom.getRoomDescription());
			legacyRoom.setRoomType(multiResponseRoom.getRoomType());
			legacyRoom.setRoomTypeDescription(multiResponseRoom.getRoomTypeDescription());
			com.coral.multi.mappedobjects.availability.responses.legacy.Pictures legacyRoomPictures = new com.coral.multi.mappedobjects.availability.responses.legacy.Pictures();
			Picture legacyRoomPicture = new Picture();
			legacyRoomPicture.setPictureType(multiResponseRoom.getPictures().getPictures().get(0).getPictureType());
			legacyRoomPicture.setPictureUrl(multiResponseRoom.getPictures().getPictures().get(0).getPictureUrl());
			List<Picture> legacyRoomPictureList = new LinkedList<Picture>();
			legacyRoomPictureList.add(legacyRoomPicture);
			legacyRoomPictures.setPictures(legacyRoomPictureList);
			legacyRoom.setPictures(legacyRoomPictures);
			listOfLegacyRoom.add(legacyRoom);
		}
		legacyHotel.setRooms(legacyRooms);
	}
}
