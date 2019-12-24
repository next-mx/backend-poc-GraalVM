package com.bbva.test.graalvm.springboot.dto;

import com.bbva.test.graalvm.springboot.dto.movie.ImdbDTO;
import com.bbva.test.graalvm.springboot.dto.movie.LastUpdateDTO;
import com.bbva.test.graalvm.springboot.dto.movie.NumberIntDTO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

@Document(collection = "movies")
public class MovieDTO {
	private ObjectId _id;
	private String title;
	private NumberIntDTO year;
	private NumberIntDTO runtime;
	private String[] cast;
	private String poster;
	private String plot;
	private String fullplot;
	private LastUpdateDTO lastupdated;
	private String type;
	private String[] directors;
	private ImdbDTO imdb;
	private String[] countries;
	private String rated;
	private String[] genres;


	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public NumberIntDTO getYear() {
		return year;
	}

	public void setYear(NumberIntDTO year) {
		this.year = year;
	}

	public NumberIntDTO getRuntime() {
		return runtime;
	}

	public void setRuntime(NumberIntDTO runtime) {
		this.runtime = runtime;
	}

	public String[] getCast() {
		return cast;
	}

	public void setCast(String[] cast) {
		this.cast = cast;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getFullplot() {
		return fullplot;
	}

	public void setFullplot(String fullplot) {
		this.fullplot = fullplot;
	}

	public LastUpdateDTO getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(LastUpdateDTO lastupdated) {
		this.lastupdated = lastupdated;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getDirectors() {
		return directors;
	}

	public void setDirectors(String[] directors) {
		this.directors = directors;
	}

	public ImdbDTO getImdb() {
		return imdb;
	}

	public void setImdb(ImdbDTO imdb) {
		this.imdb = imdb;
	}

	public String[] getCountries() {
		return countries;
	}

	public void setCountries(String[] countries) {
		this.countries = countries;
	}

	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	public String[] getGenres() {
		return genres;
	}

	public void setGenres(String[] genres) {
		this.genres = genres;
	}

	@Override
	public String toString() {
		return "MovieDTO{" +
				"_id=" + _id +
				", title='" + title + '\'' +
				", year=" + year +
				", runtime=" + runtime +
				", cast=" + Arrays.toString(cast) +
				", poster='" + poster + '\'' +
				", plot='" + plot + '\'' +
				", fullplot='" + fullplot + '\'' +
				", lastupdated=" + lastupdated +
				", type='" + type + '\'' +
				", directors=" + Arrays.toString(directors) +
				", imdb=" + imdb +
				", countries=" + Arrays.toString(countries) +
				", rated='" + rated + '\'' +
				", genres=" + Arrays.toString(genres) +
				'}';
	}
}
