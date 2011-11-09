package com.coral.multi.general;

/**
 * 
 * @author eli_n
 *
 */
public class AppConstants {
	public static final String NOA_TOURSE_IP = "62.219.84.222";
	public static final String DATE_FORMAT = "dd/MM/YYYY";
	public static final String SENT_DATA_PARAMETER = "sentData";
	public static final String REQUEST_TYPE_PARAMETER = "requestType";
	public static String sentDataParameterValue = "";
	public static String requestTypeParameterValue = "";
	public static final String IS_USA = "isUSA";
	public static final String IS_AVAILABILITY_REQUEST = "isAvailabilityRequest"; //attribute name
	public static final String IS_RESERVATION_REQUEST =  "isReservationRequest"; // attribute name
	public static final String IS_CANCELLATION_REQUEST = "isCancelationRequest"; // attribute name
	public static final String CONVERTED_OBJECT = "convertedObject"; // attribute names
	public static final String DESTINATION = "http://localhost:8080/danHotels/RemoteAccessServlet";
	public static final String REQUEST_DENIED = "DENIED";
	public static final String UTF_8_ENCODING = "UTF-8";
	public static final double P_NLS_LANG_CD_ISR = 1.072;
	public static final double P_NLS_LANG_CD_FOREIGN = 2.072;
	
	//---------------error messages --------------------------//
	public static final String AUTHENTICATION_FAILED = "Authentication failed. Either User Id or Password do not match, or Your IP  is not Registered In The System";
	public static final String PRICE_VALIDATION_FAILED = "Price Validation Failed. One of your specified room prices is not valid";
	public static final String RESERVATIONS_AMOUNT_VIOLATION = "Number of Requests exceeds the Maximum Allowed";
	public static final String NO_SATURDAY_LUNCHES_AVAILABLE = "No Saturday Lunches Available During Specified Dates";
	public static final String NO_FRIDAY_DINNERS_AVAILABLE = "No Friday Dinners Available During Specified Dates";
	public static final String NO_HOLIDAY_LUNCHES_AVAILABLE = "No Holiday Lunches Available During Specified Dates";
	public static final String NO_FREE_ROOMS_AVAILABLE = "The Amount of Rooms Specified is Not Available";
	public static final String INTERNAL_ERROR = "The Service Has Encountered an Internal Problem, Please Contact Dan's Technical Support";
	public static final String CANCELATION_REQUEST_FAILED = "Reservation Cancellation Failed Due to Unknown Reasons. You're Strongly Advised to Varify your Last Cancelations manually\nYour reservation number is: ";
	public static final String RESERVATION_AND_ARRIVAL_DATES_ARE_THE_SAME = "Making reservations for the current date is impossible";
	public static final String RESERVATION_ON_FRIDAY_ARRIVAL_ON_NEXT_SUNDAY = "Making reservations on fridays while arrival date is the nearest Sunday, is not allowed";
	public static final String CAPACITY_VIOLATION = "Reservations for children without parents accompaniment are not allowed";
	public static final String DAYS_INTERVAL_VIOLATION  = "Maximum reservation period must be not longer than 14 days";
	public static final String DATES_OVERLAPPING_VIOLATION = "The same guest can not stay in different hotels at the same period.";
	public static final String GUETS_NAMES_VALIDATION_FAILED = "Either one of the guest's first name or last name is missing.";
	public static final String ROOM_RESERVATION_FAILED = "Room reservation has failed.";
	
	public static final int INTERNAL_ERROR_NUM = 0;
	public static final int AUTHENTICATION_FAILED_NUM = 100;
	public static final int PRICE_VALIDATION_FAILED_NUM = 200;
	public static final int NO_FREE_ROOMS_NUM = 300;
	public static final int NO_SATURDAY_LUNCHES_AVAILABLE_NUM = 400;
	public static final int NO_FRIDAY_DINNERS_AVAILABLE_NUM = 500;
	public static final int NO_HOLIDAY_LUNCHES_AVAILABLE_NUM = 600;
	public static final int CANCELATION_REQUEST_FAILED_NUM = 700;
	public static final int RESERVATIONS_AMOUNT_VIOLATION_NUM = 800;
	public static final int ARRIVAL_DATE_EQUALS_RESERVATION_DATE = 900;
	public static final int RESERVATION_ON_FRIDAY_ARRIVAL_ON_SATURDAY = 1000;
	public static final int CAPACITY_VIOLATION_NUM = 1100;
	public static final int DAYS_INTERVAL_VIOLATION_NUM = 1200;
	public static final int DATES_OVERLAPPING_VIOLATION_NUM = 1300;
	public static final int GUETS_NAMES_VALIDATION_FAILED_NUM = 1400;
	public static final int ROOM_RESERVATION_FAILED_NUM = 1500;
	
	//------------------------availability response column names----------------------------------//
	public static final String HOTEL_ID = "HOTELID";
	public static final String HOTEL_ID_DESCRIPTION = "HOTELIDDESCRIPTION";
	public static final String HOTEL_DESCRIPTION = "HOTELDESCRIPTION";
	public static final String HOTEL_PICTURE_TYPE = "HOTELPICTURETYPE";
	public static final String HOTEL_PICTURE_URL = "HOTELPICTUREURL";
	public static final String ROOM_PICTURE_TYPE = "ROOMPICTURETYPE";
	public static final String ROOM_PICTURE_URL = "ROOMPICTUREURL";
	public static final String ROOM_TYPE = "ROOMTYPE";
	public static final String ROOM_TYPE_DESCRIPTION = "ROOMTYPEDESC";
	public static final String DEAL = "DEAL";
	public static final String DEAL_COMMENTS = "DEALDESCRIPTION";
	public static final String AVAILABILITY = "AVAILABILTY";
	public static final String PRICE = "PRICE";
	public static final String CURRENCY = "CURRENCY";
	public static final String ROOM_DESCRIPTION = "ROOMDESCRIPTION";
	public static final String NUM_OF_ADULTS = "ADULTS";
	public static final String NUM_OF_CHILDREN = "CHILDREN";
	public static final String BABIES = "BABIES";
	public static final String COMBINATION_STRING = "COMBINATION_STRING";
}
