package com.bbva.test.graalvm.springboot.dao;

import com.bbva.test.graalvm.springboot.dto.MovieDTO;
import com.bbva.test.graalvm.springboot.dto.movie.ImdbDTO;

public interface MovieRepo {
	MovieDTO findMovieByID(String movieID);

	void newMovie(MovieDTO movie);

	void updateMovie(String movieID, MovieDTO movie);

	void updateImdb(String movieId, ImdbDTO imbDto);

	void deleteMovie(String movieId);
}
