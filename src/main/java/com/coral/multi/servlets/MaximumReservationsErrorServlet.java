package com.coral.multi.servlets;

import static com.coral.multi.general.AppConstants.RESERVATIONS_AMOUNT_VIOLATION;
import static com.coral.multi.general.AppConstants.RESERVATIONS_AMOUNT_VIOLATION_NUM;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coral.multi.general.AppConstants;
import com.coral.multi.general.GeneralUsage;
import com.coral.multi.mappedobjects.errorresponse.ErrorResponse;
import com.coral.multi.mappedobjects.errorresponse.ErrorResponses;

public class MaximumReservationsErrorServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1334245012726095953L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		getServletContext().log("\n--------Inside Maximum Reservations Error Servlet-------\n");
		ErrorResponses errorResponses = new ErrorResponses();
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setExtraDate(GeneralUsage.getCurrentDateAsString());
		errorResponse.setResultMessage(RESERVATIONS_AMOUNT_VIOLATION);
		errorResponse.setResultStatus(RESERVATIONS_AMOUNT_VIOLATION_NUM);
		errorResponse.setResultNum(0);
		errorResponses.getErrorResponses().add(errorResponse);
		request.setAttribute(AppConstants.CONVERTED_OBJECT, errorResponses);
		GeneralUsage.setErrorResponseAttribute(request);
		getServletContext().log("\n--------Outside Maximum Reservations Error Servlet-------\n");
	}
}
