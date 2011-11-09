package com.coral.multi.mappedobjects.reservrequest;

import java.util.ArrayList;
import java.util.List;


public class MultiReservationRequest {
	
	private List<NewReservationRequest> reservDetails;

	public MultiReservationRequest(){
		reservDetails = new ArrayList<NewReservationRequest>();
	}
	
	public List<NewReservationRequest> getReservDetails() {
		return reservDetails;
	}

	public void setReservDetails(List<NewReservationRequest> reservDetails) {
		this.reservDetails = reservDetails;
	}
}
