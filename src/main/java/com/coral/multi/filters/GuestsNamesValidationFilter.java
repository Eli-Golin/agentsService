package com.coral.multi.filters;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.coral.multi.general.AppConstants;
import com.coral.multi.mappedobjects.reservrequest.MultiReservationRequest;
import com.coral.multi.mappedobjects.reservrequest.NewReservationRequest;

public class GuestsNamesValidationFilter implements Filter {

	private FilterConfig filterConfig;

	public void destroy() {
		// Nothing todo
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException,
			ServletException {
		filterConfig.getServletContext().log("\n--------Inside GuestsNamesValidationFilter Filter------\n");
		MultiReservationRequest multiReservRequest = (MultiReservationRequest)req.getAttribute(AppConstants.CONVERTED_OBJECT);
		List<NewReservationRequest> reservDetails = multiReservRequest.getReservDetails();
		boolean validationPassed = true;
		for(NewReservationRequest reservRequest : reservDetails){
			validationPassed = checkValidGuestName(reservRequest);
			if(!validationPassed)
				break;
		}
		if(!validationPassed) {
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/noGuestName");
			requestDispatcher.include(req, res);
		}
		else
			filterChain.doFilter(req, res);
		filterConfig.getServletContext().log("\n--------Outside GuestsNamesValidationFilter Filter------\n");
	}

	private boolean checkValidGuestName(NewReservationRequest reservRequest){
		boolean ans = true;
		if(reservRequest.getFirstName().equals("") | reservRequest.getLastName().equals(""))
			ans = false;
		return ans;
	}
	
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
}
