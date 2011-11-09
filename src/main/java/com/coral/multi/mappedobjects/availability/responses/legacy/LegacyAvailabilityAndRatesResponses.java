package com.coral.multi.mappedobjects.availability.responses.legacy;

import java.util.ArrayList;
import java.util.List;

public class LegacyAvailabilityAndRatesResponses {
	private int numOfResInIten;
	private List<LegacyAvailabilityAndRatesResponse> responses;
	
	public LegacyAvailabilityAndRatesResponses(){
		responses = new ArrayList<LegacyAvailabilityAndRatesResponse>();
	}

	public int getNumOfResInIten() {
		return numOfResInIten;
	}
	
	public void setNumOfResInIten(int numOfResInIten) {
		this.numOfResInIten = numOfResInIten;
	}

	public List<LegacyAvailabilityAndRatesResponse> getResponses() {
		return responses;
	}
	
	public void setResponses(List<LegacyAvailabilityAndRatesResponse> response) {
		this.responses = response;
	}
	
}
