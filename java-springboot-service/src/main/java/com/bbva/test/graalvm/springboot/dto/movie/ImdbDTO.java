package com.bbva.test.graalvm.springboot.dto.movie;

public class ImdbDTO {
	private RatingDTO rating;
	private VotesDTO votes;
	private IdDTO id;

	public ImdbDTO(RatingDTO rating, VotesDTO votes, IdDTO id) {
		this.rating = rating;
		this.votes = votes;
		this.id = id;
	}

	public RatingDTO getRating() {
		return rating;
	}

	public void setRating(RatingDTO rating) {
		this.rating = rating;
	}

	public VotesDTO getVotes() {
		return votes;
	}

	public void setVotes(VotesDTO votes) {
		this.votes = votes;
	}

	public IdDTO getId() {
		return id;
	}

	public void setId(IdDTO id) {
		this.id = id;
	}
}
