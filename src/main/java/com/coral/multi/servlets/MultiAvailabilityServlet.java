package com.coral.multi.servlets;

import static com.coral.multi.general.AppConstants.P_NLS_LANG_CD_FOREIGN;
import static com.coral.multi.general.AppConstants.P_NLS_LANG_CD_ISR;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coral.multi.general.AppConstants;
import com.coral.multi.mappedobjects.availability.requests.multi.HotelDetails;
import com.coral.multi.mappedobjects.availability.requests.multi.MultiAvailabilityRequest;
import com.coral.multi.mappedobjects.availability.requests.multi.ReservationHotel;
import com.coral.multi.mappedobjects.availability.requests.multi.RoomOccupancy;
import com.coral.multi.mappedobjects.availability.responses.multi.MultiAvailabilityResponse;
import com.coral.multi.mappedobjects.availability.responses.multi.Picture;
import com.coral.multi.mappedobjects.availability.responses.multi.ResponseHotel;
import com.coral.multi.mappedobjects.availability.responses.multi.Room;
import com.coral.multi.mappedobjects.availability.responses.multi.RoomsPerDate;
import com.coral.multi.utils.DbDaoUtils;

public class MultiAvailabilityServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1085596653695379813L;

	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		MultiAvailabilityRequest availabilityRequest = (MultiAvailabilityRequest) request.getAttribute(AppConstants.CONVERTED_OBJECT);
		List<ReservationHotel> allHotelsList = getAllHotelsFromRequest(availabilityRequest);
		Map<ReservationHotel, ResponseHotel> reservHotelToResponseHotelMap = mapReservHotelToResponseHotel(allHotelsList);
		insertInfoToResponseHotels(reservHotelToResponseHotelMap);
		Set<ReservationHotel> allReservHotels = reservHotelToResponseHotelMap.keySet();
		List<ResponseHotel> previoslySeenResponseHotels = new ArrayList<ResponseHotel>();
		for(ReservationHotel reservHotel : allReservHotels){
			ResponseHotel responseHotel = reservHotelToResponseHotelMap.get(reservHotel);
			if(previoslySeenResponseHotels.contains(responseHotel))
				continue;
			previoslySeenResponseHotels.add(responseHotel);
			double lang_cd = reservHotel.getNationality().equals("IL")|reservHotel.getNationality().equals("ISR") ? P_NLS_LANG_CD_ISR : P_NLS_LANG_CD_FOREIGN;
			responseHotel.setAreaCd(reservHotel.getHotelDetails().getAreaCd());
			responseHotel.setHotelId(reservHotel.getHotelDetails().getHotelId());
			Object[] hotelInfoResultsTable = DbDaoUtils.getHotelInfo(reservHotel.getHotelDetails().getHotelId(), lang_cd);
			responseHotel.setHotelIdDescription((String) hotelInfoResultsTable[0]);
			responseHotel.setHotelDescription((String) hotelInfoResultsTable[1]);
			responseHotel.getPictures().getPictures().add((Picture) hotelInfoResultsTable[2]);
		}
		MultiAvailabilityResponse availabilityResponse = buildResponse(reservHotelToResponseHotelMap.values());
		request.setAttribute(AppConstants.CONVERTED_OBJECT, availabilityResponse);
	}

	private MultiAvailabilityResponse buildResponse(Collection<ResponseHotel> allResponseHotels){
		MultiAvailabilityResponse availabilityResponse = new MultiAvailabilityResponse();
		List<ResponseHotel> alreadySeenResponseHotels = new ArrayList<ResponseHotel>();
		for(ResponseHotel responseHotel : allResponseHotels){
			if(alreadySeenResponseHotels.contains(responseHotel))
				continue;
			alreadySeenResponseHotels.add(responseHotel);
			availabilityResponse.getHotels().getHotels().add(responseHotel);
		}
		return availabilityResponse;
	}
	
	private void insertInfoToResponseHotels(Map<ReservationHotel, ResponseHotel> reservHotelToResponseHotelMap) {
		Set<ReservationHotel> allReservationHotels = reservHotelToResponseHotelMap.keySet();
		for (ReservationHotel reservationHotel : allReservationHotels) {
			Map<RoomOccupancy, List<Room>> occupancyToRoomsResults = occupancyToRoomsResults(reservationHotel);
			ResponseHotel responseHotel = reservHotelToResponseHotelMap.get(reservationHotel);
			String requestHotelArrivalDate = reservationHotel.getHotelDetails().getArrivalDate();
			String requestHotelDepartuDate = reservationHotel.getHotelDetails().getDepartureDate();
			List<RoomsPerDate> roomsPerDates = responseHotel.getRooms().getRoomsArrangement();
			RoomsPerDate roomArrangement = new RoomsPerDate();
			roomArrangement.setArrivalDate(requestHotelArrivalDate);
			roomArrangement.setDepartureDate(requestHotelDepartuDate);
			Set<RoomOccupancy> allOccupancies = occupancyToRoomsResults.keySet();
			for (RoomOccupancy roomOccupancy : allOccupancies) 
				roomArrangement.getRooms().addAll(occupancyToRoomsResults.get(roomOccupancy));
			roomsPerDates.add(roomArrangement);
		}
	}

	private Map<RoomOccupancy, List<Room>> occupancyToRoomsResults(ReservationHotel hotel) {
		long agentId = hotel.getAgentLogin().getId();
		String nationality = hotel.getNationality();
		double lang_cd = nationality.equals("IL") | nationality.equals("ISR") ? P_NLS_LANG_CD_ISR : P_NLS_LANG_CD_FOREIGN;
		int hotelId = hotel.getHotelDetails().getHotelId();
		int areaCd = hotel.getHotelDetails().getAreaCd();
		String roomType = hotel.getHotelDetails().getRoomType();
		String arrivalDate = hotel.getHotelDetails().getArrivalDate();
		String departureDate = hotel.getHotelDetails().getDepartureDate();
		final int HILMIS_DEFAULT_NUM_OF_ROOMS = 1;
		int numOfRooms = HILMIS_DEFAULT_NUM_OF_ROOMS; // Hilmi's default!
		String boardArrangement = hotel.getHotelDetails().getBoardArrangement();
		List<RoomOccupancy> roomsOccupancyList = hotel.getRoomsOccupancyList();
		Map<RoomOccupancy, List<Room>> occupancyToResultsRooms = new Hashtable<RoomOccupancy, List<Room>>();
		for (RoomOccupancy roomOccupancy : roomsOccupancyList) {
			int numOfAdults = roomOccupancy.getNumOfAdults();
			int numOfChildren = roomOccupancy.getNumOfChildren();
			int numOfBabies = roomOccupancy.getNumOfBabies();
			List<Object[]> resultsTable = null;
			try {
				if (hotel.getHotelDetails().getBoardArrangement().equals("")){
					resultsTable = DbDaoUtils.getBestRateCombination(new Double(lang_cd), hotelId, areaCd, roomType, arrivalDate,departureDate, numOfAdults, numOfChildren, numOfRooms,agentId, "BB",numOfBabies);
					List<Room> returnedRoomsForSingleOccupancyBB = createListOfRoomsFromResultTable(resultsTable, "BB");
					resultsTable = DbDaoUtils.getBestRateCombination(new Double(lang_cd), hotelId, areaCd, roomType, arrivalDate,departureDate, numOfAdults, numOfChildren, numOfRooms,agentId, "HB",numOfBabies);
					List<Room> returnedRoomsForSingleOccupancyHB = createListOfRoomsFromResultTable(resultsTable, "HB");
					resultsTable = DbDaoUtils.getBestRateCombination(new Double(lang_cd), hotelId, areaCd, roomType, arrivalDate,departureDate, numOfAdults, numOfChildren, numOfRooms,agentId, "FB",numOfBabies);
					List<Room> returnedRoomsForSingleOccupancyFB = createListOfRoomsFromResultTable(resultsTable, "FB");
					List<Room> allKindOfRooms = new ArrayList<Room>();
					allKindOfRooms.addAll(returnedRoomsForSingleOccupancyBB);
					allKindOfRooms.addAll(returnedRoomsForSingleOccupancyHB);
					allKindOfRooms.addAll(returnedRoomsForSingleOccupancyFB);
					occupancyToResultsRooms.put(roomOccupancy,allKindOfRooms);
				}
				else {
					resultsTable = DbDaoUtils.getBestRateCombination(new Double(lang_cd), hotelId, areaCd, roomType, arrivalDate,departureDate, numOfAdults, numOfChildren, numOfRooms,agentId, boardArrangement,numOfBabies);
					List<Room> roomsForSingleOccupancy = createListOfRoomsFromResultTable(resultsTable, hotel.getHotelDetails().getBoardArrangement());
					occupancyToResultsRooms.put(roomOccupancy,roomsForSingleOccupancy);
				}
			}catch (SQLException e){
				String logMessage = "SQLException was thrown while reading a ResultSet";
				getServletContext().log(logMessage + ": " + e);
			}
		}
		return occupancyToResultsRooms;
	}

	private List<Room> createListOfRoomsFromResultTable(List<Object[]> resultsTable,
			String boardArrangement) throws SQLException {
		List<Room> listOfCreatedRooms = new ArrayList<Room>();
		for(Object[] currentRow : resultsTable) {
			Room room = new Room();
			room.setRoomType((String)currentRow[0]);
			room.setRoomTypeDescription((String) currentRow[1]);
			room.getPictures().getPictures().add((Picture) currentRow[2]);
			room.setRoomDescription((String) currentRow[3]);
			room.setDeal((Integer) currentRow[4]);
			room.setComments((String) currentRow[5]);
			room.setAvailability((String) currentRow[6]);
			room.setBoardArrangement(boardArrangement);
			room.setPrice((Double) currentRow[7]);
			room.setCurrency((String) currentRow[8]);
			room.setNumOfAdults((Integer) currentRow[9]);
			room.setNumOfChildren((Integer) currentRow[10]);
			room.setNumOfBabies((Integer) currentRow[11]);
			room.setCombinationString((String)currentRow[12]);
			listOfCreatedRooms.add(room);
		}
		return listOfCreatedRooms;
	}

	private Map<ReservationHotel, ResponseHotel> mapReservHotelToResponseHotel(List<ReservationHotel> reservationHotels) {
		Map<ReservationHotel, ResponseHotel> reservHotelToResponseHotelMap = new Hashtable<ReservationHotel, ResponseHotel>();
		for (ReservationHotel currentReservationHotel : reservationHotels) {
			ResponseHotel responseHotel = reservHotelToResponseHotelMap.get(currentReservationHotel);
			if (responseHotel == null) { // this is a new ReservationHotel!!
				boolean foundResponseHotelWithSameIdInMap = false;
				Set<ReservationHotel> immutableReservationHotelsInMapSet = reservHotelToResponseHotelMap.keySet();
				if(immutableReservationHotelsInMapSet != null){ //if there are ReservationHotels already added to the map
					LinkedList <ReservationHotel> allReservationHotelsInMap = new LinkedList<ReservationHotel>();
					allReservationHotelsInMap.addAll(immutableReservationHotelsInMapSet); // making a list of all ReservationHotels that are already in the map!
						for(ReservationHotel currentReservHotelInMap : allReservationHotelsInMap){
							if(currentReservHotelInMap.getHotelDetails().getHotelId() == currentReservationHotel.getHotelDetails().getHotelId()){
								foundResponseHotelWithSameIdInMap = true;
								ResponseHotel matchedResponseHotel = reservHotelToResponseHotelMap.get(currentReservHotelInMap);
								reservHotelToResponseHotelMap.put(currentReservationHotel, matchedResponseHotel);
							}
						}
				}
				if(null == immutableReservationHotelsInMapSet || !foundResponseHotelWithSameIdInMap){
					reservHotelToResponseHotelMap.put(currentReservationHotel, new ResponseHotel());
				}
			}
		}
		return reservHotelToResponseHotelMap;
	}

	private List<ReservationHotel> getAllHotelsFromRequest(MultiAvailabilityRequest availabilityRequest) {
		List<ReservationHotel> allHotelsList = availabilityRequest.getReservationHotels().getHotels();
		for(int i = 0; i < allHotelsList.size(); i++){
			ReservationHotel currentReservHotel = allHotelsList.get(i);
			int hotelId = currentReservHotel.getHotelDetails().getHotelId();
			int hotelAreaCd = currentReservHotel.getHotelDetails().getAreaCd();
			if(hotelId == 0 & hotelAreaCd != 0){
				String[] hotelIds = DbDaoUtils.getHotelsIdsByArea(hotelAreaCd);
				allHotelsList.remove(i);
				for(String hotelIdString : hotelIds){
					ReservationHotel newReservationHotel = createReservationHotel(currentReservHotel, Integer.parseInt(hotelIdString));
					allHotelsList.add(newReservationHotel);
				}
			}
		}
		return allHotelsList;
	}

	private ReservationHotel createReservationHotel(ReservationHotel toCopy, int hotelId){
		ReservationHotel newReservationHotel = new ReservationHotel();
		newReservationHotel.setNationality(toCopy.getNationality());
		newReservationHotel.setNumOfReservInIten(toCopy.getNumOfReservInIten());
		newReservationHotel.setNumOfRooms(toCopy.getNumOfRooms());
		newReservationHotel.setRoomsOccupancyList(toCopy.getRoomsOccupancyList());
		newReservationHotel.setAgentLogin(toCopy.getAgentLogin());
		HotelDetails newHotelDetails = new HotelDetails();
		newHotelDetails.setAreaCd(0);
		newHotelDetails.setArrivalDate(toCopy.getHotelDetails().getArrivalDate());
		newHotelDetails.setDepartureDate(toCopy.getHotelDetails().getDepartureDate());
		newHotelDetails.setBoardArrangement(toCopy.getHotelDetails().getBoardArrangement());
		newHotelDetails.setHotelId(hotelId);
		newHotelDetails.setNumOfFridayDinners(toCopy.getHotelDetails().getNumOfFridayDinners());
		newHotelDetails.setNumOfHolidayLunches(toCopy.getHotelDetails().getNumOfHolidayLunches());
		newHotelDetails.setNumOfSaturdayLunches(toCopy.getHotelDetails().getNumOfSaturdayLunches());
		newHotelDetails.setReservInItinr(toCopy.getHotelDetails().getReservInItinr());
		newHotelDetails.setRoomType(toCopy.getHotelDetails().getRoomType());
		newReservationHotel.setHotelDetails(newHotelDetails);
		return newReservationHotel;
	}
}
