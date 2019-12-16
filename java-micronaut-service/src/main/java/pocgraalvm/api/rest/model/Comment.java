package pocgraalvm.api.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.core.annotation.Introspected;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotBlank;

@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {

    private ObjectId id;
    private String _id;
    @NotBlank
    private String name;
    private String email;
    private String movie_Id;
    @NotBlank
    private String text;
    private Long date;
    private String message;

    private void set_id(ObjectId id){
        _id = id.toHexString();
    }

    public String get_id() {
        return _id;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
        set_id(id);
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

    public String getMovie_Id() {
        return movie_Id;
    }

    public void setMovie_Id(String movie_Id) {
        this.movie_Id = movie_Id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", movieId='" + movie_Id + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", message='" + message + '\'' +
                '}';
    }
}
