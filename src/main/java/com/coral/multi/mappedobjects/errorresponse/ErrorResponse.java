package com.coral.multi.mappedobjects.errorresponse;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("singleResult")
public class ErrorResponse {
	
	@XStreamAlias("resultNo")
	private int resultNum;
	
	private int resultStatus;
	
	@XStreamAlias("resultMessge")
	private String resultMessage;
	
	private String extraDate;
	
	public int getResultNum() {
		return resultNum;
	}
	public void setResultNum(int resultNum) {
		this.resultNum = resultNum;
	}
	public int getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(int resultStatus) {
		this.resultStatus = resultStatus;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public String getExtraDate() {
		return extraDate;
	}
	public void setExtraDate(String extraDate) {
		this.extraDate = extraDate;
	}
}
