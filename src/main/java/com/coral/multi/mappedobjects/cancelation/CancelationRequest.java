package com.coral.multi.mappedobjects.cancelation;

import com.coral.multi.mappedobjects.general.AgentLogin;
import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("cancelReservationRequest")
public class CancelationRequest {
	@XStreamAlias("login")
	private AgentLogin agentLogin;
	@XStreamAlias("reservationNo")
	private long reservNum;
	
	public AgentLogin getAgentLogin() {
		return agentLogin;
	}

	public void setAgentLogin(AgentLogin agentLogin) {
		this.agentLogin = agentLogin;
	}

	
	public CancelationRequest(){
		agentLogin = new AgentLogin();
	}
	
	public Long getReservNum() {
		return reservNum;
	}
	
	public void setReservNum(Long reservNum) {
		this.reservNum = reservNum;
	}
}
