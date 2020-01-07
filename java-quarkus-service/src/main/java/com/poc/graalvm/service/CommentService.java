package com.poc.graalvm.service;

import com.poc.graalvm.config.Constants;
import com.poc.graalvm.error.InvalidMongoIdException;
import com.poc.graalvm.model.Comment;
import com.poc.graalvm.model.Movie;
import com.poc.graalvm.model.ResponseData;
import com.poc.graalvm.repository.CommentRepository;
import com.poc.graalvm.repository.MovieRepository;
import org.bson.types.ObjectId;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@ApplicationScoped
public class CommentService {

    @Inject
    MovieRepository movieRepository;

    @Inject
    CommentRepository commentRepository;

    public Response add(Comment comment){
        if(!ObjectId.isValid(comment.getMovie_id()))
            throw new InvalidMongoIdException();
        ObjectId idDB = new ObjectId(comment.getMovie_id());
        Movie movie = movieRepository.findById(idDB);
        if(movie == null)
            return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseData("Mensaje API","No se encontro la pelicula para agregar un comentario")).build();
        commentRepository.saveComment(comment);
        if(comment.getId() != null)
            return Response.created(URI.create(Constants.MOVIES_PATH + "/"  + comment.getId().toString())).build();
        return Response.serverError().build();
    }

    public List<Comment> findAll(){
        return commentRepository.getAllComments();
    }

    public Response findByIdMovie(String id){
        if(!ObjectId.isValid(id))
            throw new InvalidMongoIdException();
        return Response.ok(commentRepository.findByIdMovie(id)).build();
    }

    public Response deleteById(String id){
        if(!ObjectId.isValid(id))
            throw new InvalidMongoIdException();
        Comment comment = new Comment();
        comment.setId(new ObjectId(id));
        commentRepository.deleteComment(comment);
        return Response.noContent().build();
    }

    public Response update(Comment comment){
        if(comment.getId() == null)
            throw new InvalidMongoIdException();
        return updateById(comment.getId().toString(), comment);
    }

    public Response updateById(String id,Comment comment){
        if(!ObjectId.isValid(id))
            throw new InvalidMongoIdException();
        ObjectId idDB = new ObjectId(id);
        Comment updateComment = commentRepository.findById(idDB);
        if(updateComment == null)
            return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseData("Mensaje API","No se encontro el comentario a modificar")).build();
        updateComment.setName(comment.getName());
        updateComment.setEmail(comment.getEmail());
        updateComment.setText(comment.getText());
        updateComment.setDate(comment.getDate());
        commentRepository.updateComment(updateComment);
        return Response.noContent().build();
    }

}
