package com.poc.graalvm.service;

import com.poc.graalvm.model.Movie;
import com.poc.graalvm.repository.CommentRepository;
import com.poc.graalvm.repository.MovieRepository;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimerTask;


@Singleton
public class TimerBackupMovies extends TimerTask {
    private static final Logger log = LoggerFactory.getLogger(TimerBackupMovies.class);
    @Inject
    MovieRepository movieRepository;
    @Inject
    CommentRepository commentRepository;


    @Override
    public void run() {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("movies_catalog.csv"))){
            long time = System.currentTimeMillis();
            log.info("Inicia Backup {}", LocalDateTime.now());

            /**
             * Inicializacion de variables
             */
            long countMovies = this.movieRepository.findAll().count();
            List<Movie> movies = this.movieRepository.findAll().list();
            BigDecimal id = null;
            long commentsCount = 0;
            Movie movieIt = null;
            Document document = new Document();

            for (int i = 1; i < movies.size(); i++) {
                movieIt = movies.get(i-1);

                id = BigDecimal.valueOf(System.currentTimeMillis()+i);

                commentsCount = this.commentRepository.getAllComments(movieIt.getId().toHexString()).size();
                commentsCount = commentsCount == 0 ? 1 : commentsCount;

                id = id.multiply(BigDecimal.valueOf(commentsCount));
                id = id.divide(BigDecimal.valueOf(countMovies), MathContext.DECIMAL128);

                document.append("id",id);
                document.append("pelicula",movieIt.toString());
                writer.write(document.toJson());
                writer.newLine();
            }

            log.info("Termina Backup {}", LocalDateTime.now());
            log.info("Tiempo total de respaldo {}",System.currentTimeMillis()-time);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
    }
}
