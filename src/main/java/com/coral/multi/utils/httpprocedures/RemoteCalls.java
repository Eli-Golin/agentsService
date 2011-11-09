package com.coral.multi.utils.httpprocedures;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import com.coral.multi.general.MethodType;

public class RemoteCalls {
	public static void sendHttpRequest(HttpURLConnection connection, String body){
		OutputStream outStream;
		try {
			outStream = connection.getOutputStream();
			outStream.write(body.getBytes()); //send request!
		} catch (IOException e) {
			System.out.println("Could not fetch OutputStream in sendHttpRequest: "+e);
		}
	}
	
	//retrieves HttpURLConnection object which is open
	/***
	 * @param methodType : could be GET or POST
	 * @param destination : the url destination string
	 * @return if succeeded returns HttpURLConnection object , otherwise returns null
	 */
	public HttpURLConnection getUrlConncetion(MethodType methodType, String destination){
		try {
			URL url = new URL(destination);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			if(methodType == MethodType.GET){
				connection.setRequestMethod("GET");
			}
			else
				connection.setRequestMethod("POST");
			URLEncoder.encode(destination, "UTF-8");
			return connection;
		} catch (MalformedURLException e) {
			System.out.println("Malformed URL :"+destination+" "+e);
		} catch (IOException e) {
			String logMessage = "Could not open connection: "+e;
			System.out.println(logMessage);
		}
		return null;
	}
}
