package com.coral.multi.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.coral.multi.general.AppConstants;
import com.coral.multi.mappedobjects.availability.requests.multi.MultiAvailabilityRequest;
import com.coral.multi.mappedobjects.cancelation.CancelationRequest;
import com.coral.multi.mappedobjects.general.AgentLogin;
import com.coral.multi.mappedobjects.reservrequest.MultiReservationRequest;
import com.coral.multi.utils.DbDaoUtils;

public class AuthorizationFilter implements Filter {

	private FilterConfig filterConfig;
	private static Logger logger = Logger.getLogger(AuthorizationFilter.class);

	public void destroy() {
		DbDaoUtils.closeConncetion();
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException,
			ServletException {
		filterConfig.getServletContext().log("\n--------Inside Authorization Filter------");
		AgentLogin agentLogin = null;
		if ((Boolean) req.getAttribute(AppConstants.IS_AVAILABILITY_REQUEST)) {
			logger.debug("--- Authenticating availability request---------");
			MultiAvailabilityRequest availabilityReq = (MultiAvailabilityRequest) req
					.getAttribute(AppConstants.CONVERTED_OBJECT);
			agentLogin = availabilityReq.getReservationHotels().getHotels().get(0).getAgentLogin();
		} else if ((Boolean) req.getAttribute(AppConstants.IS_RESERVATION_REQUEST)) {
			logger.debug("--- Authenticating reservation request ----------");
			MultiReservationRequest multiReservation = (MultiReservationRequest) req
					.getAttribute(AppConstants.CONVERTED_OBJECT);
			agentLogin = multiReservation.getReservDetails().get(0).getAgentLogin();
		} else {
			logger.debug("--- Authenticating cancelation request ----------");
			CancelationRequest cancelationRequest = (CancelationRequest) req
					.getAttribute(AppConstants.CONVERTED_OBJECT);
			agentLogin = cancelationRequest.getAgentLogin();
		}
		if (!DbDaoUtils.authorizationSuccess(agentLogin.getId(), agentLogin.getPassword(), req.getRemoteAddr())) {
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/authorizationFailed");
			requestDispatcher.include((HttpServletRequest) req, (HttpServletResponse) res);
		}else if((Boolean)req.getAttribute(AppConstants.IS_CANCELLATION_REQUEST)){
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/cancellationServlet");
			requestDispatcher.include(req, res);
		}
		else {
			filterChain.doFilter(req, res);
		}
		filterConfig.getServletContext().log("\n---------Outside Authorization Filter --------");
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
}
