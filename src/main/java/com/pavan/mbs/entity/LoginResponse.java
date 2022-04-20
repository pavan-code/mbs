package com.pavan.mbs.entity;

public class LoginResponse {
	
	private String message;
	private Boolean status;
	private int statusCode;
	private UserData user;
	public LoginResponse(String message, Boolean status, int statusCode, UserData user) {
		super();
		this.message = message;
		this.status = status;
		this.statusCode = statusCode;
		this.setUser(user);
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	@Override
	public String toString() {
		return "LoginResponse [message=" + message + ", status=" + status + ", statusCode=" + statusCode+ "]";
	}
	public UserData getUser() {
		return user;
	}
	public void setUser(UserData user) {
		this.user = user;
	}
	
	
}
