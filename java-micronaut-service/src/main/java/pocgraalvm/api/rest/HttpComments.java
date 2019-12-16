package pocgraalvm.api.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import pocgraalvm.api.rest.http.Response;
import pocgraalvm.api.rest.model.Comment;
import pocgraalvm.api.rest.model.Movie;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Controller("/pocgraalvm/api/v1/movies")
@Produces(MediaType.APPLICATION_JSON)
public class HttpComments {

    private static CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    private static final Logger LOGGER = LogManager.getLogger(HttpComments.class.getName());

    private final MongoClient mongoClient;

    public HttpComments(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Post("{movieId}/comments")
    public HttpResponse post(@NotBlank String movieId, @Valid @Body Comment comment) {

        comment.setMovie_Id(movieId);
        comment.setDate(System.currentTimeMillis());
        getCollection().insertOne(comment);

        return HttpResponse.created(new Response<Movie>("Comentario agregado exitosamente"));
    }

   @Get("{movieId}/comments/{commentId}")
   public HttpResponse get(@NotBlank String movieId,@NotBlank String commentId){

       LOGGER.debug("movieId {} , commentId {} ",movieId,commentId);
       Comment comment = getCollection().find(
               and(
                    eq("_id", new ObjectId(commentId)),
                       eq("movie_Id", movieId)
               )
       ).first();
       LOGGER.debug(comment);

       Response<Comment> movieReturned = new Response<>("Pelicula consultada exitosamente");
       movieReturned.setResult(comment);
       return HttpResponse.ok(movieReturned);
   }

    @Put("{movieId}/comments/{commentId}")
    public HttpResponse put(@NotBlank String movieId,@NotBlank String commentId, @Body Comment comment){
        UpdateResult updateResult = getCollection().replaceOne(
                and(
                    eq("_id", new ObjectId(commentId)),
                    eq("movie_Id", movieId)
        ), comment);
        long updated = updateResult.getModifiedCount();

        HttpResponse response = null;

        Response<Movie> movieResponse = new Response<>("");

        if(updated == 1){
            movieResponse.setMessage("Comentario modificado exitosamente");
            response = HttpResponse.ok(movieResponse);
        } else {
            response = HttpResponse.notModified();
        }

        return response;
    }

    @Patch("{movieId}/comments/{commentId}")
    public HttpResponse patch(@NotBlank String movieId,@NotBlank String commentId, @Body Comment comment) throws JsonProcessingException {

        Document updateDocument = Document.parse(new ObjectMapper().writeValueAsString(comment));

        LOGGER.debug("updateDocument {}",updateDocument);

        Document setDocument = new Document();
        setDocument.append("$set",updateDocument);

        UpdateResult updateResult = getCollection().updateOne(and(
                eq("_id", new ObjectId(commentId)),
                eq("movie_Id", movieId)
        ), setDocument);

        LOGGER.debug("updateResult {}",updateResult);

        HttpResponse response = null;
        Response<Movie> movieResponse = new Response<>("");

        if(updateResult.getModifiedCount() == 1L){
            movieResponse.setMessage("Comentario editado exitosamente");
            response = HttpResponse.ok(movieResponse);
        } else {
            response = HttpResponse.notModified();
        }

        return response;
    }

    @Delete("{movieId}/comments/{commentId}")
    public HttpResponse delete(@NotBlank String movieId,@NotBlank String commentId){

        LOGGER.debug("Objeto a borrar {}",movieId);

        getCollection().deleteOne(and(
                eq("_id", new ObjectId(commentId)),
                eq("movie_Id", movieId)
        ));

        Response<Movie> movieResponse = new Response<>("Comentario eliminado exitosamente");
        return HttpResponse.ok(movieResponse);
    }

    private MongoCollection<Comment> getCollection() {
        return mongoClient
                .getDatabase("poc-db")
                .getCollection("Comment", Comment.class)
                .withCodecRegistry(pojoCodecRegistry);
    }

}
