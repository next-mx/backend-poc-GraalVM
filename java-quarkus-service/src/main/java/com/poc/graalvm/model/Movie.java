package com.poc.graalvm.model;

import io.quarkus.mongodb.panache.MongoEntity;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotNull;
import java.util.List;

@MongoEntity(collection="movies")
public class Movie {

	private ObjectId id;
	@NotNull
	private String title;
	@NotNull
	private int year;
	@NotNull
	private int runtime;
	private List<String> cast;
	private String poster;
	private String plot;
	private String fullplot;
	private long lastupdated;
	private String type;
	private List<String> directors;
	private IMDB imdb;
	private List<String> countries;
	private String rated;
	private List<String> genres;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public List<String> getCast() {
		return cast;
	}

	public void setCast(List<String> cast) {
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

	public long getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(long lastupdated) {
		this.lastupdated = lastupdated;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getDirectors() {
		return directors;
	}

	public void setDirectors(List<String> directors) {
		this.directors = directors;
	}

	public IMDB getImdb() {
		return imdb;
	}

	public void setImdb(IMDB imdb) {
		this.imdb = imdb;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}


	@Override
	public String toString() {
		return "Movie{" +
				"id=" + id +
				", title='" + title + '\'' +
				", year=" + year +
				", runtime=" + runtime +
				", cast=" + cast +
				", poster='" + poster + '\'' +
				", plot='" + plot + '\'' +
				", fullplot='" + fullplot + '\'' +
				", lastupdated=" + lastupdated +
				", type='" + type + '\'' +
				", directors=" + directors +
				", imdb=" + imdb +
				", countries=" + countries +
				", rated='" + rated + '\'' +
				", genres=" + genres +
				'}';
	}
}
