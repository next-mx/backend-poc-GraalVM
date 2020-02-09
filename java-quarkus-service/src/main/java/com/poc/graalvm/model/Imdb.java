package com.poc.graalvm.model;

public class Imdb {
	private double rating;
	private int votes;
	private int id;
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "IMDB{" +
				"rating=" + rating +
				", votes=" + votes +
				", id=" + id +
				'}';
	}
}
