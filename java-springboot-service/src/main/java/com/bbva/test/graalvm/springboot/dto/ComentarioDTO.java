package com.bbva.test.graalvm.springboot.dto;

import com.bbva.test.graalvm.springboot.dto.movie.LastUpdateDTO;

public class ComentarioDTO {
	private String name;
	private String email;
	private String text;
	private LastUpdateDTO date;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LastUpdateDTO getDate() {
		return date;
	}

	public void setDate(LastUpdateDTO date) {
		this.date = date;
	}
}
