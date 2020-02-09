package com.bbva.test.graalvm.springboot.dao;

import com.bbva.test.graalvm.springboot.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends MongoRepository<Comment, String> {
    List<Comment> findByMovieId(String movieId);
}
