package com.bbva.test.graalvm.springboot.dao.impl;

import com.bbva.test.graalvm.springboot.dao.MovieCustomRepo;
import com.bbva.test.graalvm.springboot.model.Movie;
import com.bbva.test.graalvm.springboot.model.movie.Imdb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Lazy
@Repository
public class MovieCustomCustomRepoImpl implements MovieCustomRepo {
	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public void updateImdb(String movieId, Imdb imbDto) {
		Query select = Query.query(Criteria.where("_id").is(movieId));
		Update update = new Update();
		update.set("imdb.rating", imbDto.getRating());
		update.set("imdb.votes", imbDto.getVotes());
		update.set("imdb.id", imbDto.getId());
		mongoOperations.findAndModify(select, update, Movie.class);
	}

}
