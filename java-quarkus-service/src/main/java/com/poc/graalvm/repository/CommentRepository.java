package com.poc.graalvm.repository;

import com.poc.graalvm.model.Comment;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.runtime.MongoOperations;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CommentRepository implements PanacheMongoRepository<Comment> {

    public void saveComment(Comment comment){
        persist(comment);
    }

    public List<Comment> getAllComments(String movieId){
        return findAll().stream()
                .filter(comment -> comment.getMovie_id().equals(movieId))
                .collect(Collectors.toList());
    }

    public Comment findByIdMovie(String id){
        return findById(new ObjectId(id));
    }

    public void deleteComment(Comment comment){
        delete(comment);
    }

    public void updateComment(Comment comment){
        update(comment);
    }

}