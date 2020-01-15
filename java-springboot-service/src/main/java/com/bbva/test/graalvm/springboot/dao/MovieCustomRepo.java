package com.bbva.test.graalvm.springboot.dao;

import com.bbva.test.graalvm.springboot.dto.MovieDTO;
import com.bbva.test.graalvm.springboot.dto.movie.ImdbDTO;

public interface MovieCustomRepo {
	void updateImdb(String movieId, ImdbDTO imbDto);

}
