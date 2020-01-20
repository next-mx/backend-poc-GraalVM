package com.bbva.test.graalvm.springboot.dto.movie;

public class LastUpdateDTO {
	private NumberLongDTO date;

	public LastUpdateDTO() {
	}

	public LastUpdateDTO(NumberLongDTO date) {
		this.date = date;
	}

	public NumberLongDTO getDate() {
		return date;
	}

	public void setDate(NumberLongDTO date) {
		this.date = date;
	}
}
