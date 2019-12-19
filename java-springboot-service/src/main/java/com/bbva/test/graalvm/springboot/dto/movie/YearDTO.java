package com.bbva.test.graalvm.springboot.dto.movie;

public class YearDTO {
	private String $numberInt;

	public YearDTO(String number) {
		this.$numberInt = number;
	}
	public String get$numberInt() {
		return $numberInt;
	}
	public void set$numberInt(String $numberInt) {
		this.$numberInt = $numberInt;
	}
}
