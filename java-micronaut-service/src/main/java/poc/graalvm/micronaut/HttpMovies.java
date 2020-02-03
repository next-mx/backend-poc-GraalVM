package poc.graalvm.micronaut;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import poc.graalvm.micronaut.http.Response;
import poc.graalvm.micronaut.model.Comment;
import poc.graalvm.micronaut.model.Imdb;
import poc.graalvm.micronaut.model.Movie;
import poc.graalvm.micronaut.service.TimerBakcupMovies;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


import java.util.*;

import static com.mongodb.client.model.Filters.eq;

@Controller("/pocgraalvm/api/v1/movies")
@Produces(MediaType.APPLICATION_JSON)
public class HttpMovies {

    private static CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    private static final Logger log = LoggerFactory.getLogger(HttpMovies.class);

    private final MongoClient mongoClient;

    public HttpMovies(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Post
    public HttpResponse post(@Valid @Body Movie movie) {
        log.info("Registrando película {}", movie);
        getMoviesCollection().insertOne(movie);

        return HttpResponse.created(new Response<>("Pelicula agregada exitosamente"));
    }

    @Get("/")
    public HttpResponse getAll(){
        log.info("Consultando listado de películas");
        List<Movie> movies = new ArrayList<>();
        MongoCursor<Movie> moviesCursor = getMoviesCollection().find().iterator();

        while (moviesCursor.hasNext()) {
            movies.add(moviesCursor.next());
        }

        Response<Movie> movieReturned = new Response<>("Pelicula consultada exitosamente");
        movieReturned.setResult(movies);
        return HttpResponse.ok(movieReturned);
    }

    @Get("/{id}")
    public HttpResponse get(@NotBlank @PathVariable String id){
        log.info("Consultando película ID {}", id);
        Movie movie = getMoviesCollection().find(eq("_id", new ObjectId(id))).first();

        Response<Movie> movieReturned = new Response<>("Pelicula consultada exitosamente");
        movieReturned.setResult(Arrays.asList(movie));
        return HttpResponse.ok(movieReturned);
    }

    @Put("/{id}")
    public HttpResponse put(@NotBlank @PathVariable String id, @Valid @Body Movie movie){
        log.info("Modificando película ID: {} con los datos: {}", id, movie);
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
    public HttpResponse patch(@NotBlank @PathVariable String id, @Valid @Body Imdb imdb) throws JsonProcessingException {
        log.info("Actualizando película ID: {}, con los datos: {}", id, imdb);

        Bson query = combine(
                set("imdb.rating", imdb.getRating()),
                set("imdb.votes", imdb.getVotes()),
                set("imdb.id", imdb.getId()));

        UpdateResult result = getMoviesCollection().updateOne(eq("_id", new ObjectId(id)), query);

        Response<Movie> movieResponse = new Response<>("Pelicula editada exitosamente");
        return HttpResponse.ok(movieResponse);
    }

    @Delete("/{id}")
    public HttpResponse delete(@NotBlank @PathVariable String id){
        log.info("Eliminando película ID: {}", id);
        DeleteResult deleteResult = getMoviesCollection().deleteOne(eq("_id", new ObjectId(id)));

        HttpResponse response = null;

        Response<Movie> movieResponse = new Response<>("");
        if(deleteResult.getDeletedCount() > 0){
            movieResponse.setMessage("Pelicula eliminada exitosamente");
            response = HttpResponse.ok(movieResponse);
        } else {
            response = HttpResponse.notModified();
        }

        return response;
    }

    @Post("/backup")
    public HttpResponse backup(){
        log.info("Agendando backup de películas");
        TimerBakcupMovies timerBakcupMovies = new TimerBakcupMovies(getMoviesCollection(), getCommentsCollection());
        Timer timerBackup = new Timer();
        timerBackup.schedule(timerBakcupMovies, 5000);

        Response<Movie> movieResponse = new Response<>("Respaldo agendado exitosamente");
        return HttpResponse.ok(movieResponse);
    }

    @Get("/backup")
    public HttpResponse getBackup(){
        log.info("Consultando backup de películas");
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
