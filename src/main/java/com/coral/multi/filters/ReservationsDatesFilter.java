package com.coral.multi.filters;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import com.coral.multi.general.AppConstants;
import com.coral.multi.general.DatesViolation;
import com.coral.multi.general.DaysOfWeek;
import com.coral.multi.general.GeneralUsage;
import com.coral.multi.mappedobjects.availability.requests.multi.MultiAvailabilityRequest;
import com.coral.multi.mappedobjects.availability.requests.multi.ReservationHotel;
import com.coral.multi.mappedobjects.reservrequest.MultiReservationRequest;
import com.coral.multi.mappedobjects.reservrequest.NewReservationRequest;
import com.coral.multi.mappedobjects.reservrequest.NewRoomReservationDetails;
public class ReservationsDatesFilter implements Filter {
	
	private FilterConfig filterConfig;
	
	public void destroy() {
		// nothing todo
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
		filterConfig.getServletContext().log("\n----Inside ReservationDatesFilter-----\n");
		boolean currentDateIsNotArrivalDate = true;
		boolean rightIntervalBetweenFridayAndSaturday = true; // reservations on Friday and arrivals on closest Sunday , are not allowed 
		boolean correctIntervalOfDays = true;
		boolean overlappingDates = false;
		if((Boolean)req.getAttribute(AppConstants.IS_RESERVATION_REQUEST)){
			MultiReservationRequest multiRequest = (MultiReservationRequest)req.getAttribute(AppConstants.CONVERTED_OBJECT);
			List<NewReservationRequest> reservationRequestsList = multiRequest.getReservDetails();
			Set<GuestReservationInfo> guestInfoSet = new HashSet<GuestReservationInfo>();
			for(NewReservationRequest reservationRequest : reservationRequestsList){
				String guestFirstName = reservationRequest.getFirstName();
				String guestLastName = reservationRequest.getLastName();
				NewRoomReservationDetails roomDetails = reservationRequest.getReservRoomDetails();
				String arrivalDateString = roomDetails.getArrivalDate();
				String departureDateString = roomDetails.getDepartureDate();
				int hotelId = roomDetails.getHotelId();
				LocalDate jodaArrivalDate = GeneralUsage.convertStringToDate(arrivalDateString);
				LocalDate jodaDepartureDate = GeneralUsage.convertStringToDate(departureDateString);
				LocalDate[]arrivalDepartureDates = new LocalDate[2];
				arrivalDepartureDates[0] = jodaArrivalDate;
				arrivalDepartureDates[1] = jodaDepartureDate;
				boolean notOverlapping = guestInfoSet.add(new GuestReservationInfo(guestFirstName, guestLastName, hotelId, arrivalDepartureDates));
				if(!notOverlapping)
					overlappingDates = true;
				currentDateIsNotArrivalDate = checkArrivalDateNotCurrentDate(arrivalDateString);
				rightIntervalBetweenFridayAndSaturday = checkCurrentDateFridayArrivalDateSunday(arrivalDateString);
				correctIntervalOfDays = isCorrectIntervalOfDays(arrivalDateString,departureDateString);
				if(!currentDateIsNotArrivalDate | !rightIntervalBetweenFridayAndSaturday | !correctIntervalOfDays | overlappingDates){
					if(!currentDateIsNotArrivalDate)
						req.setAttribute("violationType", DatesViolation.RESERVATION_DATE_EQUALS_ARRIVAL_DATE);
					else if(!rightIntervalBetweenFridayAndSaturday)
						req.setAttribute("violationType", DatesViolation.RESERVATION_DATE_FRIDAY_ARRIVAL_DATE_NEXT_SUNDAY);
					else if(!correctIntervalOfDays)
						req.setAttribute("violationType", DatesViolation.RESERVATION_INTERVAL_VIOLATION);
					else
						req.setAttribute("violationType", DatesViolation.OVERLAPPING_DATES);
					RequestDispatcher dispatcher = req.getRequestDispatcher("/dateViolation");
					dispatcher.include(req, res);
					break;
				}
			}
		}
		
		if((Boolean)req.getAttribute(AppConstants.IS_AVAILABILITY_REQUEST)){
			MultiAvailabilityRequest availabilityRequest  = (MultiAvailabilityRequest)req.getAttribute(AppConstants.CONVERTED_OBJECT);
			 List<ReservationHotel> reservationHotelsList = availabilityRequest.getReservationHotels().getHotels();
			 for(ReservationHotel reservationHotel : reservationHotelsList){
				 String arrivalDateString = reservationHotel.getHotelDetails().getArrivalDate();
				 String departureDateString = reservationHotel.getHotelDetails().getDepartureDate();
				 currentDateIsNotArrivalDate = checkArrivalDateNotCurrentDate(arrivalDateString);
				 rightIntervalBetweenFridayAndSaturday = checkCurrentDateFridayArrivalDateSunday(arrivalDateString);
				 correctIntervalOfDays = isCorrectIntervalOfDays(arrivalDateString, departureDateString);
					if(!currentDateIsNotArrivalDate | !rightIntervalBetweenFridayAndSaturday | !correctIntervalOfDays){
						if(!currentDateIsNotArrivalDate)
							req.setAttribute("violationType", DatesViolation.RESERVATION_DATE_EQUALS_ARRIVAL_DATE);
						else if(!rightIntervalBetweenFridayAndSaturday)
							req.setAttribute("violationType", DatesViolation.RESERVATION_DATE_FRIDAY_ARRIVAL_DATE_NEXT_SUNDAY);
						else if(!correctIntervalOfDays)
							req.setAttribute("violationType", DatesViolation.RESERVATION_INTERVAL_VIOLATION);
						RequestDispatcher dispatcher = req.getRequestDispatcher("/dateViolation");
						dispatcher.include(req, res);
						break;
					}
			 }
		}
		if(currentDateIsNotArrivalDate & rightIntervalBetweenFridayAndSaturday & correctIntervalOfDays & !overlappingDates)
			filterChain.doFilter(req, res);
		filterConfig.getServletContext().log("\n----Outside ReservationDatesFilter-----\n");
	}

