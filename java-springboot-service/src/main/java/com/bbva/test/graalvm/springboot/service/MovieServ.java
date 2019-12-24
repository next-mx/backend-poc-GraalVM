package com.bbva.test.graalvm.springboot.service;

import com.bbva.test.graalvm.springboot.dto.MovieDTO;
import com.bbva.test.graalvm.springboot.dto.movie.ImdbDTO;

import java.util.List;

public interface MovieServ {
	MovieDTO findMovieByID(String movieID);

	void newMovie(MovieDTO movie);

	void updateMovie(String movieID, MovieDTO movie);

	void updateIMB(String movieId, ImdbDTO imbDto);

	void deleteMovie(String movieId);

	void makeBackup();

	List<MovieDTO> getBackupFrom();
}
