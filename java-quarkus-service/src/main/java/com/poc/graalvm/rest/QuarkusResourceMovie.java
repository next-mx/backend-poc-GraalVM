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
import com.poc.graalvm.model.IMDB;
import com.poc.graalvm.service.MovieService;
import com.poc.graalvm.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path(Constants.MOVIES_PATH)
public class QuarkusResourceMovie {
    private static final Logger log = LoggerFactory.getLogger(QuarkusResourceMovie.class);
    @Inject
    MovieService movieService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMovie(Movie movie) {
        log.info("Registrando película: {}", movie);
        return movieService.add(movie);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response getMovies() {
        log.info("Consultando listado de películas");
        return movieService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{movieID}")
    public Response getMovie(@PathParam("movieID") String movieID) {
        log.info("Consultando película ID: {}", movieID);
        return movieService.findById(movieID);
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{movieID}")
    public Response modifyMovie(@PathParam("movieID") String movieID, Movie movie) {
        log.info("Modificando película ID: {} con datos: {}", movieID, movie);
        return movieService.modifyById(movieID, movie);
    }
    
    @PATCH()
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{movieID}")
    public Response updateMovie(@PathParam("movieID") String movieID, IMDB imdb) {
        log.info("Actualizando película ID: {} con datos: {}", movieID, imdb);
        return movieService.updateById(movieID, imdb);
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{movieID}")
    public Response deleteMovie(@PathParam("movieID") String movieID) {
        log.info("Eliminado película ID: {}", movieID);
        return movieService.deleteById(movieID);
    }
}