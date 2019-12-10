package com.poc.quarkus.obj;

import java.util.Date;

public class Comment {
	private String id;
	private String name;
	private String email;
	private String movieId;
	private String text;
	private Date date;
	
	
	public Comment() {
		super();
	}
	public Comment(String name, String email, String text) {
		super();
		this.name = name;
		this.email = email;
		this.text = text;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
