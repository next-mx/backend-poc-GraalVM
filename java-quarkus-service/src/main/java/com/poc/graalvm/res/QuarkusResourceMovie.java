package com.poc.graalvm.res;


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
import com.poc.graalvm.service.QuarkusService;
import com.poc.quarkus.obj.Comment;
import com.poc.quarkus.obj.Movie;
import com.poc.quarkus.obj.Response;



@Path("/movies")
public class QuarkusResourceMovie {

    @Inject
    QuarkusService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    //@Path("/path/{name}")
    public Response addMovie(Movie movie) {
        return service.saveMovie(movie);
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Movie GetComment(@PathParam("id") String id) {
        return service.getMovie(id);
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateMovie(@PathParam("id") String id,Movie movie) {
        return service.updateMovie(id,movie);
    }
    
    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response modifMovie(@PathParam("id") String id,Movie movie) {
        return service.modifMovie(id,movie);
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteMovie(@PathParam("id") String id) {
        return service.deleteMovie(id);
    }
}