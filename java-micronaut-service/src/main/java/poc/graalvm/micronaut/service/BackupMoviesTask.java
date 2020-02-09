package poc.graalvm.micronaut.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import io.micronaut.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poc.graalvm.micronaut.model.Comment;
import poc.graalvm.micronaut.model.Movie;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import static com.mongodb.client.model.Filters.eq;

@Singleton
public class BackupMoviesTask {
    private static final Logger log = LoggerFactory.getLogger(BackupMoviesTask.class);

    private boolean flag = false;
    public static String FILE_NAME = "movies_catalog.csv";

    private MongoCollection<Movie> movieRepository;
    private MongoCollection<Comment> commentRepository;


    @Scheduled(fixedDelay = "1s")
    void makeBackup() {
        try {
            if (flag) {
                flag = false;
                makeBackupToFile();
            }
        } catch (Exception ex) {
            log.warn("Error al generar backpup", ex);
            flag = false;
        }
    }


    public void makeBackup(boolean flag, MongoCollection<Movie> movieRepository, MongoCollection<Comment> commentRepository) {
        this.flag = flag;
        this.movieRepository = movieRepository;
        this.commentRepository = commentRepository;
    }


    private void makeBackupToFile() throws InterruptedException, IOException {
        Thread.sleep(5000);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))){
            log.info("Inicia Backup {}", LocalDateTime.now());
            FindIterable<Movie> movieFindIterable = this.movieRepository.find();
            MongoCursor<Movie> movieMongoCursor = movieFindIterable.iterator();
            long countMovies = this.movieRepository.countDocuments();
            int index = 0;

            while (movieMongoCursor.hasNext()) {
                Movie movie = movieMongoCursor.next();
                writer.write(buildLine(movie, ++index, countMovies));
                writer.newLine();
                index++;
            }
            log.info("Termina Backup {}", LocalDateTime.now());
        }
    }


    private String buildLine(Movie movie, int index, long totalMovies) {
        ObjectMapper objectMapper = new ObjectMapper();
        long commentsCount = this.commentRepository.countDocuments(eq("movie_Id", movie.getId().toHexString()));
        String json = "" + getIdValue(index, commentsCount, totalMovies);
        try {
            json += ",".concat(objectMapper.writeValueAsString(movie));
        } catch (IOException e) {

        }
        return json;
    }


    private long getIdValue(int index, long commentsCount, long totalMovies) {
        long id = System.currentTimeMillis()+index;
        commentsCount = commentsCount == 0 ? 1 : commentsCount;
        return id * commentsCount / totalMovies;
    }
}

