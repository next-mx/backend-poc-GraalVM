package com.bbva.test.graalvm.springboot.dto.movie;

public class ImdbDTO {
	private NumberDoubleDTO rating;
	private NumberIntDTO votes;
	private NumberIntDTO id;

	public ImdbDTO(NumberDoubleDTO rating, NumberIntDTO votes, NumberIntDTO id) {
		this.rating = rating;
		this.votes = votes;
		this.id = id;
	}

	public NumberDoubleDTO getRating() {
		return rating;
	}

	public void setRating(NumberDoubleDTO rating) {
		this.rating = rating;
	}

	public NumberIntDTO getVotes() {
		return votes;
	}

	public void setVotes(NumberIntDTO votes) {
		this.votes = votes;
	}

	public NumberIntDTO getId() {
		return id;
	}

	public void setId(NumberIntDTO id) {
		this.id = id;
	}
}
