package com.poc.graalvm.rest;


import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.poc.graalvm.config.Constants;
import com.poc.graalvm.model.Comment;
import com.poc.graalvm.model.ResponseData;
import com.poc.graalvm.service.CommentService;
import org.jboss.resteasy.annotations.Body;


@Path(Constants.COMMENTS_PATH)
public class QuarkusResourceComment {

    @Inject
    CommentService commentService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addComment(@Valid Comment comment) {
        return commentService.add(comment);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response GetAllComments() {
        return Response.ok(commentService.findAll()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response GetComment(@PathParam("id") String id) {
        return commentService.findByIdMovie(id);
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateComment(@PathParam("id") String id, @Valid Comment comment) {
        return commentService.updateById(id,comment);
    }
    
    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyComment(@Valid Comment comment) {
        return commentService.update(comment);
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteComment(@PathParam("id") String id) {
        return commentService.deleteById(id);
    }
}