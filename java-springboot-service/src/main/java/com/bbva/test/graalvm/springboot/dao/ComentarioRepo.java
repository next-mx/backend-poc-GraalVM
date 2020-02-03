package com.bbva.test.graalvm.springboot.dao;

import com.bbva.test.graalvm.springboot.dto.ComentarioDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepo extends MongoRepository<ComentarioDTO, String> {
    List<ComentarioDTO> findByMovieId(String movieId);
}
