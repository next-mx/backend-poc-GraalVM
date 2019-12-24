package com.bbva.test.graalvm.springboot.service;

import com.bbva.test.graalvm.springboot.dto.MovieDTO;

public interface MovieServ {
	MovieDTO findMovieByID(String movieID);

	void newMovie(MovieDTO movie);
}