	private boolean checkArrivalDateNotCurrentDate(String dateString){
		LocalDate currentDate = new LocalDate();
		LocalDate arrivalDate = GeneralUsage.convertStringToDate(dateString);
		if(currentDate.equals(arrivalDate))
			return false;
		return true;
	}
	
	private boolean checkCurrentDateFridayArrivalDateSunday(String arrivalDateString){
		boolean rightInterval = true;
		LocalDate currentDate = new LocalDate();
		LocalDate arrivalDate = GeneralUsage.convertStringToDate(arrivalDateString);
		boolean arrivalDateSunday  = false ; 
		if(GeneralUsage.getDayOfWeek(arrivalDate) == DaysOfWeek.SUNDAY)
			arrivalDateSunday = true;
		if(arrivalDateSunday){
			if(arrivalDate.minusDays(2).equals(currentDate))
				rightInterval = false;
		}
		return rightInterval;
	}
	
	private boolean isCorrectIntervalOfDays(String arrivalDateString, String departureDateString){
		LocalDate arrivalDate = GeneralUsage.convertStringToDate(arrivalDateString);
		LocalDate departureDate = GeneralUsage.convertStringToDate(departureDateString);
		Days days = Days.daysBetween(arrivalDate, departureDate);
		int interval = days.getDays();
		if(interval > 14)
			return false;
		return true;
	}
	
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	private boolean checkDatesOvelapping(LocalDate[] arrivalDepartureA, LocalDate[] arrivalDepartureB){
		boolean datesOvelap = false;
		LocalDate currentArrivalDate = arrivalDepartureA[0];
		LocalDate currentDepartureDate = arrivalDepartureA[1];
		LocalDate nextArrivalDate = arrivalDepartureB[0];
		LocalDate nextDepartureDate = arrivalDepartureB[1];
		if(currentArrivalDate.compareTo(nextDepartureDate) <=  0 | nextArrivalDate.compareTo(currentDepartureDate) <= 0)
			datesOvelap = true;
		return datesOvelap;
	}
	
	
	public class GuestReservationInfo {
		private String firstName;
		private String lastName;
		private int hotelId;
		private LocalDate[]arrivalDepartureDates;
		
		public GuestReservationInfo(String firstName, String lastNasme, int hotelId ,LocalDate[]arrivalDeparture){
			this.firstName = firstName;
			this.lastName = lastNasme;
			this.hotelId = hotelId;
			this.arrivalDepartureDates = arrivalDeparture;
		}
		
		@Override
		public boolean equals(Object other){
			if(!(other instanceof GuestReservationInfo))
				return false;
			GuestReservationInfo castedOther = (GuestReservationInfo)other;
			if(firstName.equals(castedOther.firstName) & lastName.equals(castedOther.lastName) & hotelId != castedOther.hotelId 
					& checkDatesOvelapping(arrivalDepartureDates,castedOther.arrivalDepartureDates) == true)
				return true;
			return false;
		}
		
		@Override
		public int hashCode(){
			return firstName.hashCode()+lastName.hashCode();
		}
		
		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public LocalDate[] getArrivalDepartureDates() {
			return arrivalDepartureDates;
		}

		public void setArrivalDepartureDates(LocalDate[] arrivalDepartureDates) {
			this.arrivalDepartureDates = arrivalDepartureDates;
		}
	}
}
