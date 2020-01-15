package com.bbva.test.graalvm.springboot.dao.impl;

import com.bbva.test.graalvm.springboot.dao.MovieCustomRepo;
import com.bbva.test.graalvm.springboot.dto.movie.ImdbDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Lazy
@Repository
public class MovieCustomCustomRepoImpl implements MovieCustomRepo {
	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public void updateImdb(String movieId, ImdbDTO imbDto) {
		throw new UnsupportedOperationException("Not implement yet 2");
	}

}
