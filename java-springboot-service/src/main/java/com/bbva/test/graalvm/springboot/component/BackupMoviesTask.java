package com.bbva.test.graalvm.springboot.component;

import com.bbva.test.graalvm.springboot.dao.CommentRepo;
import com.bbva.test.graalvm.springboot.dao.MovieRepo;
import com.bbva.test.graalvm.springboot.model.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class BackupMoviesTask {
    private static final Logger log = LoggerFactory.getLogger(BackupMoviesTask.class);

    private boolean flag = false;
    public static String FILE_NAME = "movies_catalog.csv";

    @Autowired
    private CommentRepo commentRepository;
    @Autowired
    private MovieRepo movieRepository;

    @Scheduled(fixedRate = 1000)
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
            List<Movie> movies = this.movieRepository.findAll();
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
        String json = "" + getIdValue(index, this.commentRepository.findByMovieId(movie.get_id()).size(), totalMovies);
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
