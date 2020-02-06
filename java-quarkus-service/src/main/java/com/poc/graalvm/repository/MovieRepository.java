package com.poc.graalvm.repository;

import com.poc.graalvm.model.IMDB;
import com.poc.graalvm.model.Movie;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class MovieRepository implements PanacheMongoRepository<Movie> {

    public void saveMovie(Movie movie){
        persist(movie);
    }

    public List<Movie> getAllMovies(){
        return findAll().list();
    }

    public void deleteMovie(Movie movie){
        delete(movie);
    }

    public void modifyMovie(Movie movie){
        update(movie);
    }

    public void updateMovie(String id, IMDB imdb){
        ObjectId idDB = new ObjectId(id);
        Movie movie =findById(idDB);
        movie.setImdb(imdb);
        update(movie);
    }
}