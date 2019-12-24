package com.bbva.test.graalvm.springboot.dao;

import com.bbva.test.graalvm.springboot.dto.MovieDTO;

public interface MovieRepo {
	MovieDTO findMovieByID(String movieID);
	void newMovie(MovieDTO movie);
}
