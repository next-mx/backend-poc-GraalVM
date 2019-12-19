package com.bbva.test.graalvm.springboot.dto.movie;

public class RuntimeDTO {
	private String numberString;

	public RuntimeDTO(String numberString) {
		this.numberString = numberString;
	}

	public String getNumberString() {
		return numberString;
	}

	public void setNumberString(String numberString) {
		this.numberString = numberString;
	}
}
