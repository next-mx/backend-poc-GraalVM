package poc.graalvm.micronaut;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poc.graalvm.micronaut.http.ResponseDTO;
import poc.graalvm.micronaut.model.Comment;
import poc.graalvm.micronaut.model.Imdb;
import poc.graalvm.micronaut.model.Movie;
import poc.graalvm.micronaut.service.BackupMoviesTask;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static com.mongodb.client.model.Filters.eq;

@Controller("/pocgraalvm/api/v1/movies")
@Produces(MediaType.APPLICATION_JSON)
public class HttpMovies {
    private BackupMoviesTask backupMoviesTask;
    private final MongoClient mongoClient;


    private static CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    private static final Logger log = LoggerFactory.getLogger(HttpMovies.class);


    public HttpMovies(BackupMoviesTask backupMoviesTask, MongoClient mongoClient) {
        this.backupMoviesTask = backupMoviesTask;
        this.mongoClient = mongoClient;
    }

    @Post
    public HttpResponse post(@Valid @Body Movie movie) {
        log.info("Registrando película {}", movie);
        getMoviesCollection().insertOne(movie);

        return HttpResponse.created(new ResponseDTO<>("Pelicula agregada exitosamente", null));
    }

    @Get("/")
    public HttpResponse getAll(){
        log.info("Consultando listado de películas");
        List<Movie> movies = new ArrayList<>();
        MongoCursor<Movie> moviesCursor = getMoviesCollection().find().iterator();

        while (moviesCursor.hasNext()) {
            movies.add(moviesCursor.next());
        }

        ResponseDTO<List<Movie>> movieReturned = new ResponseDTO<List<Movie>>("Pelicula consultada exitosamente", movies);
        return HttpResponse.ok(movieReturned);
    }

    @Get("/{id}")
    public HttpResponse get(@NotBlank @PathVariable String id){
        log.info("Consultando película ID {}", id);
        Movie movie = getMoviesCollection().find(eq("_id", new ObjectId(id))).first();

        ResponseDTO<Movie> movieReturned = new ResponseDTO<>("Pelicula consultada exitosamente", movie);
        return HttpResponse.ok(movieReturned);
    }

    @Put("/{id}")
    public HttpResponse put(@NotBlank @PathVariable String id, @Valid @Body Movie movie){
        log.info("Modificando película ID: {} con los datos: {}", id, movie);
        UpdateResult updateResult = getMoviesCollection().replaceOne(eq("_id", new ObjectId(id)), movie);
        long updated = updateResult.getModifiedCount();

        HttpResponse response = null;

        ResponseDTO<Movie> movieResponseDTO = new ResponseDTO<>("Pelicula modificada exitosamente", null);

        if(updated == 1){
            response = HttpResponse.ok(movieResponseDTO);
        } else {
            response = HttpResponse.notModified();
        }

        return response;
    }

    @Patch("/{id}")
    public HttpResponse patch(@NotBlank @PathVariable String id, @Valid @Body Imdb imdb) throws JsonProcessingException {
        log.info("Actualizando película ID: {}, con los datos: {}", id, imdb);

        Bson query = combine(
                set("imdb.rating", imdb.getRating()),
                set("imdb.votes", imdb.getVotes()),
                set("imdb.id", imdb.getId()));

        UpdateResult result = getMoviesCollection().updateOne(eq("_id", new ObjectId(id)), query);

        ResponseDTO<Movie> movieResponseDTO = new ResponseDTO<>("Pelicula editada exitosamente", null);
        return HttpResponse.ok(movieResponseDTO);
    }

    @Delete("/{id}")
    public HttpResponse delete(@NotBlank @PathVariable String id){
        log.info("Eliminando película ID: {}", id);
        DeleteResult deleteResult = getMoviesCollection().deleteOne(eq("_id", new ObjectId(id)));

        HttpResponse response = null;

        ResponseDTO<Movie> movieResponseDTO = new ResponseDTO<>("Pelicula eliminada exitosamente", null);
        if(deleteResult.getDeletedCount() > 0){
            response = HttpResponse.ok(movieResponseDTO);
        } else {
            response = HttpResponse.notModified();
        }

        return response;
    }

    @Post("/backup")
    public HttpResponse backup(){
        log.info("Agendando backup de películas");
        backupMoviesTask.makeBackup(true, getMoviesCollection(), getCommentsCollection());

        ResponseDTO<Movie> movieResponseDTO = new ResponseDTO<>("Respaldo agendado exitosamente", null);
        return HttpResponse.ok(movieResponseDTO);
    }

    @Get("/backup")
    public HttpResponse getBackup(){
        log.info("Consultando backup de películas");
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
            ResponseDTO<List<Movie>> movieResponseDTO = new ResponseDTO<>("Respaldo no encontrado", null);
            return HttpResponse.notFound(movieResponseDTO);
        }
        ResponseDTO<List<Movie>> movieResponseDTO = new ResponseDTO<>("Respaldo consultado exitosamente", movies);
        return HttpResponse.ok(movieResponseDTO);
    }

    private MongoCollection<Movie> getMoviesCollection() {
        return mongoClient
                .getDatabase("poc-db")
                .getCollection("Movies", Movie.class)
                .withCodecRegistry(pojoCodecRegistry);
    }

    private MongoCollection<Comment> getCommentsCollection() {
        return mongoClient
                .getDatabase("poc-db")
                .getCollection("Comment", Comment.class)
                .withCodecRegistry(pojoCodecRegistry);
    }

}
