package com.coral.multi.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.coral.multi.general.AppConstants;
import com.coral.multi.general.GeneralUsage;
import com.coral.multi.mappedobjects.availability.requests.legacy.LegacyAvailabilityAndRatesRequests;
import com.coral.multi.mappedobjects.availability.requests.multi.MultiAvailabilityRequest;
import com.coral.multi.mappedobjects.availability.responses.legacy.LegacyAvailabilityAndRatesResponses;
import com.coral.multi.mappedobjects.availability.responses.multi.MultiAvailabilityResponse;
import com.coral.multi.mappedobjects.cancelation.CancelationRequest;
import com.coral.multi.mappedobjects.cancelation.CancelationResponse;
import com.coral.multi.mappedobjects.errorresponse.ErrorResponses;
import com.coral.multi.mappedobjects.reservrequest.MultiReservationRequest;
import com.coral.multi.mappedobjects.reservrequest.OldReservationRequest;
import com.coral.multi.mappedobjects.reservresponse.MultiReservationResponse;
import com.coral.multi.mappedobjects.reservresponse.ReservationResponse;
import com.coral.multi.wrappers.XmlToObjectWrapper;

public class XmlConverterFilter implements Filter {

	private FilterConfig filterConfig;
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse resposne, FilterChain filterChain) throws IOException,
			ServletException {
		filterConfig.getServletContext().log("\n---------------Inside XmlConverterFilter----------------\n");
		String[] requestTypeAndSentData = GeneralUsage.readParameters(request);
		int requestTypeIndex = 0;
		int sentDataIndex = 1;
		Boolean isUsaClient = (Boolean)request.getAttribute(AppConstants.IS_USA);
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		Object builtObject;
		defineAttributeValues(request, requestTypeAndSentData, requestTypeIndex);
		if(isUsaClient){ // old formats for usa client
			builtObject = buildFromOldRequest(request,requestTypeAndSentData, sentDataIndex, context);
		}
		else { // multi requests for local clients
			builtObject = buildFromNewRequest(request,requestTypeAndSentData, sentDataIndex, context);
		}
		request.setAttribute(AppConstants.CONVERTED_OBJECT, builtObject);
		filterChain.doFilter(request, resposne);
		String xml;
		if(isUsaClient){
			xml = buildOldXmlResponse(request, requestTypeAndSentData, requestTypeIndex, context);
		}
		else {
			xml = buildNewXmlResponse(request, requestTypeAndSentData, requestTypeIndex, context);
		}
		request.setAttribute(AppConstants.CONVERTED_OBJECT, xml);
		filterConfig.getServletContext().log("\n---------------Outside XmlConverterFilter----------------\n");
	}
	
	private void defineAttributeValues(ServletRequest request, String[] requestTypeAndSentData,int requestTypeIndex) {
		if(requestTypeAndSentData[requestTypeIndex].equals("2")) //availabilityRequest
			request.setAttribute(AppConstants.IS_AVAILABILITY_REQUEST, new Boolean(true));
		else
			request.setAttribute(AppConstants.IS_AVAILABILITY_REQUEST, new Boolean(false));
		if(requestTypeAndSentData[requestTypeIndex].equals("3")) //reservation request
			request.setAttribute(AppConstants.IS_RESERVATION_REQUEST, new Boolean(true));
		else
			request.setAttribute(AppConstants.IS_RESERVATION_REQUEST, new Boolean(false));
		if(requestTypeAndSentData[requestTypeIndex].equals("4")) //cancellation requestType
			request.setAttribute(AppConstants.IS_CANCELLATION_REQUEST, new Boolean(true));
		else
			request.setAttribute(AppConstants.IS_CANCELLATION_REQUEST, new Boolean(false));
	}

	private String buildNewXmlResponse(ServletRequest request, String[] requestTypeAndSentData, int requestTypeIndex,
			WebApplicationContext context) {
		String xmlResponse = null;
		if((Boolean)request.getAttribute(AppConstants.IS_AVAILABILITY_REQUEST)){
			XmlToObjectWrapper wrapper = (XmlToObjectWrapper)context.getBean("MultiAvailabilityResponseWrapper");
			MultiAvailabilityResponse availabilityResponse = (MultiAvailabilityResponse)request.getAttribute(AppConstants.CONVERTED_OBJECT);
			xmlResponse = wrapper.returnAsXml(availabilityResponse);
		}
		else if((Boolean)request.getAttribute(AppConstants.IS_RESERVATION_REQUEST)){
			XmlToObjectWrapper wrapper = (XmlToObjectWrapper)context.getBean("MultiResrvResWrapper");
			MultiReservationResponse multiReservationResponse = (MultiReservationResponse)request.getAttribute(AppConstants.CONVERTED_OBJECT);
			xmlResponse = wrapper.returnAsXml(multiReservationResponse);
		}else if((Boolean)request.getAttribute(AppConstants.IS_CANCELLATION_REQUEST)){
			XmlToObjectWrapper wrapper = (XmlToObjectWrapper)context.getBean("CancelResponseWrapper");
			CancelationResponse cancellationResponse = (CancelationResponse)request.getAttribute(AppConstants.CONVERTED_OBJECT);
			xmlResponse = wrapper.returnAsXml(cancellationResponse);
		}
		else {
			XmlToObjectWrapper wrapper = (XmlToObjectWrapper)context.getBean("ErrorResponsesWrapper");
			ErrorResponses cancelationResponse = (ErrorResponses)request.getAttribute(AppConstants.CONVERTED_OBJECT);
			xmlResponse = wrapper.returnAsXml(cancelationResponse);
		}
		return xmlResponse;
	}

	private String buildOldXmlResponse(ServletRequest request, String[] requestTypeAndSentData, int requestTypeIndex,
			WebApplicationContext context) {
		String xmlResponse = null;
		if((Boolean)request.getAttribute(AppConstants.IS_AVAILABILITY_REQUEST)){
			XmlToObjectWrapper wrapper = (XmlToObjectWrapper)context.getBean("LegacyAvailabilityAndRatesResponsesWrapper");
			LegacyAvailabilityAndRatesResponses availabilityResponses = (LegacyAvailabilityAndRatesResponses)request.getAttribute(AppConstants.CONVERTED_OBJECT);
			xmlResponse = wrapper.returnAsXml(availabilityResponses);
		}
		else if((Boolean)request.getAttribute(AppConstants.IS_RESERVATION_REQUEST)){
			XmlToObjectWrapper wrapper = (XmlToObjectWrapper)context.getBean("ReservResWrapper");
			ReservationResponse reservationResponse = (ReservationResponse)request.getAttribute(AppConstants.CONVERTED_OBJECT);
			xmlResponse = wrapper.returnAsXml(reservationResponse);
		}
		else if((Boolean)request.getAttribute(AppConstants.IS_CANCELLATION_REQUEST)){
			XmlToObjectWrapper wrapper = (XmlToObjectWrapper)context.getBean("CancelResponseWrapper");
			CancelationResponse cancellationResponse = (CancelationResponse)request.getAttribute(AppConstants.CONVERTED_OBJECT);
			xmlResponse = wrapper.returnAsXml(cancellationResponse);
		}
		else {
			XmlToObjectWrapper wrapper = (XmlToObjectWrapper)context.getBean("ErrorResponsesWrapper");
			ErrorResponses cancelationResponse = (ErrorResponses)request.getAttribute(AppConstants.CONVERTED_OBJECT);
			xmlResponse = wrapper.returnAsXml(cancelationResponse);
		}
		return xmlResponse;
	}


	private Object buildFromNewRequest(ServletRequest request,String[]requestTypeAndSentData  , int sentDataIndex,
			WebApplicationContext context) {
		if((Boolean)request.getAttribute(AppConstants.IS_RESERVATION_REQUEST)){	//reservation			
			XmlToObjectWrapper wrapper = (XmlToObjectWrapper)context.getBean("MultiReservReqWrapper");
			MultiReservationRequest multiReservationRequest = (MultiReservationRequest)wrapper.buildFromXml(requestTypeAndSentData[sentDataIndex]);
			return multiReservationRequest;
		}
		else if((Boolean)request.getAttribute(AppConstants.IS_AVAILABILITY_REQUEST)){ //availability
			XmlToObjectWrapper wrapper =  (XmlToObjectWrapper)context.getBean("MultiAvailabilityRequestWrapper");
			MultiAvailabilityRequest multiAvailabilityRequest = (MultiAvailabilityRequest)wrapper.buildFromXml(requestTypeAndSentData[sentDataIndex]);
			return multiAvailabilityRequest;
		}
		else { //cancellation
			XmlToObjectWrapper wrapper = (XmlToObjectWrapper)context.getBean("CancelRequestWrapper");
			CancelationRequest cancelationRequest = (CancelationRequest)wrapper.buildFromXml(requestTypeAndSentData[sentDataIndex]);
			return cancelationRequest;
		}
	}

	private Object buildFromOldRequest(ServletRequest request ,String[] requestTypeAndSentData, int sentDataIndex,
			WebApplicationContext context) {
		if((Boolean)request.getAttribute(AppConstants.IS_RESERVATION_REQUEST)){	//reservation			
			XmlToObjectWrapper wrapper = (XmlToObjectWrapper)context.getBean("LegacyReservReqWrapper");
			OldReservationRequest oldReservationRequest = (OldReservationRequest)wrapper.buildFromXml(requestTypeAndSentData[sentDataIndex]);
			return oldReservationRequest;
		}
		else if((Boolean)request.getAttribute(AppConstants.IS_AVAILABILITY_REQUEST)){ //availability
			XmlToObjectWrapper wrapper = (XmlToObjectWrapper)context.getBean("LegacyAvailabilityAndRatesRequestsWrapper");
			LegacyAvailabilityAndRatesRequests legacyAvailabilityRequest = (LegacyAvailabilityAndRatesRequests)wrapper.buildFromXml(requestTypeAndSentData[sentDataIndex]);
			return legacyAvailabilityRequest;
		}
		else { //cancellation
			XmlToObjectWrapper wrapper = (XmlToObjectWrapper)context.getBean("CancelRequestWrapper");
			CancelationRequest cancelationRequest = (CancelationRequest)wrapper.buildFromXml(requestTypeAndSentData[sentDataIndex]);
			return cancelationRequest;
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}
