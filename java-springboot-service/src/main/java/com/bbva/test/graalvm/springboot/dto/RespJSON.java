package com.bbva.test.graalvm.springboot.dto;

public class RespJSON<T> {
	private String message;
	private T result;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
}
