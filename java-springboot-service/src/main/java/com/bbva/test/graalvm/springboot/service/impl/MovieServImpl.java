package com.bbva.test.graalvm.springboot.service.impl;

import com.bbva.test.graalvm.springboot.component.BackupMoviesTask;
import com.bbva.test.graalvm.springboot.dao.MovieCustomRepo;
import com.bbva.test.graalvm.springboot.dao.MovieRepo;
import com.bbva.test.graalvm.springboot.model.Movie;
import com.bbva.test.graalvm.springboot.model.movie.Imdb;
import com.bbva.test.graalvm.springboot.service.MovieServ;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Lazy
@Service
public class MovieServImpl implements MovieServ {

	@Autowired
	private MovieCustomRepo movieCustomRepo;
	@Autowired
	private MovieRepo movieRepo;
	@Autowired
	private BackupMoviesTask backupMoviesTask;
	private String nameFile;


	@Transactional(readOnly = true)
	@Override
	public Optional<Movie> findMovieByID(String movieID) {
		return movieRepo.findById(movieID);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Movie> findMovies() {
		return movieRepo.findAll();
	}

	@Override
	@Transactional
	public void newMovie(Movie movie) {
		this.movieRepo.insert(movie);
	}

	@Transactional
	@Override
	public void updateMovie(String movieID, Movie movie) {
		this.movieRepo.save(movie);
	}

	@Override
	public void updateIMB(String movieId, Imdb imbDto) {
		this.movieCustomRepo.updateImdb(movieId, imbDto);
	}

	@Override
	public void deleteMovie(String movieId) {
		this.movieRepo.deleteById(movieId);
	}

	@Override
	public void makeBackup() {
		backupMoviesTask.makeBackup(true);
	}

	@Override
	public List<Movie> getBackupFrom() {
		List<Movie> movies = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		try (Stream<String> stream = Files.lines(Paths.get(BackupMoviesTask.FILE_NAME))) {
			stream.forEach(item -> {
				try {
					Movie movie = objectMapper.readValue(item.substring(item.indexOf(',') + 1), Movie.class);
					movies.add(movie);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return movies;
	}

}
