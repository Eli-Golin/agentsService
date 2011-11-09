package com.coral.multi.servlets;

import static com.coral.multi.general.AppConstants.PRICE_VALIDATION_FAILED;
import static com.coral.multi.general.AppConstants.PRICE_VALIDATION_FAILED_NUM;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coral.multi.general.AppConstants;
import com.coral.multi.general.GeneralUsage;
import com.coral.multi.mappedobjects.errorresponse.ErrorResponse;
import com.coral.multi.mappedobjects.errorresponse.ErrorResponses;
public class PriceValidationErrorServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2459917977110479978L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		getServletContext().log("\n-------Inside Price Validation Error Servlet-------\n");
		ErrorResponses errorResponses = new ErrorResponses();
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setExtraDate(GeneralUsage.getCurrentDateAsString());
		errorResponse.setResultMessage(PRICE_VALIDATION_FAILED);
		errorResponse.setResultNum(0);
		errorResponse.setResultStatus(PRICE_VALIDATION_FAILED_NUM);
		errorResponses.getErrorResponses().add(errorResponse);
		request.setAttribute(AppConstants.CONVERTED_OBJECT, errorResponses);
		GeneralUsage.setErrorResponseAttribute(request);
		getServletContext().log("\n-------Outside Price Validation Error Servlet-------\n");
	}
}
