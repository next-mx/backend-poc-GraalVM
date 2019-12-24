package com.bbva.test.graalvm.springboot.dto.movie;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Objects;

public class NumberLongDTO {
	private String numberLong;

	public NumberLongDTO(String numberLong) {
		this.numberLong = numberLong;
	}

	public String getNumberLong() {
		return numberLong;
	}

	public void setNumberLong(String numberLong) {
		this.numberLong = numberLong;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof NumberLongDTO)) return false;
		NumberLongDTO that = (NumberLongDTO) o;
		return Objects.equals(numberLong, that.numberLong);
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hascode = new HashCodeBuilder();
		hascode.append(this.numberLong);
		return hascode.hashCode();
	}

	@Override
	public String toString() {
		return "NumberLongDTO{" +
				"numberLong='" + numberLong + '\'' +
				'}';
	}
}
