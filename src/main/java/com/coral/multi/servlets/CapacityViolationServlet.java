package com.coral.multi.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coral.multi.general.AppConstants;
import com.coral.multi.general.GeneralUsage;
import com.coral.multi.mappedobjects.errorresponse.ErrorResponse;
import com.coral.multi.mappedobjects.errorresponse.ErrorResponses;
public class CapacityViolationServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8061998621913291901L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		ErrorResponses errorResponses = new ErrorResponses();
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setExtraDate(GeneralUsage.getCurrentDateAsString());
		errorResponse.setResultNum(0);
		errorResponse.setResultMessage(AppConstants.CAPACITY_VIOLATION);
		errorResponse.setResultStatus(AppConstants.CAPACITY_VIOLATION_NUM);
		errorResponses.getErrorResponses().add(errorResponse);
		request.setAttribute(AppConstants.CONVERTED_OBJECT, errorResponses);
		GeneralUsage.setErrorResponseAttribute(request);
	}
}
