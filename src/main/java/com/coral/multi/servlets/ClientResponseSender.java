package com.coral.multi.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coral.multi.general.AppConstants;
import com.coral.multi.general.GeneralUsage;

public class ClientResponseSender extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7301797033021114350L;
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		getServletContext().log("---------- Inside ClientResponeSender----------\n");
		try {
			GeneralUsage.sendAnswerToClient(request.getRemoteHost(), (String) request.getAttribute(AppConstants.CONVERTED_OBJECT), response);
		} catch (IOException e) {
			request.getServletContext().log("An IO Exception occurred while sending the answer back to the client");
			e.printStackTrace();
		}
		getServletContext().log("---------- Outside ClientResponeSender----------\n");
	}

}
