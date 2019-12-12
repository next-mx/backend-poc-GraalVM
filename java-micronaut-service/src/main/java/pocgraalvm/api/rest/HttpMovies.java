package pocgraalvm.api.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import pocgraalvm.api.rest.http.Response;
import pocgraalvm.api.rest.model.Movie;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


import static com.mongodb.client.model.Filters.eq;

@Controller("/pocgraalvm/api/v1/movies")
@Produces(MediaType.APPLICATION_JSON)
public class HttpMovies {

    private static CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    private static final Logger LOGGER = LogManager.getLogger(HttpMovies.class.getName());

    private final MongoClient mongoClient;

    public HttpMovies(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Post
    public HttpResponse post(@Valid @Body Movie movie) {

        getCollection().insertOne(movie);

        return HttpResponse.created(new Response<Movie>("Pelicula agregada exitosamente"));
    }

    @Get("{id}")
    public HttpResponse get(@NotBlank String id){

        LOGGER.debug("id {}",id);
        Movie movie = getCollection().find(eq("_id", new ObjectId(id))).first();
        LOGGER.debug(movie);

        Response<Movie> movieReturned = new Response<>("Pelicula consultada exitosamente");
        movieReturned.setResult(movie);
        return HttpResponse.ok(movieReturned);
    }

    @Put("{id}")
    public HttpResponse put(@NotBlank String id, @Valid @Body Movie movie){
        UpdateResult updateResult = getCollection().replaceOne(eq("_id", new ObjectId(id)), movie);
        long updated = updateResult.getModifiedCount();

        HttpResponse response = null;

        Response<Movie> movieResponse = new Response<>("");

        if(updated == 1){
            movieResponse.setMessage("Pelicula modificada exitosamente");
            response = HttpResponse.ok(movieResponse);
        } else {
            response = HttpResponse.noContent();
        }

        return response;
    }

    @Patch("{id}")
    public HttpResponse patch(@NotBlank String id, @Valid @Body Movie movie) throws JsonProcessingException {

        Document updateDocument = Document.parse(new ObjectMapper().writeValueAsString(movie));

        LOGGER.debug("updateDocument {}",updateDocument);

        Document setDocument = new Document();
        setDocument.append("$set",updateDocument);

        getCollection().updateOne(eq("_id", new ObjectId(id)), setDocument);

        Response<Movie> movieResponse = new Response<>("Pelicula editada exitosamente");
        return HttpResponse.ok(movieResponse);
    }

    private MongoCollection<Movie> getCollection() {
        return mongoClient
                .getDatabase("movies")
                .getCollection("Movies", Movie.class)
                .withCodecRegistry(pojoCodecRegistry);
    }

}
