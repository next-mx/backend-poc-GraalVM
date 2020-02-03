package poc.graalvm.micronaut;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poc.graalvm.micronaut.http.Response;
import poc.graalvm.micronaut.model.Comment;
import poc.graalvm.micronaut.model.Movie;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Controller("/pocgraalvm/api/v1/movies")
@Produces(MediaType.APPLICATION_JSON)
public class HttpComments {

    private static CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    private static final Logger log = LoggerFactory.getLogger(HttpComments.class);

    private final MongoClient mongoClient;

    public HttpComments(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Post("{movieId}/comments")
    public HttpResponse post(@NotBlank String movieId, @Valid @Body Comment comment) {
        log.info("Agregando comentario: {} a la película: {}", comment, movieId);
        comment.setMovie_Id(movieId);
        comment.setDate(System.currentTimeMillis());
        getCollection().insertOne(comment);

        return HttpResponse.created(new Response<Movie>("Comentario agregado exitosamente"));
    }


    @Get("{movieId}/comments/")
    public HttpResponse getAll(@NotBlank String movieId){
        log.info("Consultando comentarios de la película: {}", movieId);
        List<Comment> comments = new ArrayList<>();
        Iterator<Comment> commentIterator = getCollection().find(
                and(eq("movie_Id", movieId))
        ).iterator();

        while (commentIterator.hasNext()) {
            comments.add(commentIterator.next());
        }

        Response<Comment> movieReturned = new Response<>("Pelicula consultada exitosamente");
        movieReturned.setResult(comments);
        return HttpResponse.ok(movieReturned);
    }

   @Get("{movieId}/comments/{commentId}")
   public HttpResponse get(@NotBlank String movieId,@NotBlank String commentId){
       log.info("Consultando comentario ID: {} de la película: {}", commentId, movieId);
       Comment comment = getCollection().find(
               and(
                    eq("_id", new ObjectId(commentId)),
                       eq("movie_Id", movieId)
               )
       ).first();

       Response<Comment> movieReturned = new Response<>("Pelicula consultada exitosamente");
       movieReturned.setResult(Arrays.asList(comment));
       return HttpResponse.ok(movieReturned);
   }

    @Put("{movieId}/comments/{commentId}")
    public HttpResponse put(@NotBlank String movieId,@NotBlank String commentId, @Body Comment comment){
        log.info("Modificando comentario ID: {} de la película: {}", commentId, movieId);
        comment.setMovie_Id(movieId);
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
        log.info("Actualizando comentario ID: {} de la película: {}", commentId, movieId);
        Document updateDocument = Document.parse(new ObjectMapper().writeValueAsString(comment));
        updateDocument.append("movie_Id",movieId);

        Document setDocument = new Document();
        setDocument.append("$set",updateDocument);

        UpdateResult updateResult = getCollection().updateOne(and(
                eq("_id", new ObjectId(commentId)),
                eq("movie_Id", movieId)
        ), setDocument);

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
        log.info("Eliminando comentario ID: {} de la película: {}", commentId, movieId);
        DeleteResult deleteResult = getCollection().deleteOne(and(
                eq("_id", new ObjectId(commentId)),
                eq("movie_Id", movieId)
        ));

        HttpResponse response = null;

        Response<Movie> movieResponse = new Response<>("");
        if(deleteResult.getDeletedCount() > 0){
            movieResponse.setMessage("Comentario eliminado exitosamente");
            response = HttpResponse.ok(movieResponse);
        } else {
            response = HttpResponse.notModified();
        }

        return response;
    }

    private MongoCollection<Comment> getCollection() {
        return mongoClient
                .getDatabase("poc-db")
                .getCollection("Comment", Comment.class)
                .withCodecRegistry(pojoCodecRegistry);
    }

}
