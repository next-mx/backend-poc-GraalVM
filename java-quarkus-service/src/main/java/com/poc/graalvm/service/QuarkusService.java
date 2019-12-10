package com.poc.graalvm.service;

import javax.enterprise.context.ApplicationScoped;
import com.poc.quarkus.obj.Comment;
import com.poc.quarkus.obj.Movie;
import com.poc.quarkus.obj.Response;

@ApplicationScoped
public class QuarkusService {
	
    public Response saveComment(Comment comment) {
        return new Response("Comentario agregado exitosamente",null);
    }
    
    public Comment getComment(String id){
    	return new Comment("nombre","correo","comentario");
    }
    
    public Response updateComment(String id,Comment comment) {
        return new Response("Comentario actualizado exitosamente",null);
    }
    
    public Response modifComment(String id,Comment comment) {
        return new Response("Comentario modificado exitosamente",null);
    }
    
    public Response deleteComment(String id) {
        return new Response("Comentario eliminado exitosamente",null);
    }
    
    public Response saveMovie(Movie movie) {
        return new Response("Pelicula agregada exitosamente",null);
    }
    
    public Movie getMovie(String id){
    	return new Movie();
    }
    
    public Response updateMovie(String id,Movie movie) {
        return new Response("Pelicula editada exitosamente",null);
    }
    
    public Response modifMovie(String id,Movie movie) {
        return new Response("Pelicula modificada exitosamente",null);
    }
    
    public Response deleteMovie(String id) {
        return new Response("Pelicula eliminada exitosamente",null);
    }

}
