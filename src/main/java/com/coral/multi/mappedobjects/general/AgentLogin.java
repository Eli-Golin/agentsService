package com.coral.multi.mappedobjects.general;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("login")
public class AgentLogin {
	
	@XStreamAlias("agentId")
	private long id;
	
	private String password;
	
	public AgentLogin(){}
	public AgentLogin(long id, String password){
		this.id =id;
		this.password = password;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
