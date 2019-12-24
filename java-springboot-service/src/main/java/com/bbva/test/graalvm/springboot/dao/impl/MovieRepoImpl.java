package com.bbva.test.graalvm.springboot.dao.impl;

import com.bbva.test.graalvm.springboot.dao.MovieRepo;
import com.bbva.test.graalvm.springboot.dto.MovieDTO;
import com.bbva.test.graalvm.springboot.dto.movie.ImdbDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepoImpl implements MovieRepo {
	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public MovieDTO findMovieByID(String movieID) {
		Query searh = new Query(Criteria.where("_id").is(movieID));
		return mongoOperations.findOne(searh, MovieDTO.class);
	}

	@Override
	public void newMovie(MovieDTO movie) {
		mongoOperations.save(movie);
	}

	@Override
	public void updateMovie(String movieID, MovieDTO movie) {
		throw new UnsupportedOperationException("Not implement yet 1");
	}

	@Override
	public void updateImdb(String movieId, ImdbDTO imbDto) {
		throw new UnsupportedOperationException("Not implement yet 2");
	}

	@Override
	public void deleteMovie(String movieId) {
		throw new UnsupportedOperationException("Not implement yet 3");
	}
}
