package com.bbva.test.graalvm.springboot.dto;

import com.bbva.test.graalvm.springboot.dto.movie.LastUpdateDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "comments")
public class ComentarioDTO {
	@Id
	private String _id;
	private String name;
	private String email;
	@Field(name = "movie_id")
	private String movieId;
	private String text;
	private Long date;

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

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	@Override
	public String toString() {
		return "ComentarioDTO{" +
				"_id='" + _id + '\'' +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", movie_id='" + movieId + '\'' +
				", text='" + text + '\'' +
				", date=" + date +
				'}';
	}
}
