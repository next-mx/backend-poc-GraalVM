package com.bbva.test.graalvm.springboot.controller;


import com.bbva.test.graalvm.springboot.dto.MovieDTO;
import com.bbva.test.graalvm.springboot.dto.RespJSON;
import com.bbva.test.graalvm.springboot.dto.movie.ImdbDTO;
import com.bbva.test.graalvm.springboot.service.MovieServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/pocgraalvm/api/v1")
public class MovieCtrl {

	@Autowired
	private MovieServ movieServ;

	/**
	 * Creacion de pelicula
	 *
	 * @param movieDTO objeto que representa una pelicucla
	 */
	@PostMapping(path = "/movies/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespJSON<String>> newMovie(@RequestBody MovieDTO movieDTO) {
		movieServ.newMovie(movieDTO);
		RespJSON<String> resp = new RespJSON<>();
		resp.setMessage("Pelicula agregada exitosamente");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/**
	 * obtencion de info de una pelicula en particular
	 *
	 * @param movieId
	 */
	@GetMapping(path = "/movies/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MovieDTO> getMovieInfo(@PathVariable(name = "movieId") String movieId) {
		Optional<MovieDTO> movie = this.movieServ.findMovieByID(movieId);
		return new ResponseEntity<>(movie.isPresent() ? movie.get() : new MovieDTO(), HttpStatus.OK);
	}


	/**
	 * Modificar una pelicula
	 *
	 * @param movieId
	 * @param movie
	 */
	@PutMapping(path = "/movies/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespJSON<String>> modifiMovie(@PathVariable(name = "movieId") String movieId, @RequestBody MovieDTO movie) {
		this.movieServ.updateMovie(movieId, movie);
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
	@PatchMapping(path = "/movies/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespJSON<String>> editMovie(@PathVariable(name = "movieId") String movieId, @RequestBody ImdbDTO imbDto) {
		this.movieServ.updateIMB(movieId, imbDto);
		RespJSON<String> resp = new RespJSON<>();
		resp.setMessage("Pelicula editada exitosamente");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/**
	 * Borrado de pelicula
	 *
	 * @param movieId
	 */
	@DeleteMapping(path = "/movies/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespJSON<String>> deleteMovie(@PathVariable(name = "movieId") String movieId) {
		this.movieServ.deleteMovie(movieId);
		RespJSON<String> resp = new RespJSON<>();
		resp.setMessage("Pelicula eliminada exitosamente");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}


	/**
	 * hacer backup
	 */
	@PostMapping(path = "/movies/backup", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespJSON<String>> backupDB(@RequestParam(name = "task") String task) {
		this.movieServ.makeBackup();
		RespJSON<String> resp = new RespJSON<>();
		resp.setMessage("Respaldo agendado exitosamente");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/**
	 * recuperar backup de archivo
	 */
	@GetMapping(path = "/movies/backup", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespJSON<List<MovieDTO>>> getBackupFromFile(@RequestParam(name = "task") String task) {
		RespJSON<List<MovieDTO>> resp = new RespJSON<>();
		resp.setMessage("Respaldo consultado exitosamente");
		resp.setResult(this.movieServ.getBackupFrom());
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}


}
