package com.bbva.test.graalvm.springboot.dto.movie;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class NumberIntDTO {
	private String numberInt;

	public NumberIntDTO(String number) {
		this.numberInt = number;
	}

	public String getNumberInt() {
		return numberInt;
	}

	public void setNumberInt(String numberInt) {
		this.numberInt = numberInt;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof NumberIntDTO)) return false;
		NumberIntDTO numberIntDTO = (NumberIntDTO) o;
		EqualsBuilder equalsBuilder = new EqualsBuilder();
		equalsBuilder.append(this.numberInt, numberIntDTO.numberInt);
		return equalsBuilder.isEquals();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hascode = new HashCodeBuilder();
		hascode.append(numberInt);
		return hascode.hashCode();
	}

	@Override
	public String toString() {
		return "NumberIntDTO{" +
				"numberInt='" + numberInt + '\'' +
				'}';
	}
}
