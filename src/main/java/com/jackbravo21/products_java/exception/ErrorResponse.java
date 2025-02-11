package com.jackbravo21.products_java.exception;

import com.jackbravo21.products_java.utils.DateUtil;

public class ErrorResponse {

	private int status;
	private String message;
	private String dateTime;
	
	public ErrorResponse(int status, String message) {
		this.status = status;
		this.message = message;
		this.dateTime = DateUtil.getCurrentDateTime();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}


}
