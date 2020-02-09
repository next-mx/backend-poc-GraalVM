package com.bbva.test.graalvm.springboot.controller;


import com.bbva.test.graalvm.springboot.model.Movie;
import com.bbva.test.graalvm.springboot.model.ResponseDTO;
import com.bbva.test.graalvm.springboot.model.movie.Imdb;
import com.bbva.test.graalvm.springboot.service.MovieServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/pocgraalvm/api/v1")
public class MovieCtrl {
	private static final Logger log = LoggerFactory.getLogger(MovieCtrl.class);

	@Autowired
	private MovieServ movieServ;

	/**
	 * Creacion de pelicula
	 *
	 * @param movie objeto que representa una pelicucla
	 */
	@PostMapping(path = "/movies/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<Movie>> newMovie(@RequestBody Movie movie) {
		log.info("Registrando nueva película: {}", movie);
		movieServ.newMovie(movie);
		ResponseDTO<Movie> responseDTO = new ResponseDTO<>("Pelicula agregada exitosamente", null);
		return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
	}


	/**
	 * obtencion de listado de peliculas
	 *
	 */
	@GetMapping(path = "/movies/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<List<Movie>>> getMoviesInfo() {
		log.info("Consultando listado de películas");
		List<Movie> movies = this.movieServ.findMovies();
		ResponseDTO<List<Movie>> responseDTO = new ResponseDTO<>("Pelicula consultada exitosamente", movies);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}


	/**
	 * obtencion de info de una pelicula en particular
	 *
	 * @param movieId
	 */
	@GetMapping(path = "/movies/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<Movie>> getMovieInfo(@PathVariable(name = "movieId") String movieId) {
		log.info("Consultando película con ID: {}", movieId);
		Optional<Movie> movie = this.movieServ.findMovieByID(movieId);
		return movie.isPresent() ?
				ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseDTO<>("Pelicula agregada exitosamente", movie.get())) :
				ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ResponseDTO<>("Pelicula No encontrada", null));

	}


	/**
	 * Modificar una pelicula
	 *
	 * @param movieId
	 * @param movie
	 */
	@PutMapping(path = "/movies/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<Movie>> modifiMovie(@PathVariable(name = "movieId") String movieId, @RequestBody Movie movie) {
		log.info("Modificando la película: {} con los nuevos datos: {}", movieId, movie);
		this.movieServ.updateMovie(movieId, movie);
		ResponseDTO<Movie> responseDTO = new ResponseDTO<>("Pelicula modificada exitosamente", null);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}


	/**
	 * Editar Pelicula
	 *
	 * @param imbDto
	 * @param movieId
	 */
	@PatchMapping(path = "/movies/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<Movie>> editMovie(@PathVariable(name = "movieId") String movieId, @RequestBody Imdb imbDto) {
		log.info("Actualizando película con ID: {}, los datos: {}", movieId, imbDto);
		this.movieServ.updateIMB(movieId, imbDto);
		ResponseDTO<Movie> responseDTO = new ResponseDTO<>("Pelicula editada exitosamente", null);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	/**
	 * Borrado de pelicula
	 *
	 * @param movieId
	 */
	@DeleteMapping(path = "/movies/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<Movie>> deleteMovie(@PathVariable(name = "movieId") String movieId) {
		log.info("Eliminando película con ID: {}", movieId);
		this.movieServ.deleteMovie(movieId);
		ResponseDTO<Movie> responseDTO = new ResponseDTO<>("Pelicula eliminada exitosamente", null);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}


	/**
	 * hacer backup
	 */
	@PostMapping(path = "/movies/backup", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<Movie>> backupDB() {
		log.info("Registrando backup del inventario de Películas");
		this.movieServ.makeBackup();
		ResponseDTO<Movie> responseDTO = new ResponseDTO<>("Respaldo agendado exitosamente", null);
		return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
	}

	/**
	 * recuperar backup de archivo
	 */
	@GetMapping(path = "/movies/backup", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<List<Movie>>> getBackupFromFile() {
		log.info("Consultando backup de peliculas");
		ResponseDTO<List<Movie>> responseDTO = new ResponseDTO<>("Respaldo consultado exitosamente", this.movieServ.getBackupFrom());
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}


}
