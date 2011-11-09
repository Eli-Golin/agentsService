package com.coral.multi.utils;

import static com.coral.multi.general.AppConstants.AVAILABILITY;
import static com.coral.multi.general.AppConstants.BABIES;
import static com.coral.multi.general.AppConstants.CURRENCY;
import static com.coral.multi.general.AppConstants.DEAL;
import static com.coral.multi.general.AppConstants.DEAL_COMMENTS;
import static com.coral.multi.general.AppConstants.HOTEL_DESCRIPTION;
import static com.coral.multi.general.AppConstants.HOTEL_ID_DESCRIPTION;
import static com.coral.multi.general.AppConstants.HOTEL_PICTURE_TYPE;
import static com.coral.multi.general.AppConstants.HOTEL_PICTURE_URL;
import static com.coral.multi.general.AppConstants.NUM_OF_ADULTS;
import static com.coral.multi.general.AppConstants.NUM_OF_CHILDREN;
import static com.coral.multi.general.AppConstants.PRICE;
import static com.coral.multi.general.AppConstants.ROOM_DESCRIPTION;
import static com.coral.multi.general.AppConstants.ROOM_PICTURE_TYPE;
import static com.coral.multi.general.AppConstants.ROOM_PICTURE_URL;
import static com.coral.multi.general.AppConstants.ROOM_TYPE;
import static com.coral.multi.general.AppConstants.ROOM_TYPE_DESCRIPTION;
import static com.coral.multi.general.AppConstants.COMBINATION_STRING;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.joda.time.LocalDate;
import org.joda.time.Period;

import oracle.jdbc.OracleTypes;

import com.coral.multi.general.GeneralUsage;
import com.coral.multi.mappedobjects.availability.responses.multi.Picture;

public class DbDaoUtils {
	private static Connection connection;
	private static DataSource dataSource;
	private static InitialContext in;
	
	static {
		try {
			in = new InitialContext();
			dataSource = (DataSource) in.lookup("java:/jdbc/DanHotelsPoolDS");
			connection = dataSource.getConnection();
			connection.close();
		}catch (SQLException e) {
			System.out.println("Could not establish a connection");
			e.printStackTrace();
		} catch (NamingException e) {
			System.out.println("Could not establish a connection");
			e.printStackTrace();
		}
	}
		
