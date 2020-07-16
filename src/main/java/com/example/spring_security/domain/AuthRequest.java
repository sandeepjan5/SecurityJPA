package com.example.spring_security.domain;

public class AuthRequest 
{
	public AuthRequest()
	{
		
	}
	
	private String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public AuthRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String password;
	

}
