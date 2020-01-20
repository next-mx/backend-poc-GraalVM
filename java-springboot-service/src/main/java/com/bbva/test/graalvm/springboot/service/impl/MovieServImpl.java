package com.bbva.test.graalvm.springboot.service.impl;

import com.bbva.test.graalvm.springboot.component.BackupTask;
import com.bbva.test.graalvm.springboot.dao.MovieCustomRepo;
import com.bbva.test.graalvm.springboot.dao.MovieRepo;
import com.bbva.test.graalvm.springboot.dto.MovieDTO;
import com.bbva.test.graalvm.springboot.dto.movie.ImdbDTO;
import com.bbva.test.graalvm.springboot.service.MovieServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Lazy
@Service
public class MovieServImpl implements MovieServ {

	@Autowired
	private MovieCustomRepo movieCustomRepo;
	@Autowired
	private MovieRepo movieRepo;
	@Autowired
	private BackupTask backupTask;


	@Transactional(readOnly = true)
	@Override
	public Optional<MovieDTO> findMovieByID(String movieID) {
		return movieRepo.findById(movieID);
	}

	@Override
	@Transactional
	public void newMovie(MovieDTO movie) {
		this.movieRepo.insert(movie);
	}

	@Transactional
	@Override
	public void updateMovie(String movieID, MovieDTO movie) {
		this.movieRepo.save(movie);
	}

	@Override
	public void updateIMB(String movieId, ImdbDTO imbDto) {
		this.movieCustomRepo.updateImdb(movieId, imbDto);
	}

	@Override
	public void deleteMovie(String movieId) {
		this.movieRepo.deleteById(movieId);
	}

	@Override
	public void makeBackup() {
		backupTask.makeBackup(true);
	}

	@Override
	public List<MovieDTO> getBackupFrom() {
		return Collections.emptyList();
	}
}
