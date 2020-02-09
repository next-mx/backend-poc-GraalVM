package com.bbva.test.graalvm.springboot.model;

public class ResponseDTO<T> {
	private String message;
	private T result;

	public ResponseDTO(String message, T result){
		this.message = message;
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public T getResult() {
		return result;
	}

	@Override
	public String toString() {
		return "Response{" +
				"message='" + message + '\'' +
				", result=" + result +
				'}';
	}
}