	public static synchronized String updatePnr(long agentNum, String agentRef, long reservNum) {
		System.out.println("Inside DbDaoUtils.updatePnr\n");
		String procedure = "{? = call AGENT_RESERVATION_U_yaya.UPDATE_TRAVEL_AGENT_REFERENCE(?, ?, ?)}";
		try {
			connection = dataSource.getConnection();
			CallableStatement callStatement = connection.prepareCall(procedure);
			callStatement.registerOutParameter(1, Types.BIGINT);
			callStatement.setLong(2, agentNum);
			callStatement.setString(3, agentRef);
			callStatement.setLong(4, reservNum);
			long nanoSecondsStart = System.nanoTime();
			callStatement.execute();
			long nanoSecondsEnd = System.nanoTime();
			System.out.println("updatePnr database update takes: "+(nanoSecondsEnd - nanoSecondsStart)/1000000000+" seconds\n");
			long result = callStatement.getLong(1);
			String ans = Long.toString(result);
			System.out.println("Outside DbDaoUtils.updatePnr\n");
			return ans;
		} catch (SQLException e) {
			System.out.println("Not a valid call statement! " + e);
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Could not close the db connection: " + e);
			}
		}
		System.out.println("Outside DbDaoUtils.updatePnr\n");
		return null;
	}
	
	
	public static synchronized boolean authorizationSuccess(long agentNum, String password,String ip) {
		System.out.println("Inside DbDaoUtils.authorizationSuccess\n");
		String procedure = "{? = call Ta_Login_Q.check_if_exists(? , ? ,?)}";
		try {
			connection = dataSource.getConnection();
			CallableStatement callStatement = connection.prepareCall(procedure);
			callStatement.registerOutParameter(1, Types.INTEGER);
			callStatement.setLong(2, agentNum);
			callStatement.setString(3, password);
			callStatement.setString(4, ip);
			long nanoSecondsStart = System.nanoTime();
			callStatement.execute();
			long nanoSecondsEnd = System.nanoTime();
			System.out.println("authorization database search takes: "+(nanoSecondsEnd - nanoSecondsStart)/1000000000+" seconds\n");
			int result = callStatement.getInt(1);
			System.out.println("Outside DbDaoUtils.authorizationSuccess\n");
			return 1 == result ? true : false;
		} catch (SQLException e) {
			System.out.println("Not a valid call statement! " + e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Could not close the db connection: " + e);
			}
		}
		System.out.println("Outside DbDaoUtils.authorizationSuccess\n");
		return false;
	}

	
	public static synchronized long insertReservComments(long agentId, String comments, long reservNum) {
		System.out.println("Inside DbDaoUtils.insertReservComments\n");
		String procedure = "{? = call Agent_Reservation_U_YAYA.update_comments(? , ? , ?)}";
		long ans = 0;
		try {
			connection = dataSource.getConnection();
			CallableStatement callStatement = connection.prepareCall(procedure);
			callStatement.registerOutParameter(1, Types.BIGINT);
			callStatement.setLong(2, agentId);
			callStatement.setString(3, comments);
			callStatement.setLong(4, reservNum);
			long nanoSecondsStart = System.nanoTime();
			callStatement.execute();
			long nanoSecondsEnd = System.nanoTime();
			System.out.println("insert reservation comments into database takes: "+(nanoSecondsEnd - nanoSecondsStart)/1000000000+" seconds\n");
			ans = callStatement.getLong(1);
		} catch (SQLException e) {
			System.out.println("Not a valid call statement! " + e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Could not close the db connection: " + e);
			}
		}
		System.out.println("Outside DbDaoUtils.insertReservComments\n");
		return ans;
	}
	
	public static synchronized long getMultiReservNum(String reservNums) {
		System.out.println("Inside DbDaoUtils.getMultiReservNum\n");
		String procedure = "{? =  call AGENT_RESERVATION_U_YAYA.GET_MULTI_RESERVATION_NO(?)}";
		long result = 0;
		try {
			connection = dataSource.getConnection();
			CallableStatement callStatement = connection.prepareCall(procedure);
			callStatement.registerOutParameter(1, Types.BIGINT);
			callStatement.setString(2, reservNums);
			long nanoSecondsStart = System.nanoTime();
			callStatement.execute();
			long nanoSecondsEnd = System.nanoTime();
			System.out.println("calculate multi reservation number in database takes: "+(nanoSecondsEnd - nanoSecondsStart)/1000000000+" seconds\n");
			result = callStatement.getLong(1);
		} catch (SQLException e) {
			System.out.println("Not a valid call statement! " + e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Could not close the db connection: " + e);
			}
		}
		System.out.println("Outside DbDaoUtils.getMultiReservNum\n");
		return result;
	}

	public static synchronized boolean validatePrice(int hotelNum, String roomType,String arrivalDate, String departureDate, int numOfAdults,
			int numOfChildren, int numOfRooms, String guestNationality,
			String boardArrangement, long agentId, Double price) {
		System.out.println("Inside DbDaoUtils.validatePrice\n");
		String procedure = "{? = call BEST_RATE_COMBINATION_AGENT.CHECK_RESERVATION_PRICE(?,?,?,?,?,?,?,?,?,?,?)}";
		boolean ans = false;
		try {
			connection = dataSource.getConnection();
			CallableStatement callableStatement = connection.prepareCall(procedure);
			callableStatement.registerOutParameter(1, Types.INTEGER);
			callableStatement.setInt(2, hotelNum);
			callableStatement.setString(3, roomType);
			callableStatement.setDate(4, Date.valueOf(GeneralUsage.convertJodaDateStringToSqlDateString(arrivalDate)));
			callableStatement.setDate(5, Date.valueOf(GeneralUsage.convertJodaDateStringToSqlDateString(departureDate)));
			callableStatement.setInt(6, numOfAdults);
			callableStatement.setInt(7, numOfChildren);
			callableStatement.setInt(8, numOfRooms);
			callableStatement.setString(9, guestNationality);
			callableStatement.setString(10, boardArrangement);
			callableStatement.setLong(11, agentId);
			callableStatement.setDouble(12, price);
			long nanoSecondsStart = System.nanoTime();
			callableStatement.execute();
			long nanoSecondsEnd = System.nanoTime();
			System.out.println("price validation in database takes: "+(nanoSecondsEnd - nanoSecondsStart)/1000000000+" seconds\n");
			int result = callableStatement.getInt(1);
			ans = result == 1 ? true : false;
		} catch (SQLException e) {
			System.out.println("Not a valid call statement! " + e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Could not close the db connection: " + e);
			}
		}
		System.out.println("Outside DbDaoUtils.validatePrice\n");
		return ans;
	}
	
	
	public static synchronized int maximumReservationsAllowed() {
		System.out.println("Inside DbDaoUtil.maximiuReservationsAllowed\n");
		int errorIndicator = -1;
		int ans = errorIndicator;
		String procedure = "{? = call APP_SYS_PARAM_Q.GET_PARAM_VALUE(24)}";
		try {
			connection = dataSource.getConnection();
			CallableStatement callableStatement = connection
					.prepareCall(procedure);
			callableStatement.registerOutParameter(1, Types.INTEGER);
			long nanoSecondsStart = System.nanoTime();
			callableStatement.execute();
			long nanoSecondsEnd = System.nanoTime();
			System.out.println("maximum reservations allowed check in database takes: "+(nanoSecondsEnd - nanoSecondsStart)/1000000000+" seconds\n");
			ans = callableStatement.getInt(1);
		} catch (SQLException e) {
			System.out.println("Not a valid call statement! " + e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Could not close the db connection: " + e);
			}
		}
		System.out.println("Outside DbDaoUtil.maximiuReservationsAllowed\n");
		return ans;
	}
	
	
	public static synchronized String cancelReservation(long agentId, long reservationNum) {
		System.out.println("Inside DbDaoUtil.cancelReservation\n");
		String procedure = "{? = call Agent_Reservation_U.cancel_reservation_xml(?,?)}";
		String ans = null;
		try {
			connection = dataSource.getConnection();
			CallableStatement callableStatement = connection.prepareCall(procedure);
			callableStatement.registerOutParameter(1, Types.VARCHAR);
			callableStatement.setLong(2, agentId);
			callableStatement.setLong(3, reservationNum);
			long nanoSecondsStart = System.nanoTime();
			callableStatement.execute();
			long nanoSecondsEnd = System.nanoTime();
			System.out.println("reservation cancellation in database takes: "+(nanoSecondsEnd - nanoSecondsStart)/1000000000+" seconds\n");
			ans = callableStatement.getString(1);
		} catch (SQLException e) {
			System.out.println("Not a valid call statement! " + e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Could not close the db connection: " + e);
			}
		}
		System.out.println("Outside DbDaoUtil.cancelReservation\n");
		return ans;
	}
	
	
	public static synchronized List<Object[]> getBestRateCombination(Double lang_cd, int hotelNum,
			int hotelAreaCd, String roomType, String arrivalDate,
			String departureDate, int numOfAdults, int numOfChildren,
			int numOfRooms, long agentNum, String boardArrangement, int numOfBabies) {
		System.out.println("Inside DbDaoUtil.getBestRateCombination\n");
		String procedure = "{? = call BEST_RATE_COMBINATION_AGENT.GET_BEST_RATE_COLLECTION(?,?,?,?,?,?,?,?,?,?,?,?)}";
		ResultSet resultSet = null;
		List<Object[]> resultTable = null;
		try {
			connection = dataSource.getConnection();
			CallableStatement callableStatement = connection.prepareCall(procedure);  
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			callableStatement.setDouble(2, lang_cd);
			callableStatement.setInt(3, hotelNum);
			callableStatement.setInt(4, hotelAreaCd);
			callableStatement.setString(5, roomType);
			callableStatement.setDate(6, Date.valueOf(GeneralUsage.convertJodaDateStringToSqlDateString(arrivalDate)));
			callableStatement.setDate(7, Date.valueOf(GeneralUsage.convertJodaDateStringToSqlDateString(departureDate)));
			callableStatement.setInt(8, numOfAdults);
			callableStatement.setInt(9, numOfChildren);
			callableStatement.setInt(10, numOfRooms);
			callableStatement.setLong(11, agentNum);
			callableStatement.setString(12, boardArrangement);
			callableStatement.setInt(13, numOfBabies);
			long nanoSecondsStart = System.nanoTime();
			callableStatement.execute();
			long nanoSecondsEnd = System.nanoTime();
			System.out.println("\n-----The input parameters to bestRateCombination are:");
			System.out.println("lang_cd : "+lang_cd);
			System.out.println("hotelNum: "+hotelNum);
			System.out.println("hotelAreadCd: "+hotelAreaCd);
			System.out.println("roomType: "+roomType);
			System.out.println("arrivalDate: "+arrivalDate);
			System.out.println("departureDate: "+departureDate);
			System.out.println("numOfAdults: "+numOfAdults);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
			System.out.println("numOfChildren: "+numOfChildren);
			System.out.println("numOfRooms: "+numOfRooms);
			System.out.println("agentNum: "+agentNum);
			System.out.println("boardArrangement: "+boardArrangement);
			System.out.println("numOfBabies: "+numOfBabies);
			System.out.println("-----End of parameters to bestRateCombination\n");
			System.out.println("best rate calculation in database takes: "+(nanoSecondsEnd - nanoSecondsStart)/1000000000+" seconds\n");
			resultSet = (ResultSet) callableStatement.getObject(1);
			resultTable = oneRoomResults(resultSet);
			resultSet.close();
			callableStatement.close();
		} catch (SQLException e) {
			System.out.println("Not a valid call statement! " + e);
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Could not close the db connection: " + e);
			}
		}
		System.out.println("Outside DbDaoUtil.getBestRateCombination\n");
		return resultTable;
	}


	private static List<Object[]> oneRoomResults(ResultSet resultSet){
		System.out.println("\nInside DbDaoUtil.oneRoomResults");
		long nanoSecondsStart = System.nanoTime();
		List<Object[]> resultTable = new ArrayList<Object[]>();
		try {
		int mb = 1024*1024; 
		Runtime runtime = Runtime.getRuntime(); 
		System.out.println("\n##### Heap utilization statistics [MB] #####"); 
		System.out.println("\nUsed Memory:"+ (runtime.totalMemory() - runtime.freeMemory()) / mb);
        System.out.println("\nFree Memory:"+ runtime.freeMemory() / mb);
        System.out.println("\nTotal Memory:" + runtime.totalMemory() / mb);
        System.out.println("\nMax Memory:" + runtime.maxMemory() / mb);
		while(resultSet.next()){
			Object[]tableEntry = new Object[13];
			tableEntry[0] = resultSet.getString(ROOM_TYPE);
			tableEntry[1] = resultSet.getString(ROOM_TYPE_DESCRIPTION);
			Picture roomPicture = new Picture();
			roomPicture.setPictureType(resultSet.getString(ROOM_PICTURE_TYPE));
			roomPicture.setPictureUrl(resultSet.getString(ROOM_PICTURE_URL));
			tableEntry[2] = roomPicture;
			tableEntry[3] = resultSet.getString(ROOM_DESCRIPTION);
			tableEntry[4] = resultSet.getInt(DEAL); 
			tableEntry[5] = resultSet.getString(DEAL_COMMENTS);
			tableEntry[6] = resultSet.getString(AVAILABILITY);
			tableEntry[7] = resultSet.getDouble(PRICE);
			tableEntry[8] = resultSet.getString(CURRENCY);
			tableEntry[9] = resultSet.getInt(NUM_OF_ADULTS);
			tableEntry[10] = resultSet.getInt(NUM_OF_CHILDREN);
			tableEntry[11] = resultSet.getInt(BABIES);
			tableEntry[12] = resultSet.getString(COMBINATION_STRING);
			resultTable.add(tableEntry);
		}
		} catch (SQLException e) {
			String logMessage = "SQLException was thrown while reading a ResultSet";
			System.out.println(logMessage + ": " + e);
		}
		long nanoSecondsEnd = System.nanoTime();
		System.out.println("DbDaoUtil.oneRoomResults takes: "+(nanoSecondsEnd - nanoSecondsStart)/1000000000+" seconds\n");
		System.out.println("Outside DbDaoUtil.oneRoomResults\n");
		return resultTable;
	}
	
	public static synchronized Object[] getHotelInfo(int hotelId, double lang_cd){
		System.out.println("Inside DbDaoUtil.getHotelInfo\n");
		String procedure = "{? = call hotels_q.get_hotel_descrption(?,?)}";
		ResultSet resultSet = null;
		Object[] resultsTable = null;
		try {
			connection = dataSource.getConnection();
			CallableStatement callableStatement = connection.prepareCall(procedure);
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			callableStatement.setInt(2, hotelId);
			callableStatement.setDouble(3, lang_cd);
			long nanoSecondsStart = System.nanoTime();
			callableStatement.execute();
			long nanoSecondsEnd = System.nanoTime();
			System.out.println("retrieving hotel info from database takes: "+(nanoSecondsEnd - nanoSecondsStart)/1000000000+" seconds\n");
			resultSet = (ResultSet) callableStatement.getObject(1);
			resultsTable = getHotelInfoTable(resultSet);
			resultSet.close();
			callableStatement.close();
		}
		catch (SQLException e) {
			System.out.println("Not a valid call statement! " + e);
		} 
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Could not close the db connection: " + e);
			}
		}
		System.out.println("Outside DbDaoUtil.getHotelInfo\n");
		return resultsTable;
	}
	
	private static Object[] getHotelInfoTable(ResultSet resultSet){
		Object[] resultsTable = new Object[3];
		try {
			while(resultSet.next()){
				resultsTable[0] = resultSet.getString(HOTEL_ID_DESCRIPTION);
				resultsTable[1] = resultSet.getString(HOTEL_DESCRIPTION);
				Picture hotelPicture = new Picture();
				hotelPicture.setPictureType(resultSet.getString(HOTEL_PICTURE_TYPE));
				hotelPicture.setPictureUrl(resultSet.getString(HOTEL_PICTURE_URL));
				resultsTable[2]  = hotelPicture;
			}
		}catch(SQLException e) {
			String logMessage = "SQLException was thrown while reading a ResultSet";
			System.out.println(logMessage + ": " + e);
		}
		return resultsTable;
	}
	
	public static void closeConncetion() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close the db connection: " + e);
		}
	}
	
	public static synchronized String[] getHotelsIdsByArea(int areaCd){
		String[] hotelIds = null;
		String procedure = "{? = call hotels_q.get_hotel_no_by_area(?)}";
		try {
			connection = dataSource.getConnection();
			CallableStatement callableStatement = connection.prepareCall(procedure);
			callableStatement.registerOutParameter(1, Types.CHAR);
			callableStatement.setInt(2, areaCd);
			long nanoSecondsStart = System.nanoTime();
			callableStatement.execute();
			long nanoSecondsEnd = System.nanoTime();
			System.out.println("hotels_q.get_hotel_no_by_area in database takes: "+(nanoSecondsEnd - nanoSecondsStart)/1000000000+" seconds\n");
			String ans = callableStatement.getString(1);
			hotelIds = ans.split(",");
		} catch (SQLException e) {
			System.out.println("Not a valid call statement! " + e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Could not close the db connection: " + e);
			}
		}
		return hotelIds;
	}
	
	public static synchronized int makeReservation(long agentId, String guestNationality, String guestFirstName,
			String guestLastName, int numOfAdults, int numOfChildren,int numOfBabies ,int numOfRooms, String travelAgentReference, int hotelNum,
			String roomType, String checkInDate, String checkOutDate, String boardArrangement, double nlsLangCd,
			String combinationString, double roomPrice, String commnets){
		
		String procedure = "{? = call  agent_reservation_u.new_rec_xml(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int reservationNum = 0;
		try {
			connection = dataSource.getConnection();
			CallableStatement callableStatement = connection.prepareCall(procedure);
			callableStatement.registerOutParameter(1, Types.INTEGER);
			callableStatement.setLong(2, agentId);
			callableStatement.setString(3, guestNationality);
			callableStatement.setString(4, guestFirstName);
			callableStatement.setString(5, guestLastName);
			callableStatement.setInt(6, numOfAdults);
			callableStatement.setInt(7, numOfChildren);
			callableStatement.setInt(8, numOfBabies);
			callableStatement.setInt(9, numOfRooms);
			callableStatement.setString(10, travelAgentReference);
			callableStatement.setInt(11, hotelNum);
			callableStatement.setString(12, roomType);
			callableStatement.setDate(13,Date.valueOf(GeneralUsage.convertJodaDateStringToSqlDateString(checkInDate)));
			callableStatement.setDate(14, Date.valueOf(GeneralUsage.convertJodaDateStringToSqlDateString(checkOutDate)));
			LocalDate arrivalDate =  GeneralUsage.convertStringToDate(checkInDate);
			LocalDate departureDate =  GeneralUsage.convertStringToDate(checkOutDate);
			Period period = new Period(arrivalDate,departureDate);
			int numOfDays = period.getDays();
			int numOfNights = numOfDays - 1;
			callableStatement.setInt(15, numOfNights);
			callableStatement.setString(16, boardArrangement);
			callableStatement.setDouble(17,nlsLangCd);
			callableStatement.setString(18, combinationString);
			callableStatement.setDouble(19, roomPrice);
			callableStatement.setString(20, commnets);
			long nanoSecondsStart = System.nanoTime();
			callableStatement.execute();
			long nanoSecondsEnd = System.nanoTime();
			System.out.println("agent_reservation_u.new_rec_xml in database takes: "+(nanoSecondsEnd - nanoSecondsStart)/1000000000+" seconds\n");
			reservationNum = callableStatement.getInt(1);
		} catch (SQLException e) {
			System.out.println("Not a valid call statement! " + e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Could not close the db connection: " + e);
			}
		}
		return reservationNum;
	}
}
