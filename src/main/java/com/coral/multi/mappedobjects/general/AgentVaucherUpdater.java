package com.coral.multi.mappedobjects.general;


public class AgentVaucherUpdater {
	private AgentLogin loginInfo;
	private String agentRef;
	private Integer reservNum;
	
	public AgentVaucherUpdater(){
		loginInfo = new AgentLogin();
	}
	
	public AgentLogin getLoginInfo() {
		return loginInfo;
	}
	public void setLoginInfo(AgentLogin loginInfo) {
		this.loginInfo = loginInfo;
	}
	public String getAgentRef() {
		return agentRef;
	}
	public void setAgentRef(String agentRef) {
		this.agentRef = agentRef;
	}
	public Integer getReservNum() {
		return reservNum;
	}
	public void setReservNum(Integer reservNum) {
		this.reservNum = reservNum;
	}
}
