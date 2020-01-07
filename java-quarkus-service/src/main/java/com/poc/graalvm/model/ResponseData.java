package com.poc.graalvm.model;

public class ResponseData {
	private String message;
	private String result;
	
	
	
	public ResponseData() {
		super();
	}
	public ResponseData(String message, String result) {
		super();
		this.message = message;
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}
