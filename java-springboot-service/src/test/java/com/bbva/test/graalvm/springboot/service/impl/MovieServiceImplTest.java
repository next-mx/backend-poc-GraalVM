package com.bbva.test.graalvm.springboot.service.impl;


import com.bbva.test.graalvm.springboot.dto.MovieDTO;
import com.bbva.test.graalvm.springboot.dto.movie.ImdbDTO;
import com.bbva.test.graalvm.springboot.dto.movie.LastUpdateDTO;
import com.bbva.test.graalvm.springboot.dto.movie.NumberDoubleDTO;
import com.bbva.test.graalvm.springboot.dto.movie.NumberIntDTO;
import com.bbva.test.graalvm.springboot.dto.movie.NumberLongDTO;
import com.bbva.test.graalvm.springboot.service.MovieServ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@DisplayName("Operations with movies")
@SpringBootTest
public class MovieServiceImplTest {
	@Autowired
	private MovieServ movieServ;
	private static MovieDTO movieUpdate;
	private static final String ID_TEMPORAL = "5e1e47b9beec2729cc06cb1e";


	@Test
	@DisplayName("Update movie all")
	public void f2() {
		movieUpdate.setPoster("action 2.22");
		movieUpdate.setGenres(new String[]{"Accion 2"});
		movieServ.updateMovie(movieUpdate.get_id(), movieUpdate);
		System.out.println("---------------------------------");
		System.out.println("Actulizando la movie con id : " + movieUpdate.get_id());
		System.out.println("---------------------------------");
	}


	@Test
	@DisplayName("Find movie by ID...")
	public void f1() {
		Optional<MovieDTO> movie = movieServ.findMovieByID(ID_TEMPORAL);
		System.out.println("---------------------------------");
		System.out.println(movie);
		System.out.println("---------------------------------");
		movieUpdate = movie.get();
		Assertions.assertNotNull(movie.isPresent());
	}


	@Test
	public void updateImb() {
		ImdbDTO imbdb = new ImdbDTO(new NumberDoubleDTO("5.2"), new NumberIntDTO("1000"), new NumberIntDTO("2"));
		this.movieServ.updateIMB(ID_TEMPORAL,imbdb);
		System.out.println(getClass().getResource("/"));
	}


	//@Test
	public void newMovie() {
		MovieDTO movie = new MovieDTO();
		movie.setTitle("Avengers 2");
		movie.setYear(new NumberIntDTO("2019"));
		movie.setCast(new String[]{"Thanos", "BlackPanther"});
		movie.setRuntime(new NumberIntDTO("1"));
		movie.setPoster("xxxx");
		movie.setPlot("plot");
		movie.setFullplot("full plot");
		movie.setLastupdated(new LastUpdateDTO(new NumberLongDTO("12458796525")));
		movie.setType("movie");
		movie.setDirectors(new String[]{"Anthony y Joe Russo"});
		movie.setImdb(new ImdbDTO(new NumberDoubleDTO("5.9"), new NumberIntDTO("1032"), new NumberIntDTO("1")));
		movie.setCountries(new String[]{"USA"});
		movie.setRated("NOT RATED");
		movie.setGenres(new String[]{"Acccion", "Ciencia Ficcion"});
		System.out.println(movie);
		this.movieServ.newMovie(movie);
	}
}
