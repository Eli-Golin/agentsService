<%@page import="com.coral.multi.general.AppConstants"%>
<%@page import="com.coral.multi.wrappers.CancelRequestWrapper"%>
<%@page import="com.coral.multi.mappedobjects.cancelation.CancelationRequest"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.coral.multi.wrappers.MultiReservReqWrapper"%>
<%@page import="com.coral.multi.mappedobjects.reservrequest.SpecialRequest"%>
<%@page import="com.coral.multi.mappedobjects.reservrequest.NewRoomReservationDetails"%>
<%@page import="com.coral.multi.mappedobjects.reservrequest.NewReservationRequest"%>
<%@page import="com.coral.multi.mappedobjects.reservrequest.MultiReservationRequest"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@page import="com.coral.multi.mappedobjects.availability.requests.multi.RoomOccupancy"%>
<%@page import="com.coral.multi.mappedobjects.general.AgentLogin"%>
<%@page import="com.coral.multi.mappedobjects.availability.requests.multi.ReservationHotel"%>
<%@page import="com.coral.multi.mappedobjects.availability.requests.multi.ReservationHotels"%>
<%@page import="com.coral.multi.wrappers.MultiAvailabilityRequestWrapper"%>
<%@page import="com.coral.multi.wrappers.XmlToObjectWrapper"%>
<%@page import="com.coral.multi.mappedobjects.availability.requests.multi.MultiAvailabilityRequest"%>
<%@page import="com.coral.multi.mappedobjects.availability.requests.multi.HotelDetails"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
	<%! 
		private String availabilityRequest(HttpServletRequest request){
			MultiAvailabilityRequest multiRequest = new MultiAvailabilityRequest();
			XmlToObjectWrapper wrapper = new MultiAvailabilityRequestWrapper();
			ReservationHotels reservHotels = new ReservationHotels();
			ReservationHotel reservHotel = new ReservationHotel();
			AgentLogin agentLogin = new AgentLogin();
			agentLogin.setId(Long.parseLong(request.getParameter("agentId")));
			agentLogin.setPassword(request.getParameter("password"));
			reservHotel.setAgentLogin(agentLogin);
			reservHotel.setNationality(request.getParameter("geustNationality"));
			reservHotel.setNumOfReservInIten(1);
			reservHotel.setNumOfRooms(1);
			RoomOccupancy occupancy = new RoomOccupancy();
			occupancy.setNumOfAdults(Integer.parseInt(request.getParameter("numberOfAdults")));
			occupancy.setNumOfChildren(Integer.parseInt(request.getParameter("numberOfChildren")));
			occupancy.setNumOfBabies(Integer.parseInt(request.getParameter("numberOfBabies")));
			List<RoomOccupancy> occupancylist = new LinkedList<RoomOccupancy>();
			occupancylist.add(occupancy);
			reservHotel.setRoomsOccupancyList(occupancylist);
			HotelDetails hotelDetails = new HotelDetails();
			hotelDetails.setAreaCd(Integer.parseInt(request.getParameter("hotelAreaCd")));
			hotelDetails.setHotelId(Integer.parseInt(request.getParameter("hotelId")));
			hotelDetails.setArrivalDate(request.getParameter("arrivalDate"));
			hotelDetails.setDepartureDate(request.getParameter("departureDate"));
			hotelDetails.setBoardArrangement(request.getParameter("boardArrangement"));
			hotelDetails.setNumOfFridayDinners(Integer.parseInt(request.getParameter("numOfFridayNightDinners")));
			hotelDetails.setNumOfHolidayLunches(Integer.parseInt(request.getParameter("numOfHolidayLunches")));
			hotelDetails.setNumOfSaturdayLunches(Integer.parseInt(request.getParameter("numOfSaturdayLunches")));
			hotelDetails.setReservInItinr(1);
			hotelDetails.setRoomType(request.getParameter("roomType"));
			reservHotel.setHotelDetails(hotelDetails);
			List<ReservationHotel> reservHotelsList = new LinkedList<ReservationHotel>();
			reservHotelsList.add(reservHotel);
			reservHotels.setHotels(reservHotelsList);
			multiRequest.setReservationHotels(reservHotels);
			return wrapper.returnAsXml(multiRequest);
		}
	%>
	
	<%!
		private String reservationRequest(HttpServletRequest request) {
			MultiReservationRequest multiRequest = new MultiReservationRequest();
			NewReservationRequest newRequest = new NewReservationRequest();
			AgentLogin agentLogin = new AgentLogin();
			agentLogin.setId(Long.parseLong(request.getParameter("agentId")));
			agentLogin.setPassword(request.getParameter("password"));
			newRequest.setAgentLogin(agentLogin);
			newRequest.setDanMemberNum(Integer.parseInt(request.getParameter("eDanMemberNo")));
			newRequest.setFirstName(request.getParameter("geustFirstName"));
			newRequest.setLastName(request.getParameter("geustLastName"));
			newRequest.setMop(Integer.parseInt(request.getParameter("mop")));
			newRequest.setNationality(request.getParameter("geustNationality"));
			newRequest.setNumOfAdults(Integer.parseInt(request.getParameter("numberOfAdults")));
			newRequest.setNumOfChildren(Integer.parseInt(request.getParameter("numberOfChildren")));
			newRequest.setNumOfBabies(Integer.parseInt(request.getParameter("numberOfBabies")));
			newRequest.setNumOfReservationsIten(1);
			newRequest.setNumOfRooms(1);
			newRequest.setTravAgentRef(request.getParameter("travelAgentReference"));
			NewRoomReservationDetails reservRoomDetails = new NewRoomReservationDetails();
			reservRoomDetails.setArrivalDate(request.getParameter("arrivalDate"));
			reservRoomDetails.setDepartureDate(request.getParameter("departureDate"));
			reservRoomDetails.setBoardArrangement(request.getParameter("boardArrangement"));
			reservRoomDetails.setCombinationString(request.getParameter("combinationString"));
			reservRoomDetails.setComments(request.getParameter("comments"));
			reservRoomDetails.setCurrency(request.getParameter("currency"));
			reservRoomDetails.setFridayDinnerNum(Integer.parseInt(request.getParameter("numOfFridayNightDinners")));
			reservRoomDetails.setHolidayLunchesNum(Integer.parseInt(request.getParameter("numOfHolidayLunches")));
			reservRoomDetails.setSaturdayLunchesNum(Integer.parseInt(request.getParameter("numOfSaturdayLunches")));
			reservRoomDetails.setHotelId(Integer.parseInt(request.getParameter("hotelId")));
			reservRoomDetails.setPrice(Double.parseDouble(request.getParameter("price")));
			reservRoomDetails.setReservIten(1);
			reservRoomDetails.setRoomType(request.getParameter("roomType"));
			SpecialRequest specialRequest = new SpecialRequest();
			specialRequest.setReqNum(request.getParameter("requestNo"));
			specialRequest.setReqType(request.getParameter("reqType"));
			reservRoomDetails.setSpecialReq(specialRequest);
			newRequest.setReservRoomDetails(reservRoomDetails);
			List<NewReservationRequest> newReservationRequestList = new LinkedList<NewReservationRequest>();
			newReservationRequestList.add(newRequest);
			multiRequest.setReservDetails(newReservationRequestList);
			XmlToObjectWrapper wrapper = new MultiReservReqWrapper();
			return wrapper.returnAsXml(multiRequest);
		}
	%>
	
	<%!
		private String cancellationRequest(HttpServletRequest request){
			CancelationRequest cancellationRequest = new CancelationRequest();
			AgentLogin agentLogin = new AgentLogin();
			agentLogin.setId(Long.parseLong(request.getParameter("agentId")));
			agentLogin.setPassword(request.getParameter("password"));
			cancellationRequest.setAgentLogin(agentLogin);
			cancellationRequest.setReservNum(Long.parseLong(request.getParameter("reservationNo")));
			XmlToObjectWrapper wrapper = new CancelRequestWrapper();
			return wrapper.returnAsXml(cancellationRequest);
		}
	%>
	<%
		String urlString = "http://localhost:8080/agents/agentsService";
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		URLEncoder.encode(urlString, "UTF-8");
		// Sending request
		String sentData = null;
		request.setCharacterEncoding("UTF-8");
		String requestType = request.getParameter("requestType");
		if(requestType.equals("2")){
			sentData = availabilityRequest(request);
		}
		else if(requestType.equals("3")){
			sentData = reservationRequest(request);
		}
		else {
			sentData = cancellationRequest(request);
		}
		String formattedData = "requestType=" + requestType + "&sentData=" + sentData; 	
		OutputStream outStream = connection.getOutputStream();
		outStream.write(formattedData.getBytes("UTF-8"));
		// Receiving answer
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		String line = null;
		line = in.readLine();
		String str = "";
		while (null != line) {
			str += line + "\n";
			line = in.readLine();
		} 
		out.println(str);
		System.out.println("\nEnd of proccessing!!"); 
	%>
</html>
