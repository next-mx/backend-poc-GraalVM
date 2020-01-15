package com.bbva.test.graalvm.springboot.dao;

import com.bbva.test.graalvm.springboot.dto.ComentarioDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepo extends MongoRepository<ComentarioDTO, String> {
}
