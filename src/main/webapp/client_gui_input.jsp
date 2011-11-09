<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>

<html>
<head>
<meta http-equiv="Content-Type"
	content="text/plain; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>

	<form action="client_gui_output.jsp" method="post">
		<h2>Request type:</h2>
		<input type="text" name="requestType" id="requestType" value="2-availability,3-reservation,4-cancellation"> <br />
		<h2>Agent Id:</h2>
		<input type="text" name="agentId" id="agentId" /> <br />
		<h2>Password:</h2>
		<input type="text" name="password" id="password" /> <br />
		<h2>Nationality:</h2>
		<input type="text" name="geustNationality" id="geustNationality" value="IL/ISR for Israelies, or any other for foreign"/> <br />
		<h2>Guest First Name:</h2>
		<input type="text" name="geustFirstName" id="geustFirstName" /> <br />
		<h2>Guest Last Name:</h2>
		<input type="text" name="geustLastName" id="geustLastName" /> <br />
		<h2>Dan Membership Number:</h2>
		<input type="text" name="eDanMemberNo" id="eDanMemberNo" /> <br />
		<h2>Mop:</h2>
		<input type="text" name="mop" id="mop" /> <br />
		<h2>Travel Agent Reference:</h2>
		<input type="text" name="travelAgentReference"
			id="travelAgentReference" /> <br />
		<h2>Number of adults:</h2>
		<input type="text" name="numberOfAdults" id="numberOfAdults" value ="2"/> <br />
		<h2>Number of children:</h2>
		<input type="text" name="numberOfChildren" id="numberOfChildren" value="0"/> <br />
		<h2>Number of Babies:</h2>
		<input type="text" name="numberOfBabies" id="numberOfBabies" value="0"/> <br />
		<h2>Hotel area cd:</h2>
		<input type="text" name="hotelAreaCd" id="hotelAreaCd" value="0"/> <br />
		<h2>Hotel ID:</h2>
		<input type="text" name="hotelId" id="hotelId" value="8"/> <br />
		<h2>Arrival Date:</h2>
		<input type="text" name="arrivalDate" id="arrivalDate" value="dd/mm/yyyy"/> <br />
		<h2>Departure Date:</h2>
		<input type="text" name="departureDate" id="departureDate" value="dd/mm/yyyy"/> <br />
		<h2>Board arrangement:</h2>
		<input type="text" name="boardArrangement" id="boardArrangement" value="BB,FB,HB"/> <br />
		<h2>Number of Friday dinners:</h2>
		<input type="text" name="numOfFridayNightDinners"
			id="numOfFridayNightDinners" value="0"/> <br />
		<h2>Number of Saturday lunches:</h2>
		<input type="text" name="numOfSaturdayLunches"
			id="numOfSaturdayLunches" value="0"/> <br />
		<h2>Number of Holiday lunches:</h2>
		<input type="text" name="numOfHolidayLunches" id="numOfHolidayLunches" value="0"/>
		<br />
		<h2>Room Type:</h2>
		<input type="text" name="roomType" id="roomType" /> <br />
		<h2>Price:</h2>
		<input type="text" name="price" id="price" /> <br />
		<h2>Currency:</h2>
		<input type="text" name="currency" id="currency" /> <br />
		<h2>Special Request Start</h2>
		<br />
		<h3>Request Number:</h3>
		<input type="text" name="requestNo" id="requestNo" /> <br />
		<h3>Request Type:</h3>
		<input type="text" name="reqType" id="reqType" /> <br />
		<h2>Special Request End</h2>
		<br />
		<h2>Comments:</h2>
		<input type="text" name="comments" id="comments" /> <br />
		<h2>Combination String:</h2>
		<input type="text" name="combinationString" id="combinationString" />
		<br />
		<h2>Reservation Number:</h2>
		<input type="text" name="reservationNo" id="reservationNo" /> <br />
		<input type="submit" value="send request">
	</form>
</body>

</html>