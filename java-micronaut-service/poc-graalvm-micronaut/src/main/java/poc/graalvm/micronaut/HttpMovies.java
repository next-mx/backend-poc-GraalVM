package poc.graalvm.micronaut;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poc.graalvm.micronaut.http.Response;
import poc.graalvm.micronaut.model.Comment;
import poc.graalvm.micronaut.model.Movie;
import poc.graalvm.micronaut.service.TimerBakcupMovies;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


import java.time.LocalDateTime;
import java.util.*;

import static com.mongodb.client.model.Filters.eq;

@Controller("/pocgraalvm/api/v1/movies")
@Produces(MediaType.APPLICATION_JSON)
public class HttpMovies {

    private static CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpMovies.class);

    private final MongoClient mongoClient;

    public HttpMovies(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Post
    public HttpResponse post(@Valid @Body Movie movie) {

        getMoviesCollection().insertOne(movie);

        return HttpResponse.created(new Response<>("Pelicula agregada exitosamente"));
    }

    @Get("/{id}")
    public HttpResponse get(@NotBlank @PathVariable String id){

        LOGGER.debug("id {}",id);
        Movie movie = getMoviesCollection().find(eq("_id", new ObjectId(id))).first();
        LOGGER.debug("movie {}",movie);

        Response<Movie> movieReturned = new Response<>("Pelicula consultada exitosamente");
        movieReturned.setResult(Arrays.asList(movie));
        return HttpResponse.ok(movieReturned);
    }

    @Put("/{id}")
    public HttpResponse put(@NotBlank @PathVariable String id, @Valid @Body Movie movie){
        UpdateResult updateResult = getMoviesCollection().replaceOne(eq("_id", new ObjectId(id)), movie);
        long updated = updateResult.getModifiedCount();

        HttpResponse response = null;

        Response<Movie> movieResponse = new Response<>("");

        if(updated == 1){
            movieResponse.setMessage("Pelicula modificada exitosamente");
            response = HttpResponse.ok(movieResponse);
        } else {
            response = HttpResponse.notModified();
        }

        return response;
    }

    @Patch("/{id}")
    public HttpResponse patch(@NotBlank @PathVariable String id, @Valid @Body Movie movie) throws JsonProcessingException {

        Document updateDocument = Document.parse(new ObjectMapper().writeValueAsString(movie));

        LOGGER.debug("updateDocument {}",updateDocument);

        Document setDocument = new Document();
        setDocument.append("$set",updateDocument);

        getMoviesCollection().updateOne(eq("_id", new ObjectId(id)), setDocument);

        Response<Movie> movieResponse = new Response<>("Pelicula editada exitosamente");
        return HttpResponse.ok(movieResponse);
    }

    @Delete("/{id}")
    public HttpResponse delete(@NotBlank @PathVariable String id){

        LOGGER.debug("Objeto a borrar {}",id);

        getMoviesCollection().deleteOne(eq("_id", new ObjectId(id)));

        Response<Movie> movieResponse = new Response<>("Pelicula eliminada exitosamente");
        return HttpResponse.ok(movieResponse);
    }

    @Post("/createBackup")
    public HttpResponse backup(){

        LOGGER.info("Fecha de envio de Backup {}", LocalDateTime.now());

        TimerBakcupMovies timerBakcupMovies = new TimerBakcupMovies(getMoviesCollection(), getCommentsCollection());
        Timer timerBackup = new Timer();
        timerBackup.schedule(timerBakcupMovies, 5000);

        Response<Movie> movieResponse = new Response<>("Respaldo agendado exitosamente");
        return HttpResponse.ok(movieResponse);
    }

    @Get("/getBackup")
    public HttpResponse getBackup(){

        List<Movie> movieList = new ArrayList<>();
        FindIterable<Movie> findIterable = getMoviesCollection().find();
        findIterable.iterator().forEachRemaining(movieList::add);
        Response<Movie> movieResponse = new Response<>("Respaldo consultado exitosamente");
        movieResponse.setResult(movieList);
        return HttpResponse.ok(movieResponse);
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
