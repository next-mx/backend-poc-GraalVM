package poc.graalvm.micronaut.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.core.annotation.Introspected;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.Objects;

@Introspected
public class Movie {
    @JsonIgnore
    private ObjectId id;
    private String _id;
    private String title;
    private Integer year;
    private Integer runtime;
    private List<String> cast;
    private String poster;
    private String plot;
    private String fullplot;
    private Long lastupdated;
    private String type;
    private List<String> directors;
    private Imdb imdb;
    private List<String> countries;
    private String rated;
    private List<String> genres;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String get_id() {
        return Objects.isNull(this.id) ? _id : this.id.toString();
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
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

    public Long getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(Long lastupdated) {
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

    public Imdb getImdb() {
        return imdb;
    }

    public void setImdb(Imdb imdb) {
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

    public List<String>getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
                "id=" + id + '\'' +
                ", _id='" + _id + '\'' +
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
