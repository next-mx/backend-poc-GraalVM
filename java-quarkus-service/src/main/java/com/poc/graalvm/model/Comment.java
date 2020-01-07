package com.poc.graalvm.model;

import io.quarkus.mongodb.panache.MongoEntity;
import org.bson.types.ObjectId;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@MongoEntity(collection="comments")
public class Comment {
	private ObjectId id;

	@NotNull
	private String name;
	@NotNull
	private String email;
	private String movie_id;
	@NotNull
	private String text;
	@NotNull
	private long date;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
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

	public String getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
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
}
