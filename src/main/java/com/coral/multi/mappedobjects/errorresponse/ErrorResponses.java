package com.coral.multi.mappedobjects.errorresponse;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("results")
public class ErrorResponses {
	
	@XStreamImplicit(itemFieldName="singleResult")
	private List<ErrorResponse> errorResponses;

	public ErrorResponses(){
		errorResponses = new ArrayList<ErrorResponse>();
	}
	
	public List<ErrorResponse> getErrorResponses() {
		return errorResponses;
	}

	public void setErrorResponses(List<ErrorResponse> errorResponses) {
		this.errorResponses = errorResponses;
	}
}
