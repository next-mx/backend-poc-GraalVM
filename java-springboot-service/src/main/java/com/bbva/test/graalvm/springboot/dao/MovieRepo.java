package com.bbva.test.graalvm.springboot.dao;

import com.bbva.test.graalvm.springboot.dto.MovieDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends MongoRepository<MovieDTO, String> {
}
