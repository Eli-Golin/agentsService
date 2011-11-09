package com.coral.multi.servlets;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coral.multi.general.AppConstants;
import com.coral.multi.general.GeneralUsage;
import com.coral.multi.mappedobjects.cancelation.CancelationRequest;
import com.coral.multi.mappedobjects.cancelation.CancelationResponse;
import com.coral.multi.mappedobjects.errorresponse.ErrorResponse;
import com.coral.multi.mappedobjects.errorresponse.ErrorResponses;
import com.coral.multi.utils.DbDaoUtils;

public class CancellationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		request.getServletContext().log("\n------ Inside CancellationServlet -------\n");
		CancelationRequest cancellationRequest = (CancelationRequest)request.getAttribute(AppConstants.CONVERTED_OBJECT);
		String cancellationAnswer = DbDaoUtils.cancelReservation(cancellationRequest.getAgentLogin().getId(), cancellationRequest.getReservNum());
		if(cancellationAnswer.equals("0")){
			ErrorResponses errorResponses = new ErrorResponses();
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setExtraDate(GeneralUsage.getCurrentDateAsString());
			errorResponse.setResultNum(0);
			errorResponse.setResultMessage(AppConstants.CANCELATION_REQUEST_FAILED+cancellationRequest.getReservNum());
			errorResponse.setResultStatus(AppConstants.CANCELATION_REQUEST_FAILED_NUM);
			List<ErrorResponse> errorList = new LinkedList<ErrorResponse>();
			errorList.add(errorResponse);
			errorResponses.setErrorResponses(errorList);
			request.setAttribute(AppConstants.CONVERTED_OBJECT, errorResponses);
			GeneralUsage.setErrorResponseAttribute(request);
		}
		else{
			CancelationResponse cancelationResponse = new CancelationResponse();
			cancelationResponse.setComments("This reservation is cancelled");
			cancelationResponse.setStatus("Cancelled by the member");
			cancelationResponse.setNumOfReservationsIten(1);
			cancelationResponse.setReservNum(cancellationRequest.getReservNum());
			String[]cancellationDetails = cancellationAnswer.split(",");
			cancelationResponse.setTotalPrice(Integer.parseInt(cancellationDetails[0]));
			cancelationResponse.setCurrency(cancellationDetails[1]);
			request.setAttribute(AppConstants.CONVERTED_OBJECT, cancelationResponse);
		}
		request.getServletContext().log("\n------ Outside CancellationServlet -------\n");
	}

}
