package com.coral.multi.mappedobjects.reservresponse;

import java.util.ArrayList;
import java.util.List;


public class MultiReservationResponse {
	private List<ReservationResponse> responses;

	public MultiReservationResponse(){
		responses = new ArrayList<ReservationResponse>();
		
	}
	public List<ReservationResponse> getResponses() {
		return responses;
	}

	public void setResponses(List<ReservationResponse> responses) {
		this.responses = responses;
	}
}
