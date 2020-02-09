package com.bbva.test.graalvm.springboot.dao;

import com.bbva.test.graalvm.springboot.model.movie.Imdb;

public interface MovieCustomRepo {
	void updateImdb(String movieId, Imdb imbDto);

}
