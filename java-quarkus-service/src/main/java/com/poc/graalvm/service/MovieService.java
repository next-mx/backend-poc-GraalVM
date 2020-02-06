package com.poc.graalvm.service;

import com.poc.graalvm.config.Constants;
import com.poc.graalvm.error.InvalidMongoIdException;
import com.poc.graalvm.model.IMDB;
import com.poc.graalvm.repository.MovieRepository;
import com.poc.graalvm.model.Movie;
import com.poc.graalvm.model.ResponseDTO;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@ApplicationScoped
public class MovieService {

    @Inject
    MovieRepository movieRepository;

    public Response add(Movie movie){
        movieRepository.saveMovie(movie);
        if(movie.getId() != null)
            return Response
                    .created(URI.create(Constants.MOVIES_PATH + "/"  + movie.getId().toString()))
                    .entity(new ResponseDTO<String>("Película agregada exitosamente", null))
                    .build();
        return Response.serverError().build();
    }

    public Response findAll(){
        return Response.ok(new ResponseDTO<List<Movie>>(
                "Película consultada exitosamente",
                 movieRepository.getAllMovies()))
                .build();
    }

    public Response findById(String id){
        if(!ObjectId.isValid(id))
            throw new InvalidMongoIdException();
        ObjectId idDB = new ObjectId(id);
        return Response.ok(new ResponseDTO<Movie>(
                "Película consultada exitosamente",
                 movieRepository.findById(idDB)))
                .build();
    }


    public Response modifyById(String id, Movie movie){
        if(!ObjectId.isValid(id))
            throw new InvalidMongoIdException();
        movie.setId(new ObjectId(id));
        movieRepository.modifyMovie(movie);
        return Response.noContent()
                .entity(new ResponseDTO<String>("Película modificada exitosamente", null))
                .build();
    }


    public Response updateById(String id, IMDB imdb){
        if(!ObjectId.isValid(id))
            throw new InvalidMongoIdException();
        movieRepository.updateMovie(id, imdb);
        return Response.noContent()
                .entity(new ResponseDTO<String>("Película editada exitosamente", null))
                .build();
    }


    public Response deleteById(String id){
        if(!ObjectId.isValid(id))
            throw new InvalidMongoIdException();
        Movie movie = new Movie();
        movie.setId(new ObjectId(id));
        movieRepository.deleteMovie(movie);
        return Response.noContent()
                .entity(new ResponseDTO<String>("Película eliminada exitosamente", null))
                .build();
    }
}
