package poc.graalvm.micronaut.service;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poc.graalvm.micronaut.HttpComments;
import poc.graalvm.micronaut.model.Comment;
import poc.graalvm.micronaut.model.Movie;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.util.TimerTask;

import static com.mongodb.client.model.Filters.eq;

public class TimerBakcupMovies extends TimerTask {

    private MongoCollection<Movie> movieMongoCollection;
    private MongoCollection<Comment> commentMongoCollection;

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpComments.class);

    public TimerBakcupMovies(MongoCollection<Movie> movieMongoCollection, MongoCollection<Comment> commentMongoCollection){
        this.movieMongoCollection = movieMongoCollection;
        this.commentMongoCollection = commentMongoCollection;
    }


    @Override
    public void run() {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("movies_catalog.csv"))){

            long time = System.currentTimeMillis();

            LOGGER.info("Inicia Backup {}", LocalDateTime.now());

            /**
             * Inicializacion de variables
             */
            long countMovies = this.movieMongoCollection.countDocuments();
            FindIterable<Movie> findIterable = this.movieMongoCollection.find();
            MongoCursor<Movie> movieMongoCursor = findIterable.iterator();
            BigDecimal id = null;
            long commentsCount = 0;
            Movie movieIt = null;
            Document document = new Document();

            for (int i = 1; movieMongoCursor.hasNext() ; i++) {

                movieIt = movieMongoCursor.next();

                id = BigDecimal.valueOf(System.currentTimeMillis()+i);
                LOGGER.debug("Fecha + iteracion {}",id);

                commentsCount = this.commentMongoCollection.countDocuments(eq("movie_Id", movieIt.getId().toHexString()));
                commentsCount = commentsCount == 0 ? 1 : commentsCount;

                LOGGER.debug("commentsCount {}",commentsCount);

                id = id.multiply(BigDecimal.valueOf(commentsCount));

                LOGGER.debug("Mulitplicado por comentarios {}",id);
                LOGGER.debug("countMovies {}",countMovies);

                id = id.divide(BigDecimal.valueOf(countMovies), MathContext.DECIMAL128);

                LOGGER.debug("Dividido por conteo de Movies {}",id);

                document.append("id",id);
                document.append("pelicula",movieIt.toString());
                writer.write(document.toJson());
                writer.newLine();
            }

            LOGGER.info("Termina Backup {}", LocalDateTime.now());
            LOGGER.info("Tiempo total de respaldo {}",System.currentTimeMillis()-time);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
        }
    }
}
