package com.poc.graalvm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.graalvm.config.Constants;
import com.poc.graalvm.error.InvalidMongoIdException;
import com.poc.graalvm.model.Imdb;
import com.poc.graalvm.repository.MovieRepository;
import com.poc.graalvm.model.Movie;
import com.poc.graalvm.model.ResponseDTO;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@ApplicationScoped
public class MovieService {

    @Inject
    private MovieRepository movieRepository;
    @Inject
    private BackupMoviesTask backupMoviesTask;

    public Response add(Movie movie){
        movieRepository.saveMovie(movie);
        if(movie.getId() != null)
            return Response
                    .created(URI.create(Constants.MOVIES_PATH + "/"  + movie.getId().toString()))
                    .entity(new ResponseDTO<>("Película agregada exitosamente", null))
                    .build();
        return Response.serverError().build();
    }

    public Response findAll(){
        return Response.ok(new ResponseDTO<>(
                "Película consultada exitosamente",
                 movieRepository.getAllMovies()))
                .build();
    }

    public Response findById(String id){
        if(!ObjectId.isValid(id))
            throw new InvalidMongoIdException();
        ObjectId idDB = new ObjectId(id);
        return Response.ok(new ResponseDTO<>(
                "Película consultada exitosamente",
                 movieRepository.findById(idDB)))
                .build();
    }


    public Response modifyById(String id, Movie movie){
        if(!ObjectId.isValid(id))
            throw new InvalidMongoIdException();
        movie.setId(new ObjectId(id));
        movieRepository.modifyMovie(movie);
        return Response.ok()
                .entity(new ResponseDTO<>("Película modificada exitosamente", null))
                .build();
    }


    public Response updateById(String id, Imdb imdb){
        if(!ObjectId.isValid(id))
            throw new InvalidMongoIdException();
        movieRepository.updateMovie(id, imdb);
        return Response.ok()
                .entity(new ResponseDTO<>("Película editada exitosamente", null))
                .build();
    }


    public Response deleteById(String id){
        if(!ObjectId.isValid(id))
            throw new InvalidMongoIdException();
        Movie movie = new Movie();
        movie.setId(new ObjectId(id));
        movieRepository.deleteMovie(movie);
        return Response.ok()
                .entity(new ResponseDTO<>("Película eliminada exitosamente", null))
                .build();
    }


    public Response scheduleBackup() {
        backupMoviesTask.makeBackup(true);
        return Response.created(URI.create(Constants.MOVIES_PATH + "/backup"))
                .entity(new ResponseDTO<>("Respaldo agendado exitosamente", null))
                .build();
    }


    public Response getBackup() {
        List<Movie> movies = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try (Stream<String> stream = Files.lines(Paths.get(BackupMoviesTask.FILE_NAME))) {
            stream.forEach(item -> {
                try {
                    Movie movie = objectMapper.readValue(item.substring(item.indexOf(',') + 1), Movie.class);
                    movies.add(movie);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ResponseDTO<>("Respaldo no encontrado", null))
                    .build();
        }
        return Response.ok()
                .entity(new ResponseDTO<>("Respaldo consultado exitosamente", movies))
                .build();
    }
}
