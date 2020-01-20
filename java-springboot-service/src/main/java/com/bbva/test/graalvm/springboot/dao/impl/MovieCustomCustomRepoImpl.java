package com.bbva.test.graalvm.springboot.dao.impl;

import com.bbva.test.graalvm.springboot.dao.MovieCustomRepo;
import com.bbva.test.graalvm.springboot.dto.MovieDTO;
import com.bbva.test.graalvm.springboot.dto.movie.ImdbDTO;
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
	public void updateImdb(String movieId, ImdbDTO imbDto) {
		Query select = Query.query(Criteria.where("_id").is(movieId));
		Update update = new Update();
		update.set("imdb.rating.numberDouble", imbDto.getRating().getNumberDouble());
		update.set("imdb.votes.numberInt", imbDto.getVotes().getNumberInt());
		update.set("imdb.id.numberInt", imbDto.getId().getNumberInt());
		mongoOperations.findAndModify(select, update, MovieDTO.class);
		/**
		 if you want to get the new object modify
		 MovieDTO updateObject = mongoOperations.findAndModify(select, update, MovieDTO.class);
		 */
	}

}
