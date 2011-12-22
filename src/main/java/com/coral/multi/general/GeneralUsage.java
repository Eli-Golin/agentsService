package com.coral.multi.general;

import static com.coral.multi.general.AppConstants.DATE_FORMAT;
import static com.coral.multi.general.AppConstants.NOA_TOURSE_IP;
import static com.coral.multi.general.AppConstants.REQUEST_TYPE_PARAMETER;
import static com.coral.multi.general.AppConstants.SENT_DATA_PARAMETER;
import static com.coral.multi.general.AppConstants.UTF_8_ENCODING;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class GeneralUsage {

	public static String createStringContent(String fileName) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream = loader.getResourceAsStream(fileName);
		BufferedReader bufReader = new BufferedReader(new InputStreamReader(stream));
		String temp = "";
		String answerString = "";
		do {
			try {
				if (!temp.equals(""))
					answerString += temp + '\n';
				temp = bufReader.readLine();
				if (null == temp)
					answerString = answerString.substring(0, answerString.length() - 1);
			} catch (IOException e) {
				System.out.println("Could not read from resource file: " + fileName);
			}
		} while (temp != null);
		return answerString;
	}

	public static String getCurrentDateAsString() {
		DateTimeFormatter dateFormatter = DateTimeFormat.forPattern(DATE_FORMAT);
		return new LocalDate().toString(dateFormatter);
	}

	public static LocalDate convertStringToDate(String dateString) {
		DateTimeFormatter dateFormatter = DateTimeFormat.forPattern(DATE_FORMAT);
		LocalDate date = dateFormatter.withZone(DateTimeZone.UTC).parseDateTime(dateString).toLocalDate();
		return date;
	}

	public static DaysOfWeek getDayOfWeek(LocalDate date) {
		int dayOfWeek = date.getDayOfWeek();
		switch (dayOfWeek) {
		case DateTimeConstants.SATURDAY:
			return DaysOfWeek.SATURDAY;
		case DateTimeConstants.SUNDAY:
			return DaysOfWeek.SUNDAY;
		case DateTimeConstants.MONDAY:
			return DaysOfWeek.MONDAY;
		case DateTimeConstants.TUESDAY:
			return DaysOfWeek.TUESDAY;
		case DateTimeConstants.WEDNESDAY:
			return DaysOfWeek.WEDNESDAY;
		case DateTimeConstants.THURSDAY:
			return DaysOfWeek.THURSDAY;
		default:
			return DaysOfWeek.FRIDAY;
		}
	}

	public static String convertSqlDateStringToJodaDateString(String sqlDate) {
		StringTokenizer tokenizer = new StringTokenizer(sqlDate, "-");
		String year = tokenizer.nextToken();
		String month = tokenizer.nextToken();
		String day = tokenizer.nextToken();
		return day + "/" + month + "/" + year;
	}

	public static String convertJodaDateStringToSqlDateString(String jodaDate) {
		StringTokenizer tokenizer = new StringTokenizer(jodaDate, "/");
		String day = tokenizer.nextToken();
		String month = tokenizer.nextToken();
		String year = tokenizer.nextToken();
		return year + "-" + month + "-" + day;
	}

	private static String getHttpBody(BufferedReader bufReader) {
		String ans = "";
		try {
			String line = bufReader.readLine();
			while (line != null) {
				ans += line;
				line = bufReader.readLine();
			}
		} catch (IOException e) {
			System.out.println("Could not read HttpBody: " + e);
		}
		return ans;
	}

	public static boolean isCurrentTimeLaterThanNoon(){
		LocalTime currentTime = new LocalTime();
		LocalTime noon = new LocalTime(12,0);
		if(currentTime.isAfter(noon))
			return true;
		else
			return false;
	}
	
	public static void readParameter(ServletRequest servletRequest) throws IOException { // assigns
																							// the
																							// requestType
																							// &
																							// sentData
																							// values
																							// to
																							// static
																							// variables
																							// in
																							// AppConstants
		String remoteIp = servletRequest.getRemoteHost();
		servletRequest.setCharacterEncoding(AppConstants.UTF_8_ENCODING);
		if (remoteIp.equals(NOA_TOURSE_IP)) {
			String body = getHttpBody(servletRequest.getReader());
			String[] splittedBody = body.split("&");
			int requestTypeSideIndex = 0;
			String requestTypeParameter = splittedBody[requestTypeSideIndex];
			String[] splittedRequestTypeParameter = requestTypeParameter.split("=");
			int requestTypeValueIndex = 1;
			AppConstants.requestTypeParameterValue = splittedRequestTypeParameter[requestTypeValueIndex];
			int sentDataParameterNameLength = 9;
			AppConstants.sentDataParameterValue = splittedBody[1].substring(sentDataParameterNameLength,
					splittedBody[1].length());
		} else {
			AppConstants.requestTypeParameterValue = servletRequest.getParameter(REQUEST_TYPE_PARAMETER);
			AppConstants.sentDataParameterValue = servletRequest.getParameter(SENT_DATA_PARAMETER);
		}
	}

	public static synchronized String[] readParameters(ServletRequest servletRequest) throws IOException { // assigns
		// the
		// requestType
		// &
		// sentData
		// values
		// to
		// static
		// variables
		// in
		// AppConstants
		String [] ans = new String [2];
		String remoteIp = servletRequest.getRemoteHost();
		int requestTypeIndex = 0;
		int sentDataIndex = 1;
		servletRequest.setCharacterEncoding(UTF_8_ENCODING);
		if (remoteIp.equals(NOA_TOURSE_IP)) {
			String body = getHttpBody(servletRequest.getReader());
			String[] splittedBody = body.split("&");
			int requestTypeSideIndex = 0;
			String requestTypeParameter = splittedBody[requestTypeSideIndex];
			String[] splittedRequestTypeParameter = requestTypeParameter.split("=");
			int requestTypeValueIndex = 1;
			ans[requestTypeIndex] = splittedRequestTypeParameter[requestTypeValueIndex];
			int sentDataParameterNameLength = 9;
			ans[sentDataIndex] = splittedBody[1].substring(sentDataParameterNameLength,
					splittedBody[1].length());
		} else {
			ans[requestTypeIndex] = servletRequest.getParameter(REQUEST_TYPE_PARAMETER);
			ans[sentDataIndex] = servletRequest.getParameter(SENT_DATA_PARAMETER);
		}
		return ans;
	}

	public static void sendAnswerToClient(String clientIp, String answer, ServletResponse servletResponse)
			throws IOException {
		if (clientIp.equals(NOA_TOURSE_IP)) {
			answer = removeNonUtf8CompliantCharacters(answer);
			PrintWriter printWriter = servletResponse.getWriter();
			printWriter.write(answer);
			printWriter.flush();
			printWriter.close();
		} else {
			System.out.println("\n"+answer+"\n");
			servletResponse.getOutputStream().write(answer.getBytes(UTF_8_ENCODING));
		}
	}

	private static String removeNonUtf8CompliantCharacters(String inString) throws UnsupportedEncodingException {
		if (null == inString)
			return null;
		byte[] byteArr = inString.getBytes();
		for (int i = 0; i < byteArr.length; i++) {
			byte ch = byteArr[i];
			// remove any characters outside the valid UTF-8 range as well as
			// all control characters
			// except tabs and new lines
			if (!((ch > 31 && ch < 253) || (ch > 1487 && ch < 1515) || ch == '\t' || ch == '\n' || ch == '\r')) {
				byteArr[i] = ' ';
			}
		}
		return new String(byteArr);
	}
	
	public static void setErrorResponseAttribute(HttpServletRequest request){
		request.setAttribute(AppConstants.IS_AVAILABILITY_REQUEST, false);
		request.setAttribute(AppConstants.IS_RESERVATION_REQUEST, false);
		request.setAttribute(AppConstants.IS_CANCELLATION_REQUEST, false);
	}
	
}
