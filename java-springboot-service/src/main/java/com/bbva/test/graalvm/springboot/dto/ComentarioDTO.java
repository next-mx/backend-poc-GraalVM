package com.bbva.test.graalvm.springboot.dto;

import com.bbva.test.graalvm.springboot.dto.movie.LastUpdateDTO;
import org.springframework.data.annotation.Id;

public class ComentarioDTO {
	@Id
	private String _id;
	private String name;
	private String email;
	private String movie_id;
	private String text;
	private LastUpdateDTO date;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

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

	public String getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}

	@Override
	public String toString() {
		return "ComentarioDTO{" +
				"_id='" + _id + '\'' +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", movie_id='" + movie_id + '\'' +
				", text='" + text + '\'' +
				", date=" + date +
				'}';
	}
}
