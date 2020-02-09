package com.poc.graalvm.rest;


import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.poc.graalvm.config.Constants;
import com.poc.graalvm.model.Comment;
import com.poc.graalvm.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path(Constants.COMMENTS_PATH)
public class QuarkusResourceComment {
    private static final Logger log = LoggerFactory.getLogger(QuarkusResourceComment.class);
    @Inject
    CommentService commentService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response addComment(@PathParam("movieId") String movieId, @Valid Comment comment) {
        log.info("Agregando comentario: {} a la película", comment, movieId);
        return commentService.add(movieId, comment);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response getAllComments(@PathParam("movieId") String movieId) {
        log.info("Consultando listado de comentarios de la película", movieId);
        return commentService.findAll(movieId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{commentId}")
    public Response getComment(@PathParam("movieId") String movieId, @PathParam("commentId") String commentId) {
        log.info("Consultando comentario ID: {} de la película {}", commentId, movieId);
        return commentService.findById(commentId);
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{commentId}")
    public Response modifyComment(@PathParam("movieId") String movieId, @PathParam("commentId") String commentId, @Valid Comment comment) {
        log.info("Modificando comentario ID: {} de la película {}", commentId, movieId);
        return commentService.modifyById(commentId, comment);
    }
    
    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{commentId}")
    public Response updateComment(@PathParam("movieId") String movieId, @PathParam("commentId") String commentId, @Valid Comment comment) {
        log.info("Actualizando comentario ID: {} de la película {}", commentId, movieId);
        return commentService.updateById(commentId, comment);
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{commentId}")
    public Response deleteComment(@PathParam("movieId") String movieId, @PathParam("commentId") String commentId) {
        log.info("Eliminando comentario ID: {} de la película {}", commentId, movieId);
        return commentService.deleteById(commentId);
    }
}