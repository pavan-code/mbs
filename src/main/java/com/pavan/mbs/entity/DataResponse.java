package com.pavan.mbs.entity;

import java.util.List;

public class DataResponse<T> {
	
	private Boolean status;
	private int statusCode;
	private String message;
	
	private List<T> datae;
	private T data;
	public DataResponse(Boolean status, int statusCode, String message, List<T> datae, T data) {
		super();
		this.status = status;
		this.statusCode = statusCode;
		this.message = message;
		this.datae = datae;
		this.data = data;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<T> getDatae() {
		return datae;
	}
	public void setDatae(List<T> datae) {
		this.datae = datae;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "DataResponse [status=" + status + ", statusCode=" + statusCode + ", message=" + message + ", datae="
				+ datae + ", data=" + data + "]";
	}
	
	
	
	
}
