package com.poc.graalvm.repository;

import com.poc.graalvm.model.Comment;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.runtime.MongoOperations;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CommentRepository implements PanacheMongoRepository<Comment> {

    public void saveComment(Comment comment){
        persist(comment);
    }

    public List<Comment> getAllComments(){
        return findAll().list();
    }

    public List<Comment> findByIdMovie(String id){
        Document document = new Document();
        document.append("movie_id",id);
        return (List<Comment>) MongoOperations.find(Comment.class,document).list();
    }

    public void deleteComment(Comment comment){
        delete(comment);
    }

    public void updateComment(Comment comment){
        update(comment);
    }

}