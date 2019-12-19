package com.bbva.test.graalvm.springboot.controller;


import com.bbva.test.graalvm.springboot.dto.MovieDTO;
import com.bbva.test.graalvm.springboot.dto.RespJSON;
import com.bbva.test.graalvm.springboot.dto.movie.DateDTO;
import com.bbva.test.graalvm.springboot.dto.movie.IdDTO;
import com.bbva.test.graalvm.springboot.dto.movie.ImdbDTO;
import com.bbva.test.graalvm.springboot.dto.movie.LastUpdateDTO;
import com.bbva.test.graalvm.springboot.dto.movie.RatingDTO;
import com.bbva.test.graalvm.springboot.dto.movie.RuntimeDTO;
import com.bbva.test.graalvm.springboot.dto.movie.VotesDTO;
import com.bbva.test.graalvm.springboot.dto.movie.YearDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping(path = "/pocgraalvm/api/v1")
public class MovieCtrl {

	/**
	 * Creacion de pelicula
	 *
	 * @param movie
	 */
	@PostMapping(path = "/movies/")
	public ResponseEntity<RespJSON<String>> newMovie(@RequestBody MovieDTO movie) {
		RespJSON<String> resp = new RespJSON<>();
		resp.setMessage("Pelicula agregada exitosamente");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/**
	 * obtencion de info de una pelicula en particular
	 *
	 * @param movieId
	 */
	@GetMapping(path = "/movies/{movieId}")
	public ResponseEntity<MovieDTO> getMovieInfo(@PathVariable(name = "movieId") String movieId) {
		MovieDTO movie = new MovieDTO();
		movie.setTitle("Avengers");
		movie.setYear(new YearDTO("2019"));
		movie.setCast(new String[]{"Thanos", "BlackPanther"});
		movie.setRuntime(new RuntimeDTO("1"));
		movie.setPoster("xxxx");
		movie.setPlot("plot");
		movie.setFullplot("full plot");
		movie.setLastupdated(new LastUpdateDTO(new DateDTO("12458796525")));
		movie.setType("movie");
		movie.setDirectors(new String[]{"Anthony y Joe Russo"});
		movie.setImdb(new ImdbDTO(new RatingDTO("5.9"), new VotesDTO("1032"), new IdDTO("1")));
		movie.setCountries(new String[]{"USA"});
		movie.setRated("NOT RATED");
		movie.setGenres(new String[]{"Acccion", "Ciencia Ficcion"});

		return new ResponseEntity<>(movie, HttpStatus.OK);
	}


	/**
	 * Modificar una pelicula
	 *
	 * @param movieId
	 * @param movie
	 */
	@PutMapping(path = "/movies/{movieId}")
	public ResponseEntity<RespJSON<String>> modifiMovie(@PathVariable(name = "movieId") String movieId, @RequestBody MovieDTO movie) {
		RespJSON<String> resp = new RespJSON<>();
		resp.setMessage("Pelicula agregada exitosamente");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}


	/**
	 * Editar Pelicula
	 *
	 * @param imbDto
	 * @param movieId
	 */
	@PatchMapping(path = "/movies/{movieId}")
	public ResponseEntity<RespJSON<String>> editMovie(@PathVariable(name = "movieId") String movieId, @RequestBody ImdbDTO imbDto) {
		RespJSON<String> resp = new RespJSON<>();
		resp.setMessage("Pelicula editada exitosamente");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/**
	 * Borrado de pelicula
	 *
	 * @param movieId
	 */
	@DeleteMapping(path = "/movies/{movieId}")
	public ResponseEntity<RespJSON<String>> deleteMovie(@PathVariable(name = "movieId") String movieId) {
		RespJSON<String> resp = new RespJSON<>();
		resp.setMessage("Pelicula eliminada exitosamente");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}


	/**
	 * hacer backup
	 */
	@PostMapping(path = "/movies/")
	public ResponseEntity<RespJSON<String>> backupDB(@RequestParam(name = "task") String task) {
		RespJSON<String> resp = new RespJSON<>();
		resp.setMessage("Respaldo agendado exitosamente");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/**
	 * recuperar backup de archivo
	 */
	@GetMapping(path = "/movies/")
	public ResponseEntity<RespJSON<List<MovieDTO>>> getBackupFromFile(@RequestParam(name = "task") String task) {
		RespJSON<List<MovieDTO>> resp = new RespJSON<>();
		resp.setMessage("Respaldo consultado exitosamente");
		resp.setResult(Collections.EMPTY_LIST);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}


}
