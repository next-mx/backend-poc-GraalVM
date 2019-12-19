package com.bbva.test.graalvm.springboot.dto.movie;

public class LastUpdateDTO {
	private DateDTO $date;

	public LastUpdateDTO(DateDTO $date) {
		this.$date = $date;
	}

	public DateDTO get$date() {
		return $date;
	}

	public void set$date(DateDTO $date) {
		this.$date = $date;
	}
}
