package com.bbva.test.graalvm.springboot.service;

import com.bbva.test.graalvm.springboot.model.Movie;
import com.bbva.test.graalvm.springboot.model.movie.Imdb;
import java.util.List;

import java.util.Optional;

public interface MovieServ {
	Optional<Movie> findMovieByID(String movieID);

	public List<Movie> findMovies();

	void newMovie(Movie movie);

	void updateMovie(String movieID, Movie movie);

	void updateIMB(String movieId, Imdb imbDto);

	void deleteMovie(String movieId);

	void makeBackup();

	List<Movie> getBackupFrom();
}
