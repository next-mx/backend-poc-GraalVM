package com.poc.graalvm.service;

import com.poc.graalvm.config.Constants;
import com.poc.graalvm.error.InvalidMongoIdException;
import com.poc.graalvm.repository.MovieRepository;
import com.poc.graalvm.model.Movie;
import com.sun.xml.bind.v2.runtime.reflect.opt.Const;
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
            return Response.created(URI.create(Constants.MOVIES_PATH + "/"  + movie.getId().toString())).build();
        return Response.serverError().build();
    }

    public List<Movie> findAll(){
        return movieRepository.getAllMovies();
    }

    public Response findById(String id){
        if(!ObjectId.isValid(id))
            throw new InvalidMongoIdException();
        ObjectId idDB = new ObjectId(id);
        return Response.ok(movieRepository.findById(idDB)).build();
    }

    public Response deleteById(String id){
        if(!ObjectId.isValid(id))
            throw new InvalidMongoIdException();
        Movie movie = new Movie();
        movie.setId(new ObjectId(id));
        movieRepository.deleteMovie(movie);
        return Response.noContent().build();
    }

    public Response update(Movie movie){
        if(movie.getId() == null)
            throw new InvalidMongoIdException();
        return updateById(movie.getId().toString(), movie);
    }

    public Response updateById(String id,Movie movie){
        if(!ObjectId.isValid(id))
            throw new InvalidMongoIdException();
        movie.setId(new ObjectId(id));
        movieRepository.updateMovie(movie);
        return Response.noContent().build();
    }

}
