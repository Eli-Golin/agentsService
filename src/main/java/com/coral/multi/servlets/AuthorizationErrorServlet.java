package com.coral.multi.servlets;

import static com.coral.multi.general.AppConstants.AUTHENTICATION_FAILED;
import static com.coral.multi.general.AppConstants.AUTHENTICATION_FAILED_NUM;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coral.multi.general.AppConstants;
import com.coral.multi.general.GeneralUsage;
import com.coral.multi.mappedobjects.errorresponse.ErrorResponse;
import com.coral.multi.mappedobjects.errorresponse.ErrorResponses;

public class AuthorizationErrorServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3846811191919133773L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		getServletContext().log("\n------Inside Authorization Error Servlet");
		ErrorResponses errorResponses = new ErrorResponses();
		ErrorResponse errorResponse =  new ErrorResponse();
		errorResponse.setResultNum(0);
		errorResponse.setResultStatus(AUTHENTICATION_FAILED_NUM);
		errorResponse.setResultMessage(AUTHENTICATION_FAILED);
		errorResponse.setExtraDate(GeneralUsage.getCurrentDateAsString());
		errorResponses.getErrorResponses().add(errorResponse);
		request.setAttribute(AppConstants.CONVERTED_OBJECT, errorResponses);
		GeneralUsage.setErrorResponseAttribute(request);
		getServletContext().log("\n------Inside Authorization Error Servlet");
	}
}
