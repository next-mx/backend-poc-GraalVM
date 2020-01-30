package com.bbva.test.graalvm.springboot.service.impl;

import com.bbva.test.graalvm.springboot.component.BackupTask;
import com.bbva.test.graalvm.springboot.dao.MovieCustomRepo;
import com.bbva.test.graalvm.springboot.dao.MovieRepo;
import com.bbva.test.graalvm.springboot.dto.MovieDTO;
import com.bbva.test.graalvm.springboot.dto.movie.ImdbDTO;
import com.bbva.test.graalvm.springboot.service.MovieServ;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
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
	private BackupTask backupTask;
	private String nameFile;


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
		List<MovieDTO> lista = new ArrayList<>();
		obtainPath();
		ObjectMapper objectMapper = new ObjectMapper();
		try (Stream<String> stream = Files.lines(Paths.get(this.nameFile))) {
			stream.forEach(item -> {
				try {
					MovieDTO movie = objectMapper.readValue(item.substring(item.indexOf(',') + 1, item.length()), MovieDTO.class);
					lista.add(movie);
				} catch (JsonProcessingException e) {
				}
			});
		} catch (IOException e) {
		}
		return lista;
	}

	private void obtainPath() {
		if (StringUtils.isBlank(this.nameFile)) {
			String aqui = new File(".").getAbsolutePath();
			this.nameFile = aqui.substring(0, aqui.lastIndexOf('.')).concat("movies_catalog.csv");
		}
	}
}
