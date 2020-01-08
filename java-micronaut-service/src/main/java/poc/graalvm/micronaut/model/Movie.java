package poc.graalvm.micronaut.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.core.annotation.Introspected;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Introspected
public class Movie {

    private ObjectId id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String _id;
    @NotBlank
    private String title;
    private Integer year;
    private Integer runtime;
    private List<String> cast;
    @NotBlank
    private String poster;
    private String plot;
    private String fullplot;
    private Long lastUpdated;
    private String type;
    private List<String> directors;
    private Imdb imdb;
    private List<String> countries;
    private String rated;
    private List<String> genres;

    private void set_id(ObjectId id){
        _id = id.toHexString();
    }

    public String get_id() {
        return _id;
    }


    public void setId(ObjectId id) {
        this.id = id;
        if(id != null) {
            set_id(id);
        }
    }

    public ObjectId getId() {
        return id;
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

    public Long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
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

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "_id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", runtime=" + runtime +
                ", cast=" + cast +
                ", poster='" + poster + '\'' +
                ", plot='" + plot + '\'' +
                ", fullplot='" + fullplot + '\'' +
                ", lastUpdated=" + lastUpdated +
                ", type='" + type + '\'' +
                ", directors=" + directors +
                ", imdb=" + imdb +
                ", countries=" + countries +
                ", rated='" + rated + '\'' +
                ", genres=" + genres +
                '}';
    }
}
