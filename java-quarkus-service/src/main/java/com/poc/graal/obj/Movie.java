package com.poc.graal.obj;

public class Movie {
	private String id;
	private String title;
	private int year;
	private int runtime;
	private String cast[];
	private String poster;
	private String plot;
	private String fullplot;
	private long lastupdated;
	private String type;
	private String director[];
	private IMDB imdb;
	private String countries[];
	private String rated;
	private String genres[];
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String[] getDirector() {
		return director;
	}
	public void setDirector(String[] director) {
		this.director = director;
	}
	public IMDB getImdb() {
		return imdb;
	}
	public void setImdb(IMDB imdb) {
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
}
