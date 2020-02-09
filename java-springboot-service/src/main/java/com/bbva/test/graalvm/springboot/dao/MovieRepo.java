package com.bbva.test.graalvm.springboot.dao;

import com.bbva.test.graalvm.springboot.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends MongoRepository<Movie, String> {
}
