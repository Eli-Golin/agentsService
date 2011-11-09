package com.coral.multi.servlets;

import static com.coral.multi.general.AppConstants.GUETS_NAMES_VALIDATION_FAILED;
import static com.coral.multi.general.AppConstants.GUETS_NAMES_VALIDATION_FAILED_NUM;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coral.multi.general.AppConstants;
import com.coral.multi.general.GeneralUsage;
import com.coral.multi.mappedobjects.errorresponse.ErrorResponse;
import com.coral.multi.mappedobjects.errorresponse.ErrorResponses;
public class GuestsNamesErrorServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		ErrorResponses errorResponses = new ErrorResponses();
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setExtraDate(GeneralUsage.getCurrentDateAsString());
		errorResponse.setResultNum(0);
		errorResponse.setResultStatus(GUETS_NAMES_VALIDATION_FAILED_NUM);
		errorResponse.setResultMessage(GUETS_NAMES_VALIDATION_FAILED);
		List<ErrorResponse> listOfErrors = new LinkedList<ErrorResponse>();
		listOfErrors.add(errorResponse);
		request.setAttribute(AppConstants.CONVERTED_OBJECT, errorResponses);
		GeneralUsage.setErrorResponseAttribute(request);
	}

}
