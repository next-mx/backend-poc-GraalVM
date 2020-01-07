package com.poc.graalvm.rest;


import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.poc.graalvm.config.Constants;
import com.poc.graalvm.service.MovieService;
import com.poc.graalvm.model.Movie;
import com.poc.graalvm.model.ResponseData;


@Path(Constants.MOVIES_PATH)
public class QuarkusResourceMovie {

    @Inject
    MovieService movieService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMovie(Movie movie) {
        return movieService.add(movie);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response GetMovies() {
        return Response.ok(movieService.findAll()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response GetMovie(@PathParam("id") String id) {
        return movieService.findById(id);
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateMovie(@PathParam("id") String id, Movie movie) {
        return movieService.updateById(id,movie);
    }
    
    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyMovie(Movie movie) {
        return movieService.update(movie);
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteMovie(@PathParam("id") String id) {
        return movieService.deleteById(id);
    }
}