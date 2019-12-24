package com.bbva.test.graalvm.springboot.service.impl;

import com.bbva.test.graalvm.springboot.dto.MovieDTO;
import com.bbva.test.graalvm.springboot.dao.MovieRepo;
import com.bbva.test.graalvm.springboot.dto.movie.ImdbDTO;
import com.bbva.test.graalvm.springboot.service.MovieServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Lazy
@Service
public class MovieServImpl implements MovieServ {

	@Autowired
	private MovieRepo movieRepo;


	@Transactional(readOnly = true)
	@Override
	public MovieDTO findMovieByID(String movieID) {
		return movieRepo.findMovieByID(movieID);
	}

	@Override
	@Transactional
	public void newMovie(MovieDTO movie) {
		this.movieRepo.newMovie(movie);
	}

	@Transactional
	@Override
	public void updateMovie(String movieID, MovieDTO movie) {
		this.movieRepo.updateMovie(movieID, movie);
	}

	@Override
	public void updateIMB(String movieId, ImdbDTO imbDto) {
		this.movieRepo.updateImdb(movieId, imbDto);
	}

	@Override
	public void deleteMovie(String movieId) {
		this.movieRepo.deleteMovie(movieId);
	}

	@Override
	public void makeBackup() {
		throw  new UnsupportedOperationException("not implement yet");

	}

	@Override
	public List<MovieDTO> getBackupFrom() {
		return Collections.emptyList();
	}
}
