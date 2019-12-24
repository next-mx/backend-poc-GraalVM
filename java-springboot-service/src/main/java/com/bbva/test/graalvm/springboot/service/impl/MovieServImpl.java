package com.bbva.test.graalvm.springboot.service.impl;

import com.bbva.test.graalvm.springboot.dto.MovieDTO;
import com.bbva.test.graalvm.springboot.dao.MovieRepo;
import com.bbva.test.graalvm.springboot.service.MovieServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MovieServImpl implements MovieServ {

	@Autowired
	private MovieRepo movieRepo;


	@Override
	public MovieDTO findMovieByID(String movieID) {
		return movieRepo.findMovieByID(movieID);
	}

	@Override
	public void newMovie(MovieDTO movie) {
		this.movieRepo.newMovie(movie);
	}
}
