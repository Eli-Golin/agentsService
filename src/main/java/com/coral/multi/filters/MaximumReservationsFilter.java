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
import com.coral.multi.utils.DbDaoUtils;

public class MaximumReservationsFilter implements Filter{

	private FilterConfig filterConfig;

	public void destroy() {
		DbDaoUtils.closeConncetion();
	}

	public void doFilter(ServletRequest req, ServletResponse res,FilterChain filterChain) throws IOException, ServletException {
		filterConfig.getServletContext().log("\n------- Inside Maximum Resrvations Filter--------\n");
		MultiReservationRequest request = (MultiReservationRequest)req.getAttribute(AppConstants.CONVERTED_OBJECT);
		List<NewReservationRequest> reservDetails = request.getReservDetails();
		int maximumReservationsAllowed = DbDaoUtils.maximumReservationsAllowed();
		if(reservDetails.size() > maximumReservationsAllowed){
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/maximumReservationsViolation");
			requestDispatcher.include(req,res);
		}
		else if(maximumReservationsAllowed != -1)
			filterChain.doFilter(req,res);
		else
			filterConfig.getServletContext().log("maximumReservationsAllowed has retuned -1");
		filterConfig.getServletContext().log("\n-------- Outside Maximum Reservations Filter--------\n");
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
}
