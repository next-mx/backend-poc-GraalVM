package com.poc.graalvm.service;

import com.poc.graalvm.config.Constants;
import com.poc.graalvm.error.InvalidMongoIdException;
import com.poc.graalvm.model.Comment;
import com.poc.graalvm.model.Movie;
import com.poc.graalvm.model.ResponseDTO;
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

    public Response add(String movieId, Comment comment){
        comment.setMovie_id(movieId);
        if(!ObjectId.isValid(comment.getMovie_id()))
            throw new InvalidMongoIdException();
        ObjectId idDB = new ObjectId(comment.getMovie_id());
        Movie movie = movieRepository.findById(idDB);
        if(movie == null)
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ResponseDTO<>("No se encontro la pelicula para agregar un comentario", null))
                    .build();
        commentRepository.saveComment(comment);
        if(comment.getId() != null)
            return Response.created(URI.create(Constants.MOVIES_PATH + "/"  + comment.getId().toString()))
                    .entity(new ResponseDTO<>("Comentario agregado exitosamente", null))
                    .build();
        return Response.serverError()
                .build();
    }

    public Response findAll(String movieId){
        return Response.ok()
                .entity(new ResponseDTO<>("Comentario consultado exitosamente", commentRepository.getAllComments(movieId)))
                .build();
    }

    public Response findById(String id){
        if(!ObjectId.isValid(id))
            throw new InvalidMongoIdException();
        return Response.ok()
                .entity(new ResponseDTO<>("Comentario consultado exitosamente", commentRepository.findByIdMovie(id)))
                .build();
    }


    public Response modifyById(String id, Comment comment){
        if(!ObjectId.isValid(id))
            throw new InvalidMongoIdException();
        ObjectId idDB = new ObjectId(id);
        Comment updateComment = commentRepository.findById(idDB);
        if(updateComment == null)
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ResponseDTO<>("No se encontró el comentario a modificar", null))
                    .build();
        updateComment.setName(comment.getName());
        updateComment.setEmail(comment.getEmail());
        updateComment.setText(comment.getText());
        updateComment.setDate(comment.getDate());
        commentRepository.updateComment(updateComment);
        return Response.ok()
                .entity(new ResponseDTO<>("Comentario modificado exitosamente", null))
                .build();
    }


    public Response updateById(String id,Comment comment){
        if(!ObjectId.isValid(id))
            throw new InvalidMongoIdException();
        ObjectId idDB = new ObjectId(id);
        Comment updateComment = commentRepository.findById(idDB);
        if(updateComment == null)
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ResponseDTO<>("No se encontro el comentario a modificar", null))
                    .build();
        updateComment.setText(comment.getText());
        commentRepository.updateComment(updateComment);
        return Response.ok()
                .entity(new ResponseDTO<>("Comentario editado exitosamente", null))
                .build();
    }


    public Response deleteById(String id){
        if(!ObjectId.isValid(id))
            throw new InvalidMongoIdException();
        Comment comment = new Comment();
        comment.setId(new ObjectId(id));
        commentRepository.deleteComment(comment);
        return Response.ok()
                .entity(new ResponseDTO<>("Comentario eliminado exitosamente", null))
                .build();
    }
}
