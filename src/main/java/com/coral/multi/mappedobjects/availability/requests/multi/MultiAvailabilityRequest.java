package com.coral.multi.mappedobjects.availability.requests.multi;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("availabilityAndRatesRequests")
public class MultiAvailabilityRequest {
	@XStreamAlias("hotels")
	private ReservationHotels reservationHotels;

	public ReservationHotels getReservationHotels() {
		return reservationHotels;
	}

	public void setReservationHotels(ReservationHotels reservationHotels) {
		this.reservationHotels = reservationHotels;
	}
	
}
