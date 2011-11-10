package com.coral.multi.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.coral.multi.general.AppConstants;

public class ClientOriginResolverFilter implements Filter {
	private FilterConfig filterConfig;

	public void destroy() {
		// TODO: do nothing
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
			ServletException {
		filterConfig.getServletContext().log("\n------- Inside ClientOriginResolverFilter --------\n");
		String remoteIp = request.getRemoteHost();
		String[] usaIps = filterConfig.getInitParameter(AppConstants.IS_USA).split(",");
		request.setAttribute(AppConstants.IS_USA, new Boolean(false));
		for(String ip : usaIps){
			if(remoteIp.equals(ip)){
				request.setAttribute(AppConstants.IS_USA, new Boolean(true));
				break;
			}
		}
		filterChain.doFilter(request, response);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/responseSender");
		requestDispatcher.forward(request, response);
		filterConfig.getServletContext().log("\n------- Outside ClientOriginResolverFilter --------\n");
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}
