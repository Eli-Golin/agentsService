package com.coral.multi.servlets;

import static com.coral.multi.general.AppConstants.ARRIVAL_DATE_EQUALS_RESERVATION_DATE;
import static com.coral.multi.general.AppConstants.DATES_OVERLAPPING_VIOLATION;
import static com.coral.multi.general.AppConstants.DATES_OVERLAPPING_VIOLATION_NUM;
import static com.coral.multi.general.AppConstants.DAYS_INTERVAL_VIOLATION;
import static com.coral.multi.general.AppConstants.DAYS_INTERVAL_VIOLATION_NUM;
import static com.coral.multi.general.AppConstants.RESERVATION_AND_ARRIVAL_DATES_ARE_THE_SAME;
import static com.coral.multi.general.AppConstants.RESERVATION_ON_FRIDAY_ARRIVAL_ON_NEXT_SUNDAY;
import static com.coral.multi.general.AppConstants.RESERVATION_ON_FRIDAY_ARRIVAL_ON_SATURDAY;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coral.multi.general.AppConstants;
import com.coral.multi.general.DatesViolation;
import com.coral.multi.general.GeneralUsage;
import com.coral.multi.mappedobjects.errorresponse.ErrorResponse;
import com.coral.multi.mappedobjects.errorresponse.ErrorResponses;

public class DatesErrorServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1692264187871920618L;

	public void doPost(HttpServletRequest request, HttpServletResponse response){
		getServletContext().log("\n------Inside DatesErrorServlet--------\n");
		DatesViolation violation = (DatesViolation)request.getAttribute("violationType");
		ErrorResponses errorResponses = new ErrorResponses();
		ErrorResponse singleResponse = new ErrorResponse();
		singleResponse.setExtraDate(GeneralUsage.getCurrentDateAsString());
		singleResponse.setResultNum(0);
		if(violation == DatesViolation.RESERVATION_DATE_EQUALS_ARRIVAL_DATE){
			singleResponse.setResultMessage(RESERVATION_AND_ARRIVAL_DATES_ARE_THE_SAME);
			singleResponse.setResultStatus(ARRIVAL_DATE_EQUALS_RESERVATION_DATE);
		}
		else if(violation == DatesViolation.RESERVATION_DATE_FRIDAY_ARRIVAL_DATE_NEXT_SUNDAY){
			singleResponse.setResultMessage(RESERVATION_ON_FRIDAY_ARRIVAL_ON_NEXT_SUNDAY);
			singleResponse.setResultStatus(RESERVATION_ON_FRIDAY_ARRIVAL_ON_SATURDAY);
		}
		else if(violation == DatesViolation.RESERVATION_INTERVAL_VIOLATION){
			singleResponse.setResultMessage(DAYS_INTERVAL_VIOLATION);
			singleResponse.setResultStatus(DAYS_INTERVAL_VIOLATION_NUM);
		}
		else {
			singleResponse.setResultMessage(DATES_OVERLAPPING_VIOLATION);
			singleResponse.setResultStatus(DATES_OVERLAPPING_VIOLATION_NUM);
		}
		errorResponses.getErrorResponses().add(singleResponse);
		request.setAttribute(AppConstants.CONVERTED_OBJECT, errorResponses);
		GeneralUsage.setErrorResponseAttribute(request);
		getServletContext().log("\n------Outside DatesErrorServlet--------\n");
	}
}
