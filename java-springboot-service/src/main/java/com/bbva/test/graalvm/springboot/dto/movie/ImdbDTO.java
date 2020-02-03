package com.bbva.test.graalvm.springboot.dto.movie;

public class ImdbDTO {
	private Double rating;
	private Integer votes;
	private Integer id;


	public ImdbDTO() {
	}

	public ImdbDTO(Double rating, Integer votes, Integer id) {
		this.rating = rating;
		this.votes = votes;
		this.id = id;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "ImdbDTO{" +
				"rating=" + rating +
				", votes=" + votes +
				", id=" + id +
				'}';
	}
}
