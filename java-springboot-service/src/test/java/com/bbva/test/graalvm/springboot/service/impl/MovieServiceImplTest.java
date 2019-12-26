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


@DisplayName("Operations with movies")
@SpringBootTest
public class MovieServiceImplTest {
	@Autowired
	private MovieServ movieServ;

	@Test
	@DisplayName("Find movie by ID...")
	public void findMovieByID() {
		MovieDTO movie = movieServ.findMovieByID("5e024cdb4a2f0d350104917c");
		System.out.println("---------------------------------");
		System.out.println(movie);
		System.out.println("---------------------------------");
		Assertions.assertNotNull(movie);
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
