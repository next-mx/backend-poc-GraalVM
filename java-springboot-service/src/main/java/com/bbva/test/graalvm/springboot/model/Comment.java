package com.bbva.test.graalvm.springboot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Comments")
public class Comment {
	@Id
	private String _id;
	private String name;
	private String email;
	@Field(name = "movie_id")
	private String movieId;
	private String text;
	private long date;


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

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Comment{" +
				", _id='" + _id + '\'' +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", movie_id='" + movieId + '\'' +
				", text='" + text + '\'' +
				", date=" + date +
				'}';
	}
}
