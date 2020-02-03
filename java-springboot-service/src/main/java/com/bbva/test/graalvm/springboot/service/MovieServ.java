package com.bbva.test.graalvm.springboot.service;

import com.bbva.test.graalvm.springboot.dto.MovieDTO;
import com.bbva.test.graalvm.springboot.dto.movie.ImdbDTO;
import java.util.List;

import java.util.List;
import java.util.Optional;

public interface MovieServ {
	Optional<MovieDTO> findMovieByID(String movieID);

	public List<MovieDTO> findMovies();

	void newMovie(MovieDTO movie);

	void updateMovie(String movieID, MovieDTO movie);

	void updateIMB(String movieId, ImdbDTO imbDto);

	void deleteMovie(String movieId);

	void makeBackup();

	List<MovieDTO> getBackupFrom();
}
