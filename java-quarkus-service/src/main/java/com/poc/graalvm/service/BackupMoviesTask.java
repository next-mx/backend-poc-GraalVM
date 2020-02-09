package com.poc.graalvm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.graalvm.model.Movie;
import com.poc.graalvm.repository.CommentRepository;
import com.poc.graalvm.repository.MovieRepository;
import io.quarkus.scheduler.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class BackupMoviesTask {
    private static final Logger log = LoggerFactory.getLogger(BackupMoviesTask.class);

    private boolean flag = false;
    public static String FILE_NAME = "movies_catalog.csv";

    @Inject
    private CommentRepository commentRepository;
    @Inject
    private MovieRepository movieRepository;

    @Scheduled(every = "1s")
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


    public void makeBackup(boolean flag) {
        this.flag = flag;
    }


    private void makeBackupToFile() throws InterruptedException, IOException {
        Thread.sleep(5000);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))){
            log.info("Inicia Backup {}", LocalDateTime.now());
            List<Movie> movies = this.movieRepository.findAll().list();
            int index = 0;

            for (Movie movie : movies) {
                writer.write(buildLine(movie, ++index, movies.size()));
                writer.newLine();
                index++;
            }
            log.info("Termina Backup {}", LocalDateTime.now());
        }
    }


    private String buildLine(Movie movie, int index, long totalMovies) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "" + getIdValue(index, this.commentRepository.getAllComments(movie.getId().toString()).size(), totalMovies);
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
