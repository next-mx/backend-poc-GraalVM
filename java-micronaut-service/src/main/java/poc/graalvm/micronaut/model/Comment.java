package poc.graalvm.micronaut.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.core.annotation.Introspected;
import org.bson.types.ObjectId;
import java.util.Objects;

@Introspected
public class Comment {
    @JsonIgnore
    private ObjectId id;
    private String _id;
    private String name;
    private String email;
    private String movie_id;
    private String text;
    private long date;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id + '\'' +
                ", _id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", movie_id='" + movie_id + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
