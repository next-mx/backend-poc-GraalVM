package com.poc.graalvm.repository;

import com.poc.graalvm.model.Movie;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

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

    public void updateMovie(Movie movie){
        update(movie);
    }

}